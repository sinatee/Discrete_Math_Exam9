import model.*;
import service.*;
import java.util.*;

public class EXAM9 {
    public static void main(String[] args) {
        Graph g = new Graph();
        GraphUI ui = new GraphUI();
        MST mstService = new MST();
        Dijkstra dijkstraService = new Dijkstra();
        Scanner sc = new Scanner(System.in);

        boolean running = true;

        while (running) {
            System.out.println("\n==================================");
            System.out.println("    GRAPH ALGORITHM MAIN MENU     ");
            System.out.println("==================================");
            System.out.println(" 1. Add Edges to Graph");
            System.out.println(" 2. Show Graph Analysis Report");
            System.out.println(" 3. Run Kruskal's Algorithm (MST)");
            System.out.println(" 4. Run Prim's Algorithm (MST)");
            System.out.println(" 5. Run Dijkstra's Algorithm (Shortest Path)");
            System.out.println(" 6. Clear Graph Data");
            System.out.println(" 0. Exit Program");
            System.out.println("----------------------------------");
            System.out.print("Select an option: ");

            String choice = sc.next();

            switch (choice) {
                case "1":
                    ui.inputGraph(g);
                    break;

                case "2":
                    System.out.println("\n[Analysis Report]");
                    System.out.println("Total Vertices: " + g.getVertexCount());
                    System.out.println("Total Edges   : " + g.getEdgeCount());
                    System.out.println("Complete Graph: " + GraphAnalyzer.isCompleteGraph(g));
                    break;

                case "3":
                    if (g.getEdgeCount() == 0) {
                        System.out.println("Error: Graph is empty.");
                    } else {
                        List<Edge> kruskalEdges = mstService.runKruskal(g);
                        ui.displayMST("Kruskal's Algorithm Result", kruskalEdges);
                    }
                    break;

                case "4":
                case "5":
                    if (g.getVertexCount() == 0) {
                        System.out.println("Error: Graph is empty.");
                    } else {
                        String startNode = ui.askStartPoint();
                        if (g.findVertex(startNode) == null) {
                            System.out.println("Error: Vertex '" + startNode + "' not found.");
                        } else {
                            if (choice.equals("4")) {
                                List<Edge> primEdges = mstService.runPrim(g, startNode);
                                ui.displayMST("Prim's Algorithm Result (Start: " + startNode + ")", primEdges);
                            } else {
                                Map<Vertex, Integer> dijkstraPaths = dijkstraService.runDijkstra(g, startNode);
                                ui.displayShortestPaths(startNode, dijkstraPaths);
                            }
                        }
                    }
                    break;

                case "6":
                    g = new Graph(); // สร้าง object ใหม่เพื่อล้างข้อมูล
                    System.out.println("Graph data has been cleared.");
                    break;

                case "0":
                    running = false;
                    System.out.println("Exiting program... Thank you!");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
        sc.close();
    }
}