import java.util.*;

public class Main {
    
    public static void solution() throws Exception{
        Scanner sc = new Scanner(System.in);
        
        long N = sc.nextLong();

        long res = N;

        for(long i=2; i<=Math.sqrt(N); i++){
            if(N%i==0){
                res -= res/i;

                while(N%i==0){
                    N = N/i;
                }
            }
        }

        if(N>1)
            res = res - res /N;
        System.out.println(res);

    }

    public static void main(String[] args) throws Exception {
        Main.solution();
    }
}
