import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int S = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        char[] A = new char[S];
        A = br.readLine().toCharArray();

        int[] min = new int[4];
        int[] cnt = new int[4];

        st = new StringTokenizer(br.readLine());

        for (int i=0; i<4; i++) {
            min[i] = Integer.parseInt(st.nextToken());        }

        int start = 0;
        int end = start + (P-1);
        int result = 0;

        for (int i=0; i<P ; i++){
            if (A[i]=='A')
                cnt[0]++;
            else if(A[i]=='C')
                cnt[1]++;
            else if(A[i]=='G')
                cnt[2]++;
            else if(A[i]=='T')
                cnt[3]++;
        }

        if ((cnt[0] >= min[0]) && (cnt[1] >= min[1]) && (cnt[2] >= min[2]) && (cnt[3] >= min[3]) )
            result++;

        while (end<(S-1)) {
            if (A[start]=='A')
                cnt[0]--;
            else if(A[start]=='C')
                cnt[1]--;
            else if(A[start]=='G')
                cnt[2]--;
            else if(A[start]=='T')
                cnt[3]--;
            start ++;
            
            end++;

            if (A[end]=='A')
                cnt[0]++;
            else if(A[end]=='C')
                cnt[1]++;
            else if(A[end]=='G')
                cnt[2]++;
            else if(A[end]=='T')
                cnt[3]++;
            
            if ((cnt[0] >= min[0]) && (cnt[1] >= min[1]) && (cnt[2] >= min[2]) && (cnt[3] >= min[3]) )
                result++;
            if (end==(S-1))
                break;
        }

        System.out.println(result);
        br.close();
        
    }

    public static void main(String[] args) throws Exception {
        Main.solution();
    }
}
