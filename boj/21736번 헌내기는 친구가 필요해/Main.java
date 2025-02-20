import java.io.*;
import java.util.*;

public class Main {

    static int[] dx = new int[]{-1, 0, 1, 0};
    static int[] dy = new int[]{0, -1, 0, 1};
    static int N;
    static int M;
    static int result;
    static String[][] A;

    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int x = -1;
        int y = -1;

        A = new String[N][M];

        for(int i=0; i<N; i++){
            String line = br.readLine();

            for(int j=0; j<M; j++) {
                A[i][j] = line.substring(j, j+1);
                
                if(A[i][j].equals("I")){
                    x = i;
                    y = j;
                }
            }   
        }

        bfs(x, y);
    }

    public static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[]{x, y});

        while(!queue.isEmpty()){
            int size = queue.size();

            for(int i=0; i<size; i++){
                int[] now = queue.poll();

                for(int j=0; j<4; j++){
                    int newX = now[0] + dx[j];
                    int newY = now[1] + dy[j];

                    if(newX>=0 && newY>=0 && newX<N && newY<M && !A[newX][newY].equals("X") && !A[newX][newY].equals("Z")){
                        if(A[newX][newY].equals("P")){
                            A[newX][newY] = "Z";
                            result ++;
                            queue.offer(new int[]{newX, newY});
                        } else if (A[newX][newY].equals("O")) {
                            A[newX][newY] = "Z";
                            queue.offer(new int[]{newX, newY});
                        }
                    }

                }
            }
        }

        System.out.println(result>0?result:"TT");
        
    }

    public static void main(String[] args) throws Exception {
        Main.solution();
    }
    
}
