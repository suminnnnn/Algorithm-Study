import java.io.*;
import java.util.*;

// 2023 상반기 오후 1번 메이즈러너
class Kan {
    int i;
    int x;
    int y;

    public Kan(int i, int x, int y) {
        this.i = i;
        this.x = x;
        this.y = y;
    }
}

class Square {
    int r1;
    int r2;
    int c1;
    int c2;

    public Square(int r1, int r2, int c1, int c2) {
        this.r1 = r1;
        this.r2 = r2;
        this.c1 = c1;
        this.c2 = c2;
    }

    public int findWidth() {
        return (r2-r1)*(c2-c1);
    }
}

public class Main {

    static int N, M, K;
    static int[][] miro;
    static int[][] p_idx; //참가자 좌표 배열
    static int out_x; //도착지 x 좌표
    static int out_y; //도착지 y 좌표
    static int moveCnt;
    static int remainCnt; //도착지에 도착하지 못한 참가자 수
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        miro = new int[N+1][N+1];
        p_idx = new int[M][2];

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                miro[i][j] = Integer.parseInt(st.nextToken());
            }

        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());

            p_idx[i][0] = Integer.parseInt(st.nextToken());
            p_idx[i][1] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        out_x = Integer.parseInt(st.nextToken());
        out_y = Integer.parseInt(st.nextToken());

        moveCnt = 0;
        remainCnt = M;

        for(int second=0; second<K; second++) {

            // 모든 참가자가 출구에 도착하면 출력 후 종료
            if(remainCnt<=0) {
                System.out.println(moveCnt);
                System.out.println(out_x+" "+out_y);
                return;
            }

            // 모든 참가자 움직이기
            for(int i=0; i<M; i++) {
                int[] idx = p_idx[i];

                if((idx[0]==out_x) && (idx[1]==out_y))
                    continue;
                idx = moveParticipants(idx);

                p_idx[i][0] = idx[0];
                p_idx[i][1] = idx[1];
            }

            // 미로 회전하기
            rotateMiro();
        }

        System.out.println(moveCnt);
        System.out.println(out_x+" "+out_y);

    }

    private static void rotateMiro() {
        Square square = findSquare();
        if(square == null) return;

        int r1 = square.r1;
        int r2 = square.r2;
        int c1 = square.c1;
        int c2 = square.c2;

        int size = r2 - r1 + 1;
        int[][] temp = new int[size][size];

        // 1. 회전
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                temp[j][size - 1 - i] = miro[r1 + i][c1 + j];
            }
        }

        // 2. 내구도 감소 + 반영
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (temp[i][j] > 0) temp[i][j]--;
                miro[r1 + i][c1 + j] = temp[i][j];
            }
        }

        // 3. 사람 좌표 회전
        for (int i = 0; i < p_idx.length; i++) {
            int x = p_idx[i][0];
            int y = p_idx[i][1];

            if (r1 <= x && x <= r2 && c1 <= y && y <= c2) {
                int relX = x - r1;
                int relY = y - c1;
                int rotX = relY;
                int rotY = size - 1 - relX;
                p_idx[i][0] = r1 + rotX;
                p_idx[i][1] = c1 + rotY;
            }
        }

        // 4. 출구 좌표 회전
        if (r1 <= out_x && out_x <= r2 && c1 <= out_y && out_y <= c2) {
            int relX = out_x - r1;
            int relY = out_y - c1;
            int rotX = relY;
            int rotY = size - 1 - relX;
            out_x = r1 + rotX;
            out_y = c1 + rotY;
        }
    }

    private static Square findSquare() {
        PriorityQueue<Square> pq = new PriorityQueue<>((o1, o2)->{
            if(o1.r1!=o2.r1) {
                return o1.r1 - o2.r1;
            }else if(o1.c1 != o2.c1) {
                return o1.c1 - o2.c1;
            }else {
                return o1.r2 - o2.r2;
            }
        });

        for(int i=2; i<=N; i++) {
            for(int j=1; j<=(N-i+1); j++) {
                for(int k=1; k<=(N-i+1); k++) {
                    int r1 = j;
                    int r2 = j+i-1;
                    int c1 = k;
                    int c2 = k+i-1;

                    if(!(r1<=out_x && out_x<=r2 && c1<=out_y && out_y<=c2)) {
                        continue;
                    }

                    for(int[] idx: p_idx) {
                        int x = idx[0];
                        int y = idx[1];
                        if(x == out_x && y == out_y) continue;

                        if(r1<=x && x<=r2 && c1<=y && y<=c2) {
                            pq.add(new Square(r1, r2, c1, c2));
                            break;
                        }

                    }
                }
            }

            if(!pq.isEmpty())
                break;
        }

        return pq.poll();
    }

    private static int[] moveParticipants(int[] idx) {
        int x = idx[0];
        int y = idx[1];
        int dist = findDistance(x, y);

        PriorityQueue<Kan> pq = new PriorityQueue<>((o1, o2)->{
            return o1.i - o2.i;
        });

        for(int i=0; i<4; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];

            if(newX<=0 || newY<=0 || newX>N || newY>N)
                continue;

            // 이동할 위치가 출구인 경우 출구로 이동, 남은 수 1 감소
            if((newX==out_x) &&(newY==out_y)) {
                remainCnt -= 1;
                moveCnt += 1;
                return new int[] {out_x,out_y};
            }

            // 벽으로는 이동 불가
            if(miro[newX][newY]>0)
                continue;

            // 새로운 칸은 기존 칸 보다 출구까지 최단거리가 가까워야함
            int newDist = findDistance(newX, newY);
            if(newDist>=dist)
                continue;
            else {
                pq.add(new Kan(i, newX, newY));
            }

        }

        if(pq.isEmpty()) {
            return new int[] {x, y};
        }else {
            moveCnt += 1;
            Kan kan = pq.poll();
            return new int[] {kan.x, kan.y};
        }
    }

    private static int findDistance(int x, int y) {
        return (Math.abs(x-out_x) + Math.abs(y-out_y));
    }

    private static void printMiro(int[][] arr){
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }

    private static void printArr(int[][] arr) {
        for(int i=0; i<arr.length; i++) {
            for(int j=0; j<arr[0].length; j++) {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }
}