import java.util.*;
import java.io.*;

class Edge{
    int v;
    int w;

    public Edge(int v, int w){
        this.v = v;
        this.w = w;
    }
}

class Main{

    static int V,E;
    static ArrayList<Edge>[] A;
    static PriorityQueue<Edge> D;
    static boolean[] visited;
    static int[] dist;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        int k = Integer.parseInt(br.readLine());

        D = new PriorityQueue<Edge>((o1, o2) -> Integer.compare(o1.w, o2.w));
        visited = new boolean[V+1];
        dist = new int[V+1];

        A = new ArrayList[V+1];

        for(int i=0; i<=V; i++){
            A[i] = new ArrayList<Edge>();
            if(i==k){
                dist[i] = 0;
            }else{
                dist[i] = INF;
            }
        }

        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());

            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            A[e].add(new Edge(v,w));
        }

        D.add(new Edge(k,0));

        while (!D.isEmpty()){
            Edge now = D.poll();

            if(visited[now.v]) continue;
            visited[now.v] = true;

            for(Edge edge : A[now.v]){
                if(dist[edge.v] > dist[now.v] + edge.w){
                    dist[edge.v] = dist[now.v] + edge.w;
                    D.offer(new Edge(edge.v, dist[edge.v]));
                }
            }

        }

        for(int i=1; i<=V; i++){
            if(dist[i]==INF){
                System.out.println("INF");
            }else{
                System.out.println(dist[i]);
            }
        }
    }
}