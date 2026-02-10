import java.io.*;
import java.util.*;

class Main {

    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, 1, 0, -1};

    static int R, C;
    static String[][] miro;
    static boolean[][] visitedJ;
    static Queue<int[]> fireQ;
    static Queue<int[]> jihunQ;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        miro = new String[R][C];
        visitedJ = new boolean[R][C];
        fireQ = new LinkedList<>();
        jihunQ = new LinkedList<>();

        for(int i=0; i<R; i++){
            String line = br.readLine();

            for(int j=0; j<C; j++){
                miro[i][j] = String.valueOf(line.charAt(j));

                if(miro[i][j].equals("J")){
                    jihunQ.add(new int[]{i, j});
                }

                if(miro[i][j].equals("F")) {
                    fireQ.add(new int[]{i, j});
                }
            }
        }
        int time = 0;

        while (!jihunQ.isEmpty()) {
            time++;

            int jSize = jihunQ.size();
            int fSize = fireQ.size();

            for(int f=0; f<fSize; f++) {
                int[] now = fireQ.poll();

                for(int i=0; i<4; i++) {
                    int newX = now[0] + dx[i];
                    int newY = now[1] + dy[i];

                    // 벽, 불, 경계선 체크
                    if (newX < 0 || newY < 0 || newX >= R || newY >= C || miro[newX][newY].equals("#") || miro[newX][newY].equals("F"))
                        continue;

                    // 아니면 큐에
                    fireQ.add(new int[]{newX, newY});
                    miro[newX][newY] = "F";
                }
            }

            for(int j=0; j<jSize; j++) {
                int[] now = jihunQ.poll();

                for(int i=0; i<4; i++) {
                    int newX = now[0] + dx[i];
                    int newY = now[1] + dy[i];

                    // 경계선이면 탈출 성공, 현재 time 출력 후 라턴
                    if (newX < 0 || newY < 0 || newX >= R || newY >= C) {
                        System.out.println(time);
                        return;
                    }

                    // 벽, 불 체크
                    if (miro[newX][newY].equals("#") || miro[newX][newY].equals("F"))
                        continue;

                    if (visitedJ[newX][newY]==true)
                        continue;

                    visitedJ[newX][newY] = true;

                    jihunQ.add(new int[]{newX, newY});
                }
            }
        }

        System.out.println("IMPOSSIBLE");


    }
}