import java.io.*;
import java.util.*;

class Main{

    static long T;
    static int n,m;
    static int[] A,B;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        A = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<n; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        B = new int[m];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<m; i++){
            B[i] = Integer.parseInt(st.nextToken());
        }

        long cnt = 0L;

        List<Long> sumA = new ArrayList<>();
        for(int i = 0; i<n; i++){
            long currentSum = 0;
            for(int j=i; j<n; j++){
                currentSum+=A[j];
                sumA.add(currentSum);
            }
        }

        Collections.sort(sumA);

        List<Long> sumB = new ArrayList<>();
        for(int i = 0; i<m; i++){
            long currentSum = 0;
            for(int j=i; j<m; j++){
                currentSum +=B[j];
                sumB.add(currentSum);
            }
        }

        Collections.sort(sumB);

        int left = 0;
        int right = sumB.size()-1;

        while(left<sumA.size() && right>=0){
            long currentSum = sumA.get(left) + sumB.get(right);

            if(currentSum==T){
                long sumAValue = sumA.get(left);
                long sumBValue = sumB.get(right);

                long countA = 0;
                while(left<sumA.size() && sumA.get(left)==sumAValue){
                    left++;
                    countA++;
                }

                long countB = 0;
                while(right>=0 && sumB.get(right)==sumBValue){
                    right--;
                    countB++;
                }

                cnt += countA*countB;
            }else if(currentSum<T){
                left++;
            }else
                right--;
        }

        System.out.println(cnt);

    }
}