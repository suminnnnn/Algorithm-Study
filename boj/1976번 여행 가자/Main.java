import java.io.*;
import java.util.*;

class Main{

    static int n;
    static int m;
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

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());

        parent = new int[n+1];

        for(int i=0; i<=n; i++){
            parent[i] = i;
        }

        for(int i=1; i<=n; i++){
            st =  new StringTokenizer(br.readLine());

            for(int j=1; j<=n; j++){
                int info = Integer.parseInt(st.nextToken());

                if(info==1){
                    union(i,j);
                }
            }
        }

        st = new StringTokenizer(br.readLine());

        int parent = find(Integer.parseInt(st.nextToken()));

        for(int i=0; i<m-1; i++){
            int city = Integer.parseInt(st.nextToken());

            city = find(city);

            if(city!=parent){
                System.out.println("NO");
                return;
            }else{
                parent = city;
            }
        }

        System.out.println("YES");
    }
}