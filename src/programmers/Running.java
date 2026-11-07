package programmers;

import java.util.HashMap;
import java.util.Map;

public class Running {

    public static void main(String[] args) {

    }

    class Solution {
        static Map<String, Integer> info;

        public String[] solution(String[] players, String[] callings) {
            info = new HashMap<>();
            int n = players.length;
            int m = callings.length;

            for(int i = 0 ; i< n; i++) {
                info.put(players[i], i);
            }

            for(int i =0 ;i < m; i++) {
                int index = info.get(callings[i]);
                String front = players[index-1];
                String cur = players[index];
                info.put(front, index);
                info.put(cur, index -1);
                players[index] = front;
                players[index-1] = cur;
            }
            return players;
        }

    }
}
