import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
// 340211. [PCCP 기출문제] 3번 / 충돌위험 찾기
public class CollisionRisk_340211 {

    static Map<String, Integer> map = new HashMap<>();
    static String tmp = "";
    static int cnt = 0;

    public int solution(int[][] points, int[][] routes) {
        int answer = 0;

        for (int i = 0; i < routes.length; i++) {
            cnt = 0;
            for (int j = 0; j < routes[i].length - 1; j++) {
                int[] start = {points[routes[i][j] - 1][0], points[routes[i][j] - 1][1]};
                int[] end = {points[routes[i][j+1] - 1][0], points[routes[i][j+1] - 1][1]};
                if (j == 0) {
                    tmp = tmp + "0." + start[0] + "." + start[1];
                    if (!map.containsKey(tmp)) map.put(tmp, 1);
                    else map.put(tmp, map.get(tmp) + 1);
                }
                move(start, end, cnt);
            }
        }

        for (String key : map.keySet()) {
            // 같은 좌표에 로봇이 2대 이상 모인다면 충돌할 가능성이 있는 위험 상황
            if (map.get(key) >= 2) answer++;
        }

        return answer;
    }

    static void move(int[] start, int[] end, int c) {
        // 다음 포인트로 이동할 때는 항상 최단 경로로 이동하며 최단 경로가 여러 가지일 경우,
        // r 좌표가 변하는 이동을 c 좌표가 변하는 이동보다 먼저 합니다.
        while (true) {
            tmp = "";
            if (start[0] < end[0]) start[0]++;
            else if (start[0] > end[0]) start[0]--;
            else {
                if (start[1] < end[1]) start[1]++;
                else if (start[1] > end[1]) start[1]--;
                else {
                    cnt = c;
                    return;
                }
            }
            c++;
            tmp = tmp + c + "." + start[0] + "." + start[1];
            if (!map.containsKey(tmp)) map.put(tmp, 1);
            else map.put(tmp, map.get(tmp) + 1);
        }
    }

    @Test
    public void test() {
        int[][] points = {{3, 2}, {6, 4}, {4, 7}, {1, 4}};
        int[][] routes = {{4, 2}, {1, 3}, {2, 4}};
        Assert.assertEquals(1, solution(points, routes));
    }
}
