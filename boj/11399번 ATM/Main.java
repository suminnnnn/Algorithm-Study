import java.util.*;
import java.io.*;

public class Main {
    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int A[] = new int[n];

        st = new StringTokenizer(br.readLine());

        for(int i=0; i<n; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(A);

        int result[] = new int[n];
        result[0] = A[0];

        int out = A[0];

        for(int i=1; i<n; i++){
            result[i] = result[i-1] + A[i];
            out += result[i];
        }

        System.out.println(out);
    }

    public static void main(String[] args) throws Exception {
        Main.solution();
    }
}
