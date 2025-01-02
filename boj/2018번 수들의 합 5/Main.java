import java.util.*;

public class Main {
    public static void solution() throws Exception {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        int start = 1;
        int end = 1;
        int sum = 1;
        int count = 1;

        while(end!=N){
            if (sum==N) {
                count ++;
                end ++;
                sum += end;
            } else if(sum<N){
                end++;
                sum += end;
            } else {
                sum -= start;
                start ++;
            }
        }

        System.out.println(count);
    }

    public static void main(String[] args) throws Exception {
        Main.solution();
    }
}
