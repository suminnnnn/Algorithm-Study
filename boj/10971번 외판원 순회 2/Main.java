import java.io.*;
import java.util.*;

class Main{

    static int N;
    static int min;
    static int[][] W;
    static boolean[] visited;

    private static void dfs(int start, int now, int count, int cost){
        if(count==N){
            if(W[now][start]>0){
                min = Math.min(min, cost + W[now][start]);
            }
            return;
        }

        for(int i = 0; i<N; i++){
            if(!visited[i] && W[now][i]!=0){
                visited[i] = true;
                dfs(start, i, count+1, cost+W[now][i]);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        visited = new boolean[N];
        W = new int[N][N];
        min = Integer.MAX_VALUE;

        for(int i = 0; i<N; i++){
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            for(int j=0; j<N; j++){
                W[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        for(int i = 0; i<N; i++){
            visited[i] = true;
            dfs(i, i, 1, 0);
            visited[i] = false;
        }

        System.out.println(min);
    }
}