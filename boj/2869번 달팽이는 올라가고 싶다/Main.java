import java.io.*;
import java.util.*;

public class Main {
    
    public static void solution() throws Exception {
        Scanner sc = new Scanner(System.in);

        int A = sc.nextInt();
        int B = sc.nextInt();
        int V = sc.nextInt();

        int day = 0;

        day = (V-B)/(A-B);

        if ((V-B)%(A-B)>0)
            day++;

        System.out.println(day);
    }

    public static void main(String[] args) throws Exception {
        Main.solution();
    }

}
