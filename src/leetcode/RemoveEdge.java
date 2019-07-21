package leetcode;

import java.util.*;

public class RemoveEdge {

    public int[] findRedundantDirectedConnection(int[][] edges) {
        Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
        Map<Integer, Status> statusMap = new HashMap<>();
        Map<Integer, Integer> predecessors = new HashMap<>();
        Set<Edge> violatingEdges = new HashSet<>();

        for (int i = 0; i < edges.length; i++) {
            if (!adjacencyList.containsKey(edges[i][0])) {
                adjacencyList.put(edges[i][0], new ArrayList<>());
            }
            if (!adjacencyList.containsKey(edges[i][1])) {
                adjacencyList.put(edges[i][1], new ArrayList<>());
            }
            adjacencyList.get(edges[i][0]).add(edges[i][1]);
            statusMap.put(edges[i][0], Status.NOT_VISITED);
            statusMap.put(edges[i][1], Status.NOT_VISITED);
            if (predecessors.containsKey(edges[i][1])) {
                violatingEdges.add(new Edge(predecessors.get(edges[i][1]), edges[i][1]));
                violatingEdges.add(new Edge(edges[i][0], edges[i][1]));
            } else {
                predecessors.put(edges[i][1], edges[i][0]);
            }
        }

        for (int node : adjacencyList.keySet()) {
            if (statusMap.get(node) == Status.NOT_VISITED) {
                depthFirstSearch(node, adjacencyList, statusMap, violatingEdges);
            }
        }

        for (int i = edges.length - 1; i >= 0; i--) {
            if (violatingEdges.contains(new Edge(edges[i][0], edges[i][1]))) {
                return edges[i];
            }
        }
        return null;

    }

    private void depthFirstSearch(int node, Map<Integer, List<Integer>> adjacencyList, Map<Integer, Status> statusMap, Set<Edge> violatingEdges) {
        statusMap.put(node, Status.INFLIGHT);
        for (int child : adjacencyList.get(node)) {
            if (statusMap.get(child) == Status.COMPLETE) {
                //Nothing
            } else if (statusMap.get(child) == Status.INFLIGHT) {
                //this is a back edge of a cycle
                violatingEdges.add(new Edge(node, child));

            } else {
                depthFirstSearch(child, adjacencyList, statusMap, violatingEdges);
            }
        }
        statusMap.put(node, Status.COMPLETE);
    }

    public static void main(String[] args) {
        int[][] edges = {{1,2}, {2,3}, {3,4}, {4,1}, {1,5}};
        int[] violatingEdge = new RemoveEdge().findRedundantDirectedConnection(edges);
        System.out.println("["+violatingEdge[0]+","+violatingEdge[1]+"]");
    }
}

enum  Status {
    NOT_VISITED, COMPLETE, INFLIGHT
}

class Edge {
    int from, to;
    public Edge(int from, int to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge)) return false;
        Edge edge = (Edge) o;
        return from == edge.from &&
                to == edge.to;
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }
}


