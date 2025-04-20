import java.io.*;
import java.util.*;

class Main{
    static int A, B, C;
    static boolean[][] visited;
    static boolean[] possible;
    static int[] capacity;

    public static int[] pour(int from, int to, int[] now, int[] capacity){
        int[] next = now.clone();

        int amount = Math.min(now[from], capacity[to]-now[to]);

        next[from] -= amount;
        next[to] += amount;

        return next;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        A = sc.nextInt();
        B = sc.nextInt();
        C = sc.nextInt();

        Queue<int[]> queue = new LinkedList<int[]>();
        visited = new boolean[201][201];
        possible = new boolean[201];
        capacity = new int[3];

        queue.add(new int[]{0,0,C});
        possible[C] = true;
        visited[0][0] = true;
        capacity[0] = A;
        capacity[1] = B;
        capacity[2] = C;

        List<Integer> answer = new LinkedList<Integer>();

        while(!queue.isEmpty()){
            int[] now = queue.poll();

            for(int i=0; i<3; i++){
                for(int j=0; j<3; j++){
                    if(i==j) continue;

                    int[] next = pour(i,j,now, capacity);

                    int a = next[0], b = next[1], c = next[2];
                    if(!visited[a][b]){
                        visited[a][b] = true;

                        queue.add(next);
                        if(a==0){
                            possible[c] = true;
                            answer.add(c);
                        }
                    }
                }
            }
        }

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i <= 200; i++) {
            if (possible[i]){
                sb.append(i);
                sb.append(" ");
            }
        }

        System.out.println(sb.toString().trim());

    }
}