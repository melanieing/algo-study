import org.junit.Assert;
import org.junit.Test;

import java.util.*;

// 250136. [PCCP 기출문제] 2번 / 석유 시추
public class OilDrilling_250136 {
    static int row, col;
    static int[] oil;

    public int solution(int[][] land) {
        int answer = 0;

        col = land.length; // n
        row = land[0].length; // m
        oil = new int[row];
        
        boolean[][] visited = new boolean[col][row];
        
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                if (land[i][j] == 1 && !visited[i][j]) {
                    bfs(land, visited, i, j);
                }
            }
        }

        // 시추관 하나를 설치해 뽑을 수 있는 가장 많은 석유량
        answer = Arrays.stream(oil).max().getAsInt();
        return answer;
    }

    private void bfs(int[][] land, boolean[][] visited, int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        visited[x][y] = true;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        int count = 1;
        Set<Integer> set = new HashSet<>();

        while (!q.isEmpty()) {
            int[] now = q.poll();
            set.add(now[1]);

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                // 범위를 벗어날 경우
                if (!checkRange(nx, ny)) continue;

                // 방문한적 없고 석유가 있다면
                if (land[nx][ny] == 1 && !visited[nx][ny]) {
                    q.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                    count++;
                }
            }
        }
        // 시추관이 뽑을 수 있는 석유량은 시추관이 지나는 석유 덩어리들의 크기를 모두 합한 값
        for (int index : set) oil[index] += count;
    }

    private boolean checkRange(int x, int y) {
        if (x < 0 || x >= col || y < 0 || y >= row) return false;
        return true;
    }

    @Test
    public void test() {
        int[][] land = {{0, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 1, 1, 0, 0}, {1, 1, 0, 0, 0, 1, 1, 0}, {1, 1, 1, 0, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 0, 1, 1}};
        Assert.assertEquals(9, solution(land));
    }
}
