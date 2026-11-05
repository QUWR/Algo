package programmers;

public class TriSnail {


    static class Solution {
        public int[] solution(int n) {
            int[] answer = new int[n*(n+1)/2];
            int[][] arr = new int[n][n];

            int index = 1;
            int start = 0;
            int end = n-1;

            while(index <= n*(n+1)/2) {
                for(int i = start; i <= end; i++) {
                    arr[i][start] = index++;
                }
                for(int i = start+1; i <= end; i++) {
                    arr[end][i] = index++;
                }
                for(int i = end-1 ; i > start; i--) {
                    arr[i][end] = index++;
                }

                start = start+2;
                end = end -1;
            }
            int idx = 0;
            for(int i = 0; i < n; i++) {
                for(int j = 0 ; j < n; j++) {
                    if(arr[i][j] == 0) continue;
                    answer[idx++] = arr[i][j];
                }
            }
            return answer;
        }
    }
}
