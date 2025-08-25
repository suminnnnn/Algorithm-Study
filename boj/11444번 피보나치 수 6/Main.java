import java.io.*;
import java.util.*;

public class Main {

    static final long MOD = 1_000_000_007L;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());

        long[][] result = pow(new long[][] {
                {1, 1},
                {1, 0}
        }, n);

        System.out.println(result[0][1]);
    }

    private static long[][] pow(long[][] matrix, long exp) {
        if (exp == 1L) return matrix;

        long[][] half = pow(matrix, exp / 2);
        long[][] squared = multiply(half, half);

        if (exp % 2 == 1) {
            return multiply(squared, matrix);
        } else {
            return squared;
        }
    }

    private static long[][] multiply(long[][] a, long[][] b) {
        long[][] result = new long[2][2];

        result[0][0] = (a[0][0]*b[0][0] + a[0][1]*b[1][0]) % MOD;
        result[0][1] = (a[0][0]*b[0][1] + a[0][1]*b[1][1]) % MOD;
        result[1][0] = (a[1][0]*b[0][0] + a[1][1]*b[1][0]) % MOD;
        result[1][1] = (a[1][0]*b[0][1] + a[1][1]*b[1][1]) % MOD;

        return result;
    }
}