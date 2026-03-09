package service;

import model.*;
import java.util.*;

public class MST {

    // --- Kruskal's Algorithm ---
    public List<Edge> runKruskal(Graph g) {
        List<Edge> allEdges = new ArrayList<>(g.getEdges());
        // Sort edges ตาม cost (ใช้น้อยไปมาก) โดยอาศัย Comparable ใน Edge
        Collections.sort(allEdges);

        List<Edge> result = new ArrayList<>();
        Map<Vertex, Vertex> parent = new HashMap<>();

        // Initialize Union-Find
        for (Vertex v : g.getVertices()) {
            parent.put(v, v);
        }

        for (Edge edge : allEdges) {
            Vertex root1 = find(parent, edge.getSource());
            Vertex root2 = find(parent, edge.getDest());

            if (!root1.equals(root2)) {
                result.add(edge);
                parent.put(root1, root2); // Union
            }
        }
        return result;
    }

    private Vertex find(Map<Vertex, Vertex> parent, Vertex v) {
        if (parent.get(v).equals(v)) return v;
        return find(parent, parent.get(v));
    }

    // --- Prim's Algorithm ---
    public List<Edge> runPrim(Graph g, String startName) {
        List<Edge> result = new ArrayList<>();
        Set<Vertex> visited = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        // ค้นหาจุดเริ่มต้น
        Vertex startNode = null;
        for (Vertex v : g.getVertices()) {
            if (v.getName().equals(startName)) {
                startNode = v;
                break;
            }
        }

        if (startNode == null) return result;

        visited.add(startNode);
        addAvailableEdges(g, startNode, visited, pq);

        while (!pq.isEmpty() && visited.size() < g.getVertices().size()) {
            Edge edge = pq.poll();
            Vertex next = visited.contains(edge.getSource()) ? edge.getDest() : edge.getSource();

            if (!visited.contains(next)) {
                visited.add(next);
                result.add(edge);
                addAvailableEdges(g, next, visited, pq);
            }
        }
        return result;
    }

    private void addAvailableEdges(Graph g, Vertex v, Set<Vertex> visited, PriorityQueue<Edge> pq) {
        for (Edge e : g.getEdges()) {
            if (e.getSource().equals(v) && !visited.contains(e.getDest())) pq.add(e);
            else if (e.getDest().equals(v) && !visited.contains(e.getSource())) pq.add(e);
        }
    }
}