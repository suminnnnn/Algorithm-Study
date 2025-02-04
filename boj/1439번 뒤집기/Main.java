import java.util.*;

public class Main {
    public static void solution() {
        Scanner sc = new Scanner(System.in);
        
        String line = sc.nextLine();
        
        char[] A = line.toCharArray();

        int one = 0;
        int zero = 0;
        
        if(A[0]=='0') zero++;
        else one++;

        for (int i = 1; i < line.length(); i++) {
            if (A[i] != A[i - 1]) {
                if(A[i]=='1')
                    one++;
                else
                    zero++;
            }
        }

        if(zero<=one)
            System.out.println(zero);
        else
            System.out.println(one);
    }

    public static void main(String[] args) {
        Main.solution();
    }
}
