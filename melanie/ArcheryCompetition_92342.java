import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

// 92342. 양궁대회
public class ArcheryCompetition_92342 {

    int[] gaps = new int[12]; // 점수 차이
    int[] arrows = new int[12]; // 백트래킹으로 가능한 모든 경우를 따져볼 변수

    public int[] solution(int n, int[] info) {
        Arrays.fill(gaps, -1);

        // backtracking을 통해 result에 정답을 넣어둠
        backtrack(0, n, info);

        if (gaps[0] == -1) {
            int[] resultArr = new int[1];
            resultArr[0] = -1;
            return resultArr;
        }

        int[] result = new int[11];
        for (int i = 0; i < 11; i++) {
            result[i] = gaps[i];
        }

        return result;
    }

    public void backtrack(int idx, int left, int[] info) {
        // 10번째의 값이 정해진 상황이라면
        if (idx == 10) {
            // 라이언의 점수를 계산
            arrows[idx] = left;
            int score = 0;
            for (int i = 0; i <= 10; i++) {
                if (arrows[i] > info[i]) score += (10 - i);
                else if (info[i] != 0) score -= (10 - i);
            }
            if (score <= 0) return;
            arrows[11] = score;

            // 지금 결과가 기존의 결과보다 좋다면 result를 갱신
            if (isFirstBetter(arrows, gaps)) {
                for (int i = 0; i < 12; i++) {
                    gaps[i] = arrows[i];
                }
            }
            return;
        }
        for (int i = 0; i<= left; i++) {
            arrows[idx] = i;
            backtrack(idx + 1, left - i, info);
        }
    }

    public boolean isFirstBetter(int[] a, int[] b) { // a가 b보다 더 좋은 배치일 경우 true
        for (int i = 11; i >= 0; i--) {
            if (a[i] != b[i]) return a[i] > b[i];
        }
        return false;
    }

    @Test
    public void test() {
        int n = 5;
        int[] info= {2,1,1,1,0,0,0,0,0,0,0};

        Assert.assertArrayEquals(new int[]{0,2,2,0,1,0,0,0,0,0,0}, solution(n, info));

    }
}
