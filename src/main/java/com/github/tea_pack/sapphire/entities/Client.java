package com.github.tea_pack.sapphire.entities;

public class Client {
    public int entityID;
	public final int ID;
	public String address;
	public Gender gender;
	public Range age;

	public Client(int ID, String address, Gender gender, Range age) {
		this.ID = ID;
		this.address = address;
		this.gender = gender;
		this.age = age;
	}
}
