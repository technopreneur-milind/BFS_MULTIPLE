package com.technopreneur.demo.IP1;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.TreeMap;

public class BfsSolution {

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {

		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		String[] nm = scanner.nextLine().split(" ");

		int n = Integer.parseInt(nm[0]);

		int m = Integer.parseInt(nm[1]);

		int[][] edges = new int[m][2];

		for (int i = 0; i < m; i++) {
			String[] edgesRowItems = scanner.nextLine().split(" ");
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

			for (int j = 0; j < 2; j++) {
				int edgesItem = Integer.parseInt(edgesRowItems[j]);
				edges[i][j] = edgesItem;
			}
		}

		int s = scanner.nextInt();

		int weight = scanner.nextInt();

		Map<Integer, Integer> result = bfs(n, m, edges, s, weight);
		result.forEach(
				(key, value) -> System.out.println(" Distance of Node " + key + " from  Node " + s + " is " + value));
		scanner.close();

	}

	static Map<Integer, Integer> bfs(int n, int m, int[][] edges, int s, int weight) {

		Map<Integer, Integer> distanceFromRoot = new TreeMap<Integer, Integer>();
		distanceFromRoot.put(s, 0);
		boolean[] visited = new boolean[n];
		execute(n, m, edges, s, distanceFromRoot, visited, weight);
		return distanceFromRoot;
	}

	static void execute(int n, int m, int[][] edges, int s, Map<Integer, Integer> distanceFromRoot, boolean[] visited,
			int weight) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(s);
		visited[s - 1] = true;
		while (queue.peek() != null) {
			Integer nextElement = queue.poll();
			for (int i = 0; i < m; i++) {
				if (edges[i][0] == nextElement && !visited[edges[i][1] - 1]) {
					Integer distance = distanceFromRoot.get(edges[i][0]) + weight;
					distanceFromRoot.put(edges[i][1], distance);
					queue.add(edges[i][1]);
					visited[edges[i][1] - 1] = true;
				} else if (edges[i][1] == nextElement && !visited[edges[i][0] - 1]) {
					Integer distance = distanceFromRoot.get(edges[i][1]) + weight;
					distanceFromRoot.put(edges[i][0], distance);
					queue.add(edges[i][0]);
					visited[edges[i][0] - 1] = true;
				}
			}
		}

	}

}
