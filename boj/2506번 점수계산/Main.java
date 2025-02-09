import java.util.*;
import java.io.*;

public class Main {

    public static void solution() throws Exception {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        int[] result = new int[N];
        int score = 0;

        result[0] = sc.nextInt();
        score = result[0];

        for(int i=1; i<N; i++){
            int num = sc.nextInt();

            if(result[i-1]==0){
                if(num==0){
                    result[i] = 0;
                }
                else{
                    result[i] = 1;
                }
            } else {
                if(num==0){
                    result[i] = 0;
                } else {
                    result[i] = result[i-1] + 1;
                }
            }

            score += result[i];
        }

        System.out.println(score);
    }
    public static void main(String[] args) throws Exception {
        Main.solution();
    }
}
