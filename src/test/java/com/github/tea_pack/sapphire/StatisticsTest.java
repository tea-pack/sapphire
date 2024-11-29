package com.github.tea_pack.sapphire;

import com.github.tea_pack.sapphire.entities.View;
import com.github.tea_pack.sapphire.parsers.CSVParser;
import com.github.tea_pack.sapphire.parsers.ViewParser;
import com.github.tea_pack.sapphire.statistics.BroadcastStatistics;
import com.github.tea_pack.sapphire.statistics.ChannelPopularity;
import com.github.tea_pack.sapphire.statistics.GroupBroadcastStatistics;

import java.nio.file.Path;
import java.time.Duration;
import java.util.List;
import java.util.Map;

public class StatisticsTest {
	public static void main(String[] args) {
		try	{
			Path path = Path.of("./src/test/java/com/github/tea_pack/sapphire/source_data/epg_stat_2024_10.csv");
			CSVParser csvParser = new CSVParser(path);
			csvParser.next();

			List<View> views = ViewParser.parse(csvParser.consume());

			overWatched(views);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void overWatched(List<View> views) {
		List<GroupBroadcastStatistics> top = GroupBroadcastStatistics.topNamesByWatchTime(100, views);

		int count = 0;
		for (GroupBroadcastStatistics stat: top){
			for(BroadcastStatistics bcStat: stat.broadcastStatistics){
				Duration bcDur = bcStat.broadcast.duration;
				for(Map.Entry<Long, Duration> entry: bcStat.clientWatch.entrySet()){
					Duration clDur = entry.getValue();
					Long clientID = entry.getKey();
					if(bcDur.compareTo(clDur) < 0) {
						for(View view: bcStat.clientView.get(clientID)){
							System.out.println(view);
							if(count++ > 200) {
								return;
							}
						}
					}
				}
			}
		}
	}

	public static void top100Broadcast(List<View> views) throws Exception{
		List<GroupBroadcastStatistics> top = GroupBroadcastStatistics.topNamesByWatchTime(100, views);
		int count = 1;
		for(GroupBroadcastStatistics stats: top) {
			System.out.printf("%d. ", count++);
			System.out.println(stats.name);
			System.out.printf("%s%n%n", stats);
		}
	}
}
