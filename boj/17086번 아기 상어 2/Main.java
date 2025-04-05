import java.io.*;
import java.util.*;

public class Main {

    public static int N;
    public static int M;
    public static int[][] A;
    static boolean[][] visited;
    static int[] dx = new int[]{-1, 0, 1, 0, -1, -1, 1, 1};
    static int[] dy = new int[]{0, -1, 0, 1, 1, -1, 1, -1};
    
    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new int[N][M];
        visited = new boolean[N][M];
        
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());

            for(int j=0; j<M; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        BFS();

        int max = 0;

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                max = Math.max(A[i][j], max);
            }
        }

        System.out.println(max+1);

    }

    public static void BFS(){
        Queue<int[]> queue = new LinkedList<>();

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(A[i][j]==1){
                    A[i][j] = -1;
                    queue.offer(new int[] {i,j});
                }
            }
        }

        while(!queue.isEmpty()){
            int size = queue.size();

            for(int i=0; i<size; i++){
                int[] now = queue.poll();

                for(int j=0; j<8; j++){
                    int x = now[0] +dx[j];
                    int y = now[1] +dy[j];

                    if(x>=0 && y>=0 && x<N && y<M && A[x][y]==0 && visited[x][y]==false){
                        A[x][y] = A[now[0]][now[1]] +1;
                        visited[x][y] = true;
                        queue.offer(new int[]{x,y});
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Main.solution();
    }
}
