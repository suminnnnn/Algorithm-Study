import java.util.*;
import java.io.*;
    
    public class Main {
    
        static int N;
        static int M;

        static int max;
    
        static int[] count;
        static ArrayList<Integer>[] A;
    
        static List<Integer> answer;
    
        static boolean[] visited;
    
        public static void solution() throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            StringTokenizer st = new StringTokenizer(br.readLine());
    
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            max = -1;
    
            A = new ArrayList[N+1];
            answer = new ArrayList<>();
            count = new int[N+1];
            visited = new boolean[N+1];
    
            for(int i=1; i<=N; i++){
                A[i] = new ArrayList<Integer>();
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            A[b].add(a);
        }

        for(int i=1; i<=N; i++){
            for(int j=0; j<=N; j++){
                visited[j] = false;
            }

            BFS(i);
        }

        for(int i=1; i<=N; i++){
            if(count[i]>=max && i<N){
                System.out.print(i +" ");
            } else if (count[i]>=max && i==N) {
                System.out.print(i);
            }
        }

        
    }

    private static void BFS(int node){
        Queue<Integer> queue = new LinkedList<>();

        queue.add(node);
        count[node] += 1;
        visited[node] = true;

        while(!queue.isEmpty()){
            int newNode = queue.poll();

            for(int i:A[newNode]){
                if(visited[i]==false){
                    visited[i] = true;
                    count[node] += 1;
                    queue.add(i);
                }
            }
        }

        int cnt = count[node];

        if(cnt > max){
            max = cnt;
        }
        
    }

    public static void main(String[] args) throws Exception {
        Main.solution();
    }
}
