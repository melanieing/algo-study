import org.junit.Assert;
import org.junit.Test;

// 150368. 이모티콘 할인행사
public class EmoticonSale_150368 {
    static int maxSubscribers = 0;
    static int maxProfit = 0;
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];
        int[] discountRatios = new int[emoticons.length];

        combinate(discountRatios, 0, users, emoticons);

        answer[0] = maxSubscribers;
        answer[1] = maxProfit;

        return answer;
    }

    public void combinate(int[] discountRatios, int start, int[][] users, int[] emoticons) {
        if (start == discountRatios.length) {
            calculate(discountRatios, users, emoticons);
            return;
        }

        for (int i = 10; i <= 40; i+= 10) {
            discountRatios[start] = i;
            combinate(discountRatios, start + 1, users, emoticons);
        }
    }

    public void calculate(int[] discountRatios, int[][] users, int[] emoticons) {
        int subscribers = 0, profit = 0;

        for (int[] user : users) {
            int discountRatio = user[0];
            int priceBoundary = user[1];
            int sum = 0;

            for (int i = 0; i < discountRatios.length; i++) {
                if (discountRatios[i] >= discountRatio) {
                    sum += (emoticons[i] / 100 ) * (100 - discountRatios[i]);
                }
            }

            if (sum >= priceBoundary) subscribers++; // 구입비가 가격상한선보다 높으면 가입
            else profit += sum; // 구입비가 더 낮다면 가입안하고 그냥 삼
        }

        if (subscribers > maxSubscribers) {
            maxSubscribers = subscribers;
            maxProfit = profit;
            return;
        } else if (subscribers == maxSubscribers) {
            if (maxProfit < profit) maxProfit = profit;
        }
    }

    @Test
    public void test() {
        int[][] users = {{40, 10000}, {25, 10000}};
        int[] emoticons = {7000, 9000};
        Assert.assertArrayEquals(new int[]{1, 5400}, solution(users, emoticons));
    }
}
