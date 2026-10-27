package programmers;

public class FlexibleWork {


    static class Solution {
        public int solution(int[] schedules, int[][] timelogs, int startday) {
            int n = schedules.length;
            int result = 0;
            for(int i = 0; i < n; i++) {
                if(isValid(getTime(schedules[i]), timelogs[i], startday)) result++;
            }
            return result;

        }

        boolean isValid(int schedule, int[] timelog, int startday) {
            int day = startday-1;
            for(int i = 0; i < 7; i++) {
                day++;
                if(day == 6 || day == 7) continue;
                if(schedule + 10 < getTime(timelog[i])) return false;
            }

            return true;
        }


        int getTime(int time) {
            return ((time/100) * 60) + (time%100);
        }
    }
}
