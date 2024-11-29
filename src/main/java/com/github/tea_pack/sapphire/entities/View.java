package com.github.tea_pack.sapphire.entities;

import java.time.Duration;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class View {
    public int clientID;
    public int deviceID;
    public LocalDateTime start;
    public LocalDateTime end;
    public Duration duration;
    public Broadcast broadcast;
}
