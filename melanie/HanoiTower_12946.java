import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
// 12946. 하노이의 탑
public class HanoiTower_12946 {

    private static final List<int[]> arr = new ArrayList<>(); // 이동 정보
    public static void main(String[] args) {
        int n = 2;

        HanoiTower_12946 q = new HanoiTower_12946();
        System.out.println(Arrays.deepToString(q.solution(n)));
    }

    public int[][] solution(int n) {
        move(n, 1, 2, 3);

        return arr.toArray(int[][]::new);
    }

    /**
     * 원판 이동
     * @param cnt 이동할 원판 개수
     * @param start 출발지
     * @param mid 경유지
     * @param end 도착지
     */
    private static void move(int cnt, int start, int mid, int end) {
        if (cnt == 0) return;

        move(cnt - 1, start, end, mid);
        arr.add(new int[]{start, end});
        move(cnt - 1, mid, start,end);
    }
}
