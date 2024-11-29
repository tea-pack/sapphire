package com.github.tea_pack.sapphire.entities;

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
public class Broadcast {

    @Id
    public String name;
    // @ManyToOne
    // @JoinColumn(name = "channel")
    public long channelID;
    public Integer ageRating;
    public String start;
    public String end;
    public Long duration;
}
