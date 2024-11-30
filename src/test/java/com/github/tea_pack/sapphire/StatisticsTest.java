package com.github.tea_pack.sapphire;

import com.github.tea_pack.sapphire.entities.Channel;
import com.github.tea_pack.sapphire.entities.View;
import com.github.tea_pack.sapphire.parsers.CSVParser;
import com.github.tea_pack.sapphire.parsers.ChannelParser;
import com.github.tea_pack.sapphire.parsers.ViewParser;
import com.github.tea_pack.sapphire.statistics.BroadcastStatistics;
import com.github.tea_pack.sapphire.statistics.ChannelPopularity;
import com.github.tea_pack.sapphire.statistics.GraphPopular;
import com.github.tea_pack.sapphire.statistics.GroupBroadcastStatistics;
import com.github.tea_pack.sapphire.statistics.filters.DateFilter;
import com.github.tea_pack.sapphire.statistics.filters.DateTimeWeekFilter;
import com.github.tea_pack.sapphire.statistics.filters.DayOfWeekFilter;
import com.github.tea_pack.sapphire.statistics.filters.DurationFilter;
import com.github.tea_pack.sapphire.utility.Pair;

import java.io.PrintStream;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Predicate;

public class StatisticsTest {
	public static void main(String[] args) {
		Locale.setDefault(Locale.ROOT);
		try {
			Path path = Path.of("./src/test/java/com/github/tea_pack/sapphire/source_data/epg_stat_2024_10.csv");
			CSVParser csvParser = new CSVParser(path);
			csvParser.next();
			List<View> views = ViewParser.parse(csvParser.consume());

			// path = Path.of("./src/test/java/com/github/tea_pack/sapphire/source_data/package_channel.csv");
			// csvParser = new CSVParser(path);
			// csvParser.next();
			// List<Channel> channels = ChannelParser.parse(csvParser.consume());

			//graphTest(views, channels);
			filterTest(views);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void filterTest(List<View> views) throws Exception{
		DayOfWeekFilter week = new DayOfWeekFilter(DayOfWeekFilter.MONDAY | DayOfWeekFilter.WEDNESDAY | DayOfWeekFilter.FRIDAY);
		DateFilter date = new DateFilter(LocalDate.of(2024, Month.OCTOBER, 1), LocalDate.of(2024, Month.NOVEMBER, 1));
		DateTimeWeekFilter dtw = new DateTimeWeekFilter(date, null, week);
		DurationFilter dur = new DurationFilter(Duration.ofMinutes(10), Duration.ofMinutes(40));
		views = views.stream().filter(view ->  {
			return dtw.test(view.broadcast.start) && dur.test(view.broadcast.duration);
		}).toList();

		top100Broadcast(views);
	}

	public static void graphTest(List<View> views, List<Channel> channels) throws Exception {
		Pair<List<GraphPopular.Node>, List<GraphPopular.Edge>> pair = GraphPopular.constructGraph(100, views, channels);
		Path path = Path.of("./src/test/java/com/github/tea_pack/sapphire/source_data/graph_test.txt");
		try (PrintStream out = new PrintStream(path.toFile())){
			GraphPopular.writeGraph(pair.key, pair.value, out);
		}
	}

	public static void overWatched(List<View> views) {
		List<GroupBroadcastStatistics> top = GroupBroadcastStatistics.topNamesByWatchTime(100, views);

		int count = 0;
		for (GroupBroadcastStatistics stat : top) {
			for (BroadcastStatistics bcStat : stat.broadcastStatistics) {
				Duration bcDur = bcStat.broadcast.duration;
				for (Map.Entry<Long, Duration> entry : bcStat.clientWatch.entrySet()) {
					Duration clDur = entry.getValue();
					Long clientID = entry.getKey();
					if (bcDur.compareTo(clDur) < 0) {
						for (View view : bcStat.clientView.get(clientID)) {
							System.out.println(view);
							if (count++ > 200) {
								return;
							}
						}
					}
				}
			}
		}
	}

	public static void top100Broadcast(List<View> views) throws Exception {
		List<GroupBroadcastStatistics> top = GroupBroadcastStatistics.topNamesByWatchTime(100, views);
		int count = 1;
		for (GroupBroadcastStatistics stats : top) {
			System.out.printf("%d. ", count++);
			System.out.println(stats.name);
			System.out.printf("%s%n%n", stats);
		}
	}
}
