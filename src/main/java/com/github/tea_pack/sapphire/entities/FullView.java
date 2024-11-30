package com.github.tea_pack.sapphire.entities;

public class FullView {
	public final Client client;
	public final Channel channel;
	public final Broadcast broadcast;
	public final ViewInfo info;

	public FullView(Client client, Channel channel, Broadcast broadcast, ViewInfo info) {
		this.client = client;
		this.channel = channel;
		this.broadcast = broadcast;
		this.info = info;
	}
}
