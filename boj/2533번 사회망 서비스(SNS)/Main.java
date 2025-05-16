import java.io.*;
import java.util.*;

class Main {

    static int N;
    static ArrayList<Integer> A[];
    static int[][] dp;
    static boolean[] visited;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        A = new ArrayList[N+1];
        dp = new int[N+1][2];
        visited = new boolean[N+1];

        for(int i=0; i<=N; i++){
            A[i] = new ArrayList<Integer>();
        }

        for(int i=1; i<N; i++){
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            A[u].add(v);
            A[v].add(u);
        }

        DFS(1,-1);

        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    public static void DFS(int start, int parent){
        visited[start] = true;
        dp[start][0] = 0;
        dp[start][1] = 1;

        for(int kid : A[start]){
            if(kid!=parent && !visited[kid]){
                DFS(kid, start);
                dp[start][0] += dp[kid][1];
                dp[start][1] += Math.min(dp[kid][0], dp[kid][1]);
            }
        }
    }
}