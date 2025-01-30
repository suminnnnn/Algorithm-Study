import java.util.*;
import java.io.*;

public class Main {

    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int A[][] = new int[r][c];

        for(int i=0; i<r; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<c; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int t = Integer.parseInt(br.readLine());

        int count = 0;

        int[] list = new int[9];

        for(int i=0; i<(r-2); i++){
            for(int j=0; j<(c-2);j++){
                list[0] = A[i][j];
                list[1] = A[i][j+1];
                list[2] = A[i][j+2];

                list[3] = A[i+1][j];
                list[4] = A[i+1][j+1];
                list[5] = A[i+1][j+2];

                list[6] = A[i+2][j];
                list[7] = A[i+2][j+1];
                list[8] = A[i+2][j+2];

                Arrays.sort(list);

                if(list[4] >= t)
                    count++;
            }
        }

        System.out.println(count);
    }

    public static void main(String[] args) throws Exception {
        Main.solution();
    }
}
