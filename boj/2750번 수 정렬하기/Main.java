import java.util.*;
import java.io.*;

public class Main {

    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] A = new int[n];

        for(int i=0; i<n; i++){
            A[i] = Integer.parseInt(br.readLine());
        }

        for(int i=0; i<(n-1); i++){
            for(int j=0; j<(n-1-i); j++){
                if(A[j]>A[j+1]) {
                    int temp = A[j];
                    A[j] = A[j+1];
                    A[j+1] = temp;
                }
            }

        }

        for(int i=0; i<n; i++){
            System.out.println(A[i]);
        }
    }
    
    public static void main(String[] args) throws Exception {
        Main.solution();
    }
}
