import java.io.*;
import java.util.*;

class Edge {
    int v;
    long w;

    public Edge(int v, long w) {
        this.v = v;
        this.w = w;
    }
}

class Wdge {
    int v;
    long w;
    int t;

    public Wdge(int v, long w, int t) {
        this.v = v;
        this.w = w;
        this.t = t;
    }
}

public class Main {

    static int N, M;
    static long[] dist;
    static long[][] wolfDist;
    static ArrayList<Edge>[] A;
    static final long INF = Long.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new ArrayList[N + 1];
        dist = new long[N + 1];
        wolfDist = new long[N + 1][2];

        for (int i = 0; i <= N; i++) {
            A[i] = new ArrayList<>();
            dist[i] = INF;
            wolfDist[i][0] = INF;
            wolfDist[i][1] = INF;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken()) * 2;

            A[a].add(new Edge(b, d));
            A[b].add(new Edge(a, d));
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1.w, o2.w));
        pq.add(new Edge(1, 0));
        dist[1] = 0;

        while (!pq.isEmpty()) {
            Edge now = pq.poll();

            if (dist[now.v] < now.w) continue;

            for (Edge next : A[now.v]) {
                if (dist[next.v] > dist[now.v] + next.w) {
                    dist[next.v] = dist[now.v] + next.w;
                    pq.offer(new Edge(next.v, dist[next.v]));
                }
            }
        }

        PriorityQueue<Wdge> wolfQ = new PriorityQueue<>((o1, o2) -> Long.compare(o1.w, o2.w));
        wolfQ.add(new Wdge(1, 0, 0));
        wolfDist[1][0] = 0;

        while (!wolfQ.isEmpty()) {
            Wdge now = wolfQ.poll();

            if (wolfDist[now.v][now.t] < now.w) continue;

            for (Edge next : A[now.v]) {
                if (now.t == 0) {
                    long newDist = now.w + next.w / 2;
                    if (wolfDist[next.v][1] > newDist) {
                        wolfDist[next.v][1] = newDist;
                        wolfQ.offer(new Wdge(next.v, newDist, 1));
                    }
                } else {
                    long newDist = now.w + next.w * 2;
                    if (wolfDist[next.v][0] > newDist) {
                        wolfDist[next.v][0] = newDist;
                        wolfQ.offer(new Wdge(next.v, newDist, 0));
                    }
                }
            }
        }

        int answer = 0;
        for (int i = 2; i <= N; i++) {
            if (dist[i] < Math.min(wolfDist[i][0], wolfDist[i][1])) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}