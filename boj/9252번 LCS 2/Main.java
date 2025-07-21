import java.io.*;
import java.util.*;

class Main {

    static char[] strA, strB;
    static long[][] D;
    static ArrayList<Character> Path;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        strA = br.readLine().toCharArray();
        strB = br.readLine().toCharArray();

        D = new long[strA.length+1][strB.length+1];

        Path = new ArrayList<Character>();

        for(int i=1; i<=strA.length; i++){
            for(int j=1; j<=strB.length; j++){
                if(strA[i-1]==strB[j-1]){
                    D[i][j] = D[i-1][j-1] +1;
                }else{
                    D[i][j] = Math.max(D[i-1][j], D[i][j-1]);
                }
            }
        }

        System.out.println(D[strA.length][strB.length]);
        getText(strA.length, strB.length);
        for(int i = Path.size()-1; i>=0; i--){
            System.out.print(Path.get(i));
        }
        System.out.println();
    }

    private static void getText(int r, int c){
        if(r==0 || c==0){
            return;
        }
        if(strA[r-1]==strB[c-1]){
            Path.add(strA[r-1]);
            getText(r-1, c-1);
        } else {
            if(D[r-1][c]>D[r][c-1]){
                getText(r-1,c);
            }else{
                getText(r, c-1);
            }
        }
    }
}