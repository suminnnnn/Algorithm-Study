import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int r;
    static int c;
    static int count=0;
    static int result=-1;

    public static void solution() throws Exception {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        r = sc.nextInt();
        c = sc.nextInt();

        int power = (int)Math.pow(2,N);
        
        Z(0, 0, power);

        System.out.println(result);
    }

    public static void Z(int x, int y, int size) {
        if(result!=-1) return;

        if(size==1){
            if(x==r && y==c){
                result = count;
            }

            count++;
            return;
        }

        if (r < x || r >= x + size || c < y || c >= y + size) {
            count += size * size;
            return;
        }

        int newSize = size/2;

        Z(x, y, newSize);
        Z(x, y+newSize, newSize);
        Z(x+newSize, y, newSize);
        Z(x+newSize, y+newSize, newSize);
    }

    public static void main(String[] args) throws Exception {
        Main.solution();
    }
}
