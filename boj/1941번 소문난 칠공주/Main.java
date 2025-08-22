import java.io.*;
import java.util.*;

class Main {

    static char[][] students;
    static int[][] visited;
    static int answer;
    static int cntY;
    static int cntS;
    static int[] selected;
    static Map<String, Integer> map;

    static final int[] dx = new int[]{-1, 0, 1, 0};
    static final int[] dy = new int[]{0, -1, 0, 1};

    private static void combination(int start, int depth){
        if(depth==7){
            map = new HashMap<>();

            int cntS = 0;
            for(int i = 0; i<7; i++){
                int x = selected[i]/5;
                int y = selected[i]%5;

                map.put(x+"+"+y, 1);

                if(students[x][y]=='S')
                    cntS++;
            }

            if(cntS>=4){
                visited = new int[5][5];
                for(int i = 0; i<5; i++){
                    Arrays.fill(visited[i], -1);
                }

                Queue<int[]> queue = new LinkedList<>();
                queue.add(new int[]{selected[0]/5, selected[0]%5});
                visited[selected[0]/5][selected[0]%5] = 0;

                while (!queue.isEmpty()){
                    int[] now = queue.poll();
                    int x = now[0];
                    int y = now[1];

                    for(int i = 0; i<4; i++){
                        int newX = x +dx[i];
                        int newY = y +dy[i];

                        if(newX>=0 && newY>=0 && newX<5 && newY<5 && visited[newX][newY]<0 && !(map.get(newX+"+"+newY)==null)){
                            visited[newX][newY] = visited[x][y] +1;
                            queue.add(new int[]{newX, newY});
                        }
                    }
                }
                boolean flag = false;
                for(int i = 0; i<7; i++){
                    int x = selected[i]/5;
                    int y = selected[i]%5;

                    if(visited[x][y]<0)
                        flag = true;
                }

                if(!flag)
                    answer++;
            }
            return;
        }

        for(int i = start; i<25; i++){
            selected[depth] = i;
            combination(i+1, depth+1);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        students = new char[5][5];

        for(int i = 0; i<5; i++){
            String input = br.readLine();
            for(int j=0; j<5; j++){
                students[i][j] = input.charAt(j);
            }
        }

        selected = new int[7];
        combination(0, 0);

        System.out.println(answer);
    }
}