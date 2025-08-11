class Solution {

    static int bestDiff;
    static int[] bestDist;

    public int[] solution(int n, int[] info) {
        bestDiff = -1;
        bestDist = new int[11];

        for(int mask=0; mask<(1<<11); mask++){

            int arrowsNeed = 0;
            int apeachScore = 0;
            int lionScore = 0;

            int[] dist = new int[11];

            for(int i=0; i<11; i++){
                if((mask&(1<<i))!=0){
                    arrowsNeed += (info[i]+1);
                    dist[i] = info[i]+1;
                }
            }

            if(arrowsNeed > n) {
                continue;
            } else {
                dist[10] += (n - arrowsNeed);
            }

            for(int i=0; i<11; i++){
                if(dist[i]>info[i]){
                    lionScore += (10-i);
                }else if (info[i] > 0) {
                    apeachScore += (10 - i);
                }
            }

            int diff = lionScore - apeachScore;
            if(diff<=0)
                continue;

            if (diff > bestDiff) {
                bestDiff = diff;
                bestDist = dist.clone();
            } else if (diff == bestDiff) {
                for (int i = 10; i >= 0; i--) {
                    if (dist[i] != bestDist[i]) {
                        if (dist[i] > bestDist[i])
                            bestDist = dist.clone();
                        break;
                    }
                }
            }
        }

        if(bestDiff==-1)
            return new int[]{-1};
        else
            return bestDist;
    }
}