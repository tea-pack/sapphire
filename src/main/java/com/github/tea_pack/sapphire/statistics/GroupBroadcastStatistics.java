package com.github.tea_pack.sapphire.statistics;

import com.github.tea_pack.sapphire.entities.Broadcast;
import com.github.tea_pack.sapphire.entities.View;
import com.github.tea_pack.sapphire.utility.CMPair;
import com.github.tea_pack.sapphire.utility.FMT;

import java.time.Duration;
import java.util.*;

public class GroupBroadcastStatistics {
	public String name;
	public HashSet<BroadcastStatistics> broadcastStatistics;

	public GroupBroadcastStatistics(String name) {
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

	public HashSet<Long> uniqueWatchersSet() {
		HashSet<Long> watchers = new HashSet<>();
		for(BroadcastStatistics bcStat: broadcastStatistics){
			watchers.addAll(bcStat.clientWatch.keySet());
		}
		return watchers;
	}

	public long uniqueWatchers() {
		return uniqueWatchersSet().size();
	}

	@Override
	public String toString() {
		return "Total watch time: " + FMT.formatDuration(totalWatchTime()) +
				"\nAverage broadcast duration: " + FMT.formatDuration(averageBroadcastDuration()) +
				"\nUnique watchers: " + uniqueWatchers() +
				"\nAverage watch time: " + FMT.formatDuration(averageWatchTime()) +
				"\nBroadcast count: " + broadcastCount() + "\n";
	}

	public static List<GroupBroadcastStatistics> topNamesByWatchTime(int count, List<View> views) {
		Map<Broadcast, BroadcastStatistics> map = BroadcastStatistics.getBroadcastStatistics(views);
		Map<String, GroupBroadcastStatistics> statsMap = new HashMap<>();

		for(BroadcastStatistics broadcasts: map.values()){
			statsMap.compute(broadcasts.broadcast.name, (name, stats) -> {
				if(stats == null) {
					stats = new GroupBroadcastStatistics(name);
				}
				stats.broadcastStatistics.add(broadcasts);
				return stats;
			});
		}

		PriorityQueue<CMPair<Long, GroupBroadcastStatistics>> priorityQueue = new PriorityQueue<>();
		for(GroupBroadcastStatistics stat: statsMap.values()){
			priorityQueue.add(new CMPair<>(-stat.totalWatchTime().toSeconds(), stat));
		}
		List<GroupBroadcastStatistics> list = new ArrayList<>();
		for(int i = 0; i < count && !priorityQueue.isEmpty(); i++){
			list.add(priorityQueue.poll().value);
		}
		return list;
	}
}