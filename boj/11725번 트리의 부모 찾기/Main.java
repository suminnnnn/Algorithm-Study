import java.io.*;
import java.util.*;

class Main {

    static int N;
    static ArrayList<Integer> A[];
    static int[] parent;
    static boolean[] visited;

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        A = new ArrayList[N+1];
        parent = new int[N+1];
        visited = new boolean[N+1];

        for(int i=0; i<=N; i++){
            A[i] = new ArrayList<Integer>();
        }

        for(int i=1; i<N; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            A[a].add(b);
            A[b].add(a);
        }

        Queue<Integer> queue = new LinkedList<>();

        queue.add(1);

        while(!queue.isEmpty()){
            int now = queue.poll();

            visited[now] = true;

            for(int kid : A[now]){
                if(visited[kid]==false){
                    visited[kid] = true;
                    parent[kid] = now;
                    queue.add(kid);
                }
            }
        }

        for(int i=2; i<=N; i++){
            System.out.println(parent[i]);
        }



    }
}