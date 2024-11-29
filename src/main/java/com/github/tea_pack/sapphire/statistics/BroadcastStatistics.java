package com.github.tea_pack.sapphire.statistics;

import com.github.tea_pack.sapphire.entities.Broadcast;
import com.github.tea_pack.sapphire.entities.View;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BroadcastStatistics {
	public final Broadcast broadcast;
	public final HashMap<Long, Duration> clientWatch;
	public final HashMap<Long, List<View>> clientView;

	public BroadcastStatistics(Broadcast broadcast) {
		this.broadcast = broadcast;
		clientWatch = new HashMap<>();
		clientView = new HashMap<>();
	}

	public void view(View view) {
		if (view.duration.toSeconds() > 10) {
			if (broadcast.equals(view.broadcast)) {
				clientWatch.compute(view.clientID, (client, duration) -> duration == null ? Duration.ZERO.plus(view.duration) : duration.plus(view.duration));
				clientView.compute(view.clientID, (client, list) -> {
					if(list == null) {
						list = new ArrayList<>();
					}
					list.add(view);
					return list;
				});
			}
		}
	}

	public Duration totalWatchTime() {
		Duration time = Duration.ZERO;
		for (Duration clientWatchTime: clientWatch.values()) {
			time = time.plus(clientWatchTime);
		}
		return time;
	}

	public Duration averageWatchTime() {
		return totalWatchTime().dividedBy(clientWatch.size());
	}

	public long uniqueWatchers() {
		return clientWatch.size();
	}


	public static Map<Broadcast, BroadcastStatistics> getBroadcastStatistics(List<View> views) {
		Map<Broadcast, BroadcastStatistics> map = new HashMap<>();
		for (View view : views) {
			if(!map.containsKey(view.broadcast)){
				map.put(view.broadcast, new BroadcastStatistics(view.broadcast));
			}
			map.get(view.broadcast).view(view);
		}
		return map;
	}
}
