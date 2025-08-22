import java.util.*;
import java.io.*;

class Edge{
    int s;
    int e;
    int w;

    public Edge(int s, int e, int w){
        this.s = s;
        this.e = e;
        this.w = w;
    }
}

class Main{

    static int p,w,c,v;
    static LinkedList<Edge> edges;
    static int[] parent;

    private static void union(int a, int b){
        a = find(a);
        b = find(b);

        if(b!=a)
            parent[b] = a;
    }

    private static int find(int a){
        if(a==parent[a])
            return a;
        else{
            return parent[a] = find(parent[a]);
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        p = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        parent = new int[p];
        for(int i = 0; i<p; i++){
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        c = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());

        edges = new LinkedList<Edge>();

        for(int i = 0; i<w; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            edges.add(new Edge(s,e,w));
        }

        edges.sort((o1,o2)-> Integer.compare(o2.w, o1.w));

        int min = Integer.MAX_VALUE;

        for(Edge edge: edges){
            union(edge.s, edge.e);

            if(find(c)==find(v)){
                System.out.println(edge.w);
                return;
            }
        }
    }
}
