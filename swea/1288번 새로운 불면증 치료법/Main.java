import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());

            if (N == 0) {
                System.out.println("#" + tc + " INSOMNIA");
                continue;
            }

            boolean[] seen = new boolean[10];
            int count = 0;
            int seenCount = 0;
            int current = 0;

            while (seenCount < 10) {
                count++;
                current = N * count;
                int temp = current;

                while (temp > 0) {
                    int digit = temp % 10;
                    if (!seen[digit]) {
                        seen[digit] = true;
                        seenCount++;
                    }
                    temp /= 10;
                }
            }

            System.out.println("#" + tc + " " + current);
        }
    }
}
