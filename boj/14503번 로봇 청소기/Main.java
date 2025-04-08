import java.util.*;
import java.io.*;

class Main{

    static int N,M;
    static int r,c;
    static int d;
    static int count;
    static boolean isAllClean = true;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static int[][] A;
    static boolean[][] visited;

    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new int[N][M];
        visited = new boolean[N][M];

        st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());

            for(int j=0; j<M; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        BFS(r,c);

        System.out.println(count);

    }

    public static void BFS(int r, int c){
        Queue<int[]> queue = new LinkedList<int[]>();

        int[] node = new int[]{r,c};

        queue.add(node);
        A[r][c]=2;
        count++;

        while(!queue.isEmpty()){
            int[] now = queue.poll();

            if(A[now[0]][now[1]]==0){
                A[now[0]][now[1]]=2;
                count++;
            }

            isAllClean = true;

            for(int i=0; i<4; i++){

                d = (d+3)%4;

                int newX = now[0] + dx[d];
                int newY = now[1] + dy[d];

                if(newX>=0 && newY>=0 && newX<N && newY<M && A[newX][newY]==0){
                    isAllClean = false;

                    queue.add(new int[]{newX, newY});
                    break;
                }
            }

            if(isAllClean){
                int newX = now[0] + dx[d]*(-1);
                int newY = now[1] + dy[d]*(-1);

                if(newX>=0 && newY>=0 && newX<N && newY<M){
                    if(A[newX][newY]==1){
                        return;
                    }

                    queue.add(new int[]{newX, newY});
                }
            }
        }

    }

    public static void main(String[] args) throws Exception {
        Main.solution();
    }
}