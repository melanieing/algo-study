import java.util.PriorityQueue;
// 142085. 디펜스 게임
public class DefenseGame_142085 {
    public static void main(String[] args) {
        int n = 7;
        int k = 3;
        int[] enemy = {4, 2, 4, 5, 3, 3, 1};

        DefenseGame_142085 q = new DefenseGame_142085();
        System.out.println(q.solution(n, k, enemy));
    }

    public int solution(int n, int k, int[] enemy) {
        int answer = enemy.length;

        // 무적권을 사용해 해치울 적의 수를 남기기 위한 우선순위 큐
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // 디펜스 게임 시작
        for (int i = 0; i < enemy.length; i++) {
            pq.offer(enemy[i]);

            // 무적권으로 막을 수 없으면 그중 가장 적은 적이 나타나는 라운드는 병사를 소모하여 클리어
            if (pq.size() > k) {
                n -= pq.poll();
            }

            if (n < 0) {
                return i;
            }
        }

        return answer;
    }
}

