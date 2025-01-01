import java.util.*;

public class Main {

    public static void solution() throws Exception {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        String sNum = sc.next();
        char[] cNum = sNum.toCharArray();

        int sum = 0;
        for (int i=0;i<N;i++) {

            sum += cNum[i] - '0';
        }
        
        System.out.println(sum);
    }

    public static void main(String[] args) throws Exception {
        Main.solution();
    }
}