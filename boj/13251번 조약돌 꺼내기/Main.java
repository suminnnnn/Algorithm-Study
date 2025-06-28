import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int M = sc.nextInt();
        int[] J = new int[M];
        int N = 0;

        for (int i = 0; i < M; i++) {
            J[i] = sc.nextInt();
            N += J[i];
        }

        int K = sc.nextInt();

        double totalComb = comb(N, K);
        double successComb = 0.0;

        for (int i = 0; i < M; i++) {
            if (J[i] >= K) {
                successComb += comb(J[i], K);
            }
        }

        System.out.println(successComb / totalComb);
    }

    static double comb(int n, int r) {
        if (r > n) return 0.0;
        if (r == 0 || r == n) return 1.0;

        double result = 1.0;
        for (int i = 1; i <= r; i++) {
            result *= (double)(n - i + 1) / i;
        }
        return result;
    }
}
