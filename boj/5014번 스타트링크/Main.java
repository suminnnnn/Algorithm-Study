import java.io.*;
import java.util.*;

class Main {

    public static int F, S, G, U, D;
    public static int[] dist;
    public static int[] buttons;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        F = sc.nextInt();
        S = sc.nextInt();
        G = sc.nextInt();
        U = sc.nextInt();
        D = sc.nextInt();

        dist = new int[F+1];
        Arrays.fill(dist, -1);

        buttons = new int[2];
        buttons[0] = U;
        buttons[1] = (-1)*D;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(S);
        dist[S] = 0;

        while(!queue.isEmpty()) {
            int now = queue.poll();

            if (now==G) {
                System.out.println(dist[now]);
                return;
            }

            for(int i=1; i>=0; i--) {
                int next = now + buttons[i];


                if(next<1 || next>F) continue;

                if(dist[next]>=0) continue;

                dist[next] = dist[now] + 1;
                queue.add(next);
            }
        }

        System.out.println("use the stairs");
    }
}