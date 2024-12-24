import org.junit.Assert;
import org.junit.Test;

// 161989. 덧칠하기
public class Repaint_161989 {
    public int solution(int n, int m, int[] section) {
        int start = section[0];
        int end = section[0] + (m - 1);
        int answer = 1; // 롤러로 페인트칠해야 하는 최소 횟수

        for (int i : section) {
            if (i < start || i > end) {
                start = i;
                end = i + (m - 1);
                answer++;
            }
        }

        return answer;
    }

    @Test
    public void test() {
        int n = 8, m = 4;
        int[] section = {2, 3, 6};
        Assert.assertEquals(2, solution(n, m, section));
    }
}
