import org.junit.Assert;
import org.junit.Test;

import java.util.*;

// 176962. 과제어쩌구하는중
public class Assignment_176962 {
    class Assignment {
        String name;
        int start, playtime;

        public Assignment(String name, int start, int playtime) {
            this.name = name;
            this.start = start;
            this.playtime = playtime;
        }

        public Assignment(String name, int playtime) {
            this.name = name;
            this.playtime = playtime;
        }
    }
    public String[] solution(String[][] plans) {
        List<String> answerList = new ArrayList<>();

        // 과제들을 시작시간 오름차순으로 저장
        PriorityQueue<Assignment> pq = new PriorityQueue<>(
                Comparator.comparingInt(o -> o.start)
        );

        for (int i = 0; i < plans.length; i++) {
            String name = plans[i][0];
            String startTime = plans[i][1];
            int start = convertToMinute(startTime);
            int playTime = Integer.parseInt(plans[i][2]);

            pq.add(new Assignment(name, start, playTime));
        }

        Stack<Assignment> remainingAssignments = new Stack<>();

        while (!pq.isEmpty()) {
            Assignment curTask = pq.poll();

            String curName = curTask.name;
            int curStart = curTask.start;
            int curPlaytime = curTask.playtime;

            // 현재시각
            int curTime = curStart;

            // 새로운 과제가 남아있는 경우
            if (!pq.isEmpty()) {
                Assignment nextTask = pq.peek();


            }

        }

        return answer;
    }

    int convertToMinute(String time) {
        String[] t = time.split(":");
        int hour = Integer.parseInt(t[0]) * 60;
        int min = Integer.parseInt(t[1]);

        return hour + min;
    }

    @Test
    public void test() {
        String[][] plans = {{"korean", "11:40", "30"}, {"english", "12:10", "20"}, {"math", "12:30", "40"}};
        Assert.assertEquals(new String[]{"korean", "english", "math"}, solution(plans));
    }
}
