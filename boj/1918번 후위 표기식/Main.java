import java.io.*;
import java.util.*;

class Main {

    static int priority(String op) {
        if (op.equals("+") || op.equals("-")) return 1;
        if (op.equals("*") || op.equals("/")) return 2;
        return 0;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] operators = br.readLine().split("");

        StringBuffer sb = new StringBuffer();
        Stack<String> opr = new Stack<>();

        for (String operator : operators) {
            if (Character.isAlphabetic(operator.charAt(0))) {
                sb.append(operator);
            }

            else if (operator.equals("(")) {
                opr.push(operator);
            }

            else if (operator.equals(")")) {
                while (!opr.isEmpty() && !opr.peek().equals("(")) {
                    sb.append(opr.pop());
                }
                if (!opr.isEmpty()) opr.pop();
            }
            else {
                while (!opr.isEmpty() && priority(opr.peek()) >= priority(operator)) {
                    sb.append(opr.pop());
                }
                opr.push(operator);
            }
        }

        while (!opr.isEmpty()) {
            sb.append(opr.pop());
        }

        System.out.println(sb.toString());
    }
}
