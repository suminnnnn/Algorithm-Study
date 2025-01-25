import java.util.*;
import java.io.*;

public class Main {
    
    public static void solution() throws Exception {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        long A[] = new long[2000001];

        for(int i=2; i<A.length; i++){
            A[i] = i;
        }

        for(int i=2; i<Math.sqrt(A.length); i++){
            if(A[i] == 0)
                continue;
            for(int j=i+i; j<A.length; j=j+i){
                A[j] = 0;
            }
        }

        for(int i=2; i<A.length; i++){
            if(A[i]!=0 && A[i]>=N){
                boolean isCorrect = true;

                String num = String.valueOf(A[i]);
                
                for(int j=0; j<num.length()/2; j++){
                    if((num.charAt(j)) != (num.charAt(num.length()-1-j))){
                        isCorrect = false;
                        break;
                    }
                }

                if(isCorrect){
                    System.out.println(num);
                    return;
                }

            }
        }


    }

    public static void main(String[] args) throws Exception {
        Main.solution();
    }
}
