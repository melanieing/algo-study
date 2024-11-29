import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

// 181188. 요격 시스템
public class InterceptSystem_181188 {
    public int solution(int[][] targets) {
        int answer = 0;

        Arrays.sort(targets, Comparator.comparingInt(o -> o[1]));

        int currEnd = -1;

        for (int[] target : targets) {
            if (currEnd == -1) {
                answer++; // 요격하기
                currEnd = target[1];
                continue;
            }
            // 시작점이 현재 종료점보다 작으면 이미 발사된 요격미사일에 요격되니까 넘어가기
            if (target[0] < currEnd) continue;
            // 시작점이 현재 종료점보다 작지 않으면 요격하기
            answer++;

            // 현재 종료점 업데이트하기
            currEnd = target[1];
        }

        return answer;
    }

    @Test
    public void test() {
        int[][] targets = {{4,5},{4,8},{10,14},{11,13},{5,12},{3,7},{1,4}};
        Assert.assertEquals(3, solution(targets));
    }
}
