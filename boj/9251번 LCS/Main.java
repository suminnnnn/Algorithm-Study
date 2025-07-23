import java.io.*;

class Main{

    static long[][] DP;
    static String A, B;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        A = br.readLine();
        B = br.readLine();

        DP = new long[A.length() +1][B.length() +1];

        for(int i=1; i<=A.length(); i++){
            for(int j=1; j<=B.length(); j++){
                if(A.charAt(i-1)==B.charAt(j-1)){
                    DP[i][j] = DP[i-1][j-1]+1;
                }else{
                    DP[i][j] = Math.max(DP[i-1][j], DP[i][j-1]);
                }
            }
        }

        System.out.println(DP[A.length()][B.length()]);
    }
}