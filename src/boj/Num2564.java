package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Num2564 {

    static int n;
    static int m;
    static int[] locs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int num = Integer.parseInt(st.nextToken());
        locs = new int[num];

        for (int i = 0; i < num; i++) {

            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            locs[i] = getLoc(a, b);

        }
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int cur = getLoc(x, y);

        int answer = 0;
        for (int i = 0; i < num; i++) {
            answer += Math.min(Math.abs(cur - locs[i]), 2*(m+n)-Math.abs(cur - locs[i]));
        }

        System.out.println(answer);
    }

    static int getLoc(int a, int b) {
        if(a == 1) {
            return n + m + n - b;
        } else if(a == 2) {
            return b;
        } else if(a == 3) {
            return 2*n + m + b;
        } else if(a == 4) {
            return n + m-b;
        }
        return 0;
    }


}
