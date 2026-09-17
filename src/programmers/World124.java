package programmers;

public class World124 {

    public static void main(String[] args) {
        Solution s = new Solution();
        String result = s.solution(1);

        System.out.println(result);
    }

    static class Solution {
        public String solution(int n) {
            StringBuilder sb = new StringBuilder();
            while(n > 0) {
                int temp = n%3;
                if(temp == 1) {
                    sb.append("1");
                    n = n/3;
                } else if(temp == 2) {
                    sb.append("2");
                    n = n/3;
                } else{
                    sb.append("4");
                    n = n/3 - 1;
                }

            }

            return sb.reverse().toString();
        }
    }
}
