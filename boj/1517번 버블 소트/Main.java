import java.util.*;
import java.io.*;

public class Main {

    public static int N;
    public static int[] A, tmp;
    public static long result=0;
    
    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        A = new int[N+1];
        tmp = new int[N+1];

        for(int i=1; i<=N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        merge_sort(1, N);

        System.out.println(result);
    }

    private static void merge_sort(int s, int e){
        if (e-s <1)
            return;
        
        int m = s + (e-s)/2;

        merge_sort(s, m);
        merge_sort(m+1, e);

        for(int i=s; i<=e; i++){
            tmp[i] = A[i];
        }

        int k=s;
        int index1 = s;
        int index2 = m+1;

        while(index1<=m && index2 <=e){
            if(tmp[index1]>tmp[index2]){

                result += m-index1+1;
                A[k] = tmp[index2];
                k++;
                index2++;
            } else {
                A[k] = tmp[index1];
                k++;
                index1++;
            }
        }

        while(index1<=m){
            A[k] = tmp[index1];
            k++;
            index1++;
        }
        
        while(index2<=e) {
            A[k] = tmp[index2];
            k++;
            index2++;
        }
    }

    public static void main(String[] args) throws Exception {
        Main.solution();
    }
}
