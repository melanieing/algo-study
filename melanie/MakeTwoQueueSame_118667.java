import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;
// 118667. 두 큐 합 같게 만들기
public class MakeTwoQueueSame_118667 {

    public static void main(String[] args) {
        int[] queue1 = {1, 2, 1, 2};
        int[] queue2 = {1, 10, 1, 2};

        MakeTwoQueueSame_118667 q = new MakeTwoQueueSame_118667();
        System.out.println(q.solution(queue1, queue2));
    }

    public int solution(int[] queue1, int[] queue2) {
        // int[] -> queue로 변환
        Queue<Long> q1 = Arrays.stream(queue1)
                .mapToLong(i -> (long) i)
                .boxed()
                .collect(Collectors.toCollection(LinkedList::new));
        Queue<Long> q2 = Arrays.stream(queue2)
                .mapToLong(i -> (long) i)
                .boxed()
                .collect(Collectors.toCollection(LinkedList::new));

        // 각각의 합과 만들어야 하는 합을 구하기
        long sum1 = q1.stream().mapToLong(Long::longValue).sum();
        long sum2 = q2.stream().mapToLong(Long::longValue).sum();
        long targetSum = (sum1 + sum2) / 2;

        // 두 queue를 합친 큐를 만들기
        Queue<Long> combined = new LinkedList<>(q1);
        combined.addAll(q2);

        // 최대 이동 횟수
        long maxMoves = 3L * combined.size() - 3;
        int moves = 0;

        while (sum1 != targetSum && moves < maxMoves) { // 만들어야 하는 합에 도달할 때까지 반복
            if (sum1 > targetSum && !q1.isEmpty()) { // q1의 합이 더 크면 q1 -> q2로 옮기기
                long num = q1.poll();
                q2.offer(num);
                sum1 -= num;
                sum2 += num;
            } else if (!q2.isEmpty()) { // q2의 합이 더 크면 q2 -> q1로 옮기기
                long num = q2.poll();
                q1.offer(num);
                sum1 += num;
                sum2 -= num;
            }
            moves++;
        }

        return (sum1 == targetSum) ? moves : -1; // 불가능할 경우 - 반환
    }
}
