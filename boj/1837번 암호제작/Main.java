import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    public static void solution() throws Exception {
        Scanner sc = new Scanner(System.in);

        BigInteger P = new BigInteger(sc.next());

        int K = sc.nextInt();

        boolean isGood = true;

        long[] A = new long[1000001];

        for(int i=2; i<K; i++){
            A[i] = i;
        }

        for(int i=2; i<=Math.sqrt(K); i++){
            if(A[i]==0){
                continue;
            }
            for(int j=i+i; j<K; j=j+i){
                A[j] = 0;
            }
        }

        for(int i=2; i<K; i++){
            if(A[i]!=0){
                if(P.mod(BigInteger.valueOf(i)).equals(BigInteger.ZERO)){
                    isGood=false;
                    System.out.println("BAD "+i);
                    break;
                }
            }
        }

        if(isGood)
            System.out.println("GOOD");
    }

    public static void main(String[] args) throws Exception {
        Main.solution();
    }
    
}
