import java.util.*;

public class Main {
    
    public static void solution() throws Exception {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        int[] numArr = new int[N];
        int[] sumArr = new int[N];

        for (int i=0; i<N; i++){
            numArr[i] = sc.nextInt();
            
            if (i==0)
                sumArr[i] = numArr[i];
            else {
                sumArr[i] = sumArr[i-1] + numArr[i];
            }
        }

        for (int i=0; i<M; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();

            if(start ==1)
                System.out.println(sumArr[end-1]);
            else
                System.out.println(sumArr[end-1]-sumArr[start-2]);
        }
    }

    public static void main(String[] args) throws Exception {
        Main.solution();
    }
}
