import java.util.*;

// 17680. [1차] 캐시

public class Cache_17680 {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;

        // 캐시 생성
        Queue<String> cache = new LinkedList<>();

        for (String city : cities) {
            String target = city.toLowerCase();
            if (cache.contains(target)) { // cache hit
                answer += 1;
                cache.remove(target); // 원래 있던 자리에서 새로운 자리로 옮겨주기
                cache.offer(target);
            } else { // cache miss
                cache.offer(target);
                answer += 5;
                if (cache.size() > cacheSize) {
                    cache.poll();
                }
            }
        }

        return answer;
    }
}
