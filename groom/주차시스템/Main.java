import java.io.*;
import java.util.*;

class Main {

    static int N,M;
    static int[][] A;
    static boolean[][] visited;
    static int cnt;
    static int[] answer;
    static int[] dx = new int[]{-1, 0, 1, 0};
    static int[] dy = new int[]{0, -1, 0, 1};

    public static void BFS(int x, int y) throws Exception {
        Queue<int[]> queue = new LinkedList<int[]>();

        queue.add(new int[]{x, y});
        visited[x][y] = true;

        while(!queue.isEmpty()){
            int[] now = queue.poll();

            if(A[now[0]][now[1]]==2){
                answer[cnt] -= 2;
            } else if(A[now[0]][now[1]]==0){
                answer[cnt] += 1;
            }

            for(int i=0; i<4; i++){
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if(nx>=0 && ny>=0 && nx<N && ny<M && !visited[nx][ny] && A[nx][ny]!=1){
                    visited[nx][ny] = true;
                    queue.add(new int[]{nx, ny});

                }
            }

        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N][M];
        visited = new boolean[N][M];
        answer = new int[N*M+1];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());

            for(int j=0; j<M; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(!visited[i][j] && A[i][j]!=1){
                    cnt++;
                    BFS(i,j);
                }
            }
        }

        Arrays.sort(answer);

        if(cnt==0 || answer[N*M]<0){
            System.out.println(0);
        } else {
            System.out.println(answer[N*M]);
        }
    }
}