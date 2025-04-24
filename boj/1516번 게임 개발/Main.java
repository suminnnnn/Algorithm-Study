import java.io.*;
import java.util.*;

class Main{

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Integer>> A = new ArrayList<>();

        for(int i=0; i<=n; i++){
            A.add(new ArrayList<Integer>());
        }

        int[] indegree = new int[n+1];
        int[] time = new int[n+1];
        int[] result = new int[n+1];

        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());

            int input = Integer.parseInt(st.nextToken());

            time[i] = input;

            while(true){
                input = Integer.parseInt(st.nextToken());

                if(input==-1)
                    break;

                A.get(input).add(i);
                indegree[i]++;
            }
        }

        Queue<Integer> queue = new LinkedList<Integer>();

        for(int i=1; i<=n; i++){
            if(indegree[i]==0){
                queue.offer(i);
            }
        }

        while(!queue.isEmpty()){
            int now = queue.poll();

            for(Integer next: A.get(now)){

                indegree[next]--;

                result[next] = Math.max(result[next], result[now] + time[now]);

                if(indegree[next]==0){
                    queue.offer(next);
                }
            }
        }

        StringBuffer sb = new StringBuffer();

        for(int i=1; i<=n; i++){
            System.out.println(result[i]+time[i]);
        }
    }
}