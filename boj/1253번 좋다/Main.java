import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        long[] arr = new long[N];

        for (int i=0; i<N; i++){
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);

        int count=0;

        for(int m=0; m<N; m++){
            long target = arr[m];
            int i=0;
            int j=N-1;
            
            while(i<j){
                if ((arr[i]+arr[j])>target){
                    j--;
                } else if ((arr[i]+arr[j])<target) {
                    i++;
                } else {
                    if (i!=m && j!=m){
                        count++;
                        break;
                    } else if (i==m){
                        i++;
                    } else if (j==m) {
                        j--;
                    }
                }
            }
        
        }
        System.out.println(count);
        br.close();
    }
}
