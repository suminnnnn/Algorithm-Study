import java.util.*;
import java.io.*;

public class Main {

    static ArrayList<Integer>[] A;
    static int[] check;
    static boolean[] visited;
    static boolean IsEven;
    
    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(br.readLine());

        for(int a=0; a<K; a++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            A = new ArrayList[V+1];
            check = new int[V+1];
            visited = new boolean[V+1];
            IsEven = true;

            for(int i=1; i<=V; i++){
                A[i] = new ArrayList<Integer>();
            }

            for(int i=0; i<E; i++){
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                A[u].add(v);
                A[v].add(u);
            }

            for(int i=1; i<V; i++){
                if (IsEven)
                    DFS(i);
                else
                    break;
            }

            if(IsEven)
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }

    private static void DFS(int start) {
        visited[start] = true;

        for(int i:A[start]){
            if(!visited[i]){
                check[i] = (check[start] + 1)%2;
                DFS(i);
            } 

            else if (check[start]==check[i])
                IsEven = false;
        }
    }

    public static void main(String[] args) throws Exception {
        Main.solution();
    }
}
