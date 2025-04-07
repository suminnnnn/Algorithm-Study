import java.util.*;
import java.io.*;

class Fish{
    public int x;
    public int y;
    public int dist;

    public Fish(int x, int y, int dist){
        this.x = x;
        this.y = y;
        this.dist = dist;
    }

}

class Main {

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int[][] A;
    static boolean[][] visited;
    static int[][] dist;
    static int answer;
    static int startX, startY;
    static List<Fish> fishes;

    static int size;
    static int eaten;

    static int N, M;

    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = new int[N][N];
        visited = new boolean[N][N];
        dist = new int[N][N];


        size = 2;
        eaten = 0;

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());

            for(int j=0; j<N; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
                if(A[i][j]==9){
                    startX = i;
                    startY = j;
                    A[i][j] = 0;
                }
            }
        }

        while(true){
            visited = new boolean[N][N];
            dist = new int[N][N];
            List<Fish> targets = BFS(startX, startY);

            if(targets.isEmpty())
                break;

            Fish target = targets.get(0);

            startX = target.x;
            startY = target.y;

            A[startX][startY] = 0;

            answer += target.dist;

            eaten++;

            if(eaten == size){
                size++;
                eaten=0;
            }

        }

        System.out.println(answer);
    }

    public static List<Fish> BFS(int x, int y) {
        List<Fish> fishes = new LinkedList<Fish>();
        Queue<int[]> queue = new LinkedList<int[]>();

        queue.add(new int[]{x, y});
        visited[x][y] = true;

        while(!queue.isEmpty()){
            int[] now = queue.poll();

            for(int i=0; i<4; i++){
                int newX = now[0] +dx[i];
                int newY = now[1] +dy[i];

                if(newX>=0 && newY>=0 && newX<N && newY<N && visited[newX][newY]==false && A[newX][newY]<=size){
                    visited[newX][newY] = true;
                    dist[newX][newY] = dist[now[0]][now[1]] + 1;
                    queue.add(new int[]{newX, newY});

                    if(A[newX][newY]<size && A[newX][newY]>0){
                        fishes.add(new Fish(newX, newY, dist[newX][newY]));
                    }
                }
            }
        }

        Collections.sort(fishes, (a,b) -> {
            if(a.dist != b.dist){
                return a.dist - b.dist;
            }

            if(a.x != b.x){
                return a.x - b.x;
            }

            return a.y - b.y;
        });

        return fishes;

    }

    public static void main(String[] args) throws Exception{
        Main.solution();
    }
}