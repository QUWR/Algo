package programmers;

import java.util.PriorityQueue;

public class NewsCluster {

    static class Solution {
        PriorityQueue<String> str1Set;
        PriorityQueue<String> str2Set;
        public int solution(String str1, String str2) {
            str1Set = getSet(str1);
            str2Set = getSet(str2);

            int n = str1Set.size() + str2Set.size();
            if(n == 0) return 65536;

            int m = 0;
            while(!str1Set.isEmpty() && !str2Set.isEmpty()) {
                if(str1Set.peek().equals(str2Set.peek())) {
                    m++;
                    str1Set.poll();
                    str2Set.poll();
                } else if(str1Set.peek().compareTo(str2Set.peek()) < 0) {
                    str1Set.poll();
                } else {
                    str2Set.poll();
                }
            }


            return 65536 * m / (n-m);


        }

        PriorityQueue<String> getSet(String str) {
            int n = str.length();
            String tmp = str.toUpperCase();
            PriorityQueue<String> strSet = new PriorityQueue<>();

            for(int i = 0; i < n-1; i++) {
                StringBuilder sb = new StringBuilder();
                if(isValid(tmp.charAt(i)) && isValid(tmp.charAt(i+1))) {
                    sb.append(tmp.charAt(i)).append(tmp.charAt(i+1));
                    strSet.add(sb.toString());
                }

            }

            return strSet;
        }

        boolean isValid(char c) {
            return c >= 'A' && c <= 'Z';
        }
    }
}
