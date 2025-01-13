import java.util.*;
import java.io.*;

public class Main {

    public static int[] A;
    public static int N;

    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        A = new int[N];

        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }

        radix_sort(A);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(A[i]).append("\n");
        }
        System.out.print(sb);
    }

    private static void radix_sort(int[] A) {
        int max = Arrays.stream(A).max().getAsInt();
        int exp = 1;

        int[] output = new int[N];

        while (max / exp > 0) {
            int[] count = new int[10];

            for (int i = 0; i < N; i++) {
                count[(A[i] / exp) % 10]++;
            }

            for (int i = 1; i < 10; i++) {
                count[i] += count[i - 1];
            }

            for (int i = N - 1; i >= 0; i--) {
                int digit = (A[i] / exp) % 10;
                output[count[digit] - 1] = A[i];
                count[digit]--;
            }

            System.arraycopy(output, 0, A, 0, N);

            exp *= 10;
        }
    }

    public static void main(String[] args) throws Exception {
        Main.solution();
    }
}
