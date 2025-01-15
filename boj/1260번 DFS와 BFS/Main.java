import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int V;
    static ArrayList<Integer>[] A;
    static boolean[] visitedDFS;
    static boolean[] visitedBFS;
    static StringBuffer resultDFS;
    static StringBuffer resultBFS;
    
    @SuppressWarnings("unchecked")
    public static void solution() throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        A = new ArrayList[N+1];
        visitedDFS = new boolean[N+1];
        visitedBFS = new boolean[N+1];

        resultDFS = new StringBuffer();
        resultBFS = new StringBuffer();

        for (int i=1; i<N+1; i++){
            A[i] = new ArrayList<>();
            visitedDFS[i] = false;
            visitedBFS[i] = false;
        }

        for (int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            A[a].add(b);
            A[b].add(a);
        }

        for (int i=1; i<=N; i++) {
            Collections.sort(A[i]);
        }

        DFS(V);
        BFS(V);

        System.out.println(resultDFS.toString().trim());
        System.out.println(resultBFS.toString().trim());
        
    }

    private static void DFS(int start) {
        resultDFS.append(start);
        resultDFS.append(" ");

        visitedDFS[start] = true;

        for(int i:A[start])
            if (!visitedDFS[i])
                DFS(i);
        
    }

    private static void BFS(int start) {
        Queue<Integer> queue = new LinkedList<Integer>();

        queue.add(start);
        visitedBFS[start] = true;

        while(!queue.isEmpty()) {
            int new_Node = queue.poll();

            resultBFS.append(new_Node);
            resultBFS.append(" ");

            for (int i: A[new_Node]){
                if(!visitedBFS[i]){
                    visitedBFS[i]=true;
                    queue.add(i);
                }
            }
        }

    }

    public static void main(String[] args) throws Exception {
        Main.solution();
    }
}
