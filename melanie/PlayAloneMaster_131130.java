import org.junit.Assert;
import org.junit.Test;

import java.util.*;

// 131130. 혼자 놀기의 달인
public class PlayAloneMaster_131130 {

    public int solution(int[] cards) {

        boolean[] visited = new boolean[cards.length + 1];
        ArrayList<ArrayList<Integer>> groupList = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < cards.length; i++) {
            if (visited[i + 1]) continue;
            visited[i + 1] = true;
            ArrayList<Integer> tmpList = new ArrayList<>();
            q.add(i + 1);
            tmpList.add(i + 1);
            while (!q.isEmpty()) {
                int cur = q.poll();
                int next = cards[cur-1];
                if (visited[next]) break;
                visited[next] = true;
                tmpList.add(next);
                q.add(next);
            }
            groupList.add(tmpList);
        }

        groupList.sort((o1, o2) -> o2.size() - o1.size());

        if (groupList.size() == 1) return 0; // 만약 1번 상자 그룹을 제외하고 남는 상자가 없으면 그대로 게임이 종료되며, 이때 획득하는 점수는 0점
        else return groupList.get(0).size() * groupList.get(1).size(); // 1번 상자 그룹에 속한 상자의 수와 2번 상자 그룹에 속한 상자의 수를 곱한 값이 게임의 점수

    }

    @Test
    public void test() {
        int[] cards = {8, 6, 3, 7, 2, 5, 1, 4};
        Assert.assertEquals(12, solution(cards));
    }
}
