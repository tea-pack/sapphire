package com.github.tea_pack.sapphire.entities;


public class Client {
    public final long clientID;
    public final String address;
    public final Gender gender;
    public final int estAge;

    public Client(long clientID, String address, Gender gender, int estAge) {
        this.clientID = clientID;
        this.address = address;
        this.gender = gender;
        this.estAge = estAge;
    }
}
