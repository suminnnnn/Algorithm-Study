import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] map;
    static boolean[][][] visited;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static class Node {
        int x, y, dist, broke;

        Node(int x, int y, int dist, int broke) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.broke = broke;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M][2];

        for (int i = 0; i < N; i++) {
            String row = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = row.charAt(j) - '0';
            }
        }

        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0, 1, 0));
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            Node curr = queue.poll();

            if (curr.x == N - 1 && curr.y == M - 1) {
                return curr.dist;
            }

            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

                if (map[nx][ny] == 0 && !visited[nx][ny][curr.broke]) {
                    visited[nx][ny][curr.broke] = true;
                    queue.add(new Node(nx, ny, curr.dist + 1, curr.broke));
                }

                if (map[nx][ny] == 1 && curr.broke == 0 && !visited[nx][ny][1]) {
                    visited[nx][ny][1] = true;
                    queue.add(new Node(nx, ny, curr.dist + 1, 1));
                }
            }
        }

        return -1;
    }
}