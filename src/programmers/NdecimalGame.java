package programmers;

public class NdecimalGame {

    public static void main(String[] args) {

    }
    static class Solution {
        public String solution(int n, int t, int m, int p) {
            StringBuilder sb = new StringBuilder();
            int index = 0;
            sb.append(0);
            while(sb.length() < t*m) {
                String temp = getNum(index, n);
                sb.append(temp);
                index++;
            }

            String num = sb.toString();
            System.out.println(num);
            StringBuilder answer = new StringBuilder();
            for(int i = 0; i < t; i++) {
                answer.append(num.charAt(p + i*m - 1));
            }
            return answer.toString();
        }


        String getNum(int index, int n) {
            StringBuilder sb = new StringBuilder();

            while(index > 0) {

                String temp = switch(index % n) {
                    case 10 -> "A";
                    case 11 -> "B";
                    case 12 -> "C";
                    case 13 -> "D";
                    case 14 -> "E";
                    case 15 -> "F";
                    default -> String.valueOf(index%n);
                };

                sb.append(temp);
                index = index/n;
            }
            return sb.reverse().toString();
        }
    }
}
