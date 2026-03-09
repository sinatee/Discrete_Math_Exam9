import model.*;
import service.*;
import java.util.*;

public class EXAM9 {
    public static void main(String[] args) {
        Graph g = new Graph();
        MST mstService = new MST();

        // 1. เพิ่มจุด (Vertices)
        g.addVertex("A");
        g.addVertex("B");
        g.addVertex("C");
        g.addVertex("D");
        g.addVertex("E");

        // 2. เพิ่มเส้นเชื่อม (Edges) - ตัวอย่างกราฟ
        g.addEdge("A", "B", 4);
        g.addEdge("A", "C", 2);
        g.addEdge("B", "C", 1);
        g.addEdge("B", "D", 5);
        g.addEdge("C", "D", 8);
        g.addEdge("C", "E", 10);
        g.addEdge("D", "E", 2);

        System.out.println("=== Graph Analysis ===");
        System.out.println("Is Complete Graph: " + GraphAnalyzer.isCompleteGraph(g));
        System.out.println("Total Vertices: " + g.getVertices().size());
        System.out.println("Total Edges: " + g.getEdges().size());

        // 3. รัน Kruskal's Algorithm
        System.out.println("\n--- Kruskal's MST Result ---");
        List<Edge> kruskalRes = mstService.runKruskal(g);
        printResult(kruskalRes);

        // 4. รัน Prim's Algorithm (เริ่มจากจุด A)
        String startPoint = "A";
        System.out.println("\n--- Prim's MST Result (Starting at " + startPoint + ") ---");
        List<Edge> primRes = mstService.runPrim(g, startPoint);
        printResult(primRes);
    }

    private static void printResult(List<Edge> edges) {
        int totalCost = 0;
        if (edges.isEmpty()) {
            System.out.println("No MST found (Graph might be disconnected).");
            return;
        }
        for (Edge e : edges) {
            System.out.println(e); // ใช้ toString() ของ Edge ที่คุณเขียนไว้
            totalCost += e.getWeight();
        }
        System.out.println("Total Weight: " + totalCost);
    }
}