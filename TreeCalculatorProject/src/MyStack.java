public class MyStack {
    // ใช้ Inner Class เป็น Wrapper เพื่อไม่ให้ไปกวน setLeft/setRight ของ Node จริงๆ
    private class StackNode {
        Node data;
        StackNode next;

        StackNode(Node data, StackNode next) {
            this.data = data;
            this.next = next;
        }
    }

    private StackNode top; // เก็บตัวบนสุดของ Stack
    private int size;      // เก็บจำนวนข้อมูลใน Stack

    public MyStack() {
        this.top = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return top == null;
    }

    // ใส่ข้อมูลเข้า Stack
    public void push(Node node) {
        top = new StackNode(node, top);
        size++;
    }

    // เอาข้อมูลออกจาก Stack
    public Node pop() {
        if (isEmpty()) return null;
        Node data = top.data;
        top = top.next;
        size--;
        return data;
    }

    // แอบดูข้อมูลตัวบนสุด
    public Node peek() {
        return (isEmpty()) ? null : top.data;
    }

    // แสดงข้อมูลทั้งหมดใน Stack เพื่อการ Debug
    public void displayStack() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return;
        }
        System.out.print("Stack (top to bottom): ");
        StackNode current = top;
        while (current != null) {
            System.out.print("[" + current.data.getValue() + "] ");
            current = current.next;
        }
        System.out.println("\nStack Size: " + size);
    }
}