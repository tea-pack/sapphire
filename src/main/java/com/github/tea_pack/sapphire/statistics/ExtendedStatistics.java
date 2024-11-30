package com.github.tea_pack.sapphire.statistics;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtendedStatistics {
    public List<Entry> entries = new ArrayList<>();

    public void addEntry(Entry entry) {
        entries.add(entry);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Entry {

        private String program;
        private String timeWatch;
        private String uniqueClients;
        private String channelPack;
        private String duration;
        private String avgWatchingTime;
    }

    public static class StatisticsPreferences {
        String startDate;
        String endDate;
        boolean isMonday;
        boolean isTuesday;
        boolean isWednesday;
        boolean isThursday;
        boolean isFriday;
        boolean isSaturday;
        boolean isSunday;
        int startTime;
        int endTime;

    }

}
