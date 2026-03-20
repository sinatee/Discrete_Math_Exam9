package service;

import model.*;

public class GraphAnalyzer {

    /**
     * ตรวจสอบว่ากราฟเป็น Complete Graph หรือไม่
     * สูตร: จำนวนเส้นเชื่อม (e) ต้องเท่ากับ n(n-1)/2
     */
    public static boolean isCompleteGraph(Graph g) {
        if (g == null) return false;

        // ดึงจำนวนจุดและเส้นเชื่อมผ่าน method ที่เราสร้างไว้ใน Graph
        int n = g.getVertexCount();
        int e = g.getEdgeCount();

        // กราฟที่มี 0 หรือ 1 จุด ในทางทฤษฎีถือว่าเป็น Complete Graph
        if (n <= 1) return true;

        // ใช้ long ในการคำนวณเพื่อป้องกันเลขเกิน (Overflow)
        long expectedEdges = (long) n * (n - 1) / 2;

        return e == expectedEdges;
    }
}