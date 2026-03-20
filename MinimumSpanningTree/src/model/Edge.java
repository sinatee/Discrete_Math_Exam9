package model;

public class Edge implements Comparable<Edge> {

    private final Vertex source;
    private final Vertex dest;
    private final int weight; // เปลี่ยนจาก cost เป็น weight ให้ตรงกับชื่อเมธอด

    public Edge(Vertex source, Vertex dest, int weight) {
        // Validation: ป้องกัน Error ที่ต้นเหตุ
        if (source == null || dest == null) {
            throw new IllegalArgumentException("Source and Destination vertices cannot be null");
        }
        this.source = source;
        this.dest = dest;
        this.weight = weight;
    }

    public Vertex getSource() {
        return source;
    }

    public Vertex getDest() {
        return dest;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);
    }

    @Override
    public String toString() {
        // toString เดิมของคุณดีอยู่แล้วครับ อ่านง่าย
        return source + " - " + dest + " : " + weight;
    }
}