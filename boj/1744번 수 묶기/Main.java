import java.util.*;
import java.io.*;

public class Main {
    
    public static void solution() throws Exception {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        PriorityQueue<Integer> positive = new PriorityQueue<Integer>();
        PriorityQueue<Integer> negative = new PriorityQueue<Integer>();

        int zeroCnt = 0;
        int oneCnt = 0;

        int sum = 0;

        for(int i=0; i<N; i++){
            int num = sc.nextInt();

            if(num==0)
                zeroCnt++;
            else if(num==1)
                oneCnt++;
            else if(num>0){
                positive.offer((-1)*num);
            }
            else if(num<0){
                negative.offer(num);
            }
        }

        while(negative.size()>=2){
            int data1 = negative.remove();
            int data2 = negative.remove();

            sum += ((data1) * (data2));
        }

        while (positive.size()>=2){
            int data1 = (-1)*positive.remove();
            int data2 = (-1)*positive.remove();

            sum += ((data1) * (data2));
        }

        if(negative.size()>0){
            int data = negative.remove();

            if(zeroCnt==0)
                sum += data;
        }

        if(positive.size()>0){
            int data = positive.remove();

            if ((-1)*data!=1)
                sum += (-1)*data;
        }

        sum += oneCnt;

        System.out.println(sum);
    }

    public static void main(String[] args) throws Exception {
        Main.solution();
    }
}
