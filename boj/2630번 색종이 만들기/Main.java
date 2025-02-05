import java.util.*;
import java.io.*;

public class Main {

    public static int[][] A;
    public static int whiteCnt=0;
    public static int blueCnt=0;

    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        A = new int[N][N];

        for(int i=0; i<N; i++){

            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j=0; j<N; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        divide(0,0,N);

        System.out.println(whiteCnt);
        System.out.println(blueCnt);

    }

    static void divide(int x, int y, int size){
        if (isSameColor(x, y, size)) {
            if (A[x][y]==0) whiteCnt++;
            else blueCnt++;

            return;
        }

        int newSize = size/2;

        divide(x, y, newSize);
        divide(x, y + newSize, newSize);
        divide(x+newSize, y, newSize);
        divide(x+newSize, y+newSize, newSize);
    }

    static boolean isSameColor(int x, int y, int size){
        int color = A[x][y];

        for(int i=x; i< x+size; i++){
            for(int j=y; j<y+size; j++){
                if(A[i][j]!=color)
                    return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws Exception {
        Main.solution();
    }
}
