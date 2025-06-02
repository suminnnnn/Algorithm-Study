import java.io.*;
import java.util.*;

class Edge {
    int v;
    int w;

    Edge(int v, int w){
        this.v = v;
        this.w = w;
    }
}

class Main {
    static int N, E;
    static ArrayList<Edge> list[];
    static int[][] distance;
    static boolean[][] visited;
    static PriorityQueue<Edge> priorityQueue;
    static final int INF = 200000000;

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        E = Integer.parseInt(stringTokenizer.nextToken());

        distance = new int[3][N+1];
        visited = new boolean[3][N+1];

        list = new ArrayList[N+1];

        for(int i = 1; i<=N; i++){
            list[i] = new ArrayList<Edge>();
        }

        for(int i = 0; i<E; i++){
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            int s = Integer.parseInt(stringTokenizer.nextToken());
            int v = Integer.parseInt(stringTokenizer.nextToken());
            int w = Integer.parseInt(stringTokenizer.nextToken());

            list[s].add(new Edge(v,w));
            list[v].add(new Edge(s,w));
        }

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int v1 = Integer.parseInt(stringTokenizer.nextToken());
        int v2 = Integer.parseInt(stringTokenizer.nextToken());

        int[] node = new int[]{1, v1, v2};

        for(int i = 0; i<3; i++){
            Arrays.fill(distance[i], INF);
            priorityQueue = new PriorityQueue<Edge>((o1, o2) -> Integer.compare(o1.w, o2.w));

            distance[i][node[i]] = 0;
            priorityQueue.add(new Edge(node[i], 0));

            while (!priorityQueue.isEmpty()){
                Edge now = priorityQueue.poll();

                if(visited[i][now.v]) continue;
                visited[i][now.v] = true;

                for(Edge next : list[now.v]){
                    if(distance[i][next.v] > distance[i][now.v] + next.w){
                        distance[i][next.v] = distance[i][now.v] + next.w;
                        priorityQueue.add(new Edge(next.v, distance[i][next.v]));
                    }
                }
            }
        }

        int s1 = distance[0][v1] + distance[1][v2] + distance[2][N];
        int s2 = distance[0][v2] + distance[2][v1] + distance[1][N];

        boolean checkS1 = false;
        boolean checkS2 = false;

        if(distance[0][v1]==INF || distance[1][v2]==INF || distance[2][N]==INF)
            checkS1 = true;
        if(distance[0][v2]==INF || distance[2][v1]==INF || distance[1][N]==INF)
            checkS2 = true;

        if(checkS1==true&&checkS2==true)
            System.out.println(-1);
        else if(s1<=s2)
            System.out.println(s1);
        else
            System.out.println(s2);
    }
}