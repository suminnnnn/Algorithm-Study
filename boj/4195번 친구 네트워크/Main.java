import java.io.*;
import java.util.*;

class Main {

    static HashMap<String, String> parent;
    static HashMap<String, Integer> count;
    static int group;

    private static int union(String a, String b){
        String parentA = find(a);
        String parentB = find(b);

        if(!parentA.equals(parentB)){
            parent.put(parentB, parentA);
            count.put(parentA, count.get(parentB)+count.get(parentA));
            return count.get(parentA);
        }

        return count.get(parentA);
    }

    private static String find(String a){
        if(a.equals(parent.get(a))){
           return a;
        }

        String root = find(parent.get(a));
        parent.put(a, root);
        return root;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int testCase = Integer.parseInt(st.nextToken());

        for(int t = 0; t<testCase; t++){
            st = new StringTokenizer(br.readLine());
            int network = Integer.parseInt(st.nextToken());

            parent=new HashMap<String,String>();
            count = new HashMap<>();

            for(int n=0; n<network; n++){
                st = new StringTokenizer(br.readLine());
                String f1 = st.nextToken();
                String f2 = st.nextToken();

                if (!parent.containsKey(f1)) {
                    parent.put(f1, f1);
                    count.put(f1, 1);
                }
                if (!parent.containsKey(f2)) {
                    parent.put(f2, f2);
                    count.put(f2, 1);
                }

                System.out.println(union(f1, f2));
            }
        }
    }
}