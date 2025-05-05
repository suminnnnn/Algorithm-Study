import java.io.*;
import java.util.*;

class Edge {
    int s;
    int e;
    int w;

    public Edge(int s, int e, int w){
        this.s = s;
        this.e = e;
        this.w = w;

    }
}

class Main {

    static int n,m;
    static Edge A[];
    static long[] dir;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        A = new Edge[m+1];

        dir = new long[n+1];

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            A[i] = new Edge(s,e,w);
        }

        Arrays.fill(dir, INF);
        dir[1] = 0;

        for(int i=1; i<n; i++){
            for(int j=0; j<m; j++){
                Edge now = A[j];

                if(dir[now.s]!=INF && dir[now.e]>dir[now.s] + now.w){
                    dir[now.e] = dir[now.s] + now.w;
                }
            }
        }

        boolean cycle = false;

        for(int i=0; i<m; i++){
            Edge edge = A[i];

            if(dir[edge.s] != INF && dir[edge.e] > dir[edge.s] + edge.w ){
                cycle = true;
            }
        }

        if(!cycle){
            for(int i=2; i<=n; i++){
                if(dir[i]==INF)
                    System.out.println("-1");
                else
                    System.out.println(dir[i]);
            }
        }else{
            System.out.println("-1");
        }

    }
}