import java.io.*;
        import java.util.*;

class Main {

    static int N;
    static int[] col;
    static int answer;

    private static void backtrack(int row){
        if(row==N){
            answer++;
            return;
        }

        for(int c=0; c<N; c++){
            if(isSafe(row, c)){
                col[row] = c;
                backtrack(row+1);
            }
        }
    }

    private static boolean isSafe(int row, int c){
        for(int i = 0; i<row; i++){
            if(col[i]==c || Math.abs(row-i) == Math.abs(col[i]-c)) return false;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        N = scanner.nextInt();
        col = new int[N];
        answer = 0;

        backtrack(0);
        System.out.println(answer);
    }
}