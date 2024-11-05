import java.util.LinkedList;
import java.util.Queue;
// 169199. 리코쳇 로봇
public class RicochetRobot_169199 {
    private static int col, row;
    private static final int[] dx = {0, 0, -1, 1};
    private static final int[] dy = {-1, 1, 0, 0};
    private static int[][] visited;

    public static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        // "."은 빈 공간을, "R"은 로봇의 처음 위치를, "D"는 장애물의 위치를, "G"는 목표지점
        String[] board = {"...D..R", ".D.G...", "....D.D", "D....D.", "..D...."};

        RicochetRobot_169199 q = new RicochetRobot_169199();
        System.out.println(q.solution(board));
    }

    public int solution(String[] board) {
        col = board.length;
        row = board[0].length();
        visited = new int[col][row];

        int result = -1;

        for (int i = 0; i < board.length; i++) { // 열
            for (int j = 0; j < board[0].length(); j++) { // 행
                if (board[i].charAt(j) == 'R') {
                    result = find(board, i, j);
                }
            }
        }

        return result;
    }

    public static int find(String[] board, int a, int b) {
        Queue<Point> q = new LinkedList<>();

        // 출발점 표시
        q.add(new Point(a, b));
        visited[a][b] = 1;

        while (!q.isEmpty()) {
            Point cur = q.poll();
            if (board[cur.x].charAt(cur.y) == 'G') { // 목표지점에 도착하면 끝내기
                return visited[cur.x][cur.y] - 1;
            }

            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];
                while (true) {
                    // 이동할 수 있는 최대한의 범위까지 이동
                    if (isInRange(nx, ny) && board[nx].charAt(ny) != 'D') {
                        nx += dx[d];
                        ny += dy[d];
                    } else { // 보드범위 밖 or 장애물 발견 -> 직전으로 이동
                        nx -= dx[d];
                        ny -= dy[d];
                        break;
                    }
                }

                // 해당 지점에 방문한적이 없다면, 해당 지점에서 탐색
                if (visited[nx][ny] == 0) {
                    q.add(new Point(nx, ny));
                    visited[nx][ny] = visited[cur.x][cur.y] + 1;
                }
            }
        }
        return -1;
    }

    private static boolean isInRange(int x, int y) { // 이동할 수 있는 범위 안에 있으면 true, 아니면 false 반환
        return y >= 0 && y < row && x >= 0 && x < col;
    }

}
