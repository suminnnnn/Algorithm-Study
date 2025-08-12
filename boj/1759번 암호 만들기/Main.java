import java.io.*;
import java.util.*;

class Main{

    static int L, C;
    static char[] alphabet;

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(bufferedReader.readLine());

        alphabet = new char[C];

        HashSet<Character> isAEIOU = new HashSet<Character>();
        isAEIOU.add('a');
        isAEIOU.add('e');
        isAEIOU.add('i');
        isAEIOU.add('o');
        isAEIOU.add('u');

        for(int i=0; i<C; i++){
            alphabet[i] = st.nextToken().toCharArray()[0];
        }

        HashSet<String> set = new HashSet<String>();

        for(int i = 0; i<(1<<C); i++){
            int oneCnt = 0;
            int aeiou = 0;
            int bcdfg = 0;

            for(int j=0; j<C; j++){

                if((i&(1<<j)) != 0){
                    oneCnt++;
                    if(isAEIOU.contains(alphabet[j])){
                        aeiou++;
                    }else{
                        bcdfg++;
                    }
                }
            }

            if(oneCnt!=L || aeiou<1 || bcdfg<2)
                continue;

            oneCnt = 0;
            char[] answer = new char[L];

            for(int j=0; j<C; j++){
                if((i&(1<<j)) != 0){
                    answer[oneCnt] = alphabet[j];
                    oneCnt++;
                }
            }
            Arrays.sort(answer);

            set.add(String.valueOf(answer));
        }

        Object[] arr = set.toArray();
        Arrays.sort(arr);

        for(int i = 0; i<arr.length; i++){
            System.out.println(arr[i]);
        }
    }
}