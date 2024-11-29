package com.github.tea_pack.sapphire.statistics;

import com.github.tea_pack.sapphire.entities.Broadcast;
import com.github.tea_pack.sapphire.entities.View;

import java.time.Duration;
import java.util.*;

public class ChannelPopularity {
	public static class Pair<K extends Comparable<K>, V> implements Comparable<Pair<K, V>> {
		public K key;
		public V value;

		public Pair(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public int compareTo(Pair<K, V> o) {
			return key.compareTo(o.key);
		}
	}

	public static class Stats {
		public String name;
		public HashSet<BroadcastStatistics> broadcastStatistics;

		public Stats(String name) {
			this.name = name;
			broadcastStatistics = new HashSet<>();
		}

		public Duration totalWatchTime() {
			Duration time = Duration.ZERO;
			for(BroadcastStatistics bcStat: broadcastStatistics){
				time = time.plus(bcStat.totalWatchTime());
			}
			return time;
		}

		public Duration averageWatchTime() {
			Duration time = Duration.ZERO;
			long views = 0;
			for(BroadcastStatistics bcStat: broadcastStatistics){
				time = time.plus(bcStat.totalWatchTime());
				views += bcStat.uniqueWatchers();
			}
			return time.dividedBy(views);
		}

		public Duration averageBroadcastDuration() {
			Duration time = Duration.ZERO;
			for(BroadcastStatistics bcStat: broadcastStatistics){
				time = time.plus(bcStat.broadcast.duration);
			}
			return time.dividedBy(broadcastCount());
		}

		public long broadcastCount() {
			return broadcastStatistics.size();
		}

		public long uniqueWatchers() {
			HashSet<Long> watchers = new HashSet<>();
			for(BroadcastStatistics bcStat: broadcastStatistics){
				watchers.addAll(bcStat.clientWatch.keySet());
			}
			return watchers.size();
		}

		@Override
		public String toString() {
			return "Total watch time: " + formatDuration(totalWatchTime()) +
					"\nAverage broadcast duration: " + formatDuration(averageBroadcastDuration()) +
					"\nUnique watchers: " + uniqueWatchers() +
					"\nAverage watch time: " + formatDuration(averageWatchTime()) +
					"\nBroadcast count: " + broadcastCount() + "\n";
		}
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

	public static List<Stats> top100(List<View> views) {
		Map<Broadcast, BroadcastStatistics> map = getBroadcastStatistics(views);
		Map<String, Stats> statsMap = new HashMap<>();

		for(BroadcastStatistics broadcasts: map.values()){
			statsMap.compute(broadcasts.broadcast.name, (name, stats) -> {
				if(stats == null) {
					stats = new Stats(name);
				}
				stats.broadcastStatistics.add(broadcasts);
				return stats;
			});
		}

		PriorityQueue<Pair<Long, Stats>> priorityQueue = new PriorityQueue<>();
		for(Stats stat: statsMap.values()){
			priorityQueue.add(new Pair<>(-stat.totalWatchTime().toSeconds(), stat));
		}
		List<Stats> list = new ArrayList<>();
		for(int i = 0; i<100 && !priorityQueue.isEmpty(); i++){
			list.add(priorityQueue.poll().value);
		}
		return list;
	}

	public static String formatDuration(Duration duration) {
		long s = duration.toSeconds();
		return String.format("%d:%02d:%02d", s / 3600, (s % 3600) / 60, (s % 60));
	}
}
