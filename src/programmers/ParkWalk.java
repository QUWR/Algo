package programmers;

public class ParkWalk {

    public static void main(String[] args) {

    }

    static class Solution {

        static int[] dx = {-1, 1, 0, 0};
        static int[] dy = {0, 0, -1, 1};
        static int n;
        static int m;
        public int[] solution(String[] park, String[] routes) {
            n = park.length;
            m = park[0].length();

            int x = -1;
            int y = -1;
            for(int i = 0;i < n; i++) {
                for(int j = 0; j < m; j++) {
                    if(park[i].charAt(j) == 'S'){
                        x = i;
                        y = j;
                        break;
                    }
                }
                if(x != -1 && y != -1) break;
            }

            for(int i = 0; i< routes.length; i++) {
                String[] cmd = routes[i].split(" ");
                int l = Integer.parseInt(cmd[1]);
                int d = -1;
                if(cmd[0].equals("N")) {
                    d = 0;
                } else if(cmd[0].equals("S")){
                    d = 1;
                } else if(cmd[0].equals("W")) {
                    d = 2;
                } else if(cmd[0].equals("E")) {
                    d = 3;
                }
                int nx = x;
                int ny = y;
                boolean canMove = true;
                for(int j = 1 ; j <= l; j++) {
                    nx += dx[d];
                    ny += dy[d];
                    if(!isValid(nx, ny)) {
                        canMove = false;
                        break;
                    }
                    if(park[nx].charAt(ny) == 'X') {
                        canMove = false;
                        break;
                    }
                }
                if(canMove) {
                    x = nx;
                    y = ny;
                }


            }

            return new int[] {x, y};
        }

        boolean isValid(int x, int y) {
            return x >= 0 && x < n && y >= 0 && y < m;
        }
    }
}
