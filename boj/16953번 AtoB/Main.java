import java.io.*;
import java.util.*;

public class Main {

    public static int A;
    public static int B;
    public static LinkedList<Integer> answer;

    public static void solution() throws Exception {
        Scanner sc = new Scanner(System.in);

        answer = new LinkedList<Integer>();

        A = sc.nextInt();
        B = sc.nextInt();

        DFS(A,1);

        Collections.sort(answer);
        
        if(answer.isEmpty()){
            System.out.println(-1);
        }else {
            System.out.println(answer.getFirst());
        }

    }
    
    public static void DFS(long current, int count){
        if(current==B){
            answer.add(count);
            return;
        } else if(current>B){
            return;
        } else {
            DFS(current*2, count+1);
            DFS(current*10 +1, count+1);
        }
    }

    public static void main(String[] args) throws Exception {
        Main.solution();
    }
}
