package com.technopreneur.demo.IP1;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class BfsSolutionDiffWeigthBiDirectional {

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {

		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		String[] nm = scanner.nextLine().split(" ");

		int n = Integer.parseInt(nm[0]);

		int m = Integer.parseInt(nm[1]);

		int[][] edges = new int[m][3];

		for (int i = 0; i < m; i++) {
			String[] edgesRowItems = scanner.nextLine().split(" ");
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

			for (int j = 0; j < 3; j++) {
				int edgesItem = Integer.parseInt(edgesRowItems[j]);
				edges[i][j] = edgesItem;
			}
		}

		int s = scanner.nextInt();

		Map<Integer, Integer> result = bfs(n, m, edges, s);
		result.forEach(
				(key, value) -> System.out.println(" Distance of Node " + key + " from  Node " + s + " is " + value));
		scanner.close();

	}

	static Map<Integer, Integer> bfs(int n, int m, int[][] edges, int s) {

		Map<Integer, Integer> distanceFromRoot = new TreeMap<Integer, Integer>();
		distanceFromRoot.put(s, 0);
		Set<Integer> visitedEdges = new HashSet<>();
		execute(n, m, edges, s, distanceFromRoot,visitedEdges);
		return distanceFromRoot;
	}

	static void execute(int n, int m, int[][] edges, int s, Map<Integer, Integer> distanceFromRoot,Set<Integer> visitedEdges) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(s);
		while (queue.peek() != null) {
			Integer nextElement = queue.poll();
			for (int i = 0; i < m; i++) {
				if (edges[i][0] == nextElement && !visitedEdges.contains(Integer.valueOf(String.valueOf(edges[i][0]) + String.valueOf(edges[i][1])))) {
					Integer distance = distanceFromRoot.get(edges[i][0]) + edges[i][2];
					if (distanceFromRoot.get(edges[i][1]) == null || distanceFromRoot.get(edges[i][1]) > distance)
						distanceFromRoot.put(edges[i][1], distance);
					queue.add(edges[i][1]);
					visitedEdges.add(Integer.valueOf(String.valueOf(edges[i][0]) + String.valueOf(edges[i][1])));
				}
			}
		}

	}

}
