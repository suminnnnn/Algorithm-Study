import java.io.*;
import java.util.*;

class Edge {
    public int v;
    public int w;

    Edge(int v, int w){
        this.v = v;
        this.w = w;
    }
}

class Main {

    static int N,M,X;

    static int[][] dir;
    static ArrayList<Edge>[] A;
    static PriorityQueue<Edge> pq;
    static boolean[] visited;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        A = new ArrayList[N+1];
        dir = new int[N+1][N+1];

        for(int i=0; i<=N; i++){
            Arrays.fill(dir[i], INF);
            A[i] = new ArrayList<Edge>();
        }

        for(int i=0; i<=N; i++) dir[i][i]=0;

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            A[u].add(new Edge(v,w));
        }

        for(int i=1; i<=N; i++){
            pq = new PriorityQueue<>((o1,o2) -> Integer.compare(o1.w, o2.w));
            visited = new boolean[N+1];

            pq.add(new Edge(i, 0));

            while(!pq.isEmpty()){
                Edge now = pq.poll();

                if(visited[now.v]) continue;
                visited[now.v] = true;

                for(Edge next : A[now.v]){
                    if(dir[i][next.v] > dir[i][now.v] + next.w){
                        dir[i][next.v] = dir[i][now.v] + next.w;
                        pq.add(new Edge(next.v, dir[i][next.v]));
                    }
                }
            }


        }

        int time = 0;
        int answer = -1;

        for(int i=1; i<=N; i++){
            time = dir[i][X] +dir[X][i];

            if(time>answer)
                answer = time;
        }

        System.out.println(answer);
    }
}