package com.github.tea_pack.sapphire.statistics;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.github.tea_pack.sapphire.entities.View;
import com.github.tea_pack.sapphire.parsers.CSVParser;
import com.github.tea_pack.sapphire.parsers.ViewParser;
import com.github.tea_pack.sapphire.utility.FMT;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UniqueStatsReport {

    private static final String FORMAT = "<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>";

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ReportEntry {
        private long channel;
        private String broadcast;
        private long clientsCount;
        private String avgWatchingTime;

        @Override
        public String toString() {
            return String.format(FORMAT, channel, broadcast, clientsCount, avgWatchingTime);
        }
    }

    private List<ReportEntry> entries = new ArrayList<>();

    public void addEntry(ReportEntry entry) {
        entries.add(entry);
    }

    public String toHtmlTableRow() {
        StringBuilder sb = new StringBuilder();
        sb.append(
                String.format(FORMAT, "Канал", "Программа", "Уникальных клиентов",
                        "Среднее время просмотра"));
        for (ReportEntry re : entries) {
            sb.append(String.format("%s", re));
        }
        return sb.toString();
    }

    public void calcStat() {
        try {
            Path path = Path.of("./src/test/java/com/github/tea_pack/sapphire/source_data/epg_stat_2024_10.csv");
            CSVParser csvParser = new CSVParser(path);
            csvParser.next();

            List<View> views = ViewParser.parse(csvParser.consume());

            List<GroupBroadcastStatistics> top = GroupBroadcastStatistics.topNamesByWatchTime(10, views);
            System.out.println("parsed");
            for (GroupBroadcastStatistics stats : top) {
                addEntry(new ReportEntry(
                        stats.broadcastStatistics.stream().findAny().get().broadcast.channelID,
                        stats.name, stats.uniqueWatchers(),
                        FMT.formatDuration(stats.averageWatchTime())));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
