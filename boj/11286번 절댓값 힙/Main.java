import java.io.*;
import java.util.*;

class Number implements Comparable<Number>{   
    int absolute;
    int origin;

    public Number(int absolute, int origin) {
        this.absolute = absolute;
        this.origin = origin;
    }   

    @Override
    public int compareTo(Number o) {
        if (this.absolute == o.absolute)
            return this.origin <= o.origin ? -1 : 1;
        else
            return this.absolute <= o.absolute ? -1 : 1;
    }
}

public class Main {

    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Number> queue = new PriorityQueue<>();

        for(int i=0; i<n; i++){
            int x = Integer.parseInt(br.readLine());

            if (x==0) {
                if(queue.isEmpty())
                    System.out.println(0);
                else {
                    System.out.println(queue.poll().origin);
                }

            } else {
                int absolute = x;
                if (x<0)
                    absolute = x *(-1);
                Number number = new Number(absolute, x);
                queue.offer(number);
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
        Main.solution();
    }
}
