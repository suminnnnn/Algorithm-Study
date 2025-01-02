import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    
    public static void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[N];

        for (int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, 0, N);

        int start = 0;
        int end = N-1;
        int count = 0;

        while (arr[start]<arr[end]){
            if ((arr[start] + arr[end]) < M) {
                start ++;
            } else if ((arr[start] + arr[end]) > M) {
                end --;
            } else {
                start ++;
                end --;
                count ++;
            }
        }

        System.out.println(count);
    }

    public static void main(String[] args) throws Exception{
        Main.solution();
    }
}
