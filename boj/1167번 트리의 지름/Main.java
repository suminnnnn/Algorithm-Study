import java.util.*;
import java.io.*;

class Node{

    int number;
    int distance;

    public Node(int number, int distance) {
        this.number = number;
        this.distance = distance;
    }
}

public class Main {

    static int V;
    static ArrayList<Node>[] A;
    static boolean[] visited;
    static int[] distance;

    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        V = Integer.parseInt(br.readLine());
        A = new ArrayList[V+1];
        visited = new boolean[V+1];
        distance = new int[V+1];

        for(int i=0; i<V; i++){
            String[] line = br.readLine().split(" ");
            visited[i+1] = false;
            
            for (int j=0; j<line.length; j++){
                if(line[j].equals("-1"))
                    break;
                else if(j==0){
                    int node = Integer.parseInt(line[j]);

                    A[node] = new ArrayList<>();
                } else if (j%2==1){

                    int number = Integer.parseInt(line[j]);
                    int distance = Integer.parseInt(line[j+1]);

                    A[Integer.parseInt(line[0])].add(new Node(number, distance));
                }
            }
        }

        BFS(1);

        int max = 1;
        
        for(int i=2; i <=V; i++){
            if (distance[max] < distance[i]){
                max = i;
            }
        }

        distance = new int[V+1];
        visited = new boolean[V+1];
        BFS(max);

        Arrays.sort(distance);

        System.out.println(distance[V]);
    }

    private static void BFS(int start) {

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(start, 0));

        visited[start] = true;

        while(!queue.isEmpty()){
            Node node = queue.poll();

            for (Node n: A[node.number]){
                if (!visited[n.number]){
                    visited[n.number] = true;
                    queue.add(n);
                    distance[n.number] = distance[node.number] + n.distance;
                }
            }
        }

    }

     public static void main(String[] args) throws Exception{
        Main.solution();
     }
}