package programmers;

import java.util.*;

public class DecodeSecretKey {


    static class Solution {
        static Set<String> answer;
        static int m;

        public int solution(int n, int[][] q, int[] ans) {
            answer = new HashSet<>();
            m = q.length;

            getKeys(0, q, ans, new int[m], new int[5], 1, n);

            return answer.size();

        }

        void getKeys(int idx, int[][] q, int[] ans, int[] cnt, int[] key, int x, int n) {
            if(idx == 5) {
                //조건 확인
                if(isKey(cnt, ans)) {
                    //Srting 변환후 저장
                    answer.add(makeKey(key));
                }
                return;
            }
            if(x == n+1) return;

            key[idx] = x;

            boolean canProceed = true;
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < 5; j++) {
                    if(q[i][j] == x) {
                        cnt[i]++;
                        if(cnt[i] > ans[i]) canProceed = false;
                        break;
                    }
                }
            }
            if(canProceed) {
                getKeys(idx + 1, q, ans, cnt, key, x + 1, n);
            }

            for(int i = 0; i < m; i++) {
                for(int j = 0; j < 5; j++) {
                    if(q[i][j] == x) {
                        cnt[i]--;
                        break;
                    }
                }
            }
            getKeys(idx, q, ans, cnt, key, x+1, n);
        }

        boolean isKey(int[] cnt, int[] ans) {
            for(int i = 0; i < m; i++) {
                if(cnt[i] != ans[i]) return false;
            }

            return true;
        }

        String makeKey(int[] key) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < 5; i++) {
                sb.append(key[i]);
            }
            return sb.toString();
        }
    }
}
