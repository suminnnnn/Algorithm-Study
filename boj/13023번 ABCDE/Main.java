import java.util.*;
import java.io.*;

public class Main {

    public static ArrayList<Integer>[] A;
    public static Boolean visited[];
    public static int N;
    public static int count;
    public static int max;
    public static Boolean possible;

    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        int M = Integer.parseInt(st.nextToken());

        A = new ArrayList[N];
        visited = new Boolean[N];

        possible = false;

        for(int i=0; i<N; i++){
            A[i] = new ArrayList<Integer>();
            visited[i] = false;
        }

        count = 0;
        max = 0;

        for (int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            A[a].add(b);
            A[b].add(a);
        }

        for (int i=0; i<N; i++){
            if(!possible){
                DFS(i,0);
            }
        }

        System.out.println(possible==true?1:0);

    }

    private static void DFS(int start, int depth) {
        if (depth == 4) {
            possible = true;
            return;
        }

        visited[start] = true;

        for (int i : A[start]){
            if (!visited[i])
                DFS(i, depth+1);
        }
        
        visited[start] = false;
    }

    public static void main(String[] args) throws Exception {
        Main.solution();
    }
    
}
