import java.util.*;
import java.io.*;

class Main{

    static int N, K, L;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int dir;
    static int time;
    static int[][] A;
    static boolean finished;
    static Queue<int[]> snake;

    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        A = new int[N][N];
        dir = 0;

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());

        snake = new LinkedList<int[]>();
        snake.add(new int[]{0,0});

        A[0][0] = 2;

        for(int i=0; i<K; i++){
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            A[x-1][y-1] = 1;
        }

        st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());

        Map<Integer, String> rotateMap = new HashMap<Integer, String>();

        for(int i=0; i<L; i++){
            st = new StringTokenizer(br.readLine());

            int X = Integer.parseInt(st.nextToken());
            String C = st.nextToken();

            rotateMap.put(X,C);
        }

        while(!finished){
            time++;

            move();

            if(rotateMap.containsKey(time)){
                rotate(rotateMap.get(time));
            }
        }

        System.out.println(time);
    }

    public static void move() {
        int[] head = ((LinkedList<int[]>)snake).peekLast();

        int newX = head[0] + dx[dir];
        int newY = head[1] + dy[dir];

        if(newX<0 || newY<0 || newX>=N || newY>=N || A[newX][newY]==2){
            finished = true;
            return;
        }

        if(A[newX][newY]==1){
            snake.add(new int[]{newX, newY});
            A[newX][newY]=2;
        } else {
            snake.add(new int[]{newX, newY});
            A[newX][newY]=2;

            int[] tail = snake.poll();
            A[tail[0]][tail[1]] = 0;
        }
    }

    public static void rotate(String C) {
        if(C.equals("L"))
            dir = (dir+3)%4;
        else if(C.equals("D")){
            dir = (dir+1)%4;
        }
    }

    public static void main(String[] args) throws Exception {
        Main.solution();
    }
}