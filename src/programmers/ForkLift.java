package programmers;

import java.util.*;

public class ForkLift {

    public static void main(String[] args) {

    }

    static class Solution {
        static int n;
        static int m;
        static int[] dx = {0, 0, 1, -1};
        static int[] dy = {1, -1, 0, 0};

        public int solution(String[] storage, String[] requests) {
            n = storage.length;
            m = storage[0].length();
            char[][] store = new char[n + 2][m + 2];
            for (int i = 0; i < n + 2; i++) {
                Arrays.fill(store[i], '0');
            }

            Map<Character, List<int[]>> map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    char key = storage[i].charAt(j);
                    store[i + 1][j + 1] = key;
                    map.computeIfAbsent(key, k -> new ArrayList<>()).add(new int[]{i + 1, j + 1});
                }
            }

            for(int i = 0; i < requests.length; i++) {
                String s = requests[i];
                char key = s.charAt(0);
                if(map.get(key) == null) continue;

                if(s.length() == 2) {
                    for (int[] loc : map.get(key)) {
                        if (store[loc[0]][loc[1]] == key) {
                            store[loc[0]][loc[1]] = '0';
                        }
                    }
                } else{
                    bfs(store, key);
                }
            }

            int answer = 0;
            for(int i = 1 ; i <= n; i++) {
                for(int j = 1;j <= m; j++) {
                    if(store[i][j] != '0') answer++;
                }
            }

            return answer;
        }

        static void bfs(char[][] store, char target) {
            Queue<int[]> q = new LinkedList<>();
            boolean[][] visited = new boolean[n + 2][m + 2];
            List<int[]> toDelete = new ArrayList<>();

            q.add(new int[]{0, 0});
            visited[0][0] = true;

            while (!q.isEmpty()) {
                int[] curr = q.poll();
                int x = curr[0];
                int y = curr[1];

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (nx >= 0 && nx < n + 2 && ny >= 0 && ny < m + 2 && !visited[nx][ny]) {
                        if (store[nx][ny] == '0') {
                            visited[nx][ny] = true;
                            q.add(new int[]{nx, ny});
                        } else if (store[nx][ny] == target) {
                            visited[nx][ny] = true;
                            toDelete.add(new int[]{nx, ny});
                        }
                    }
                }
            }

            for (int[] loc : toDelete) {
                store[loc[0]][loc[1]] = '0';
            }
        }
    }
}
