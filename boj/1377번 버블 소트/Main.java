import java.util.*;
import java.io.*;

class Number implements Comparable<Number> {
    Integer origin;
    Integer index;

    public Number(Integer origin, Integer index) {
        this.origin = origin;
        this.index = index;
    }

    @Override
    public int compareTo(Number o) {
        return this.origin - o.origin;
    }
}

public class Main {

    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Number[] A = new Number[n];

        for(int i=0; i<n; i++) {
            Number number = new Number(Integer.parseInt(br.readLine()),(Integer)i);
            A[i] = number;
        }
        Arrays.sort(A);

        int max = 0;

        for (int i=0; i<n; i++){
            if (A[i].index -i > max)
                max = A[i].index -i;
        }

        System.out.println(max +1);

    }
    
    public static void main(String[] args) throws Exception {
        Main.solution();
    }
}
