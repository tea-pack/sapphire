package com.github.tea_pack.sapphire.entities;

import java.time.Duration;
import java.time.LocalDateTime;

import static com.github.tea_pack.sapphire.utility.FMT.DATE_TIME_FORMAT;
import static com.github.tea_pack.sapphire.utility.FMT.formatDuration;

public class View {
    public long clientID;
    public long deviceID;
    public LocalDateTime start;
    public LocalDateTime end;
    public Duration duration;
    public Broadcast broadcast;

    public View(long clientID, long deviceID, LocalDateTime start, Duration duration, Broadcast broadcast) {
        this.clientID = clientID;
        this.deviceID = deviceID;
        this.start = start;
        this.end = start.plus(duration);
        this.duration = duration;
        this.broadcast = broadcast;
    }

    @Override
    public String toString() {
        return String.format("Client: %d; Device: %d\n" +
                "%s -- %s; duration: %s\n" +
                "%s", clientID, deviceID,
                             start.format(DATE_TIME_FORMAT), end.format(DATE_TIME_FORMAT), formatDuration(duration),
                             broadcast);
    }
}
