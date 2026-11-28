package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Num22866 {

    static int n;
    static int[] arr;
    static int[] left;
    static int[] right;
    static int[] near;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        arr = new int[n];
        left = new int[n];
        right = new int[n];
        near = new int[n];
        Arrays.fill(near, Integer.MAX_VALUE);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Deque<int[]> leftStack = new ArrayDeque<>();
        Deque<int[]> rightStack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            while(!leftStack.isEmpty() && leftStack.peekFirst()[1] <= arr[i]) {
                leftStack.pollFirst();
            }
            left[i] = leftStack.size();
            if(!leftStack.isEmpty()) {
                int l = leftStack.peekFirst()[0];
                if(i-l <= near[i] -i) near[i] = l;
            }


            while(!rightStack.isEmpty() && rightStack.peekFirst()[1] <= arr[n-1-i]) {
                rightStack.pollFirst();
            }
            right[n-1-i] = rightStack.size();
            if(!rightStack.isEmpty()) {
                int r = rightStack.peekFirst()[0];
                if(r-(n-i-1) < n-1-i-near[n- 1- i] || near[n-1-i] == Integer.MAX_VALUE) near[n-1-i] = r;
            }

            leftStack.offerFirst(new int[] {i, arr[i]});
            rightStack.offerFirst(new int[] {n-1-i, arr[n-1-i]});

        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int a = left[i] + right[i];
            sb.append(a);
            if(a!= 0) sb.append(" ").append(near[i]+1);
            sb.append("\n");
        }

        System.out.println(sb.toString());

    }
}
