import java.io.*;
import java.util.*;

class Main {

    public static ArrayList<Integer> result;
    public static int M, N, K;
    public static int[][] A;
    public static int[][] dist;
    public static final int[] dx = new int[]{-1, 0, 1, 0};
    public static final int[] dy = new int[]{0, -1, 0, 1};

    public static int BFS(int startX, int startY) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startX, startY});
        dist[startX][startY] = 1;
        int r = 1;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int nowX = now[0];
            int nowY = now[1];

            for(int i=0; i<4; i++) {
                int newX = nowX + dx[i];
                int newY = nowY + dy[i];

                if(newX<0 || newY<0 || newX>=M || newY>=N || dist[newX][newY]>0) continue;

                if(A[newX][newY]>=0) continue;

                queue.add(new int[]{newX, newY});
                r++;
                dist[newX][newY] = dist[nowX][nowY] + 1;
            }
        }

        return r;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[M][N];
        dist = new int[M][N];
        result = new ArrayList<Integer>();

        for(int i=0;i<M; i++) {
            for(int j=0; j<N; j++) {
                A[i][j] = -1;
            }
        }

        for(int k=0; k<K; k++) {
            st = new StringTokenizer(br.readLine());
            int y1 = Integer.parseInt(st.nextToken());
            int x1 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());

            for(int j=y1; j<y2; j++) {
                for(int i=x1; i<x2; i++) {
                    A[i][j] += 1;
                }
            }
        }

        for(int i=0; i<M; i++) {
            for(int j=0; j<N; j++) {
                if(A[i][j]<0 && dist[i][j]==0) {
                    int area = BFS(i, j);
                    result.add(area);
                }
            }
        }

        Collections.sort(result);
        System.out.println(result.size());

        StringBuilder sb = new StringBuilder();
        for(int r : result) {
            sb.append(r);
            sb.append(" ");
        }
        System.out.println(sb.toString());
    }
}