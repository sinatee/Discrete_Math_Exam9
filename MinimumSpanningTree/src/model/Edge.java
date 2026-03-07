public class Edge implements Comparable<Edge> {

    private Vertex source;
    private Vertex dest;
    private int cost;

    public Edge(Vertex source, Vertex dest, int cost) {
        this.source = source;
        this.dest = dest;
        this.cost = cost;
    }

    public Vertex getSource() {
        return source;
    }

    public Vertex getDest() {
        return dest;
    }

    public int getWeight() {
        return cost;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.cost, other.cost);
    }

    @Override
    public String toString() {
        return source + " - " + dest + " : " + cost;
    }
}