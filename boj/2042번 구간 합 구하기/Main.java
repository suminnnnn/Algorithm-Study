import java.io.*;
import java.util.*;

class Main{

    static int n,m,k;
    static long[] tree;
    static int treeSize;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        int num = 1;
        int startIdx = 0;
        treeSize = 0;
        int treedepth = 0;

        for(int i=1; i<65; i++){
            if(num>=n){
                startIdx = num;
                treeSize = num*2;
                treedepth = i;
                break;
            }else{
                num = num*2;
            }
        }

        tree = new long[treeSize];

        for(int i=0; i<n; i++){
            tree[i+startIdx] = Long.parseLong(br.readLine());
        }

        for(int i=(treedepth-1); i>0; i--){
            for (int j = 0; j < (1 << i); j += 2) {
                int left = startIdx + j;
                int right = startIdx + j + 1;

                if (right < tree.length) {
                    tree[(left) / 2] = tree[left] + tree[right];
                } else {
                    tree[(left) / 2] = tree[left];
                }
            }
            startIdx = startIdx / 2;
        }

        for(int i=0; i<(m+k); i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if(a==1){
                update(b,c);
            }else{
                sum(b,c);
            }
        }
    }

    public static void update(int b, long c){
        b = b + (treeSize/2)-1;
        tree[b] = c;

        while(b>0){
            if(b%2==0){
                tree[b/2] = tree[b] + tree[b+1];
            }else{
                tree[b/2] = tree[b-1] + tree[b];
            }

            b=b/2;
        }
    }

    public static void sum(int start, long end){
        start = start + (treeSize/2)-1;
        end = end + (treeSize/2)-1;

        long sum = 0;

        while(start<=end){
            if(start % 2==1){
                sum += tree[start];
                start++;
            }
            if(end % 2 ==0){
                sum += tree[(int)end];
                end--;
            }

            start /= 2;
            end /= 2;
        }

        System.out.println(sum);
    }
}