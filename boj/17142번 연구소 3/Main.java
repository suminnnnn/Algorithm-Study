import java.io.*;
import java.util.*;

class Main{

    static int N, M;
    static int minTime;
    static int[][] lab;
    static int[][] tempLab;
    static boolean[][] visited;
    static ArrayList<int[]> virusAt;
    static int[] dx = new int[]{-1, 0, 1, 0};
    static int[] dy = new int[]{0, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        lab = new int[N][N];
        virusAt = new ArrayList<>();

        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());

            for(int j=0; j<N; j++){
                lab[i][j] = (-1)*(Integer.parseInt(st.nextToken()));
                if(lab[i][j]==-2)
                    virusAt.add(new int[]{i,j});
            }
        }

        int virusCnt = virusAt.size();
        minTime = Integer.MAX_VALUE;

        int initialBlanks = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(lab[i][j] == 0) {
                    initialBlanks++;
                }
            }
        }

        if(initialBlanks == 0) {
            System.out.println(0);
            return;
        }

        for(int mask = 0; mask<(1<<virusCnt); mask++){
            if(Integer.bitCount(mask)!=M)
                continue;

            tempLab = new int[N][N];
            for(int i = 0; i<N; i++){
                tempLab[i] = lab[i].clone();
            }

            Queue<int[]> queue = new LinkedList<>();
            visited = new boolean[N][N];

            for(int i=0; i<virusCnt; i++){
                if((mask&(1<<i))!=0){
                    tempLab[virusAt.get(i)[0]][virusAt.get(i)[1]] = 0;
                    queue.add(virusAt.get(i));
                    visited[virusAt.get(i)[0]][virusAt.get(i)[1]]=true;
                }
            }

            BFS(queue, tempLab);

            boolean allInfected = true;
            int maxTimeForBlanks = 0;

            int cnt=0;
            for(int i = 0; i<N; i++){
                for(int j=0; j<N; j++){
                    if(lab[i][j]==0){
                        if(tempLab[i][j]==0){
                            allInfected = false;
                            break;
                        }
                        maxTimeForBlanks = Math.max(maxTimeForBlanks, tempLab[i][j]);
                    }
                }
                if(!allInfected) break;
            }

            if(allInfected){
                if(minTime>maxTimeForBlanks){
                    minTime = maxTimeForBlanks;
                }
            }
        }
        if(minTime==Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(minTime);
    }

    public static void BFS(Queue<int[]> queue, int[][] tempLab){
        while(!queue.isEmpty()){
            int size = queue.size();

            for(int i = 0; i<size; i++){
                int[] now = queue.poll();
                visited[now[0]][now[1]] = true;

                for(int j=0; j<4; j++){
                    int x = now[0] + dx[j];
                    int y = now[1] + dy[j];

                    if(x>=0 && y>=0 && x<N && y<N && tempLab[x][y]!=-1 && !visited[x][y]){
                        tempLab[x][y] = tempLab[now[0]][now[1]] + 1;
                        queue.offer(new int[]{x, y});
                        visited[x][y] = true;
                    }
                }
            }
        }
    }

}