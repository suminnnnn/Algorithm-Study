import java.util.*;
import java.io.*;

public class Main {
    
    public static void solution() throws Exception {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int A[] = new int[n];

        for(int i=0; i<n; i++){
            A[i] = sc.nextInt();
        }

        int L[] = new int[n];
        int R[] = new int[n];

        L[0] = A[0];
        int result = L[0];

        for(int i=1; i<n; i++){
            L[i] = Math.max(L[i-1]+A[i], A[i]);
            result = Math.max(result, L[i]);
        }

        R[n-1] = A[n-1];
        for(int i=(n-2); i>=0; i--){
            R[i] = Math.max(A[i], R[i+1]+A[i]);
        }

        for(int i=1; i<n; i++) {
            int temp = L[i-1] + R[i+1];
            result = Math.max(result, temp);
        }
        System.out.println(result);
    }

    public static void main(String[] args) throws Exception {
        Main.solution();
    }
}
