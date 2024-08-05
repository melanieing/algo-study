import java.util.LinkedList;
import java.util.Queue;
// 154538. 숫자 변환하기
public class ConvertNum_154538 {

    static class State {
        int value; // 현재값
        int steps; // 연산횟수

        State(int value, int steps) {
            this.value = value;
            this.steps = steps;
        }
    }

    public static void main(String[] args) {
        int x = 10;
        int y = 40;
        int n = 5;

        ConvertNum_154538 q = new ConvertNum_154538();
        System.out.println(q.solution(x, y, n));
    }

    public int solution(int x, int y, int n) {
        // BFS 탐색을 위한 큐와 방문 체크 배열
        Queue<State> queue = new LinkedList<>();
        boolean[] visited = new boolean[Math.max(y * 3, x + n + 1)]; // 가능한 최댓값으로 설정

        // 초기 상태 설정
        queue.add(new State(x, 0));
        visited[x] = true;

        // BFS 탐색
        while (!queue.isEmpty()) {
            State current = queue.poll();

            // 목표 도달 시 현재 연산 횟수 반환
            if (current.value == y) {
                return current.steps;
            }

            // 가능한 연산: n을 더하기, 2를 곱하기, 3을 곱하기
            int[] nextValues = {current.value + n, current.value * 2, current.value * 3};

            for (int next : nextValues) {
                // 범위 체크 및 방문 여부 확인
                if (next <= y && !visited[next]) {
                    visited[next] = true;
                    queue.add(new State(next, current.steps + 1));
                }
            }

        }

        return -1; // y에 도달할 수 없는 경우
    }

}
