public class MyStack {

    private Node top;

    public MyStack() {
        top = null;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void push(Node newNode) {
        newNode.right = top;
        top = newNode;
    }

    public Node pop() {
        if (isEmpty()) {
            return null;
        }

        Node temp = top;
        top = top.right;
        return temp;
    }

    public Node peek() {
        return top;
    }
}