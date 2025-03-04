import java.util.*;
import java.io.*;

public class Main {

    static int visited[];
    static ArrayList<Integer>[] A;
    static int N, M, K, X;
    static List<Integer> answer;

    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        A = new ArrayList[N+1];
        answer = new ArrayList<>();
        
        for(int i=1; i<=N; i++){
            A[i] = new ArrayList<Integer>();
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            A[S].add(E);
        }

        visited = new int[N+1];

        for(int i=0; i<=N; i++){
            visited[i] = -1;
        }

        BFS(X);

        for(int i=0; i<=N; i++){
            if(visited[i] == K) {
                answer.add(i);
            }
        }

        if(answer.isEmpty()){
            System.out.println("-1");
        } else {
            Collections.sort(answer);

            for(int tmp:answer){
                System.out.println(tmp);
            }
        }
    }

    private static void BFS(int start){
        Queue<Integer> queue = new LinkedList<Integer>();

        queue.add(start);

        visited[start] += 1;

        while(!queue.isEmpty()){
            int node = queue.poll();

            for(int i:A[node]){
                if(visited[i]==-1){
                    visited[i] = visited[node] + 1;
                    queue.add(i);
                }
            }
        }
    }


    public static void main(String[] args) throws Exception {
        Main.solution();
    }
}
