import java.io.*;
import java.util.*;

class Main {

    static int K, W, H;
    static int[][] A;
    static int[][][] dist;

    // 0~3: 위, 오른쪽, 아래, 왼쪽
    static final int[] dx = new int[]{-1, 0, 1, 0};
    static final int[] dy = new int[]{0, 1, 0, -1};

    // 0~7: 주어진 그림 순서대로 시계 방향으로
    static final int[] hx = new int[]{-1, -2, -2, -1, 1, 2, 2, 1};
    static final int[] hy = new int[]{-2, -1, 1, 2, 2, 1, -1, -2};

    private static final int BFS() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{1,1,K});
        dist[1][1][K] = 0;


        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            if(now[0]==H && now[1]==W)
                return dist[ now[0] ][ now[1] ][ now[2] ];

            if(now[2]>0){
                for(int i=0; i<8; i++){
                    int nx = now[0] + hx[i];
                    int ny = now[1] + hy[i];
                    int nk = now[2]-1;

                    if(nx<1 || ny<1 || nx>H || ny>W) continue;

                    if(A[nx][ny]==0 && dist[nx][ny][nk]==-1){
                        dist[nx][ny][nk] = dist[now[0]][now[1]][now[2]] + 1;
                        queue.add(new int[]{nx, ny, nk});
                    }
                }
                for(int i=0; i<4; i++){
                    int nx = now[0] + dx[i];
                    int ny = now[1] + dy[i];
                    int nk = now[2];

                    if(nx<1 || ny<1 || nx>H || ny>W) continue;

                    if(A[nx][ny]==0 && dist[nx][ny][nk]==-1){
                        dist[nx][ny][nk] = dist[now[0]][now[1]][now[2]] + 1;
                        queue.add(new int[]{nx, ny, nk});
                    }
                }
            }else {
                for(int i=0; i<4; i++){
                    int nx = now[0] + dx[i];
                    int ny = now[1] + dy[i];
                    int nk = now[2];

                    if(nx<1 || ny<1 || nx>H || ny>W) continue;

                    if(A[nx][ny]==0 && dist[nx][ny][nk]==-1){
                        dist[nx][ny][nk] = dist[now[0]][now[1]][now[2]] + 1;
                        queue.add(new int[]{nx, ny, nk});
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        A = new int[H+1][W+1];
        dist = new int[H+1][W+1][K+1];

        for(int i=1; i<=H; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=W; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=1; i<=H; i++){
            for(int j=1; j<=W; j++){
                for(int k=0; k<=K; k++){
                    dist[i][j][k] = -1;
                }
            }
        }

        if(A[1][1]==1 || A[H][W]==1)
            System.out.println(-1);
        else
            System.out.println(BFS());
    }
}