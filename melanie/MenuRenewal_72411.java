import java.util.*;
// 72411. 메뉴 리뉴얼
public class MenuRenewal_72411 {

    Map<Integer, Map<String, Integer>> map; // 길이, 메뉴조합, 반복횟수
    Set<Integer> set; // 코스
    int maxCourse;

    public static void main(String[] args) {
        String[] orders = {"XYZ", "XWY", "WXA"}; // 각 손님들이 주문한 단품메뉴들
        int[] course = {2, 3, 4}; // 추가하고 싶어하는 코스요리를 구성하는 단품메뉴들의 갯수
        MenuRenewal_72411 q = new MenuRenewal_72411();
        System.out.println(Arrays.toString(q.solution(orders, course))); // 각 코스요리 메뉴의 구성
    }

    public String[] solution(String[] orders, int[] course) {
        map = new HashMap<>();
        set = new HashSet<>();
        List<String> answer = new ArrayList<>();
        maxCourse = course[course.length - 1]; // 4
        for (int c : course) { // 2, 3, 4
            map.put(c, new HashMap<>());
            set.add(c);
        }

        for (String order : orders) { // "XYZ", "XWY", "WXA"
            order = sortString(order); // XYZ, WXY, AWX
            dfs(order, new StringBuilder(), 0, new boolean[order.length()]);
        }

        for (int c : course) { // 2, 3, 4
            int max = 0;
            Map<String, Integer> tmpMap = map.get(c);
            for (String key : tmpMap.keySet()) {
                max = Math.max(max, tmpMap.get(key));
            }

            // 최소 2명 이상의 손님으로부터 주문된 단품메뉴 조합에 대해서만 코스요리 메뉴 후보에 포함
            if (max < 2) continue;

            for (String key : tmpMap.keySet()) {
                if (tmpMap.get(key) == max) answer.add(key); // 가장 많이 함께 주문된 메뉴 구성이 여러 개라면, 모두 배열에 담기
            }
        }

        Collections.sort(answer); // 사전 순으로 오름차순 정렬
        return answer.toArray(new String[0]);

    }

    public void dfs(String str, StringBuilder sb, int t, boolean[] check) {
        int len = sb.length();
        if (set.contains(len)) {
            String combination = sb.toString();
            int cnt = map.get(len).getOrDefault(combination, 0) + 1;
            map.get(len).put(combination, cnt);
            if (len == maxCourse) return;
        }

        for (int i = t; i < str.length(); i++) {
            if (check[i]) continue;
            sb.append(str.charAt(i));
            check[i] = true;
            dfs(str, sb, i + 1, check);
            check[i] = false;
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public String sortString(String str) { // 문자열을 사전순으로 정렬
        char[] arr = str.toCharArray();
        Arrays.sort(arr);

        return new String(arr);
    }

}
