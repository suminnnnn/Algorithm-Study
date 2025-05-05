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

    static int n,m, sCity, eCity;
    static Edge[] A;
    static long[] distance, cityMoney;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        sCity = Integer.parseInt(st.nextToken());
        eCity = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        A = new Edge[m];
        distance = new long[n];
        cityMoney = new long[n];

        Arrays.fill(distance, Long.MIN_VALUE);

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            A[i] = new Edge(s,e,w);
        }

        st = new StringTokenizer(br.readLine());

        for(int i=0; i<n; i++){
            int m = Integer.parseInt(st.nextToken());
            cityMoney[i] = m;
        }

        distance[sCity] = cityMoney[sCity];

        for(int i=0; i<=n+100; i++){
            for(int j=0; j<m; j++){
                Edge now = A[j];

                if(distance[now.s] == Long.MIN_VALUE) continue;
                else if(distance[now.s] == Long.MAX_VALUE){
                    distance[now.e] = Long.MAX_VALUE;
                }

                else if(distance[now.e] < distance[now.s] + cityMoney[now.e] - now.w){
                    distance[now.e] = distance[now.s] + cityMoney[now.e] - now.w;

                    if(i >= n-1) distance[now.e] = Long.MAX_VALUE;
                }
            }
        }

        if(distance[eCity] == Long.MIN_VALUE) System.out.println("gg");
        else if(distance[eCity] == Long.MAX_VALUE) System.out.println("Gee");
        else System.out.println(distance[eCity]);
    }
}