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

class Main {

    static int N, M, K;
    static long[] dist;
    static PriorityQueue<Edge> pq;
    static ArrayList<Edge>[] A;
    static final long INF = Long.MAX_VALUE;
    static HashSet<Integer> kk;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new ArrayList[N+1];
        for(int i=0; i<=N; i++) {
            A[i] = new ArrayList<Edge>();
        }

        dist = new long[N+1];
        for(int i=0; i<=N; i++){
            dist[i] = INF;
        }

        for(int m=0; m<M; m++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            long w = Long.parseLong(st.nextToken());

            // 역방향 저장
            A[v].add(new Edge(u, w));
        }

        kk = new HashSet<Integer>();
        st = new StringTokenizer(br.readLine());

        pq = new PriorityQueue<Edge>((o1, o2) -> Long.compare(o1.w, o2.w));

        for(int k=0; k<K; k++){
            int interview = Integer.parseInt(st.nextToken());
            kk.add(interview);
            dist[interview] = 0;
            pq.add(new Edge(interview, 0));
        }

        while(!pq.isEmpty()) {
            Edge now = pq.poll();

            if(dist[now.v] < now.w)
                continue;

            for(Edge next : A[now.v]) {
                if(dist[next.v] > now.w + next.w) {
                    dist[next.v] = now.w + next.w;
                    pq.add(new Edge(next.v, dist[next.v]));
                }
            }
        }

        int answerNode = 1;
        long answerDist = dist[1];

        for(int i=2; i<=N; i++) {
            if(dist[i] > answerDist) {
                answerDist = dist[i];
                answerNode = i;
            }
        }

        System.out.println(answerNode);
        System.out.println(answerDist);
    }
}