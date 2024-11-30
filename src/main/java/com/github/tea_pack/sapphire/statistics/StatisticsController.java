package com.github.tea_pack.sapphire.statistics;

import static com.github.tea_pack.sapphire.statistics.filters.DayOfWeekFilter.FRIDAY;
import static com.github.tea_pack.sapphire.statistics.filters.DayOfWeekFilter.MONDAY;
import static com.github.tea_pack.sapphire.statistics.filters.DayOfWeekFilter.SATURDAY;
import static com.github.tea_pack.sapphire.statistics.filters.DayOfWeekFilter.SUNDAY;
import static com.github.tea_pack.sapphire.statistics.filters.DayOfWeekFilter.THURSDAY;
import static com.github.tea_pack.sapphire.statistics.filters.DayOfWeekFilter.TUESDAY;
import static com.github.tea_pack.sapphire.statistics.filters.DayOfWeekFilter.WEDNESDAY;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import com.github.tea_pack.sapphire.entities.Channel;
import com.github.tea_pack.sapphire.entities.Client;
import com.github.tea_pack.sapphire.entities.FullView;
import com.github.tea_pack.sapphire.entities.View;
import com.github.tea_pack.sapphire.parsers.CSVParser;
import com.github.tea_pack.sapphire.parsers.ChannelParser;
import com.github.tea_pack.sapphire.parsers.ClientParser;
import com.github.tea_pack.sapphire.parsers.FullViewParser;
import com.github.tea_pack.sapphire.parsers.ViewParser;
import com.github.tea_pack.sapphire.statistics.ExtendedStatistics.Entry;
import com.github.tea_pack.sapphire.statistics.filters.BroadcastFilter;
import com.github.tea_pack.sapphire.statistics.filters.DateFilter;
import com.github.tea_pack.sapphire.statistics.filters.DateTimeWeekFilter;
import com.github.tea_pack.sapphire.statistics.filters.DayOfWeekFilter;
import com.github.tea_pack.sapphire.statistics.filters.FullViewFilter;
import com.github.tea_pack.sapphire.statistics.filters.TimeFilter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatisticsController {

    @GetMapping("/stat")
    @ResponseBody
    public ResponseEntity<List<ExtendedStatistics.Entry>> readAll(
            @RequestBody ExtendedStatistics.StatisticsPreferences prefs) {
        ExtendedStatistics extendedStaistics = new ExtendedStatistics();

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate start = LocalDate.parse(prefs.startDate, fmt);
        LocalDate end = LocalDate.parse(prefs.startDate, fmt);
        DateFilter dateFilter = new DateFilter(start, end);
        TimeFilter timeFilter = new TimeFilter(
                LocalTime.of(prefs.startTime / 60, prefs.startTime % 60),
                LocalTime.of(prefs.endTime / 60, prefs.endTime % 60));

        int mask = 0b0;

        mask |= prefs.isMonday ? MONDAY : 0b0;
        mask |= prefs.isTuesday ? TUESDAY : 0b0;
        mask |= prefs.isWednesday ? WEDNESDAY : 0b0;
        mask |= prefs.isThursday ? THURSDAY : 0b0;
        mask |= prefs.isFriday ? FRIDAY : 0b0;
        mask |= prefs.isSaturday ? SATURDAY : 0b0;
        mask |= prefs.isSunday ? SUNDAY : 0b0;
        DayOfWeekFilter dayOfWeekFilter = new DayOfWeekFilter(mask);
        DateTimeWeekFilter compositeFilter = new DateTimeWeekFilter(dateFilter, timeFilter, dayOfWeekFilter);

        Locale.setDefault(Locale.ROOT);
        try {
            Path path = Path.of("./src/test/java/com/github/tea_pack/sapphire/source_data/epg_stat_2024_10.csv");
            CSVParser csvParser = new CSVParser(path);
            csvParser.next();
            List<View> views = ViewParser.parse(csvParser.consume());

            path = Path.of("./src/test/java/com/github/tea_pack/sapphire/source_data/package_channel.csv");
            csvParser = new CSVParser(path);
            csvParser.next();
            List<Channel> channels = ChannelParser.parse(csvParser.consume());

            path = Path.of("./src/test/java/com/github/tea_pack/sapphire/source_data/client.csv");
            csvParser = new CSVParser(path);
            csvParser.next();
            List<Client> clients = ClientParser.parse(csvParser.consume());

            List<FullView> fullViews = FullViewParser.parse(clients, views, channels);

            FullViewFilter.Builder builder = FullViewFilter.builder();
            builder.broadcastFilter = new BroadcastFilter(null, null, compositeFilter);

            fullViews = fullViews.stream().filter(builder.build()).toList();
            List<GroupBroadcastStatistics> top = GroupBroadcastStatistics.topNamesByWatchTime(100, fullViews);

            for (GroupBroadcastStatistics stats : top) {
                extendedStaistics.addEntry(new Entry(
                        stats.name,
                        Long.toString(stats.totalWatchTime().toHours()),
                        Long.toString(stats.uniqueWatchers()),
                        stats.broadcastStatistics.stream().findAny().get().clientView.values().stream().findAny().get()
                                .stream().findAny().get().channel.pack,
                        Long.toString(stats.averageBroadcastDuration().toMinutes()),
                        Long.toString(stats.averageWatchTime().toMinutes())));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ResponseEntity.ok(extendedStaistics.entries);
    }
}
