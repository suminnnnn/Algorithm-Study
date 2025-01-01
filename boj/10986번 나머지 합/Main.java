import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    
    public static void solution() throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        long[] D = new long[N];
        long[] R = new long[M];
        long result = 0;

        D[0] = Integer.parseInt(st.nextToken());

        for(int i=1; i<N; i++) {
            D[i] = D[i-1] + Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<N; i++) {
            D[i] = (int)(D[i]%M);
            
            if(D[i]==0)
                result += 1;
            R[(int)D[i]] += 1;
        }

        for (int i=0; i<M; i++) {
            if (R[i]>=2) {
                result += (R[i] * (R[i]-1)/2);
            }
        }

        System.out.println(result);

    }

    public static void main(String[] args) throws Exception{
        Main.solution();
    }
    
}
