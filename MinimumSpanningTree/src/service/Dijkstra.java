package service;

import model.*;
import java.util.*;

public class Dijkstra {

    /**
     * คำนวณระยะทางที่สั้นที่สุดจากจุดเริ่มไปยังทุกจุดในกราฟ
     * @return Map ที่เก็บ Vertex และระยะทางที่สั้นที่สุด (Distance)
     */
    public Map<Vertex, Integer> runDijkstra(Graph g, String startName) {
        Vertex startNode = g.findVertex(startName);
        Map<Vertex, Integer> distances = new HashMap<>();
        
        if (startNode == null) return distances;

        // 1. Initialize: ให้ทุกจุดมีระยะทางเป็น Infinity ยกเว้นจุดเริ่มเป็น 0
        for (Vertex v : g.getVertices()) {
            distances.put(v, Integer.MAX_VALUE);
        }
        distances.put(startNode, 0);

        PriorityQueue<NodeDistance> pq = new PriorityQueue<>();
        pq.add(new NodeDistance(startNode, 0));

        while (!pq.isEmpty()) {
            NodeDistance currentEntry = pq.poll();
            Vertex current = currentEntry.vertex;
            int currentDist = currentEntry.dist;

            // ถ้าเจอระยะทางที่บันทึกไว้ดีกว่าค่าใน PQ แล้ว ให้ข้ามไป 
            if (currentDist > distances.get(current)) continue;

            // 2. ใช้ Adjacency List จาก Graph เพื่อหาเพื่อนบ้าน (Neighbor) ได้ทันที
            for (Edge e : g.getEdgesOf(current)) {
                // หาว่าโหนดปลายทางคือใคร
                Vertex neighbor = e.getSource().equals(current) ? e.getDest() : e.getSource();
                
                int newDist = distances.get(current) + e.getWeight();

                // 3. Relaxation: ถ้าเจอทางที่สั้นกว่า ให้ Update และใส่ลง PQ
                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    pq.add(new NodeDistance(neighbor, newDist));
                }
            }
        }
        return distances;
    }

    // Helper Class สำหรับใช้ใน PriorityQueue
    private static class NodeDistance implements Comparable<NodeDistance> {
        final Vertex vertex;
        final int dist;

        NodeDistance(Vertex v, int d) {
            this.vertex = v;
            this.dist = d;
        }

        @Override
        public int compareTo(NodeDistance o) {
            return Integer.compare(this.dist, o.dist);
        }
    }
}