import java.util.*;
import java.io.*;

class Paper {
    int index;
    int score;

    public Paper(int index, int score){
        this.index = index;
        this.score = score;
    }
}

public class Main {

    public static void solution() throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        for(int i=0; i<testCase; i++) {
            int count = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            LinkedList<Paper> queue = new LinkedList<>();

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            
            String[] strArr = br.readLine().split(" ");

            for(int j=0; j<strArr.length; j++){
                Paper paper = new Paper(j, Integer.parseInt(strArr[j]));

                queue.add(paper);
            }

            while(!queue.isEmpty()){
                Paper target = queue.peek();

                boolean isHighest = true;

                for(int k=1; k<queue.size(); k++){
                    Paper paper = queue.get(k);

                    if(paper.score > target.score){
                        isHighest = false;
                        break;
                    }
                }

                if(isHighest){
                    Paper paper = queue.poll();
                    count++;
                    if(paper.index == M){
                        System.out.println(count);
                        break;
                    }

                } else {
                    Paper paper = queue.poll();
                    queue.add(paper);
                }
            }


        }

    }
    
    public static void main(String[] args) throws Exception {
        Main.solution();
    }
}
