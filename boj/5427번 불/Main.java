import java.io.*;
import java.util.*;

class Main {

    public static final int[] dx = new int[]{-1, 0, 1, 0};
    public static final int[] dy = new int[]{0, -1, 0, 1};

    public static int T, H, W, second;
    public static char[][] B;
    public static int sangX, sangY;

    public static Queue<int[]> fireQ;
    public static Queue<int[]> sangQ;
    public static boolean[][] fireVisited;
    public static boolean[][] sangVisited;

    private static void printB(){
        for(int i=0; i<H; i++) {
            for(int j=0; j<W; j++) {
                System.out.print(B[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static boolean fireBFS() {
        boolean isFireSpread = false;

        int size = fireQ.size();

        for(int s=0; s<size; s++) {
            int[] now = fireQ.poll();
            fireVisited[now[0]][now[1]] = true;

            for(int i=0; i<4; i++) {
                int newX = now[0] + dx[i];
                int newY = now[1] + dy[i];

                if(newX<0 || newY<0 || newX>=H || newY>=W || fireVisited[newX][newY]) continue;

                if(B[newX][newY]=='#') continue;
                if(B[newX][newY]=='*') continue;

                fireVisited[newX][newY] = true;

                B[newX][newY] = '*';
                fireQ.add(new int[]{newX, newY});

                isFireSpread = true;

            }
        }

        return isFireSpread;
    }

    public static boolean sangBFS() {
        boolean isPossible = false;

        int size = sangQ.size();

        for(int s=0; s<size; s++) {
            int[] now = sangQ.poll();
            sangVisited[now[0]][now[1]] = true;

            for(int i=0; i<4; i++) {
                int newX = now[0] + dx[i];
                int newY = now[1] + dy[i];

                if(newX<0 || newY<0 || newX>=H || newY>=W ) {
                    isPossible = true;
                    return isPossible;
                }

                if(sangVisited[newX][newY]) continue;

                if(B[newX][newY]=='#' || B[newX][newY]=='*') continue;

                sangVisited[newX][newY] = true;
                sangQ.add(new int[]{newX, newY});
            }
        }


        return isPossible;

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());

        for(int t=0; t<T; t++) {
            st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            B = new char[H][W];
            fireQ = new LinkedList<int[]>();
            sangQ = new LinkedList<int[]>();
            fireVisited = new boolean[H][W];
            sangVisited = new boolean[H][W];

            for(int i=0; i<H; i++) {
                B[i] = br.readLine().toCharArray();

                for(int j=0; j<W; j++) {
                    if(B[i][j]=='@') {
                        sangX = i;
                        sangY = j;

                        sangQ.add(new int[]{sangX,sangY});
                        sangVisited[sangX][sangY] = true;
                    }
                    if(B[i][j]=='*') {
                        fireQ.add(new int[]{i,j});
                        fireVisited[i][j] = true;
                    }
                }
            }

            second = 1;

            while(true) {
//                System.out.println();
//                System.out.println(second+"초 START>>>");

                boolean onFire = false;
                onFire = fireBFS();

                boolean possible = false;
                possible = sangBFS();

//                printB();

                if(sangQ.isEmpty() && !possible) {
                    System.out.println("IMPOSSIBLE");
                    break;
                }

                if(possible) {
                    System.out.println(second);
                    break;
                }

                second++;
            }
        }
    }
}