import java.util.Scanner;

public class EXAM9 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Infix Expression: ");
        String infix = scanner.nextLine();

        InfixConverter converter = new InfixConverter();
        String postfix = converter.toPostfix(infix);

        System.out.println("Postfix Expression: " + postfix);

        ExpressionTree tree = new ExpressionTree();
        Node root = tree.buildTree(postfix);

        String prefix = tree.getPrefix(root);
        System.out.println("Prefix Expression: " + prefix);

        // String postfixFromTree = tree.getPostfix(root);
        // System.out.println("Postfix from Tree: " + postfixFromTree);

        double result = tree.evaluate(root);
        System.out.println("Evaluation Result: " + result);

        scanner.close();
    }
}