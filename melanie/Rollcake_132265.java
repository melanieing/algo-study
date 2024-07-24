import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// 132265. 롤케이크 자르기
public class Rollcake_132265 {

    public int solution(int[] topping) {
        int answer = 0;

        // 집합1은 set에, 집합2는 map에 담기 위해 생성
        Set<Integer> set = new HashSet<>();
        Map<Integer, Integer> map = new HashMap<>();

        // map에 토핑 종류별로 개수 넣기
        for (int top : topping) {
            map.put(top, map.getOrDefault(top, 0) + 1);
        }

        for (int top : topping) {
            // 집합1에 순서대로 넣기
            set.add(top);
            // 집합2에서 제거하기
            if (map.get(top) == 1) {
                map.remove(top);
            } else {
                map.replace(top, map.get(top) - 1);
            }
            // 공평하게 나누어져으면 개수 올리기
            if (set.size() == map.size()) answer++;
        }

        return answer;
    }

    public int solution2(int[] topping) {
        int answer = 0;

        // 집합1, 집합2를 모두 set에 담기
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        // 중첩 for문으로 시간초과
        for (int i = 0; i < topping.length; i++) {
            for (int j = 0; j < topping.length; j++) {
                if (i - 1 >= j) {
                    set1.add(topping[j]);
                } else {
                    set2.add(topping[j]);
                }
            }
            if (set1.size() == set2.size()) {
                answer++;
            }
            set1.clear();
            set2.clear();
        }

        return answer;
    }
}
