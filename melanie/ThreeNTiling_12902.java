import org.junit.Assert;
import org.junit.Test;
// 12902. 3 x n 타일링
public class ThreeNTiling_12902 {

    private static final int REST = 1000000007;

    public int solution(int n) {
        // tiles[i] = tiles[i - 2] * 3 + tiles[i - 4] * 2 + tils[i - 6] * 2 ...
        long[] tiles = new long[n + 1];

        tiles[0] = 1;
        tiles[2] = 3;

        for (int i = 4; i <= n; i += 2) {
            tiles[i] = tiles[i - 2] * 3;
            for (int j = i - 4; j >= 0; j -=2) {
                tiles[i] += tiles[j] * 2;
            }
            tiles[i] %= REST;
        }

        return (int) tiles[n];
    }

    @Test
    public void test() {
        int n = 4;

        Assert.assertEquals(11, solution(n));
    }
}
