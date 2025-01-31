import java.util.*;
import java.io.*;

public class Main {
    public static void solution() throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i=0; i<3; i++){

            StringTokenizer st = new StringTokenizer(br.readLine());

            
            int zero = 0;
            int one = 0;

            for(int j=0; j<4; j++){
                if(Integer.parseInt(st.nextToken())==0)
                    zero++;
                else
                    one++;
            }

            if(zero==0)
                System.out.println("E");
            else if(zero==1)
                System.out.println("A");
            else if(zero==2)
                System.out.println("B");
            else if(zero==3)
                System.out.println("C");
            else if(zero==4)
                System.out.println("D");
        }

    }
    public static void main(String[] args) throws Exception{
        Main.solution();
    }
}
