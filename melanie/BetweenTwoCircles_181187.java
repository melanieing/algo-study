import org.junit.Assert;
import org.junit.Test;

// 181187. 두 원 사이의 정수 쌍
public class BetweenTwoCircles_181187 {
    public long solution(int r1, int r2) {
        long answer = 0;

        for (int i = 1; i <= r2; i++) {
            long minJ = (int) Math.ceil(Math.sqrt(1.0 * r1 * r1 - 1.0 * i * i));
            long maxJ = (int) Math.floor(Math.sqrt(1.0 * r2 * r2 - 1.0 * i * i));
            answer += (maxJ - minJ + 1);
        }

        return answer * 4; // 4사분면
    }

    @Test
    public void test() {
        Assert.assertEquals(20, solution(2, 3));
    }
}
