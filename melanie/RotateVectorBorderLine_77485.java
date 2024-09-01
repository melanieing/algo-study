import java.util.*;
// 77485. 행렬 테두리 회전하기
public class RotateVectorBorderLine_77485 {
    public static void main(String[] args) {
        int rows = 6;
        int columns = 6;
        int[][] queries = {{2,2,5,4},{3,3,6,6},{5,1,6,3}};

        RotateVectorBorderLine_77485 q = new RotateVectorBorderLine_77485();
        System.out.println(Arrays.toString(q.solution(rows, columns, queries)));
    }

    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];

        // 배열 만들기
        int[][] arr = new int[rows][columns];
        int num = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                arr[i][j] = num++;
            }
        }

        // 움직일 방향 설정
        int[] dirRow = {0, 1, 0, -1};
        int[] dirCol = {1, 0, -1, 0};

        // 회전
        for (int i = 0; i < queries.length; i++) {
            int min = Integer.MAX_VALUE;

            // 회전하면서 들릴 꼭짓점 순서대로 넣기
            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[] {queries[i][0] - 1, queries[i][3] - 1});
            q.offer(new int[] {queries[i][2] - 1, queries[i][3] - 1});
            q.offer(new int[] {queries[i][2] - 1, queries[i][1] - 1});
            q.offer(new int[] {queries[i][0] - 1, queries[i][1] - 1});

            int tmp = 0;
            int d = 0;
            int row = queries[i][0] - 1;
            int col = queries[i][1] - 1;

            while (!q.isEmpty()) {
                // 바꾸기
                int value = arr[row][col];
                min = Math.min(min, value);

                if (tmp != 0) {
                    arr[row][col] = tmp;
                }

                // 이동하기
                int[] p = q.peek();
                if (row== p[0] && col == p[1]) {
                    q.poll();
                    if (q.isEmpty()) {
                        break;
                    }
                    d++; // 현재위치가 꼭짓점이면 d 증가시키기
                }

                row += dirRow[d];
                col += dirCol[d];
                tmp = value;
            }

            answer[i] = min;

        }

        return answer;
    }

}
