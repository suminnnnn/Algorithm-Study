import java.io.*;
import java.util.*;

class Main {

    static int n, m, start, end, chonsu, answer;
    static ArrayList<Integer>[] A;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        A = new ArrayList[n+1];
        for(int i=0; i<=n; i++){
            A[i] = new ArrayList<Integer>();
        }

        visited = new boolean[n+1];

        m = Integer.parseInt(br.readLine());

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            A[x].add(y);
            A[y].add(x);
        }

        chonsu = -1;
        answer = -1;

        DFS(end);

        System.out.println(answer);
    }

    public static void DFS(int x) {
        visited[x] = true;
        chonsu++;
        //System.out.println("DFS IN in "+x);

        if(x==start)
            answer = chonsu;

        for(int i : A[x]) {
            if(!visited[i]){
                DFS(i);
            }

        }

        chonsu--;
    }
}