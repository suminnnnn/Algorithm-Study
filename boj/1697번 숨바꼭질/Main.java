import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int K;
    static int result;
    static int count;
    static boolean[] visited;
    
    public static void solution() throws Exception {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();

        visited = new boolean[100001];
        count =1;
        
        System.out.println(BFS(N));
    }

    private static int BFS(int start){

        int count = 0;

        Queue<Integer> queue = new LinkedList<Integer>();

        queue.add(start);
        visited[start] = true;
        
        while(!queue.isEmpty()){
            int size = queue.size();

            for(int k=0; k<size; k++){
                int newNode = queue.poll();

                if(newNode==K){
                    return count;
                }

                int o3 = (newNode-1);
                int o2 = (newNode+1);
                int o1 = 2*(newNode);

                int options[] = new int[]{o1, o2, o3};

                for (int j=0; j<3; j++){
                    if(options[j]>=0 && options[j]<=100000 && !visited[options[j]]){
                        visited[options[j]] = true;
                        queue.add(options[j]);
                    }
                }
            }
            count++;
        }
                return -1;
    }

    public static void main(String[] args) throws Exception{
        Main.solution();
    }
}
