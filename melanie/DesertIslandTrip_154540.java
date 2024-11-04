import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
// 154540. 무인도 여행
public class DesertIslandTrip_154540 {

    private int sum = 0;
    private char[][] land;
    private boolean[][] visited;
    private List<Integer> result;
    public static void main(String[] args) {
        String[] maps = {"X591X","X1X5X","X231X", "1XXX1"};

        DesertIslandTrip_154540 q = new DesertIslandTrip_154540();
        System.out.println(Arrays.toString(q.solution(maps))); // 1, 1, 27
    }

    public int[] solution(String[] maps) {
        int row = maps.length;
        int col = maps[0].length();

        land = new char[row][col];
        visited = new boolean[row][col];
        result = new ArrayList<>();

        // 지도 세팅
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                land[i][j] = maps[i].charAt(j);
                if (maps[i].charAt(j) == 'X') visited[i][j] = true;
            }
        }

        // 탐색 시작
        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land[0].length; j++) {
                if (!visited[i][j]) { // 방문 안했으면
                    sum = 0;
                    dfs(land, visited, i, j);
                    result.add(sum);
                }
            }
        }

        int[] answer;
        if (result.isEmpty()) return new int[]{-1};
        else answer = result.stream().mapToInt(Integer::intValue).sorted().toArray();

        return answer;
    }

    public void dfs(char[][] land, boolean[][] visited, int x, int y) {
        // 범위를 벗어나거나 방문한 곳이면 패스
        if (x < 0 || y < 0 || x >= land.length || y >= land[0].length) return;
        if (visited[x][y]) return;

        // 문자를 숫자로 변환해서 저장
        sum += land[x][y] - '0';
        visited[x][y] = true;

        // 상하좌우 탐색
        dfs(land, visited, x, y + 1);
        dfs(land, visited, x, y - 1);
        dfs(land, visited, x + 1, y);
        dfs(land, visited, x - 1, y);
    }

}
