import java.util.Stack;

public class ExpressionTree {

    private Node root;

    public ExpressionTree() {
        root = null;
    }


    public Node buildTree(String postfix) {

        Stack<Node> stack = new Stack<>();
        String[] tokens = postfix.split(" ");

        for (String token : tokens) {

            if (isOperator(token)) {

                Node right = stack.pop();
                Node left = stack.pop();

                Node newNode = new Node(token);
                newNode.setLeft(left);
                newNode.setRight(right);

                stack.push(newNode);

            } else {

                stack.push(new Node(token));

            }
        }

        root = stack.pop();
        return root;
    }

    
    public String getPrefix(Node node) {

        if (node == null)
            return "";

        return node.getValue() + " "
                + getPrefix(node.getLeft())
                + getPrefix(node.getRight());
    }

   
    public String getPostfix(Node node) {

        if (node == null)
            return "";

        return getPostfix(node.getLeft())
                + getPostfix(node.getRight())
                + node.getValue() + " ";
    }

    
    public double evaluate(Node node) {

        if (node == null)
            return 0;

        if (!isOperator(node.getValue())) {
            return Double.parseDouble(node.getValue());
        }

        double left = evaluate(node.getLeft());
        double right = evaluate(node.getRight());

        switch (node.getValue()) {

            case "+":
                return left + right;

            case "-":
                return left - right;

            case "*":
                return left * right;

            case "/":
                return left / right;
        }

        return 0;
    }

    
    public Node getRoot() {
        return root;
    }

   
    private boolean isOperator(String c) {

        return c.equals("+")
                || c.equals("-")
                || c.equals("*")
                || c.equals("/");
    }
}