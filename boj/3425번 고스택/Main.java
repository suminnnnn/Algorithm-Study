import java.util.*;
import java.io.*;

public class Main {

    static Stack<Long> stack;
    static LinkedList<String[]> cmdQueue;
    static final int MAX_VALUE = 1000000000;

    private static int run(LinkedList<String[]> commands) {
        int commandsLength = commands.size();

        for(int i=0; i<commandsLength; i++) {
            String cmd = commands.get(i)[0];

            try {
                if(commands.get(i).length==2 && cmd.equals("NUM")){
                    stack.add(Long.parseLong(commands.get(i)[1]));
                } else if(cmd.equals("POP")){
                    if(stack.isEmpty()) return 100;
                    stack.pop();
                } else if(cmd.equals("INV")){
                    if(stack.isEmpty()) return 100;
                    long origin = stack.pop();
                    stack.push(-origin);
                } else if(cmd.equals("DUP")){
                    if(stack.isEmpty()) return 100;
                    stack.push(stack.peek());
                } else if(cmd.equals("SWP")){
                    if(stack.size()<2) return 100;
                    long first = stack.pop();
                    long second = stack.pop();
                    stack.push(first);
                    stack.push(second);
                } else if(cmd.equals("ADD")){
                    if(stack.size()<2) return 100;
                    long first = stack.pop();
                    long second = stack.pop();
                    long ans = first + second;
                    if(Math.abs(ans) > MAX_VALUE) return 100;
                    stack.push(ans);
                } else if(cmd.equals("SUB")){
                    if(stack.size()<2) return 100;
                    long first = stack.pop();
                    long second = stack.pop();
                    long ans = second - first;
                    if(Math.abs(ans) > MAX_VALUE) return 100;
                    stack.push(ans);
                } else if(cmd.equals("MUL")){
                    if(stack.size()<2) return 100;
                    long first = stack.pop();
                    long second = stack.pop();
                    long ans = second * first;
                    if(Math.abs(ans) > MAX_VALUE) return 100;
                    stack.push(ans);
                } else if(cmd.equals("DIV")){
                    if(stack.size()<2) return 100;
                    long first = stack.pop();
                    long second = stack.pop();
                    if(first == 0) return 100;

                    long sign = ((first < 0) ^ (second < 0)) ? -1 : 1;
                    long ans = Math.abs(second) / Math.abs(first) * sign;

                    stack.push(ans);
                } else if(cmd.equals("MOD")){
                    if(stack.size()<2) return 100;
                    long first = stack.pop();
                    long second = stack.pop();
                    if(first == 0) return 100;

                    long ans = Math.abs(second) % Math.abs(first);
                    if (second < 0) ans = -ans;

                    stack.push(ans);
                } else {
                }
            } catch (Exception e) {
                return 100;
            }
        }

        return stack.size() == 1 ? 1 : 100;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            cmdQueue = new LinkedList<>();

            while (true) {
                String line = br.readLine();
                String[] command = line.split(" ");

                if (command[0].equals("QUIT")) return;

                if (command[0].equals("END")) break;

                cmdQueue.add(command);
            }

            int n = Integer.parseInt(br.readLine());

            for (int i = 0; i < n; i++) {
                stack = new Stack<>();
                long num = Long.parseLong(br.readLine());
                stack.push(num);

                int result = run(cmdQueue);

                if (result != 1) System.out.println("ERROR");
                else System.out.println(stack.pop());
            }

            System.out.println();
        }
    }
}
