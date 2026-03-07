package service;

import model.Graph;

public class GraphAnalyzer {

    // เมธอดนี้ใช้ตรวจสอบว่า Graph ที่รับเข้ามาเป็น Complete Graph หรือไม่
    public static boolean isCompleteGraph(Graph g) {

        // n คือจำนวนจุด (Vertex) ทั้งหมดในกราฟ
        int n = g.getVertices().size();

        // e คือจำนวนเส้น (Edge) ทั้งหมดในกราฟ
        int e = g.getEdges().size();

        // สูตรของ Complete Graph คือ จำนวนเส้นต้องเท่ากับ n(n-1)/2
        int expectedEdges = (n * (n - 1)) / 2;

        // ถ้าจำนวนเส้นจริง เท่ากับ จำนวนเส้นตามสูตร
        // แสดงว่าเป็น Complete Graph
        return e == expectedEdges;
    }
}