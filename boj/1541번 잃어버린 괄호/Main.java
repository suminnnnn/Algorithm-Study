import java.util.*;
import java.io.*;

public class Main {

    public static void solution() throws Exception {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        String[] groups = input.split("-");

        int sum = 0;

        String[] firstGroup = groups[0].split("\\+");
        for (String num : firstGroup) {
            sum += Integer.parseInt(num);
        }

        for (int i = 1; i < groups.length; i++) {
            String[] subGroup = groups[i].split("\\+");
            int subSum = 0;
            for (String num : subGroup) {
                subSum += Integer.parseInt(num);
            }
            sum -= subSum;
        }

        System.out.println(sum);
    }

    public static void main(String[] args) throws Exception {
        Main.solution();
    }
}
