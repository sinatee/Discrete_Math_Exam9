package model;

import java.util.*;

public class Graph {
    private final Map<String, Vertex> vertexMap;
    private final List<Edge> edges;
    private final Map<Vertex, List<Edge>> adj;

    public Graph() {
        this.vertexMap = new HashMap<>();
        this.edges = new ArrayList<>();
        this.adj = new HashMap<>();
    }

    public boolean addVertex(String name) {
        if (name == null || name.trim().isEmpty() || vertexMap.containsKey(name)) {
            return false;
        }
        Vertex v = new Vertex(name);
        vertexMap.put(name, v);
        adj.put(v, new ArrayList<>()); 
        return true;
    }

    public void addEdge(String sourceName, String destName, int weight) {
        this.addVertex(sourceName);
        this.addVertex(destName);

        Vertex s = vertexMap.get(sourceName);
        Vertex d = vertexMap.get(destName);
        Edge edge = new Edge(s, d, weight);

        this.edges.add(edge);
        
        // สำหรับ Undirected Graph
        adj.get(s).add(edge);
        adj.get(d).add(edge);
    }

    /**
     * ดึง Vertex จากชื่อ
     */
    public Vertex findVertex(String name) {
        return vertexMap.get(name);
    }

    public List<Edge> getEdgesOf(Vertex v) {
        return Collections.unmodifiableList(adj.getOrDefault(v, new ArrayList<>()));
    }

    public Collection<Vertex> getVertices() { 
        return Collections.unmodifiableCollection(vertexMap.values()); 
    }
    
    public List<Edge> getEdges() { 
        return Collections.unmodifiableList(edges); 
    }

    public int getVertexCount() { return vertexMap.size(); }
    public int getEdgeCount() { return edges.size(); }

    public void clear() {
        this.vertexMap.clear();
        this.edges.clear();
        this.adj.clear();
    }
}