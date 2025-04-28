import java.util.*;
import java.io.*;

class Main{

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int test=0; test<T; test++){
            st = new StringTokenizer(br.readLine());

            int K  = Integer.parseInt(st.nextToken());

            TreeMap<Integer, Integer> map = new TreeMap<>();

            for(int i=0; i<K; i++){

                st = new StringTokenizer(br.readLine());

                String command = st.nextToken();
                int n = Integer.parseInt(st.nextToken());

                if(command.equals("I")){
                    map.put(n, map.getOrDefault(n, 0) + 1);
                }else{
                    if (map.isEmpty()) continue;

                    if (n == 1) {

                        int maxKey = map.lastKey();
                        if (map.get(maxKey) == 1) map.remove(maxKey);
                        else map.put(maxKey, map.get(maxKey) - 1);
                    } else {

                        int minKey = map.firstKey();
                        if (map.get(minKey) == 1) map.remove(minKey);
                        else map.put(minKey, map.get(minKey) - 1);
                    }
                }
            }

            if (map.isEmpty()) {
                System.out.println("EMPTY");
            } else {
                System.out.println(map.lastKey() + " " + map.firstKey());
            }
        }
    }
}