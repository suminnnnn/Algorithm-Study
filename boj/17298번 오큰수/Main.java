import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();

        Stack<Integer> A = new Stack<>();

        Stack<Integer> result = new Stack<>();

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++){
            stack.push(Integer.parseInt(st.nextToken()));
        }

        for(int i=0; i<N; i++) {
            int current = stack.peek();

            if(A.isEmpty())
                result.push(-1);
            else{
                boolean flag = false;
                for(int j=A.size()-1; j>-1; j--){
                    if(A.get(j)>current){
                        result.push(A.get(j));
                        flag = true;
                        break;
                    } else {
                        A.remove(j);
                    }
                }
                if(!flag)
                    result.push(-1);
            }

            A.push(stack.pop());
        }

        StringBuilder bf = new StringBuilder();

        for(int i=result.size()-1; i>-1; i--){
            bf.append(result.get(i)+" ");
        }

        System.out.println(bf.toString().trim());
    }

    public static void main(String[] args) throws Exception {
        Main.solution();
    }
}
