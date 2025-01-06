import java.util.*;

public class Main {
    
    public static void solution() throws Exception {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        Queue<Integer> queue = new LinkedList<>();

        for(int i=1; i<=n; i++){
            queue.add(i);
        } 

        while(queue.size()>1){
            queue.poll();
            
            int top = queue.poll();
            queue.add(top);
        }

        System.out.println(queue.peek());
    }

    public static void main(String[] args) throws Exception {
        Main.solution();
    }
}
