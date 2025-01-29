import java.util.*;
import java.io.*;

public class Main {
    
    public static void solution() throws Exception {
        long[][] D = new long[1001][1001];

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        long max = 0;

        for(int i=0; i<n; i++) {
            String line = sc.next();
            for(int j=0; j<m; j++){
                D[i][j] = Long.parseLong(String.valueOf(line.charAt(j)));

                if(D[i][j] == 1 && i>0 &&j>0) {
                    D[i][j] = Math.min(D[i-1][j-1], Math.min(D[i-1][j], D[i][j-1])) + 1;
                }

                if (max < D[i][j])
                    max = D[i][j];
            }
        }

        System.out.println(max*max);
    }

    public static void main(String[] args) throws Exception{
        Main.solution();
    }
}
