import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, F;
    static int[][] arr;    // 2D map used later in BFS; cells modified (e.g., exit cell becomes 0, cube base set to 1)
    static int[][] orig;   // Original 2D map (used for finding cube base)
    static int[][][] arr3; // 3D cube: dimensions 5 x M x M
    static int[][] timeMap;  // For time events in the miji map

    // Directions for 2D movement (right, left, down, up)
    static int[] rowDir = {0, 0, 1, -1};
    static int[] colDir = {1, -1, 0, 0};

    // For cube unfolding (neighbors)
    static int[] row = {0, 0, 1, -1};
    static int[] col = {1, -1, 0, 0};

    // Lateral face transitions for 3D BFS (if needed)
    static Map<Integer, Integer> left_nxt = new HashMap<>();
    static Map<Integer, Integer> right_nxt = new HashMap<>();

    // Face indices: 0 = East, 1 = West, 2 = South, 3 = North, 4 = Top
    final static int EAST = 0;
    final static int WEST = 1;
    final static int SOUTH = 2;
    final static int NORTH = 3;
    final static int TOP = 4;

    public static void main(String[] args) throws IOException {
        // Input reading
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());

        // Allocate arrays for 2D map
        arr = new int[N][N];
        orig = new int[N][N];

        int finalR = 0, finalC = 0;
        int endCubeR = 0, endCubeC = 0;

        // Read the 2D map.
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++){
                int cell = Integer.parseInt(st.nextToken());
                orig[i][j] = cell;   // preserve original
                // If cell is exit (4), record coordinates and set arr cell to 0.
                if (cell == 4) {
                    finalR = i;
                    finalC = j;
                    arr[i][j] = 4;
                } else if (cell == 3) {
                    // Record cube base (last occurrence)
                    endCubeR = i;
                    endCubeC = j;
                    arr[i][j] = 1; // mark as wall for later BFS on 2D map
                } else {
                    arr[i][j] = cell;
                }
            }
        }
        System.out.println("baseX = " + endCubeR); // later, base is found from orig
        System.out.println("baseY = " + endCubeC);

        // Read the 3D cube (5 faces, M x M)
        arr3 = new int[5][M][M];
        for (int face = 0; face < 5; face++){
            for (int i = 0; i < M; i++){
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++){
                    arr3[face][i][j] = Integer.parseInt(st.nextToken());
                }
            }
        }

        // Read wall events
        int[][] wall = new int[F][4];
        for (int i = 0; i < F; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++){
                wall[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // Set up lateral transition maps for cube BFS (if needed)
        left_nxt.put(0, 2);
        left_nxt.put(2, 1);
        left_nxt.put(1, 3);
        left_nxt.put(3, 0);
        right_nxt.put(0, 3);
        right_nxt.put(2, 0);
        right_nxt.put(1, 2);
        right_nxt.put(3, 1);

        // [1] Find key positions.
        // 3D start: In the TOP face, find cell==2.
        int[] start3d = find3dStart();  // returns {face, i, j} from arr3 (TOP face)
        int sk_3d = start3d[0], si_3d = start3d[1], sj_3d = start3d[2];
        // 2D end: Find exit in arr (cell==4 originally, now set to 0).
        int[] end2d = find2dEnd();
        int ei = end2d[0], ej = end2d[1];
        // 3D end to 2D start: Use the original map (orig) to find cube base.
        int[] end3d_2d = find3dEnd2dStart(endCubeR, endCubeC);
        // Returned values: {ek, ei3d, ej3d, si, sj}
        int ek_3d = end3d_2d[0], ei_3d = end3d_2d[1], ej_3d = end3d_2d[2];
        int si_d = end3d_2d[3], sj_d = end3d_2d[4];

        // [2] BFS in the 3D cube to compute distance from start to exit connection.
        int dist3d = bfs3d(sk_3d, si_3d, sj_3d, ek_3d, ei_3d, ej_3d);
        if (dist3d == -1) {
            System.out.println(-1);
            return;
        }

        // [3] Prepare 2D grid for time events.
        int[][] v2d = new int[N][N];
        for (int i = 0; i < N; i++){
            Arrays.fill(v2d[i], 401);
        }
        timeMap = new int[N][N];
        for (int i = 0; i < N; i++){
            Arrays.fill(timeMap[i], 0);
        }
        int[] di = {0, 0, 1, -1};
        int[] dj = {1, -1, 0, 0};
        for (int[] w : wall) {
            int wi = w[0], wj = w[1], wd = w[2], wv = w[3];
            timeMap[wi][wj] = 1;
            for (int mul = 1; mul <= N; mul++){
                wi = wi + di[wd];
                wj = wj + dj[wd];
                if (wi >= 0 && wi < N && wj >= 0 && wj < N && arr[wi][wj] == 0 && !(wi == ei && wj == ej)) {
                    if (timeMap[wi][wj] == 0 || timeMap[wi][wj] > wv * mul) {
                        timeMap[wi][wj] = wv * mul;
                    }
                } else {
                    break;
                }
            }
        }

        // [4] BFS in the 2D map from the connection point (si_d, sj_d) to the exit.
        int dist2d = bfs2d(v2d, dist3d, si_d, sj_d, ei, ej);
        if (dist2d == -1) {
            System.out.println(-1);
            return;
        }
        System.out.println(dist2d);
    }

    static int[] find3dStart() {
        for (int i = 0; i < M; i++){
            for (int j = 0; j < M; j++){
                if (arr3[4][i][j] == 2) {
                    return new int[]{4, i, j};
                }
            }
        }
        return null;
    }

    static int[] find2dEnd() {
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                if (arr[i][j] == 4) {
                    arr[i][j] = 0;
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    static int[] find3dBase() {
        // Use the original map to find a cell with value 3.
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                if (orig[i][j] == 3) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    static int[] find3dEnd2dStart(int endCubeR, int endCubeC) {
        int[] base = find3dBase();
        if (base == null) {
            return null;
        }
        int bi = base[0], bj = base[1];
        int middleR = -1, middleC = -1;
        int endR = -1, endIdx = -1, endC = -1;
        int ek = -1, ei3d = -1, ej3d = -1, si = -1, sj = -1;
        outer:
        for (int i = bi; i < bi + M; i++){
            for (int j = bj; j < bj + M; j++){
                for (int k = 0; k < 4; k++){
                    int nr = i + row[k];
                    int nc = j + col[k];
                    if (nr >= 0 && nr < N && nc >= 0 && nc < N && arr[nr][nc] == 0) {
                        middleR = nr;
                        middleC = nc;
                        endR = M - 1;
                        endIdx = k;
                        if (k == 0) {
                            endC = endCubeR - nr;
                        } else if (k == 1) {
                            endC = M - (endCubeR - nr) - 1;
                        } else if (k == 2) {
                            endC = M - (endCubeC - nc) - 1;
                        } else if (k == 3) {
                            endC = endCubeC - nc;
                        }
                        ek = k;
                        ei3d = M - 1;
                        ej3d = (M - 1) - (i - bi);
                        si = i;
                        sj = j + 1;
                        break outer;
                    }
                }
            }
        }
        return new int[]{ek, ei3d, ej3d, si, sj};
    }

    static int bfs3d(int sk, int si, int sj, int ek, int ei, int ej) {
        int[][][] dist = new int[5][M][M];
        for (int k = 0; k < 5; k++){
            for (int i = 0; i < M; i++){
                Arrays.fill(dist[k][i], 0);
            }
        }
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{sk, si, sj});
        dist[sk][si][sj] = 1;
        while (!q.isEmpty()){
            int[] cur = q.poll();
            int ck = cur[0], ci = cur[1], cj = cur[2];
            if (ck == ek && ci == ei && cj == ej) {
                return dist[ck][ci][cj];
            }
            for (int[] dxy : new int[][] { {-1,0}, {1,0}, {0,-1}, {0,1} }) {
                int ni = ci + dxy[0], nj = cj + dxy[1];
                int nk;
                int nr, nc;
                if (ni < 0) {
                    if (ck == 0) { nr = (M - 1) - cj; nc = M - 1; nk = 4; }
                    else if (ck == 1) { nr = cj; nc = 0; nk = 4; }
                    else if (ck == 2) { nr = M - 1; nc = cj; nk = 4; }
                    else if (ck == 3) { nr = (M - 1) - cj; nc = 0; nk = 4; }
                    else if (ck == 4) { nr = 0; nc = (M - 1) - cj; nk = 3; }
                    else continue;
                } else if (ni >= M) {
                    if (ck == 4) { nr = cj; nc = 0; nk = 2; }
                    else continue;
                } else if (nj < 0) {
                    if (ck == 4) { nr = ci; nc = 0; nk = 1; }
                    else {
                        nk = left_nxt.get(ck);
                        nr = ci;
                        nc = M - 1;
                    }
                } else if (nj >= M) {
                    if (ck == 4) { nr = (M - 1) - ci; nc = M - 1; nk = 0; }
                    else {
                        nk = right_nxt.get(ck);
                        nr = ci;
                        nc = 0;
                    }
                } else {
                    nk = ck;
                    nr = ni;
                    nc = nj;
                }
                if (dist[nk][nr][nc] == 0 && arr3[nk][nr][nc] == 0) {
                    q.offer(new int[]{nk, nr, nc});
                    dist[nk][nr][nc] = dist[ck][ci][cj] + 1;
                }
            }
        }
        return -1;
    }

    static int bfs2d(int[][] v, int distVal, int si, int sj, int ei, int ej) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{si, sj});
        v[si][sj] = distVal;
        while (!q.isEmpty()){
            int[] cur = q.poll();
            int ci = cur[0], cj = cur[1];
            if (ci == ei && cj == ej) {
                return v[ci][cj];
            }
            for (int[] dxy : new int[][] { {-1,0}, {1,0}, {0,-1}, {0,1} }) {
                int ni = ci + dxy[0], nj = cj + dxy[1];
                if (ni >= 0 && ni < N && nj >= 0 && nj < N && arr[ni][nj] == 0 && v[ci][cj] + 1 < v[ni][nj]) {
                    q.offer(new int[]{ni, nj});
                    v[ni][nj] = v[ci][cj] + 1;
                }
            }
        }
        return -1;
    }
}
