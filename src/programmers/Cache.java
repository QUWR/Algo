package programmers;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Cache {

    public static void main(String[] args) {
        
    }

    class Solution {

        static Map<String, Integer> cache;
        static Deque<String> order;
        static int n;

        public int solution(int cacheSize, String[] cities) {
            cache = new HashMap<>();
            order = new ArrayDeque<>();
            n = cities.length;
            int index = 0;
            int answer = 0;

            if(cacheSize == 0) {
                return 5 * n;
            }
            while(cache.size() < cacheSize && index < n) {
                String city = cities[index].toUpperCase();
                order.offer(city);
                if(cache.containsKey(city)) {
                    cache.put(city, cache.get(city)+1);
                    answer += 1;
                } else {
                    cache.put(city, 1);
                    answer += 5;
                }
                index++;
            }

            while(index < n) {
                String city = cities[index].toUpperCase();
                order.offer(city);
                if(cache.containsKey(city)) {
                    cache.put(city, cache.get(city)+1);
                    answer += 1;
                } else {
                    while(cache.size() == cacheSize) {
                        String last = order.poll();
                        if(cache.get(last) == 1) {
                            cache.remove(last);
                        } else {
                            cache.put(last, cache.get(last) - 1);
                        }
                    }
                    cache.put(city, 1);
                    answer += 5;
                }
                index++;
            }

            return answer;
        }
    }
}
