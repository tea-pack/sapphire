package com.github.tea_pack.sapphire.db_entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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

    @Id
    private long clientID;
    private long deviceID;
    private String start;
    private String end;
    private int duration;
    // @ManyToOne
    // @JoinColumn(name = "broadcast_id")
    // private BroadcastDB broadcast;
    private int broadcastId;
}
