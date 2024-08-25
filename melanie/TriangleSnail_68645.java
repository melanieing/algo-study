import java.util.Arrays;
// 68645. 삼각 달팽이
public class TriangleSnail_68645 {

    private static final int[] dx = {0, 1, -1};
    private static final int[] dy = {1, 0, -1};

    public static void main(String[] args) {
        int n = 4;
        
        TriangleSnail_68645 q = new TriangleSnail_68645();
        System.out.println(Arrays.toString(q.solution(n)));
    }

    public int[] solution(int n) {
        int[][] arr = new int[n][n];
        int number = 1;
        int dir = 0;
        int x = 0, y = 0;

        while (true) {
            arr[y][x] = number++;
            int nextX = x + dx[dir];
            int nextY = y + dy[dir];
            if (nextX == n || nextY == n || arr[nextY][nextX] != 0) {
                dir = (dir + 1) % 3;
                nextX = x + dx[dir];
                nextY = y + dy[dir];
                if (nextX == n || nextY == n || arr[nextY][nextX] != 0) {
                    break;
                }
            }
            x = nextX;
            y = nextY;
        }

        int[] answer = new int[number-1];
        int idx = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                answer[idx++] = arr[i][j];
            }
        }

        return answer;
    }
}
