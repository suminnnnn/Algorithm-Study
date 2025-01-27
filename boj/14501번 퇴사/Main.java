import java.util.*;
import java.io.*;

public class Main {
    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int T[] = new int[N+1];
        int P[] = new int[N+1];
        int D[] = new int[N+2];

        for(int i=1; i<=N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        for (int i=N; i>0; i--){
            if(i +T[i]>N+1){
                D[i] = D[i+1];
            } else {
                D[i] = Math.max(D[i+1], D[i+T[i]] + P[i]);
            }
        }
        System.out.println(D[1]);

    }

    public static void main(String[] args) throws Exception{
        Main.solution();
    }
}
