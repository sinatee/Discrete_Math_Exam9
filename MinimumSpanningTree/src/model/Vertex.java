package model;

import java.util.Objects;

public class Vertex {
    // 1. ใช้ final: ชื่อโหนดสร้างแล้วไม่ควรเปลี่ยน เพื่อความคงที่ของ Hash
    private final String name;

    public Vertex(String name) {
        // 2. Validation: ป้องกันการสร้างโหนดที่ไม่มีชื่อ
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Vertex name cannot be null or empty");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        // 3. ใช้ getClass() เพื่อความเข้มงวดในการเปรียบเทียบ Object
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return Objects.equals(name, vertex.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}