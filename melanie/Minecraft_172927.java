import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

// 172927. 광물 캐기
public class Minecraft_172927 {
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;

        int dia = picks[0];
        int iron = picks[1];
        int stone = picks[2];

        // 곡괭이 총 개수
        int pickSum = dia + iron + stone;

        // 광석 총 개수
        int mineralSum = minerals.length;

        int[][] mineralGroup = new int[pickSum][6];

        for (int i = 0; i < Math.min(pickSum * 5, mineralSum); i += 5) {
            int sum = 0;
            int damage = 0;

            for (int j = i; j < Math.min(i + 5, mineralSum); j++) {
                switch (minerals[j]) {
                    case "diamond" -> {
                        damage = 25;
                    }
                    case "iron" -> {
                        damage = 5;
                    }
                    case "stone" -> {
                        damage = 1;
                    }
                }
                sum += damage;
                mineralGroup[i / 5][j % 5 + 1] = damage;
            }
            mineralGroup[i / 5][0] = sum;
        }

        // 피로도 합 기준으로 내림차순 정렬
        Arrays.sort(mineralGroup, (o1, o2) -> o2[0] - o1[0]);

        for (int i = 0; i < pickSum; i++) {
            int[] tmp = mineralGroup[i];
            StringBuilder pick = new StringBuilder();

            if (dia > 0) {
                dia -= 1;
                pick.append("diamond");
            } else if (iron > 0) {
                iron -= 1;
                pick.append("iron");
            } else if (stone > 0) {
                stone -= 1;
                pick.append("stone");
            }

            // 광물별 피로도 계산
            for (int j = 1; j < 6; j++) {
                switch (pick.toString()) {
                    case "diamond" -> {
                        answer += (int)Math.ceil(tmp[j] / 25.0);
                    }
                    case "iron" -> {
                        answer += (int)Math.ceil(tmp[j] / 5.0);
                    }
                    case "stone" -> {
                        answer += tmp[j];
                    }
                }
            }
        }

        return answer;

    }

    @Test
    public void test() {
        int[] picks = {1, 3, 2};
        String[] minerals = {"diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"};
        Assert.assertEquals(12, solution(picks, minerals));
    }

    @Test
    public void test2() {
        int[] picks2 = {0, 1, 1};
        String[] minerals2 = {"diamond", "diamond", "diamond", "diamond", "diamond", "iron", "iron", "iron", "iron", "iron", "diamond"};
        Assert.assertEquals(50, solution(picks2, minerals2));
    }
}
