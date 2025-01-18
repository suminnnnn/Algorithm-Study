import java.util.*;
import java.io.*;

public class Main {
    
    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();

        for(int i=0; i<N; i++){
            int x = Integer.parseInt(br.readLine());

            if(x==0){
                if(queue.isEmpty())
                    System.out.println(0);
                else {
                    System.out.println(queue.poll());
                }
            } else {
                queue.offer(x);
            }
        }
    }

    public static void main(String[] args) throws Exception{
        Main.solution();
    }
    
}
