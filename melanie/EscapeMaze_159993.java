import java.util.LinkedList;
import java.util.Queue;
// 159993. 미로 탈출
public class EscapeMaze_159993 {

    class Node {
        int x, y, cnt;

        public Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) {
        String[] maps = {"SOOOL","XXXXO","OOOOO","OXXXX","OOOOE"};

        EscapeMaze_159993 q = new EscapeMaze_159993();
        System.out.println(q.solution(maps));
    }

    public int solution(String[] maps) {
        int[] start = getStartPosition('S', maps); // Start 지점
        int[] lever = getStartPosition('L', maps); // Lever 위치

        int leverCnt = bfs('L', start, maps); // S에서 L까지의 최소 거리
        if (leverCnt == 0) return -1;

        int exitCnt = bfs('E', lever, maps); // L에서 E까지의 최소 거리
        if (exitCnt == 0) return -1;

        return leverCnt + exitCnt;
    }

    public int bfs(char findChar, int[] start, String[] maps) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(start[0], start[1], 0));

        boolean[][] visited = new boolean[maps.length][maps[0].length()];
        visited[start[0]][start[1]] = true;

        int answer = 0;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (maps[cur.x].charAt(cur.y) == findChar) {
                if (answer == 0) answer = cur.cnt;
                else answer = Math.min(answer, cur.cnt);
            }
            for (int i = 0; i < dx.length; i++) {
                int x = cur.x + dx[i];
                int y = cur.y + dy[i];

                if (x < 0 || y < 0 || x >= maps.length || y >= maps[0].length() || maps[x].charAt(y) == 'X' || visited[x][y]) continue;

                visited[x][y] = true;
                q.offer(new Node(x, y, cur.cnt + 1));
            }
        }
        return answer;
    }

    public int[] getStartPosition(char findChar, String[] maps) {
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[i].length(); j++) {
                if (maps[i].charAt(j) == findChar) return new int[]{i, j};
            }
        }
        return null;
    }
}
