package com.github.tea_pack.sapphire.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ViewDTO {

    private long clientID;
    private long deviceID;
    private String start;
    private String end;
    private int duration;
    //private BroadcastDB broadcast;
    private int broadcastId;
}
