import java.io.*;
import java.util.*;

// 2023 상반기 오전 1번 포탑 부수기
class Potop {

    int power;
    int turn;
    int attackTurn;
    int row;
    int col;

    public Potop(int power, int turn, int attackTurn, int row, int col) {
        this.power = power;
        this.turn = turn;
        this.row = row;
        this.col = col;
    }

}

public class Main {

    static int N, M, K;
    static Potop[][] D;
    static boolean[][] visited;
    static ArrayList<int[]> laserAttackList;
    static PriorityQueue<Potop> weak;
    static PriorityQueue<Potop> strong;
    static ArrayList<Integer> answer;

    static int[] dx = {0, 1, 0, -1, 1, -1, 1, -1};
    static int[] dy = {1, 0, -1, 0, -1, 1, 1, -1};

    // 배열 출력용 메서드
    private static void printArray(Potop[][] D) {
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=M; j++) {
                System.out.print(D[i][j].power+" ");
            }
            System.out.println();
        }
    }

    private static void resetPQ() {
        weak.clear();
        strong.clear();

        for(int i=1; i<=N; i++) {
            for(int j=1; j<=M; j++) {
                if(D[i][j].power>0) {
                    weak.add(D[i][j]);
                    strong.add(D[i][j]);
                }
            }
        }
    }

    private static int laserAvailable(Potop weakest, Potop strongest) {
        Queue<Potop> queue = new LinkedList<Potop>();

        queue.add(weakest);
        visited[weakest.row][weakest.col] = true;

        // 부모 위치 저장용 배열
        int[][][] parent = new int[N + 1][M + 1][2];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                parent[i][j][0] = -1;
                parent[i][j][1] = -1;
            }
        }

        while(!queue.isEmpty()) {
            Potop now = queue.poll();

            int nowRow = now.row;
            int nowCol = now.col;

            if (nowRow == strongest.row && nowCol == strongest.col) {
                // 최단 경로 도착 시 경로 복원
                int curRow = nowRow;
                int curCol = nowCol;

                LinkedList<int[]> path = new LinkedList<>();
                while (!(curRow == weakest.row && curCol == weakest.col)) {
                    int prevRow = parent[curRow][curCol][0];
                    int prevCol = parent[curRow][curCol][1];

                    // 시작점과 도착점 제외하고 경유지만 추가
                    if (!(prevRow == weakest.row && prevCol == weakest.col)) {
                        path.addFirst(new int[]{prevRow, prevCol});
                    }

                    curRow = prevRow;
                    curCol = prevCol;
                }

                laserAttackList.addAll(path);
                return 1;
            }


            for(int i=0; i<4; i++) {
                int newRow = nowRow + dx[i];
                int newCol = nowCol + dy[i];

                // 인덱스 보정
                if(newRow<1)
                    newRow = N;
                else if(newCol<1)
                    newCol = M;
                else if(newRow>N)
                    newRow = newRow%N;
                else if(newCol>M)
                    newCol = newCol%M;

                if(!visited[newRow][newCol]&&D[newRow][newCol].power>0) {
                    visited[newRow][newCol] = true;
                    queue.add(D[newRow][newCol]);

                    parent[newRow][newCol][0] = nowRow;
                    parent[newRow][newCol][1] = nowCol;
                }
            }
        }

        return 0;
    }

    private static void laserAttack(Potop weakest, Potop strongest, int k) {
        //System.out.println("레이저 공격 발생");
        int damage = weakest.power;

        // 대상 포탑 공격
        D[strongest.row][strongest.col].power -= damage;
        D[strongest.row][strongest.col].turn = k;
        D[weakest.row][weakest.col].turn = k;
        D[weakest.row][weakest.col].attackTurn = k;

        // 경로 포탑 공격
        for(int i=0; i<laserAttackList.size(); i++) {
            int[] attackIdx = laserAttackList.get(i);
            int x = attackIdx[0];
            int y = attackIdx[1];

            //System.out.print(" ("+x+", "+y+") ->");

            D[x][y].power -= damage/2;
            D[x][y].turn = k;
        }
    }

    private static void potanAttack(Potop weakest, Potop strongest, int k) {
        //System.out.println("포탄 공격 발생");
        int damage = weakest.power;

        // 대상 포탑 공격
        D[strongest.row][strongest.col].power -= damage;
        D[strongest.row][strongest.col].turn = k;
        D[weakest.row][weakest.col].turn = k;
        D[weakest.row][weakest.col].attackTurn = k;

        for(int i=0; i<8; i++) {
            int newRow = strongest.row + dx[i];
            int newCol = strongest.col + dy[i];

            if(newRow<1)
                newRow = N;
            if(newCol<1)
                newCol = M;
            if(newRow>N)
                newRow = newRow%N;
            if(newCol>M)
                newCol = newCol%M;

            // 공격자는 공격 제외
            if (newRow == weakest.row && newCol == weakest.col) continue;

            D[newRow][newCol].power -= damage/2;
            D[newRow][newCol].turn = k;
        }

    }

    private static void fixPotop(Potop weakest, Potop strongest, int k) {
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=M; j++) {
                if (D[i][j].power > 0) {
                    if(D[i][j].turn !=k) {
                        D[i][j].power += 1;
                    }
                }
            }
        }
    }

    private static int countPotop() {
        int count = 0;
        answer = new ArrayList<Integer>();

        for(int i=1; i<=N; i++) {
            for(int j=1; j<=M; j++) {
                if(D[i][j].power>0) {
                    count++;
                    answer.add(D[i][j].power);
                }
            }
        }

        return count;
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        D = new Potop[N+1][M+1];
        answer = new ArrayList<Integer>();

        weak = new PriorityQueue<Potop>((o1, o2) ->{
            if(o1.power != o2.power) {
                return o1.power - o2.power;
            } else if(o1.attackTurn != o2.attackTurn) {
                return o2.attackTurn - o1.attackTurn;
            } else if((o1.row + o1.col)!=(o2.row + o2.col)) {
                return (o2.row + o2.col) - (o1.row + o1.col);
            } else if(o1.col != o2.col) {
                return o2.col - o1.col;
            } else {
                return o2.row - o1.row;
            }
        });

        strong = new PriorityQueue<Potop>((o1, o2) ->{
            if(o1.power != o2.power) {
                return o2.power - o1.power;
            } else if(o1.attackTurn != o2.attackTurn) {
                return o1.attackTurn - o2.attackTurn;
            } else if((o1.row + o1.col)!=(o2.row + o2.col)) {
                return (o1.row + o1.col) - (o2.row + o2.col);
            } else if(o1.col != o2.col) {
                return o1.col - o2.col;
            } else {
                return o1.row - o2.row;
            }
        });

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j=1; j<=M; j++) {
                int inputPower = Integer.parseInt(st.nextToken());
                Potop p = new Potop(inputPower, 0, 0, i, j);
                D[i][j] = p;
            }
        }

        //printArray(D);

        for(int k=1; k<=K; k++) {

            visited = new boolean[N+1][M+1];
            laserAttackList = new ArrayList<int[]>();

            // 현재 D 상태로 weak, strong 우선순위 큐 초기화
            resetPQ();

            //System.out.println(k+"번 째 실행 시작");

            // 가장 약한 포탑 선정 -> 공격력 증가
            Potop weakest = weak.poll();
            weakest.power = weakest.power + (N+M);
            D[weakest.row][weakest.col].power = weakest.power;

            //System.out.println("가장 약한 포탑: "+ "(" +weakest.row + "," + weakest.col +")");

            // 가장 강한 포탑 선정
            Potop strongest = strong.poll();
            //System.out.println("가장 강한 포탑: "+ "(" +strongest.row + "," + strongest.col +")");


            // 레이저 공격 가능 여부 판단(BFS)
            int laser = laserAvailable(weakest, strongest);

            // 가능하다면 레이저 공격
            if(laser==1) {
                laserAttack(weakest, strongest, k);
            } else {
                potanAttack(weakest, strongest, k);
            }

            //System.out.println("\n공격 후 상태");
            //printArray(D);

            // 포탑 정비
            fixPotop(weakest, strongest, k);
            //System.out.println("\n정비 후 상태");
            //printArray(D);

            //System.out.println();

            // 남은 포탑 카운트
            int count = countPotop();

            //System.out.println("남은 포탑 개수: "+count);

            if(count==1) {
                System.out.println(answer.get(0));
                return;
            }
        }

        Collections.sort(answer);
        System.out.println(answer.get(answer.size()-1));
        return;

    }
}