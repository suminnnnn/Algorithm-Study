import java.util.*;

class Solution {

    public int solution(int[] numbers, int target) {
        int answer = 0;

        Queue<int[]> queue = new LinkedList<int[]>();

        queue.add(new int[]{0, 0});

        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            int sum = current[0];
            int idx = current[1];

            // numbers 끝까지 왔으면 결과 체크
            if (idx == numbers.length) {
                if (sum == target) {
                    answer++;
                }
                continue;
            }

            queue.add(new int[]{sum + numbers[idx], idx + 1});
            queue.add(new int[]{sum - numbers[idx], idx + 1});
        }

        return answer;
    }
}