import java.util.*;
import java.io.*;

public class Main {

    static int[] A;
    static int N;


    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        A = new int[N];

        for(int i=0; i<N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(A);

        int M = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        for (int i=0; i<M; i++){
            boolean find = false;
            
            int target = Integer.parseInt(st.nextToken());

            int start = 0;
            int end = N-1;

            while(start<=end){
                int midi = (start+end)/2;
                int midV = A[midi];

                if(midV>target){
                    end = midi -1;
                } else if (midV < target){
                    start = midi +1;
                } else {
                    find = true;
                    break;
                }
            }

            System.out.println(find==true?1:0);
        }
        
    }

    public static void main(String[] args) throws Exception {
        Main.solution();
    }
}
