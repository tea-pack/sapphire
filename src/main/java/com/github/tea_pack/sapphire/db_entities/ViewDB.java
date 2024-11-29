package com.github.tea_pack.sapphire.db_entities;

import com.github.tea_pack.sapphire.entities.Broadcast;

import org.springframework.boot.autoconfigure.h2.H2ConsoleProperties.Settings;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ViewDB {

    public int clientID;
    public int deviceID;
    public Settings start;
    public Settings end;
    public Settings duration;
    @ManyToOne
    @JoinColumn(name = "broadcast_id")
    public Broadcast broadcast;
}
