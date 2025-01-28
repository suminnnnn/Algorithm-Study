import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int M;
    static int[][] A;
    static boolean[][] visited;
    static int count;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A= new int[N][M];
        visited = new boolean[N][M];
        count = 1;

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());

            String line = st.nextToken();

            for(int j=0; j<M; j++){
                A[i][j] = Integer.parseInt(line.substring(j,j+1));
            }
        }

        BFS(0,0);

        System.out.println(A[N-1][M-1]);

    }

    private static void BFS(int i, int j){
        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[] {i, j});
        visited[i][j] = true;

        while(!queue.isEmpty()){
            int now[] = queue.poll();

            for(int k=0; k<4; k++){
                int x = now[0] + dx[k];
                int y = now[1] + dy[k];

                if (x>=0 && x<N && y>=0 && y<M) {
                    if (A[x][y]!=0 && !visited[x][y]){
                        visited[x][y] = true;
                        A[x][y] = A[now[0]][now[1]] + 1;
                        queue.add(new int[] {x, y});
                    }

                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        Main.solution();
    }
}
