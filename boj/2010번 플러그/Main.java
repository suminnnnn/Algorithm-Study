import java.util.*;
import java.io.*;

public class Main {

    public static void solution() throws Exception {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int result = 0;

        for(int i=0; i<N-1; i++){
            result += (sc.nextInt() -1);
        }

        result += sc.nextInt();

        System.out.println(result);
    }

    public static void main(String[] args) throws Exception{
        Main.solution();
    }
}
