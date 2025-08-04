import java.io.*;
import java.util.*;

class Main {

    static int N,K;
    static int[] dist;
    static final int MAX=200001;

    private static void BFS(int start) {
        Deque<Integer> deque = new ArrayDeque<>();
        dist[start] = 0;
        deque.add(start);

        while(!deque.isEmpty()){
            int now = deque.pollFirst();

            if(now==K)
                return;

            int teleport = now * 2;

            if(teleport<MAX && dist[teleport] == -1){
                deque.addFirst(teleport);
                dist[teleport] = dist[now];
            }

            int back = now-1;
            if(back>=0 && dist[back]==-1){
                dist[back] = dist[now] + 1;
                deque.addLast(back);
            }

            int forward = now+1;
            if(forward<MAX && dist[forward]==-1){
                dist[forward] = dist[now] +1;
                deque.addLast(forward);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();

        dist = new int[MAX];
        Arrays.fill(dist, -1);

        BFS(N);

        System.out.println(dist[K]);



    }
}