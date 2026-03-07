import java.util.*;

public class Graph {

    private Set<Vertex> vertices;
    private List<Edge> edges;

    public Graph() {
        vertices = new HashSet<>();
        edges = new ArrayList<>();
    }

    public boolean addVertex(String name) {
        Vertex v = new Vertex(name);
        return vertices.add(v);
    }

    public void addEdge(String source, String dest, int cost) {

    Vertex s = findVertex(source);
    Vertex d = findVertex(dest);

    if (s == null || d == null) {
        return;
    }

    edges.add(new Edge(s, d, cost));
}
    public Set<Vertex> getVertices() {
        return vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    private Vertex findVertex(String name) {

        for (Vertex v : vertices) {

            if (v.getName().equals(name)) {
                return v;
            }

        }

        return null;
    }
}