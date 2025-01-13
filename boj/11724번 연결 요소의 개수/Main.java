import java.util.*;
import java.io.*;

public class Main {
    
    public static ArrayList<Integer>[] A;
    public static boolean visited[];

    public static void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        A = new ArrayList[N+1];
        visited = new boolean[N+1];

        for(int i=1; i<N+1; i++){
            A[i] = new ArrayList<Integer>();
        }

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            A[a].add(b);
            A[b].add(a);
        }

        int count = 0;

        for(int i=1; i<=N; i++){
            if(!visited[i]){
                count++;
                DFS(i);
            }
        }

        System.out.println(count);
    }

    static void DFS(int start){
        if (visited[start]){
            return;
        }

        visited[start] = true;

        for (int i : A[start]){
            if (!visited[i])
                DFS(i);
        }
    }

    public static void main(String[] args) throws Exception {
        Main.solution();
    }
}
