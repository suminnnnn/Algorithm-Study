import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[][] A;
    static ArrayList<Integer> result;
    static boolean[][] visited;
    static int[] dx = new int[]{-1, 0, 1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};
    
    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N][N];
        A = new int[N][N];
        result = new ArrayList<>();

        for(int i=0; i<N; i++){
            String line = br.readLine();

            for(int j=0; j<line.length(); j++){
                A[i][j] = Integer.parseInt(line.substring(j, j+1));
            }
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(visited[i][j]==false && A[i][j]!=0)
                    BFS(i,j);
            }
        }
        
        Collections.sort(result);

        System.out.println(result.size());
        for(int i:result)
            System.out.println(i);
    }

    private static void BFS(int i, int j){
        Queue<int[]> queue = new LinkedList<int[]>();
        int cnt = 1;
        
        queue.offer(new int[]{i,j});

        visited[i][j] = true;

        while(!queue.isEmpty()){
            
            int[] now = queue.poll();

            for(int k=0; k<4; k++){
                int x = now[0] + dx[k];
                int y = now[1] + dy[k];

                if(x>=0 && y>=0 && x<N && y<N){
                    if(visited[x][y]==false && A[x][y]!=0){
                        visited[x][y] = true;
                        cnt++;
                        queue.add(new int[]{x,y});
                    }
                }
            }
        }
        result.add(cnt);
    }
    public static void main(String[] args) throws Exception{
        Main.solution();
    }
}
