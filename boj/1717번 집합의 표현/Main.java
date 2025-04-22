import java.io.*;
import java.util.*;

class Main{

    static int n,m;
    static int[] parent;

    public static int find(int a){
        if(a==parent[a]){
            return a;
        }else{
            return parent[a] = find(parent[a]);
        }
    }

    public static void union(int a, int b){
        a = find(a);
        b = find(b);

        if(a!=b){
            parent[b] = a;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parent = new int[n+1];

        for(int i=0; i<=n; i++){
            parent[i] = i;
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());

            int command = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(command==0){
                union(a,b);
            }else{
                System.out.println(find(a)==find(b)?"YES":"NO");
            }
        }
    }
}