package programmers;

import java.util.ArrayDeque;
import java.util.Deque;

public class MazeEscape {

    static class Solution {
        static int n;
        static int m;
        static int[] dx = new int[] {0, 0, 1, -1};
        static int[] dy = new int[] {1, -1, 0, 0};

        public int solution(String[] maps) {
            int[] start = new int[2];
            int[] end = new int[2];
            int[] laber = new int[2];

            n = maps.length;
            m = maps[0].length();

            for(int i = 0 ; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    if(maps[i].charAt(j) == 'S') {
                        start[0] = i;
                        start[1] = j;
                    } else if(maps[i].charAt(j) == 'E') {
                        end[0] = i;
                        end[1] = j;
                    } else if(maps[i].charAt(j) == 'L') {
                        laber[0] = i;
                        laber[1] = j;
                    }
                }
            }

            return getMin(start, laber, end, maps);
        }

        int getMin(int[] start, int[] laber, int[] end, String[] maps) {
            int toLaber = bfs(start, laber, maps);
            if(toLaber == -1) return -1;
            int toEnd = bfs(laber, end, maps);
            if(toEnd == -1) return -1;
            return toLaber + toEnd;
        }

        int bfs(int[] from, int[] to, String[] maps) {
            int cnt = -1;
            boolean[][] visited = new boolean[n][m];

            Deque<int[]> queue = new ArrayDeque<>();
            queue.offer(new int[] {from[0], from[1], 0});
            visited[from[0]][from[1]] = true;
            while(!queue.isEmpty()) {
                int[] poll = queue.poll();
                int x = poll[0];
                int y = poll[1];
                int t = poll[2];
                if(x == to[0] && y == to[1]) {
                    cnt = t;
                    break;
                }
                for(int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if(isValid(nx, ny) && maps[nx].charAt(ny) != 'X' && !visited[nx][ny]) {
                        queue.offer(new int[] {nx, ny, t + 1});
                        visited[nx][ny] = true;
                    }
                }
            }

            return cnt;
        }

        boolean isValid(int x, int y) {
            return x >= 0 && x < n && y >= 0 && y < m;
        }
    }
}
