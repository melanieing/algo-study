import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
// 134239. 우박수열 정적분
public class HailDefiniteIntegral_134239 {
    public double[] solution(int k, int[][] ranges) {
        double[] answer = new double[ranges.length];

        ArrayList<Integer> list = new ArrayList<>();

        // 콜라츠 추측
        while (k > 1) {
            list.add(k);
            if (k % 2 == 0) {
                k /= 2;
            } else {
                k = k * 3 + 1;
            }
        }

        list.add(k);

        int n = list.size(); // 총 횟수

        for (int i = 0; i < ranges.length; i++) {
            // 주어진 구간의 시작점이 끝점보다 커서 유효하지 않은 구간
            if (ranges[i][0] > ranges[i][1] + n - 1) {
                answer[i] = -1;
                continue;
                // 시작점과 끝점이 같아도 면적을 구할 수 없음
            } else if (ranges[i][0] == ranges[i][1] + n - 1) {
                answer[i] = 0;
                continue;
            }

            // 면적 구하기
            double area = 0;
            for (int j = ranges[i][0]; j < ranges[i][1] + n - 1; j++) {
                area += (list.get(j) + list.get(j+1)) / 2.0;
            }

            answer[i] = area;
        }


        return answer;
    }

    @Test
    public void test() {
        int[][] ranges = {{0,0},{0,-1},{2,-3},{3,-3}};
        Assert.assertArrayEquals(new double[]{33.0,31.5,0.0,-1.0}, solution(5, ranges), 0.001);
    }
}
