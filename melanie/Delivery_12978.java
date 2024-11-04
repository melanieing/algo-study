import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
// 12978. 배달
public class Delivery_12978 {
    public static void main(String[] args) {
        int N = 5;
        int[][] road = {{1,2,1},{2,3,3},{5,2,2},{1,4,2},{5,3,1},{5,4,2}};
        int K = 3;

        Delivery_12978 q = new Delivery_12978();
        System.out.println(q.solution(N, road, K));
    }

    static class Node {
        int a, b, c;

        public Node(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    static List<ArrayList<Node>> graph;
    static int count = 1; // 1마을은 무조건 방문하므로 1로 초기화

    public int solution(int N, int[][] road, int K) {

        graph = new ArrayList<>();
        int[] visited = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] r : road) {
            graph.get(r[0]).add(new Node(r[0], r[1], r[2]));
            graph.get(r[1]).add(new Node(r[1], r[0], r[2]));
        }

        bfs(N, K, visited);

        return count;
    }

    static void bfs(int N, int K, int[] visited) {

        // 탐색 큐에 1번마을에서 갈 수 있는 마을 모두 추가
        Queue<Node> q = new LinkedList<>(graph.get(1));

        // 방문 배열 초기화
        for (int i = 2; i <= N; i++) {
            visited[i] = Integer.MAX_VALUE;
        }

        while (!q.isEmpty()) {
            Node node = q.poll();
            if (!(visited[node.b] <= visited[node.a] + node.c)) { // 방문예정마을이 이동거리의 합보다 작으면 추가
                visited[node.b] = visited[node.a] + node.c;
                q.addAll(graph.get(node.b));
            }
        }

        for (int i = 2; i <= N; i++) {
            if (visited[i] <= K) { // 제한된 거리 이하일 때만 추가
                count++;
            }
        }
    }
}
