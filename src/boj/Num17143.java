package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Num17143 {

/*    static int R;
    static int C;
    static int M;
    static int[][] map;
    static Map<Integer, int[]> sharks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sharks = new HashMap<>();

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[R][C];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken())-1;
            int z = Integer.parseInt(st.nextToken());

            map[r][c] = i+1;
            sharks.put(i+1, new int[] {r, c, s, d, z});
        }

        int answer = 0;
        for(int i = 0; i < C; i++) {

            for(int j = 0; j < R; j++) {
                if(map[j][i] != 0) {
                    answer += sharks.get(map[j][i])[4];
                    sharks.remove(map[j][i]);
                    map[j][i] = 0;
                    break;
                }
            }


            for(int j = 0; j < R; j++) {
                for(int k = 0; k < C; k++) {
                    if(map[j][k] != 0) {
                        int sharkNo = map[j][k];
                        int a = sharks.get(sharkNo)[0];
                        int b = sharks.get(sharkNo)[1];
                        int[] info = move(sharks.get(sharkNo));
                        sharks.put(sharkNo, info);
                        map[j][k] = 0;
                    }
                }
            }
            List<Integer> deadList = new ArrayList<>();


            for (Map.Entry<Integer, int[]> info : sharks.entrySet()) {
                int[] value = info.getValue();
                if(map[value[0]][value[1]] == 0) {
                    map[value[0]][value[1]] = info.getKey();
                } else {
                    int temp = map[value[0]][value[1]];
                    if (sharks.get(temp)[4] > sharks.get(info.getKey())[4]) {
                        deadList.add(info.getKey());
                    } else {
                        deadList.add(temp);
                        map[value[0]][value[1]] = info.getKey();
                    }


                }
            }

            for (Integer integer : deadList) {
                sharks.remove(integer);
            }

        }
        System.out.println(answer);

    }

    static int[] move(int[] info) {
        int r = info[0];
        int c = info[1];
        int s = info[2];
        int d = info[3];
        int z = info[4];

        if (d == 0 || d == 1) {
            if (R > 1) {
                int moveCount = s % ((R - 1) * 2);
                for (int step = 0; step < moveCount; step++) {
                    if (d == 0 && r == 0) d = 1;
                    else if (d == 1 && r == R - 1) d = 0;
                    r += (d == 0) ? -1 : 1;
                }
            }
        } else {
            if (C > 1) {
                int moveCount = s % ((C - 1) * 2);
                for (int step = 0; step < moveCount; step++) {
                    if (d == 2 && c == C - 1) d = 3;
                    else if (d == 3 && c == 0) d = 2;
                    c += (d == 2) ? 1 : -1;
                }
            }
        }

        return new int[]{r, c, s, d, z};
    }*/

    //위코드 백준에서 403 뜸 이유는 모르겠는데 특정 변수가 방화벽에 막힌것 같음

    static int R, C;

    static class Shark {
        int r, c, s, d, z;

        Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Shark[][] grid = new Shark[R][C];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            if (d <= 2) s %= (R - 1) * 2 == 0 ? 1 : (R - 1) * 2;
            else s %= (C - 1) * 2 == 0 ? 1 : (C - 1) * 2;
            grid[r][c] = new Shark(r, c, s, d, z);
        }

        int total = 0;
        for (int col = 0; col < C; col++) {
            for (int row = 0; row < R; row++) {
                if (grid[row][col] != null) {
                    total += grid[row][col].z;
                    grid[row][col] = null;
                    break;
                }
            }

            Shark[][] next = new Shark[R][C];
            for (int row = 0; row < R; row++) {
                for (int k = 0; k < C; k++) {
                    Shark sh = grid[row][k];
                    if (sh == null) continue;
                    step(sh);
                    Shark old = next[sh.r][sh.c];
                    if (old == null || old.z < sh.z) next[sh.r][sh.c] = sh;
                }
            }
            grid = next;
        }
        System.out.println(total);
    }

    static void step(Shark sh) {
        for (int t = 0; t < sh.s; t++) {
            if (sh.d == 1 && sh.r == 0) sh.d = 2;
            else if (sh.d == 2 && sh.r == R - 1) sh.d = 1;
            else if (sh.d == 3 && sh.c == C - 1) sh.d = 4;
            else if (sh.d == 4 && sh.c == 0) sh.d = 3;

            if (sh.d == 1) sh.r--;
            else if (sh.d == 2) sh.r++;
            else if (sh.d == 3) sh.c++;
            else sh.c--;
        }
    }
}
