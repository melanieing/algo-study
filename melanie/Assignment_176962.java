import org.junit.Assert;
import org.junit.Test;

import java.util.*;

// 176962. 과제 진행하기
public class Assignment_176962 {
    class Task {
        String name;
        int start, playtime;

        public Task(String name, int start, int playtime) {
            this.name = name;
            this.start = start;
            this.playtime = playtime;
        }

        public Task(String name, int playtime) {
            this.name = name;
            this.playtime = playtime;
        }
    }
    public String[] solution(String[][] plans) {
        List<String> answerList = new ArrayList<>();

        // 과제들을 시작시간 오름차순으로 저장
        PriorityQueue<Task> tasks = new PriorityQueue<>(
                Comparator.comparingInt(o -> o.start)
        );

        for (int i = 0; i < plans.length; i++) {
            String name = plans[i][0];
            String startTime = plans[i][1];
            int start = convertToMinute(startTime);
            int playTime = Integer.parseInt(plans[i][2]);

            tasks.add(new Task(name, start, playTime));
        }

        Stack<Task> remainingTasks = new Stack<>();
        List<String> finishedTasks = new ArrayList<>();


        Task curTask = tasks.poll();
        int time = curTask.start;
        while (!tasks.isEmpty()) {
            time += curTask.playtime;
            Task nextTask = tasks.peek();

            if (time > nextTask.start) { // 현재 과제의 마치는 시간이 다음 과제의 시작 시간보다 크면
                curTask.playtime = time - nextTask.start;
                remainingTasks.push(curTask);
            } else { // 작거나 같으면
                finishedTasks.add(curTask.name);
                if (!remainingTasks.empty()) {
                    curTask = remainingTasks.pop();
                    continue;
                }
            }
            curTask = tasks.poll();
            time = curTask.start;

        }

        finishedTasks.add(curTask.name);
        while (!remainingTasks.empty()) {
            finishedTasks.add(remainingTasks.pop().name);
        }

        return finishedTasks.toArray(new String[0]);
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
