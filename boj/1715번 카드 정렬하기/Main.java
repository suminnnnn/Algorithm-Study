import java.util.*;
import java.io.*;

public class Main {
    
    public static void solution() throws Exception {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();

        for(int i=0; i<N; i++){
            queue.add(sc.nextInt());
        }

        int data1 = 0;
        int data2 = 0;
        int sum = 0;

        while(queue.size()!=1){
            data1 = queue.remove();
            data2 = queue.remove();

            sum += data1 + data2;
            queue.add(data1 + data2);
        }

        System.out.println(sum);
    }

    public static void main(String[] args) throws Exception {
        Main.solution();
    }
}
