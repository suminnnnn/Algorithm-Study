import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        List<int[]> items = new ArrayList<>();
        long totalWeight = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            items.add(new int[]{x, a});
            totalWeight += a;
        }

        items.sort((o1, o2) -> Integer.compare(o1[0], o2[0]));

        long halfWeight = (totalWeight + 1) / 2;
        long currentWeight = 0;
        int result = items.get(0)[0];

        for (int[] item : items) {
            currentWeight += item[1];
            if (currentWeight >= halfWeight) {
                result = item[0];
                break;
            }
        }

        System.out.println(result);
    }
}