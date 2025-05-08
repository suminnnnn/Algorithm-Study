import java.io.*;
import java.util.*;

class Main {

    static int N;
    static int root;
    static int deletedNode;
    static int answer;
    static ArrayList<Integer> tree[];
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        tree = new ArrayList[N];

        visited = new boolean[N];

        for(int i=0; i<N; i++){
            tree[i] = new ArrayList<Integer>();
        }

        st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++){
            int parent = Integer.parseInt(st.nextToken());

            if(parent!=-1){
                tree[i].add(parent);
                tree[parent].add(i);
            } else {
                root = i;
            }
        }

        deletedNode = Integer.parseInt(br.readLine());

        if(deletedNode == root)
            System.out.println(0);
        else{
            DFS(root);
            System.out.println(answer);
        }


    }

    static void DFS(int start){
        visited[start] = true;
        int cNode = 0;

        for(int i : tree[start]){
            if(visited[i]==false && i!=deletedNode){
                cNode++;
                DFS(i);
            }
        }

        if(cNode==0)
            answer++;
    }
}