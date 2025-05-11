import java.io.*;
import java.util.*;

class Main{
    static long[] tree;
    static int treeSize;
    static int startIdx;
    static int treeDepth;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int num = 1;

        for(int i=0; i<20; i++){
            num *= 2;

            if(num>=n){
                treeDepth = i+1;
                startIdx = num;
                treeSize = num *2;
                break;
            }
        }

        tree = new long[treeSize];
        Arrays.fill(tree, 1);

        for(int i=0; i<n; i++){
            tree[i+startIdx] = (Long.parseLong(br.readLine()))%1000000007;
        }

        int base = startIdx;

        for(int i=(treeDepth); i>0; i--){
            for(int j=0; j<Math.pow(2,i); j=j+2){
                int left = base + j;
                int right = base + j +1;

                if(right < tree.length){
                    tree[(left)/2] = (tree[left] * tree[right])%1000000007;
                }else{
                    tree[(left)/2] = tree[left]%1000000007;
                }
            }

            base = base/2;
        }

        for(int i=0; i<(m+k); i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if(a==1)
                update(b,c);
            else
                multiply(b,c);
        }
    }

    public static void update(int b, long c){
        b = b + (treeSize/2) - 1;

        tree[b] = c;

        while (b>1){
            b /=2;
            tree[b] = (tree[b * 2] * tree[b * 2 + 1]) % 1000000007;
        }
    }

    public static void multiply(int b, long c){
        b = b + (treeSize/2) - 1;
        c = c + (treeSize/2) - 1;

        long multiple = 1;

        while(b<=c){
            if(b%2==1){
                multiple = (multiple * tree[b])%1000000007;
                b++;
            }
            if(c%2==0){
                multiple = (multiple * tree[(int)c])%1000000007;
                c--;
            }

            b /= 2;
            c /= 2;
        }

        System.out.println(multiple);
    }
}