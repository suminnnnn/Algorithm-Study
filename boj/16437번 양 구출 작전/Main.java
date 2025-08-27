import java.io.*;
import java.util.*;

class Main{

    static int N;
    static ArrayList<Integer>[] A;
    static long[] count;
    static boolean[] visited;

    private static void dfs(int start, int depth){
        visited[start] = true;

        for(int next : A[start]){
            if(!visited[next]){
                dfs(next, start);
            }
        }

        if(depth!=-1){
            if(count[start]>0)
                count[depth] += count[start];
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        A = new ArrayList[N+1];
        for(int i = 0; i<=N; i++){
            A[i] = new ArrayList<Integer>();
        }

        count = new long[N+1];
        visited = new boolean[N+1];

        for(int i = 2; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            int cnt = Integer.parseInt(st.nextToken());
            int node = Integer.parseInt(st.nextToken());
            if(command.equals("S")){
                count[i] = cnt;
            } else{
                count[i] = (-1)*cnt;
            }

            A[i].add(node);
            A[node].add(i);
        }

        dfs(1, -1);

        System.out.println(count[1]);
    }
}