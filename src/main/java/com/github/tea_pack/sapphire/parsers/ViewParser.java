package com.github.tea_pack.sapphire.parsers;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.tea_pack.sapphire.entities.Broadcast;
import com.github.tea_pack.sapphire.entities.View;

import static com.github.tea_pack.sapphire.utility.FMT.DATE_TIME_FORMAT;

public class ViewParser {
    public static View parse(String[] values) throws Exception {
        int clientID = Integer.parseInt(values[0].substring(1));
        int deviceID = Integer.parseInt(values[1].substring(1));
        int channelID = Integer.parseInt(values[3]);
        String broadcastName = parseName(values[4]);
        Integer ageRating = parseAgeRating(values[4]);
        String category = values[8];
        List<String> genres = parseGenres(values[9]);

        LocalDateTime startTime = LocalDateTime.parse(values[2], DATE_TIME_FORMAT);
        Duration watchDuration = Duration.ofSeconds(Integer.parseInt(values[7]));

        LocalDateTime broadcastStart = LocalDateTime.parse(values[5], DATE_TIME_FORMAT);
        LocalDateTime broadcastEnd = LocalDateTime.parse(values[6], DATE_TIME_FORMAT);
        Broadcast broadcast = new Broadcast(broadcastName, channelID, ageRating, broadcastStart, broadcastEnd, category,
                genres);

        startTime = startTime.isAfter(broadcastStart) ? startTime : broadcastStart;

        return new View(clientID, deviceID, startTime, watchDuration, broadcast);
    }

    public static List<View> parse(List<String[]> values) throws Exception {
        List<View> views = new ArrayList<>();
        for (String[] val : values) {
            views.add(parse(val));
        }
        return views;
    }

    private static List<String> parseGenres(String genres) {
        String[] split = genres.split(",");
        List<String> result = new ArrayList<>();
        Arrays.stream(split).forEach((str) -> result.add(str.strip()));
        return result;
    }

    private static String parseName(String name) {
        if (name.startsWith("\"") && name.endsWith("\"")) {
            name = name.substring(1, name.length() - 1)
                    .replace("\"\"", "\"");
        }
        return name.replaceAll("\\(\\d+\\+\\)", "").strip();
    }

    private static Integer parseAgeRating(String name) {
        Pattern pattern = Pattern.compile("\\((\\d+)\\+\\)");
        Matcher matcher = pattern.matcher(name);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        return null;
    }
}
