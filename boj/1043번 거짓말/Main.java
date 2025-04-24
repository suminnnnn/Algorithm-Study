import java.io.*;
import java.util.*;

class Main{

    static int n,m;
    static ArrayList<Integer> known;
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

        known = new ArrayList<Integer>();

        for(int i=0; i<=n; i++){
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());

        int knownNum = Integer.parseInt(st.nextToken());

        for(int i=0; i<knownNum; i++){
            known.add(Integer.parseInt(st.nextToken()));


        }



    }
}