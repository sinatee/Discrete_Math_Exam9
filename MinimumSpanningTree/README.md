```mermaid
classDiagram
    class EXAM9 {
        +main(String[] args)
    }

    class Vertex {
        -String name
        +Vertex(String name)
        +getName() String
        +equals(Object obj) boolean
        +hashCode() int
        +toString() String
    }

    class Edge {
        -Vertex source
        -Vertex dest
        -int cost
        +Edge(Vertex source, Vertex dest, int cost)
        +getSource() Vertex
        +getDest() Vertex
        +getWeight() int
        +compareTo(Edge other) int
        +toString() String
    }

    class Graph {
        -Set~Vertex~ vertices
        -List~Edge~ edges
        +addVertex(String name) boolean
        +addEdge (String source, String dest, int cost)
        +getVertices() Set~Vertex~
        +getEdges() List~Edge~
        -findVertex(String name) Vertex
    }

    class GraphAnalyzer {
        +isCompleteGraph(Graph g) boolean
    }

    class MST {
        +runKruskal(Graph g) List~Edge~
        +runPrim(Graph g, String startName) List~Edge~
    }

    %%
    EXAM9 ..> Graph
    EXAM9 ..> MST
    EXAM9 ..> GraphAnalyzer
    Graph "1" *-- "many" Vertex
    Graph "1" *-- "many" Edge
    Edge "1" o-- "2" Vertex
