import org.junit.Assert;
import org.junit.Test;

// 340212. [PCCP 기출문제] 2번 / 퍼즐 게임 챌린지
public class PuzzleGameChallenge_340212 {

    public int solution(int[] diffs, int[] times, long limit) {
        long left = 1;
        long right = limit;

        while (left < right) {
            long mid = (left + right) / 2;

            // 불가능하다면 더 큰 범위
            if (isImpossible(diffs, times, mid, limit)) {
                left = mid + 1;
            } else { // 가능하다면 해당 범위
                right = mid;
            }
        }

        return (int) left;
    }

    boolean isImpossible(int[] diffs, int[] times, long level, long limit) {
        long time = times[0];

        for (int i = 1; i < times.length; i++) {
            if (diffs[i] > level) {
                time += ((long)diffs[i] - level) * ((long)times[i-1] + (long)times[i]);
            }
            time += times[i];
        }

        return limit < time; // 제한시간보다 더 많은 시간이 든다면 불가능
    }


    // 특정 케이스에서 시간초과
    public int solution2(int[] diffs, int[] times, long limit) {
        int level = 1;
        while (true) {
            long time_prev = 0;
            long tmpLimit = limit;
            for (int i = 0; i < diffs.length; i++) {
                // 퍼즐을 틀리지 않고 해결 가능
                if (diffs[i] <= level) {
                    tmpLimit -= times[i]; // 해당 소요시간만큼 사용하여 해결
                } else { // 퍼즐을 틀림
                    long 틀리는횟수 = diffs[i] - level;
                    tmpLimit -= (times[i] + time_prev) * 틀리는횟수 + times[i];
                }
                time_prev = times[i];
                if (tmpLimit < 0) {
                    level++;
                    break;
                } else if (i == diffs.length-1) {
                    return level;
                }
            }
        }
    }

    @Test
    public void test() {
        int[] diffs = {1,5,3};
        int[] times = {2,4,7};
        int limit = 30;
        Assert.assertEquals(3, solution(diffs, times, limit));
    }

    @Test
    public void test2() {
        int[] diffs = {1, 328, 467, 209, 54};
        int[] times = {2, 7, 1, 4, 3};
        int limit = 1723;
        Assert.assertEquals(294, solution(diffs, times, limit));
    }
}
