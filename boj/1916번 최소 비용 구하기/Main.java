import java.io.*;
import java.util.*;

class Edge{
    int v;
    int w;

    Edge(int v, int w){
        this.v = v;
        this.w = w;
    }
}

class Main{

    static int N;
    static int M;

    static int[] dist;
    static boolean[] visited;
    static PriorityQueue<Edge> pq;
    static ArrayList<Edge>[] A;

    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());

        dist = new int[N+1];
        visited = new boolean[N+1];

        pq = new PriorityQueue<Edge>((o1,o2)->Integer.compare(o1.w, o2.w));

        A = new ArrayList[N+1];

        for(int i=0; i<=N; i++){
            A[i] = new ArrayList<Edge>();
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            A[u].add(new Edge(v,w));
        }

        st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        Arrays.fill(dist, INF);

        dist[start] = 0;
        pq.add(new Edge(start, 0));

        while(!pq.isEmpty()){
            Edge now = pq.poll();

            if(visited[now.v]) continue;

            visited[now.v] = true;

            for(Edge next : A[now.v]){
                if(dist[next.v] > dist[now.v] + next.w){
                    dist[next.v] = dist[now.v] + next.w;
                    pq.add(new Edge(next.v, dist[next.v]));
                }
            }
        }

        System.out.println(dist[end]);
    }
}