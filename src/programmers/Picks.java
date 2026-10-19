package programmers;

public class Picks {
    static class Solution {
        static int answer;
        public int solution(int[] picks, String[] minerals) {
            int sum = 0;
            answer = Integer.MAX_VALUE;
            for(int i = 0; i < picks.length; i++) {
                sum += picks[i];
            }
            int n = Math.min((minerals.length + 4) /5, sum);
            int[] pickOrder = new int[n];

            if(n < sum) {
                int[] usedPick = new int[3];
                int cnt = n;
                for(int i = 0 ; i < 3; i++) {
                    if(cnt > picks[i]) {
                        usedPick[i] = picks[i];
                        cnt -= picks[i];
                    } else{
                        usedPick[i] = cnt;
                        break;
                    }
                }
                picks = usedPick;
            }

            getMin(picks, pickOrder, minerals, 0);

            return answer;
        }

        void getMin(int[] picks, int[] pickOrder, String[] minerals, int idx) {
            if(idx == pickOrder.length) {
                int t = getTime(minerals, pickOrder);
                answer = Math.min(t, answer);
                return;
            }

            for(int i = 0; i < 3; i++) {
                if(picks[i] != 0) {
                    picks[i] -= 1;
                    pickOrder[idx] = i;
                    getMin(picks, pickOrder, minerals, idx+1);
                    picks[i] += 1;
                }
            }
        }

        int getTime(String[] minerals, int[] pickOrder) {
            int sum = 0;
            int idx = 0;
            for(int i = 0; i< pickOrder.length; i++) {
                int cnt = 0;
                while(cnt  < 5 && idx < minerals.length) {
                    String mineral = minerals[idx++];
                    cnt++;
                    int m = -1;
                    if(mineral.equals("diamond")) {
                        m = 0;
                    } else if (mineral.equals("iron")){
                        m = 1;
                    } else if(mineral.equals("stone")) {
                        m = 2;
                    }
                    if(m - pickOrder[i] >= 0) {
                        sum += 1;
                    } else if(m - pickOrder[i] == -1) {
                        sum += 5;
                    } else if(m - pickOrder[i] == -2) {
                        sum += 25;
                    }
                }
            }
            return sum;
        }
    }
}
