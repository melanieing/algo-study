import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

// 340198. [PCCE 기출문제] 10번 / 공원
public class Park_340198 {
    public int solution(int[] mats, String[][] park) {
        int answer = -1;

        // mats를 내림차순으로 정렬
        Integer[] matsArr = Arrays.stream(mats).boxed().toArray(Integer[]::new);
        Arrays.sort(matsArr, Collections.reverseOrder());
        mats = Arrays.stream(matsArr).mapToInt(Integer::intValue).toArray();

        for (int i : mats) {
            for (int j = 0; j <= park.length - i; j++) {
                for (int k = 0; k <= park[0].length - i; k++) {
                    boolean flag = true;
                    // 돗자리를 놓을 수 있는지 확인
                    if (park[j][k].equals("-1") && j + i <= park.length && k + i <= park[0].length) {
                        for (int l = 0; l < i; l++) {
                            for (int m = 0; m < i; m++) {
                                if (!park[j + l][k + m].equals("-1")) { // 사람이 있으면 돗자리 설치 불가
                                    flag = false;
                                    break;
                                }
                            }
                            if (!flag) break;
                        }
                        if (flag) return i;
                    }
                }
            }
        }

        return answer;
    }

    @Test
    public void test() {
        int[] mats = {5,3,2};
        String[][] park = {
                {"A", "A", "-1", "B", "B", "B", "B", "-1"},
                {"A", "A", "-1", "B", "B", "B", "B", "-1"},
                {"-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1"},
                {"D", "D", "-1", "-1", "-1", "-1", "E", "-1"},
                {"D", "D", "-1", "-1", "-1", "-1", "-1", "F"},
                {"D", "D", "-1", "-1", "-1", "-1", "E", "-1"}
        };
        Assert.assertEquals(3, solution(mats, park));
    }
}
