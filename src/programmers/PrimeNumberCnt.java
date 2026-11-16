package programmers;

public class PrimeNumberCnt {

    public static void main(String[] args) {
        
    }

    class Solution {
        public int solution(int n, int k) {
            String num = Integer.toString(n, k);

            int answer = 0;
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < num.length(); i++) {
                if(num.charAt(i) == '0'){
                    if(sb.length() != 0 && isPrime(sb.toString())) {
                        answer++;
                    }
                    sb = new StringBuilder();
                } else {
                    sb.append(num.charAt(i));
                }
            }
            if(sb.length() != 0 && isPrime(sb.toString())) {
                answer++;
            }
            return answer;

        }

        boolean isPrime(String num) {
            long x = Long.parseLong(num);
            if(x == 1) return false;
            if(x < 4) return true;
            long sqrt = (long)Math.sqrt(x);
            for(long i = 2 ;i <= sqrt; i++) {
                if(x%i == 0) return false;
            }
            return true;
        }
    }
}
