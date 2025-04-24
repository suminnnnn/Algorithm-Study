import java.io.*;
import java.util.*;

class Main{

    static int n,m;
    static ArrayList<ArrayList<Integer>> A;
    static int[] indegree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        A = new ArrayList<>();

        for(int i=0; i<=n; i++){
            A.add(new ArrayList<Integer>());
        }

        indegree = new int[n+1];

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            A.get(a).add(b);

            indegree[b]++;
        }

        Queue<Integer> queue = new LinkedList<>();

        for(int i=1; i<=n; i++){
            if(indegree[i]==0){
                queue.add(i);
            }
        }

        StringBuffer sb = new StringBuffer();

        while(!queue.isEmpty()){
            int now = queue.poll();

            sb.append(now);
            sb.append(" ");

            for(int next:A.get(now)){
                indegree[next]--;

                if(indegree[next]==0){
                    queue.add(next);
                }
            }
        }

        System.out.println(sb.toString().trim());
    }
}