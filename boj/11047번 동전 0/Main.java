import java.util.*;
import java.io.*;

public class Main {

    public static void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] A = new int[N];
        int count = 0;

        for(int i=(N-1); i>=0; i--){
            A[i] = Integer.parseInt(br.readLine());
        }

        for (int a: A){
            if(K==0){
                break;
            }
            if(K/a >0){
                count += K/a;
                K = K%a;
            }
        }        

        System.out.println(count);
    }

    public static void main(String[] args) throws Exception {
        Main.solution();
    }
    
}
