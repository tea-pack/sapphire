package com.github.tea_pack.sapphire.statistics;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UniqueStatsReport {

    private static final String FORMAT = "<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>";

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ReportEntry {
        private String channel;
        private String broadcast;
        private int clientsCount;
        private int avgWatchingTime;

        @Override
        public String toString() {
            return String.format(FORMAT, channel, broadcast, clientsCount, avgWatchingTime);
        }
    }

    List<ReportEntry> entries = new ArrayList<>();

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

}
