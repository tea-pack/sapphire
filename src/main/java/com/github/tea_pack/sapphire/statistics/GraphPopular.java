package com.github.tea_pack.sapphire.statistics;

import com.github.tea_pack.sapphire.entities.Channel;
import com.github.tea_pack.sapphire.entities.View;
import com.github.tea_pack.sapphire.utility.Pair;

import java.io.PrintStream;
import java.util.*;

public class GraphPopular {
	public static class Node {
		public int id;
		public int hours;
		public String name;
		public String pack;

		public Node(int id, int hours, String name, String pack) {
			this.id = id;
			this.hours = hours;
			this.name = name;
			this.pack = pack;
		}

		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder("{");
			sb.append("id: ").append(id);
			sb.append(", hours: ").append(hours);
			sb.append(", name: \"").append(name).append('"');
			sb.append(", pack: \"").append(pack).append('"');
			sb.append('}');
			return sb.toString();
		}
	}

	public static class Edge {
		public int node1;
		public int node2;
		public int mutualViews;

		public Edge(int node1, int node2, int mutualViews) {
			this.node1 = node1;
			this.node2 = node2;
			this.mutualViews = mutualViews;
		}

		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder("{");
			sb.append("node1: ").append(node1);
			sb.append(", node2: ").append(node2);
			sb.append(", mutualViews: ").append(mutualViews);
			sb.append('}');
			return sb.toString();
		}
	}

	public static Pair<List<Node>, List<Edge>> constructGraph(int count, List<View> views, List<Channel> channels) {
		List<GroupBroadcastStatistics> groupStatistics = GroupBroadcastStatistics.topNamesByWatchTime(count, views);
		count = groupStatistics.size();
		List<Node> nodes = new ArrayList<>();
		List<Edge> edges = new ArrayList<>();

		HashMap<Long, String> channelPack = new HashMap<>();
		for(Channel channel: channels) {
			channelPack.put(channel.channelId, channel.pack);
		}

		for (int i = 0; i < count; i++) {
			GroupBroadcastStatistics group1 = groupStatistics.get(i);
			nodes.add(getNode(i, group1, channelPack));
			for (int j = i + 1; j < count; j++) {
				GroupBroadcastStatistics group2 = groupStatistics.get(j);
				Edge edge = getEdge(i, j, group1, group2);
				if(edge.mutualViews > 0){
					edges.add(edge);
				}
			}
		}

		return new Pair<>(nodes, edges);
	}

	public static Node getNode(int id, GroupBroadcastStatistics group, Map<Long, String> channelPack) {
		String name = group.name;
		// FIXME: think of adequate way to fetch category
		String pack = channelPack.get(group.broadcastStatistics.stream().findAny().get().broadcast.channelID);
		int hours = (int) group.totalWatchTime().toHours();
		return new Node(id, hours, name, pack);
	}

	public static Edge getEdge(int id1, int id2, GroupBroadcastStatistics group1, GroupBroadcastStatistics group2) {
		int mutualViews = 0;
		HashSet<Long> set1 = group1.uniqueWatchersSet();
		HashSet<Long> set2 = group2.uniqueWatchersSet();

		for (long cl1 : set1) {
			mutualViews += set2.contains(cl1) ? 1 : 0;
		}

		return new Edge(id1, id2, mutualViews);
	}

	public static void writeGraph(List<Node> nodes, List<Edge> edges, PrintStream out) throws Exception {
		out.println("[");
		for (Node node : nodes) {
			out.printf("\t%s,\n", node);
		}
		out.println("]\n");
		out.println("[");
		for (Edge edge : edges) {
			out.printf("\t%s,\n", edge);
		}
		out.println("]");
	}
}
