import java.io.*;
import java.util.*;

class Main {

    static int n,m;
    static int startIdx;
    static int treeSize, treeDepth;
    static int[] tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int num=1;
        startIdx=0;
        treeDepth=0;

        for(int i=1; i<30; i++){
            num*=2;

            if(num>=n){
                startIdx = num;
                treeSize = num *2;
                treeDepth = i+1;
                break;
            }
        }

        tree = new int[treeSize];
        Arrays.fill(tree, 1000000000);

        for(int i=0; i<n; i++){
            tree[i+startIdx] = Integer.parseInt(br.readLine());
        }

        for(int i=treeDepth-1; i>0; i--){
            for(int j=0; j < (1<<i); j+=2){
                int start = startIdx + j;
                int end = startIdx + j +1;

                if(end < tree.length){
                    tree[start/2] = Math.min(tree[start], tree[end]);
                }else{
                    tree[start/2] = tree[start];
                }
            }

            startIdx /= 2;
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            findMin(a,b);
        }

    }

    public static void findMin(int a, int b){
        a = a + (treeSize/2)-1;
        b = b + (treeSize/2)-1;

        int min = Integer.MAX_VALUE;

        while(a<=b){
            if(a%2==1){
                min = Math.min(min, tree[a]);
                a++;
            }
            if(b%2==0){
                min = Math.min(min, tree[b]);
                b--;
            }

            a /= 2;
            b /= 2;
        }

        System.out.println(min);
    }
}