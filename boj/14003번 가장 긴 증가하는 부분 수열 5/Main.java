import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] A, dp, prev;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        A = new int[N];
        dp = new int[N];
        prev = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Integer> lis = new ArrayList<>();
        ArrayList<Integer> indices = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int idx = Collections.binarySearch(lis, A[i]);
            if (idx < 0) idx = -idx - 1;

            if (idx == lis.size()) {
                lis.add(A[i]);
            } else {
                lis.set(idx, A[i]);
            }

            dp[i] = idx;
        }

        int len = lis.size();
        int target = len - 1;
        Stack<Integer> answer = new Stack<>();

        for (int i = N - 1; i >= 0; i--) {
            if (dp[i] == target) {
                answer.push(A[i]);
                target--;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(len).append('\n');
        while (!answer.isEmpty()) {
            sb.append(answer.pop()).append(' ');
        }
        System.out.println(sb);
    }
}
