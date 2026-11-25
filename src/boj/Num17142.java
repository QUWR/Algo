package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Num17142 {

    static int n;
    static int m;
    static int blank;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int answer = Integer.MAX_VALUE;
    static int[][] arr;
    static List<int[]> loc;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        loc = new ArrayList<>();
        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 2) {
                    loc.add(new int[] {i, j});
                } else if(arr[i][j] == 0) blank++;
            }
        }

        int[][] active = new int[m][2];

        getMin(0, 0, active);
        if(answer != Integer.MAX_VALUE) System.out.println(answer);
        else System.out.println("-1");
    }

    static void getMin(int index, int num, int[][] active) {
        if(index == m) {
            bfs(active);
            return;
        } else if(num == loc.size()) return;
        int[] l = loc.get(num);
        active[index][0] = l[0];
        active[index][1] = l[1];

        getMin(index+1, num+1, active);
        getMin(index, num+1, active);


    }

    static void bfs(int[][] active) {
        Deque<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][n];
        int cnt = 0;
        int time = 0;
        for(int i = 0; i < m; i++) {
            int x = active[i][0];
            int y = active[i][1];

            queue.offer(new int[] {x, y, 0});
            visited[x][y] = true;
        }

        while(!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];
            int t = poll[2];
            if(arr[x][y] == 0) time = Math.max(time, t);
            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny=  y + dy[i];
                if(isValid(nx, ny) && !visited[nx][ny] && arr[nx][ny] != 1) {
                    queue.offer(new int[] {nx, ny, t+1});
                    visited[nx][ny] = true;
                    if (arr[nx][ny] == 0) {
                        cnt++;
                    }
                }
            }
        }

        if(cnt == blank) answer = Math.min(answer, time);
    }

    static boolean isValid(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }
}
