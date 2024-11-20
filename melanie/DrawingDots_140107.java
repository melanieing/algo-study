import org.junit.Assert;
import org.junit.Test;
// 140107. 점 찍기
public class DrawingDots_140107 {
    public long solution(int k, int d) {
        long answer = 0;

        // 밑의 for문 시간 줄이기
        for (int i = 0; i * k <= d; i++) {
            int maxJ = (int) Math.sqrt((long) d * d - (long) (i * k) * (i * k));
            answer += (maxJ / k) + 1;
        }

//        for (int i = 0; i <= d; i++) {
//            for (int j = 0; j <= d; j++) {
//                if (Math.pow(d, 2) >= Math.pow(i * k, 2) + Math.pow(j * k, 2)) {
//                    answer++;
//                }
//            }
//        }

        return answer;
    }

    public long solution2(int k, int d) { // 피타고라스 정리 활용
        long answer = 0;

        for (int i = 0; i <= d; i += k) {
            int maxY = getMaxY(i, d);
            answer += countPossibleY(maxY, k);
        }

        return answer;
    }

    private long countPossibleY(int y, int k) {
        return y / k + 1;
    }

    private int getMaxY(int x, int d) {
        return (int) Math.sqrt(d * d - x * x);
    }


    @Test
    public void test() {
        Assert.assertEquals(6, solution2(2, 4));
        Assert.assertEquals(26, solution2(1, 5));
    }
}
