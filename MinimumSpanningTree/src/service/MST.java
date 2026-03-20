package service;

import model.*;
import java.util.*;

public class MST {

    // --- Kruskal's Algorithm ---
    public List<Edge> runKruskal(Graph g) {
        // ใช้ PriorityQueue หรือ Sort ก็ได้ (Clean Code: Sort List อ่านง่ายกว่า)
        List<Edge> allEdges = new ArrayList<>(g.getEdges());
        Collections.sort(allEdges);

        List<Edge> result = new ArrayList<>();
        Map<Vertex, Vertex> parent = new HashMap<>();

        // Initialize Union-Find: ทุกโหนดเป็นหัวหน้าตัวเอง
        for (Vertex v : g.getVertices()) {
            parent.put(v, v);
        }

        for (Edge edge : allEdges) {
            Vertex root1 = find(parent, edge.getSource());
            Vertex root2 = find(parent, edge.getDest());

            // ถ้าไม่ทำให้เกิด Cycle (อยู่คนละกลุ่ม) ให้เลือกเส้นนี้
            if (!root1.equals(root2)) {
                result.add(edge);
                parent.put(root1, root2); // Union
            }
        }
        return result;
    }

    private Vertex find(Map<Vertex, Vertex> parent, Vertex v) {
        if (parent.get(v).equals(v)) return v;
        // Path Compression: ชี้ไปที่ root โดยตรงเพื่อความเร็ว
        parent.put(v, find(parent, parent.get(v))); 
        return parent.get(v);
    }

    // --- Prim's Algorithm ---
    public List<Edge> runPrim(Graph g, String startName) {
        // ใช้ findVertex ที่เราทำไว้ใน Graph.java ได้เลย สั้นกว่า Stream มาก
        Vertex startNode = g.findVertex(startName);
        if (startNode == null) return Collections.emptyList();

        List<Edge> result = new ArrayList<>();
        Set<Vertex> visited = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        visited.add(startNode);
        enqueueEdges(g, startNode, visited, pq);

        int targetEdges = g.getVertexCount() - 1;
        
        while (!pq.isEmpty() && result.size() < targetEdges) {
            Edge edge = pq.poll();
            
            // หาโหนดปลายทางที่ยังไม่เคยไป
            Vertex next = visited.contains(edge.getSource()) ? edge.getDest() : edge.getSource();

            if (!visited.contains(next)) {
                visited.add(next);
                result.add(edge);
                enqueueEdges(g, next, visited, pq);
            }
        }
        return result;
    }

    /**
     * ใช้ Adjacency List จาก Graph ช่วยให้ไม่ต้องวนลูป Edge ทั้งหมด
     */
    private void enqueueEdges(Graph g, Vertex v, Set<Vertex> visited, PriorityQueue<Edge> pq) {
        // ดึงเฉพาะเส้นที่เชื่อมกับจุด v มาพิจารณา (เร็วมาก!)
        for (Edge e : g.getEdgesOf(v)) {
            Vertex neighbor = e.getSource().equals(v) ? e.getDest() : e.getSource();
            if (!visited.contains(neighbor)) {
                pq.add(e);
            }
        }
    }
}