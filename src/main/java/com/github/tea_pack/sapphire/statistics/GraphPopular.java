package com.github.tea_pack.sapphire.statistics;

import com.github.tea_pack.sapphire.entities.Broadcast;

import java.io.File;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class GraphPopular {
	public static class Node {
		public int id;
		public int hours;
		public String name;
		public String category;

		public Node(int id, int hours, String name, String category) {
			this.id = id;
			this.hours = hours;
			this.name = name;
			this.category = category;
		}

		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder("{");
			sb.append("id: ").append(id);
			sb.append(", hours: ").append(hours);
			sb.append(", name: \"").append(name).append('"');
			sb.append(", category: \"").append(category).append('"');
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

	public static void constructGraph(int count, Map<Broadcast, BroadcastStatistics> statisticsMap) {

	}

	public static void writeGraph(List<Node> nodes, List<Edge> edges, Path path) throws Exception {
		try (PrintStream print = new PrintStream(path.toFile())) {
			print.println("[");
			for(Node node: nodes){
				print.printf("\t%s,\n", node);
			}
			print.println("]\n");
			print.println("[");
			for(Edge edge: edges){
				print.printf("\t%s,\n", edge);
			}
			print.println("]");
		}
	}
}
