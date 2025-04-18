import java.util.*;

class Solution {
    public int solution(int[] elements) {
        int answer = 0;
        Set<Integer> set = new HashSet<Integer>();

        for(int i=0; i<elements.length; i++){
            int sum = 0;
            int newSum = 0;
            for(int j=i; j<(i+elements.length); j++){
                newSum = sum + elements[j%elements.length];
                set.add(newSum);
                sum = newSum;
            }
        }

        return set.size();
    }
}