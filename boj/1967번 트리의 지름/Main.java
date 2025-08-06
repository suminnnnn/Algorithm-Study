import java.io.*;
import java.util.*;

class Node {
    int e;
    int w;

    public Node(int e, int w){
        this.e = e;
        this.w = w;
    }
}

class Main {

    static int N;
    static ArrayList<Node>[] A;
    static boolean[] visited;
    static int maxDist = 0;
    static int farthestNode = 0;
    static StringTokenizer st;

    private static void DFS(int node, int sum){
        visited[node] = true;

        if(sum>maxDist){
            maxDist = sum;
            farthestNode = node;
        }

        for(Node next : A[node]){
            if(!visited[next.e]){
                DFS(next.e, sum+next.w);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        A = new ArrayList[N+1];
        visited = new boolean[N+1];

        for(int i = 1; i<=N; i++){
            A[i] = new ArrayList<Node>();
        }

        for(int i = 0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            A[s].add(new Node(e,w));
            A[e].add(new Node(s,w));
        }

        DFS(1,0);

        Arrays.fill(visited, false);
        maxDist = 0
        DFS(farthestNode, 0);

        System.out.println(maxDist);
    }
}