import java.io.*;
import java.util.*;

class Matrix {
    int row;
    int col;
    Queue<int[]> points = new LinkedList<>();

    public void addPoint(int x, int y) {
        int[] point = new int[] {x,y};
        this.points.offer(point);
    }

    public void findRowCol() {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;

        int maxX = -1;
        int maxY = -1;

        while(!this.points.isEmpty()) {
            int[] point = this.points.poll();

            if(point[0]>maxX) {
                maxX = point[0];
            }

            if(point[0]<minX) {
                minX = point[0];
            }

            if(point[1]>maxY) {
                maxY = point[1];
            }

            if(point[1]<minY) {
                minY = point[1];
            }
        }

        this.row = (maxX - minX) + 1;
        this.col = (maxY - minY) + 1;
    }
}

public class Main {

    static int testCase;
    static int n;
    static int[][] A;
    static boolean[][] visited;
    static PriorityQueue<Matrix> queue;
    static int[] dx = new int[] {-1, 0, 1, 0};
    static int[] dy = new int[] {0, -1, 0, 1};

    public static void DFS(int x, int y, Matrix matrix) {
        visited[x][y] = true;
        matrix.addPoint(x,y);

        for(int i=0; i<4; i++){
            int newX = x + dx[i];
            int newY = y + dy[i];

            if(newX>=0 && newY>=0 && newX<n && newY<n && A[newX][newY]>0){
                if(!visited[newX][newY])
                    DFS(newX,newY, matrix);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        testCase = Integer.parseInt(st.nextToken());

        queue = new PriorityQueue<Matrix>((o1, o2) -> {
            if((o1.row * o1.col)!=(o2.row * o2.col)) {
                return (o1.row * o1.col) - (o2.row * o2.col);
            }else {
                return o1.row - o2.row;
            }

        });

        for(int tc = 1; tc<=testCase; tc++) {
            StringBuffer sb = new StringBuffer();

            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());

            A = new int[n][n];
            visited = new boolean[n][n];

            for(int i=0; i<n; i++) {
                st = new StringTokenizer(br.readLine());

                for(int j=0; j<n; j++) {
                    A[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++) {
                    if(!visited[i][j] && A[i][j]>0) {

                        Matrix matrix = new Matrix();
                        DFS(i,j, matrix);

                        matrix.findRowCol();
                        queue.add(matrix);
                    }
                }
            }

            int count = queue.size();
            sb.append(" ");
            sb.append(count);

            while(!queue.isEmpty()) {
                Matrix m = queue.poll();

                sb.append(" ");
                sb.append(m.row);
                sb.append(" ");
                sb.append(m.col);
            }

            System.out.println("#"+tc+sb);
        }

    }

}
