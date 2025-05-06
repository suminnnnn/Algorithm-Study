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

    static int v,e;
    static PriorityQueue<Edge> pq;
    static int[] parent;

    public static void union(int a, int b){
        a = find(a);
        b = find(b);

        if(a!=b){
            parent[b] = a;
        }
    }

    public static int find(int v){
        if(v==parent[v]){
            return v;
        }else{
            return parent[v] = find(parent[v]);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        pq = new PriorityQueue<Edge>((o1,o2)-> Integer.compare(o1.w, o2.w));
        parent = new int[v+1];

        for(int i=0; i<v; i++){
            parent[i] = i;
        }

        for(int i=0; i<e; i++){
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            pq.add(new Edge(s,e,w));

        }

        int useEdge = 0;
        int result = 0;

        while(useEdge < v-1){
            Edge now = pq.poll();

            if(find(now.s) != find(now.e)){
                union(now.s, now.e);
                result += now.w;
                useEdge++;
            }
        }

        System.out.println(result);

    }
}