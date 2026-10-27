package programmers;

import java.util.ArrayList;
import java.util.List;

public class Integral {


    static class Solution {
        static int n;
        static List<Double> loc;
        public double[] solution(int k, int[][] ranges) {
            loc = new ArrayList<>();

            while(k!= 1) {
                loc.add((double)k);

                if(k % 2 == 0) {
                    k = k/2;
                } else {
                    k = 3*k +1;
                }
            }
            n = loc.size();
            loc.add((double)k);
            double[] result = new double[ranges.length];
            for(int i = 0; i < ranges.length; i++) {
                result[i] = getVoid(ranges[i]);
            }
            return result;
        }

        double getVoid(int[] ranges) {
            int s = ranges[0];
            int e = n + ranges[1];
            if(s > e) return -1;
            if(s == e) return 0;
            double v = 0;
            for(int i = s; i < e; i++) {
                v = v + (loc.get(i) + loc.get(i+1))/2;
            }
            return v;
        }
    }
}
