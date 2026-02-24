import java.io.*;
import java.util.*;

class Main {
    public static int N, M;
    public static int[] P;
    public static int[] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());

        P = new int[M];

        st = new StringTokenizer(br.readLine());

        Queue<Integer> queue = new LinkedList<>();
        dist = new int[N+1];

        for(int i=0; i<=N; i++){
            dist[i] = -1;
        }

        for(int i=0; i<M; i++) {
            int pi = Integer.parseInt(st.nextToken());
            dist[pi] = 0;
            queue.add(pi);
        }

        int maxBit = 0;

        while((1<<maxBit) <= N) maxBit++;

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for(int j=0; j<maxBit; j++) {
                int next = now ^ (1<<j);

                if(next < 0 || next > N) continue;

                if(dist[next]!=-1) continue;

                dist[next] = dist[now] +1;
                queue.add(next);
            }
        }

        int ans = 0;
        for (int i=0; i<=N; i++) {
            ans = Math.max(ans, dist[i]);
        }

        System.out.println(ans);

    }
}