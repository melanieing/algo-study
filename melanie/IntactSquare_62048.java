import org.junit.Assert;
import org.junit.Test;
// 62048. 멀쩡한 사각형
public class IntactSquare_62048 {
    private long solution(int w, int h) {
        int gcd = gcd(w, h);

        return ((long) w * (long) h) - ((((long) w / gcd) + ((long) h / gcd) - 1) * gcd);
    }

    private static int gcd(int w, int h) {
        while (h != 0) {
            int temp = h;
            h = w % h;
            w = temp;
        }
        return w;
    }

    @Test
    public void test() {
        Assert.assertEquals(80, solution(8, 12));
    }
}
