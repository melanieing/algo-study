import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;
// 150369. 택배 배달과 수거하기
public class Delivery_150369 {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        Stack<Integer> deliverStack = new Stack<>();
        Stack<Integer> pickupStack = new Stack<>();

        // 배달 및 수거할 택배 상자의 위치와 그 개수만큼 순서대로 스택에 넣기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < deliveries[i]; j++) {
                deliverStack.push(i + 1);
            }
            for (int j = 0; j < pickups[i]; j++) {
                pickupStack.push(i + 1);
            }
        }

        while (!deliverStack.isEmpty() && !pickupStack.isEmpty()) {
            // 가장 멀리 떨어진 배달 및 수거해야 할 집을 구하기
            int lastDeliver = deliverStack.peek();
            int lastPickup = pickupStack.peek();

            // 한쪽 스택이 빌 때까지 뒤에서부터 cap 개수만큼 배달 및 수거
            for (int i = 0; i < cap; i++) {
                if (!deliverStack.isEmpty()) deliverStack.pop();
                if (!pickupStack.isEmpty()) pickupStack.pop();
            }

            answer += Math.max(lastDeliver, lastPickup) * 2L; // 왕복거리
        }

        // 마지막으로 남은 배달
        while (!deliverStack.isEmpty()) {
            int last = deliverStack.peek();
            for (int i = 0; i < cap; i++) {
                if (!deliverStack.isEmpty()) deliverStack.pop();
            }
            answer += last * 2L;
        }

        // 마지막으로 남은 수거
        while (!pickupStack.isEmpty()) {
            int last = pickupStack.peek();
            for (int i = 0; i < cap; i++) {
                if (!pickupStack.isEmpty()) pickupStack.pop();
            }
            answer += last * 2L;
        }

        return answer;
    }

    @Test
    public void test() {
        Assert.assertEquals(16, solution(4, 5, new int[]{1,0,3,1,2}, new int[]{0,3,0,4,0}));
    }
}
