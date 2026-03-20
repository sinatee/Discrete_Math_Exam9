package service;

import model.*;
import java.util.*;

public class GraphUI {
    private final Scanner sc = new Scanner(System.in);

    /**
     * รับข้อมูลเส้นเชื่อมจากผู้ใช้
     */
    public void inputGraph(Graph g) {
        System.out.print("Enter number of edges to add: ");
        // ป้องกันการใส่ค่าที่ไม่ใช่ตัวเลข
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            sc.next();
        }
        int edgeCount = sc.nextInt();
        
        for (int i = 0; i < edgeCount; i++) {
            System.out.println("\nEdge " + (i + 1) + ":");
            System.out.print("  Source: ");
            String u = sc.next();
            System.out.print("  Dest: ");
            String v = sc.next();
            System.out.print("  Weight: ");
            int w = sc.nextInt();

            // เรียก addEdge อย่างเดียวพอ เพราะ Graph.java ใหม่จัดการสร้าง Vertex ให้เอง
            g.addEdge(u, v, w);
        }
    }

    /**
     * แสดงผลรายการเส้นเชื่อมของ MST และน้ำหนักรวม
     */
    public void displayMST(String title, List<Edge> edges) {
        System.out.println("\n==================================");
        System.out.println("   " + title.toUpperCase());
        System.out.println("==================================");
        
        if (edges == null || edges.isEmpty()) {
            System.out.println("Result: No MST found (Graph may be disconnected).");
            return;
        }

        int totalWeight = 0;
        for (Edge e : edges) {
            System.out.printf(" %-15s | Weight: %d%n", e.getSource().getName() + " - " + e.getDest().getName(), e.getWeight());
            totalWeight += e.getWeight();
        }
        
        System.out.println("----------------------------------");
        System.out.println("Total MST Weight: " + totalWeight);
        System.out.println("==================================\n");
    }

    public String askStartPoint() {
        System.out.print("Enter starting vertex name: ");
        return sc.next();
    }

    /**
     * แสดงผลระยะทางที่สั้นที่สุดจาก Dijkstra
     */
    public void displayShortestPaths(String start, Map<Vertex, Integer> dists) {
        System.out.println("\n--- Dijkstra's Results (Start: " + start + ") ---");
        if (dists == null || dists.isEmpty()) {
            System.out.println("No paths found.");
            return;
        }

        // เรียงลำดับชื่อ Vertex ก่อนแสดงผลเพื่อให้ดูง่าย
        List<Vertex> sortedVertices = new ArrayList<>(dists.keySet());
        sortedVertices.sort(Comparator.comparing(Vertex::getName));

        for (Vertex v : sortedVertices) {
            int d = dists.get(v);
            String distanceStr = (d == Integer.MAX_VALUE) ? "INF" : String.valueOf(d);
            System.out.printf("To: %-10s | Distance: %s%n", v.getName(), distanceStr);
        }
        System.out.println("----------------------------------------------\n");
    }
}