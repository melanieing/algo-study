import org.junit.Assert;
import org.junit.Test;
// 161990. 바탕화면 정리
public class ClearDesktop_161990 {
    public int[] solution(String[] wallpaper) {
        int[] answer = new int[4];

        int rdy = Integer.MIN_VALUE;
        int luy = Integer.MAX_VALUE;
        int lux = Integer.MAX_VALUE;
        int rdx = Integer.MIN_VALUE;

        for (int i = 0; i < wallpaper.length; i++) {
            for (int j = 0; j < wallpaper[i].length(); j++) {
                if (wallpaper[i].charAt(j) == '#') {
                    rdy = Math.max(j, rdy);
                    luy = Math.min(j, luy);
                    lux = Math.min(i, lux);
                    rdx = Math.max(i, rdx);
                }
            }
        }

        answer[0] = lux;
        answer[1] = luy;
        answer[2] = rdx + 1;
        answer[3] = rdy + 1;

        return answer;
    }

    @Test
    public void test() {
        String[] wallpaper = {".#...", "..#..", "...#."};
        Assert.assertArrayEquals(new int[]{0, 1, 3, 4}, solution(wallpaper));
    }
}
