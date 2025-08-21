import java.io.*;
import java.util.*;

class Main{
    static int n, m;
    static String[][] A;
    static int[][] power;
    static boolean[][][] visited;
    static final int[] dx = new int[]{-1, 0, 1, 0};
    static final int[] dy = new int[]{0, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        A = new String[n][m];
        power = new int[n][m];
        visited = new boolean[n][m][1<<6];
        Queue<int[]> queue = new LinkedList<>();

        for(int i = 0; i<n; i++){
            String[] inputs = br.readLine().split("");

            for(int j=0; j<m; j++){
                power[i][j] = -1;
                A[i][j] = inputs[j];
                if(A[i][j].equals("0")){
                    power[i][j] = 0;
                    queue.add(new int[]{i,j,0});
                    visited[i][j][0] = true;
                }
            }
        }

        while(!queue.isEmpty()){
            int[] now = queue.poll();

            int nowX = now[0];
            int nowY = now[1];
            int keyMask = now[2];

            if(A[nowX][nowY].equals("1")){
                System.out.println(power[nowX][nowY]);
                return;
            }

            for(int i = 0; i<4; i++){
                int newX = nowX + dx[i];
                int newY = nowY + dy[i];
                int newMask = keyMask;

                if(newX>=0 && newY>=0 && newX<n && newY<m && !A[newX][newY].equals("#")){
                    if(A[newX][newY].equals("A") && (keyMask &(1<<0))==0){
                        continue;
                    }else if(A[newX][newY].equals("B") && (keyMask &(1<<1))==0){
                        continue;
                    }else if(A[newX][newY].equals("C") && (keyMask &(1<<2))==0){
                        continue;
                    }else if(A[newX][newY].equals("D") && (keyMask &(1<<3))==0){
                        continue;
                    }else if(A[newX][newY].equals("E") && (keyMask &(1<<4))==0){
                        continue;
                    }else if(A[newX][newY].equals("F") && (keyMask &(1<<5))==0){
                        continue;
                    }

                    else if(A[newX][newY].equals("a")){
                        newMask = (keyMask | 1<<0);
                    }
                    else if(A[newX][newY].equals("b")){
                        newMask = (keyMask | 1<<1);
                    }
                    else if(A[newX][newY].equals("c")){
                        newMask = (keyMask | 1<<2);
                    }
                    else if(A[newX][newY].equals("d")){
                        newMask = (keyMask | 1<<3);
                    }
                    else if(A[newX][newY].equals("e")){
                        newMask = (keyMask | 1<<4);
                    }
                    else if(A[newX][newY].equals("f")) {
                        newMask = (keyMask | 1<<5);
                    }
                    if(!visited[newX][newY][newMask]) {
                        visited[newX][newY][newMask] = true;
                        queue.add(new int[]{newX, newY, newMask});
                        power[newX][newY] = power[nowX][nowY] + 1;
                    }
                }
            }
        }

        System.out.println(-1);
    }
}