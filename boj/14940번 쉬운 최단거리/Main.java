import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int m;
    static int[][] A;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int idxX = 0;
        int idxY = 0;
        A = new int[n][m];
        visited = new boolean[n][m];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                A[i][j] = Integer.parseInt(st.nextToken());

                if(A[i][j]==2){
                    idxX = i;
                    idxY = j;
                }
            }
        }

        A[idxX][idxY] = 0;

        BFS(idxX, idxY);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (A[i][j] == 1 && !visited[i][j]) {
                    A[i][j] = -1;
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(A[i][j]);
                if (j < m - 1) {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }
        
        System.out.print(sb.toString());
    }

    private static void BFS(int i, int j){
        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{i, j});
        visited[i][j] = true;
        
        while(!queue.isEmpty()){
            int[] now = queue.poll();

            for(int k=0; k<4; k++){
                int x = now[0] + dx[k];
                int y = now[1] + dy[k];

                if(x>=0 && y>=0 && x<n && y<m && A[x][y]!=0 && !visited[x][y]){
                    visited[x][y] = true;
                    A[x][y] = A[now[0]][now[1]] + 1;

                    queue.offer(new int[]{x,y});
                }
            }
        }
    
    }

    public static void main(String[] args) throws Exception {
        Main.solution();
    }
}
