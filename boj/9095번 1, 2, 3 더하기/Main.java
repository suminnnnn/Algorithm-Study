import java.util.*;
import java.io.*;

public class Main {

    public static void solution() throws Exception {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        int D[]= new int[11];

        D[1] = 1;
        D[2] = 2;
        D[3] = 4;

        for(int i=0; i<T; i++){
            int n = sc.nextInt();
            
            for(int j=4; j<=n; j++){
                D[j] = D[j-1] + D[j-2] + D[j-3];
            }
            System.out.println(D[n]);
        }
    }
    public static void main(String[] args) throws Exception {
        Main.solution();
    }
}
