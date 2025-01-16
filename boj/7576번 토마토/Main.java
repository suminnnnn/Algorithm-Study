import java.util.*;
import java.io.*;

public class Main {

    static int M;
    static int N;
    static boolean[][] visited;
    static int[][] A;
    static int[] dx = new int[]{-1, 0, 1, 0};
    static int[] dy = new int[]{0, -1, 0, 1};

    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        A = new int[N][M];
        visited = new boolean[N][M];

        int isRippened = 1;

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
                if(A[i][j]==0)
                    isRippened = 0;
            }
        }

        if(isRippened==1){
            System.out.println(0);
            return;
        }

        int day = BFS();
        
        for (int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(A[i][j]==0){
                    System.out.println(-1);
                    return;
                }
            }
        }

        System.out.println(day-1);

    }

    private static int BFS(){
        Queue<int[]> queue = new LinkedList<>();

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(A[i][j]==1){
                    queue.offer(new int[] {i,j});
                }
            }
        }

        int day =0;

        while(!queue.isEmpty()){
            int size = queue.size();

            for(int i=0; i<size; i++){
                int now[] = queue.poll();

                for(int k=0; k<4; k++){
                    int x = now[0] + dx[k];
                    int y = now[1] + dy[k];
    
                    if(x>=0 && y>=0 && x<N && y<M && A[x][y]==0){
                        A[x][y] = 1;
                        queue.offer(new int[]{x, y});
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
