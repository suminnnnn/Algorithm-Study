import java.io.*;
import java.util.*;

class Main {

    static int N, answer, count, maxH, H;
    static boolean[][] visited;
    static int[][] A;
    static final int[] dx = new int[]{-1, 0, 1, 0};
    static final int[] dy = new int[]{0, -1, 0, 1};

    public static void BFS(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x,y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            int nowX = now[0];
            int nowY = now[1];

            for(int i=0; i<4; i++) {
                int newX = nowX + dx[i];
                int newY = nowY + dy[i];

                if(newX<0 || newY<0 || newX>=N || newY>=N || visited[newX][newY])
                    continue;

                if(A[newX][newY]<=H)
                    continue;

                queue.add(new int[]{newX, newY});
                visited[newX][newY] = true;

            }
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        A = new int[N][N];
        maxH = -1;

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j=0; j<N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                if(A[i][j]>maxH)
                    maxH = A[i][j];
            }
        }

        answer = -1;

        for(int h=0; h<=maxH; h++) {
            count = 0;
            H=h;
            visited = new boolean[N][N];

            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(!visited[i][j] && A[i][j]>H) {
                        BFS(i, j);
                        count++;
                    }
                }
            }

            if(count>answer)
                answer = count;
        }

        System.out.println(answer);
    }
}