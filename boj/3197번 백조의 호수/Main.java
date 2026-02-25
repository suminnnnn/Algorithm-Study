import java.io.*;
import java.util.*;

class Main {

    static int R, C;
    static int[][] Lmap;
    static char[][] hosu;
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, -1, 0, 1};

    static boolean[][] visited;

    static Queue<int[]> swanQ = new ArrayDeque<>();
    static Queue<int[]> nextSwanQ = new ArrayDeque<>();
    static boolean[][] swanVisited;

    public static boolean check() {
        while (!swanQ.isEmpty()) {
            int[] now = swanQ.poll();
            int x = now[0];
            int y = now[1];

            if (x == Lmap[1][0] && y == Lmap[1][1]) return true;

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                if (swanVisited[nx][ny]) continue;

                swanVisited[nx][ny] = true;

                if (hosu[nx][ny] == 'X') {
                    nextSwanQ.add(new int[]{nx, ny});
                } else {
                    swanQ.add(new int[]{nx, ny});
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        visited = new boolean[R][C];
        swanVisited = new boolean[R][C];

        hosu = new char[R][C];
        Lmap = new int[2][2];
        int lIdx = 0;

        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < R; i++) {
            String line = br.readLine();

            for (int j = 0; j < C; j++) {
                char c = line.charAt(j);
                hosu[i][j] = c;

                if (c == 'L') {
                    Lmap[lIdx][0] = i;
                    Lmap[lIdx][1] = j;
                    lIdx++;

                    queue.add(new int[]{i, j});
                    visited[i][j] = true;
                }

                if (c == '.') {
                    queue.add(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        swanQ.add(new int[]{Lmap[0][0], Lmap[0][1]});
        swanVisited[Lmap[0][0]][Lmap[0][1]] = true;

        int day = 0;

        if (check()) {
            System.out.println(day);
            return;
        }

        while (!queue.isEmpty()) {
            day++;
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] now = queue.poll();

                if (hosu[now[0]][now[1]] == 'X') continue;

                for (int d = 0; d < 4; d++) {
                    int newX = now[0] + dx[d];
                    int newY = now[1] + dy[d];

                    if (newX < 0 || newY < 0 || newX >= R || newY >= C) continue;
                    if (visited[newX][newY]) continue;

                    if (hosu[newX][newY] == 'L' || hosu[newX][newY] == '.') continue;

                    hosu[newX][newY] = '.';
                    queue.add(new int[]{newX, newY});
                    visited[newX][newY] = true;
                }
            }

            if (!nextSwanQ.isEmpty()) {
                swanQ = nextSwanQ;
                nextSwanQ = new ArrayDeque<>();
            }

            if (check()) {
                System.out.println(day);
                return;
            }
        }

        System.out.println(day);
    }
}