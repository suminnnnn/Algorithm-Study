import java.util.*;
import java.io.*;

public class Main {

    static int[][][] A;
    static boolean[][][] visited;
    static int M;
    static int N;
    static int H;
    static int isRippened;
    static int[] dx = new int[]{-1, 0, 1, 0, 0, 0};
    static int[] dy = new int[]{0, -1, 0, 1, 0, 0};
    static int[] dz = new int[]{0, 0, 0, 0, -1, 1};

    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        A = new int[N][M][H];

        isRippened = 1;

        for (int i=0; i<(N*H); i++){
            st = new StringTokenizer(br.readLine());

            for (int j=0; j<M; j++){
                A[(i%N)][j][(i/N)] = Integer.parseInt(st.nextToken());

                if(A[(i%N)][j][(i/N)]==0)
                    isRippened = 0;
            }
        }

        if(isRippened==1){
            System.out.println(0);
            return;
        }

        int day = BFS();

        for (int i=0; i<(N*H); i++){
            for (int j=0; j<M; j++){
                if(A[(i%N)][j][(i/N)]==0){
                    System.out.println(-1);
                    return;
                }
            }
        }

        System.out.println(day-1);

    }

    public static int BFS() {
        Queue<int[]> queue = new LinkedList<>();

        for (int i=0; i<(N*H); i++){
            for (int j=0; j<M; j++){
                if(A[(i%N)][j][(i/N)]==1){
                    queue.offer(new int[]{(i%N), j, (i/N)});
                }
            }
        }

        int day = 0;

        while(!queue.isEmpty()){
            int size = queue.size();

            for(int i=0; i<size; i++){
                int[] now = queue.poll();

                for(int k=0; k<6; k++){
                    int newX = now[0] + dx[k];
                    int newY = now[1] + dy[k];
                    int newZ = now[2] + dz[k];

                    if(newX>=0 && newY>=0 && newZ>=0 && newX<N && newY<M && newZ<H && A[newX][newY][newZ]==0){
                        A[newX][newY][newZ] = 1;
                        queue.offer(new int[]{newX, newY, newZ});
                    }
                }
            }
            day++;
        }
        return day;
    }

    public static void main(String[] args) throws Exception {
        Main.solution();
    }
    
}
