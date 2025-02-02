import java.util.*;
import java.io.*;

public class Main {

    public static void solution() throws Exception {
        Scanner sc = new Scanner(System.in);

        double num = sc.nextDouble();

        while(true){
            double now = sc.nextDouble();

            if(now==999)
                break;
            
            System.out.printf("%.2f\n", now-num);

            num = now;
        }
    }
    public static void main(String[] args) throws Exception{
        Main.solution();
    }
}
