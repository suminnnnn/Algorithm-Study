import java.io.*;
import java.util.*;

class Main{

    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);

        int N, Q;
        long F[] = new long[21];
        int S[] = new int[21];
        boolean visited[] = new boolean[21];

        N = sc.nextInt();
        Q = sc.nextInt();

        F[0] = 1;
        for(int i = 1; i<=N; i++){
            F[i] = F[i-1]*i;
        }

        if(Q==1){
            long K = sc.nextLong();

            for(int i = 1; i<=N; i++){
                for(int j=1, cnt=1; j<=N; j++){
                    if(visited[j]){
                        continue;
                    }

                    if(K<=cnt * F[N-i]) {
                        K -= ((cnt-1)*F[N-i]);
                        S[i] = j;
                        visited[j] = true;
                        break;
                    }
                    cnt++;
                }
            }
            for(int i = 1; i<=N; i++){
                System.out.print(S[i]+" ");
            }
        }else{
            long K=1;

            for(int i = 1; i<=N; i++){
                S[i] = sc.nextInt();

                long cnt = 0;
                for(int j=1; j<S[i]; j++){
                    if (visited[j]==false){
                        cnt++;
                    }
                }
                K+=cnt*F[N-i];
                visited[S[i]] = true;
            }
            System.out.println(K);
        }
    }
}