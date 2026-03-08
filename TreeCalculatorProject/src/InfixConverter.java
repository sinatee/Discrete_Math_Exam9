import java.util.StringTokenizer;

public class InfixConverter {


    public String toPostfix(String infixString) {
        String result = "";
        MyStack stack = new MyStack(); 
        StringTokenizer st = new StringTokenizer(infixString, "+-*/() ", true);

        while (st.hasMoreTokens()) {
            String token = st.nextToken().trim();
            if (token.isEmpty()) continue;
            
            if (isOperator(token)) {
                // หาตัวที่ต้องคำนวณก่อนมาใส่ postfix stพing ก่อนตัวที่ต้องคำนวณทีหลังก็เก็ยใส่ stack ไว้ก่อน
                while (!stack.isEmpty() && getPriority(stack.peek().getValue()) >= getPriority(token) && !stack.peek().getValue().equals("(")) {
                    result += stack.pop().getValue() + " ";
                }
                stack.push(new Node(token));
                System.out.print("After Push Operator: "); 
                stack.displayStack();
            }
            // วงเล็บเปิด ต้องคิดก่อน 
            else if (token.equals("(")) {
                stack.push(new Node(token));
            }
            // ต่อจากวงเล็บเปิด ต้องหาไปเรื่อยจนกว่าจะเจอวงเล็บปิด แล้วค่อยเพิ่มเข้าไปใน Postfix String
            else if (token.equals(")")) {
                while (!stack.isEmpty() && !stack.peek().getValue().equals("(")) {
                    result += stack.pop().getValue() + " ";
                }
                stack.pop();
            }
            else {
                // เป็นตัวเลข ให้ต่อท้ายผลลัพธ์ทันที
                result += token + " ";
            }
        }

        // สุดท้าย: ถ้ามีเครื่องหมายค้างใน Stack ให้เอาออกมาให้หมด
        while (!stack.isEmpty()) {
            result += stack.pop().getValue() + " ";
        }

        return result.trim();
    }
    
    // เช็คว่าเครื่องหมายไหนคิดก่อน
    private int getPriority(String operator) {
        if (operator.equals("+") || operator.equals("-")) return 1;
        if (operator.equals("*") || operator.equals("/")) return 2;
        return 0;
    }

    // เช็คว่าเป็นเคื่องหมาย
    private boolean isOperator(String c) {
        return c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/");
    }
}