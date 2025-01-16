import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 입력 N
        int[] A = new int[N]; // 배열 A

        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt(); // 배열 입력
        }

        // 모든 순열을 저장할 리스트
        List<List<Integer>> permutations = new ArrayList<>();
        permute(A, 0, permutations);

        int maxSum = 0;

        for (List<Integer> perm : permutations) {
            int currentSum = 0;
            for (int i = 0; i < N - 1; i++) {
                currentSum += Math.abs(perm.get(i) - perm.get(i + 1));
            }
            maxSum = Math.max(maxSum, currentSum);
        }

        System.out.println(maxSum);
    }

    public static void permute(int[] arr, int depth, List<List<Integer>> result) {
        if (depth == arr.length) {
            List<Integer> current = new ArrayList<>();
            for (int num : arr) {
                current.add(num);
            }
            result.add(current);
            return;
        }

        for (int i = depth; i < arr.length; i++) {
            swap(arr, depth, i);
            permute(arr, depth + 1, result);
            swap(arr, depth, i);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
