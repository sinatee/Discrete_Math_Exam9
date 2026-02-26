# Discrete_Math_Exam9
Tree calculator
```mermaid
classDiagram
    class Node {
        -String value
        -Node left
        -Node right
        +Node(String value)
        +getValue() String
        +setValue(String value)
        +getLeft() Node
        +setLeft(Node left)
        +getRight() Node
        +setRight(Node right)
    }

    class MyStack {
        -Node top
        -int size
        +push(Node node)
        +pop() Node
        +peek() Node
        +isEmpty() boolean
        displayStack() void
    }

    class InfixConverter {
        +toPostfix(String infix) String
        -getPriority(String op) int
        -isOperator(String c) boolean
    }

    class ExpressionTree {
        -Node root
        +buildTree(String postfix) Node
        +getPrefix(Node node) String
        +getPostfix(Node node) String
        +evaluate(Node node) double
        +getRoot() Node
    }

    class EXAM9 {
        +main(String[] args)
    }

    %%Relationship
    EXAM9 ..> InfixConverter : "Calls to convert"
    EXAM9 ..> ExpressionTree : "Calls to build/evaluate"
    InfixConverter ..> MyStack : "Uses Stack for logic"
    ExpressionTree ..> MyStack : "Uses stack to build tree"
    ExpressionTree o-- Node 
    MyStack o-- Node 
