import java.util.Arrays;
// 12949. 행렬의 곱셈
public class MatrixMultiply_12949 {
    public static void main(String[] args) {
        int[][] arr1 = {{1, 4}, {3, 2}, {4, 1}};
        int[][] arr2 = {{3, 3}, {3, 3}};

        MatrixMultiply_12949 q = new MatrixMultiply_12949();
        System.out.println(Arrays.deepToString(q.solution(arr1, arr2))); // [[15, 15], [15, 15], [15, 15]]

    }

    public int[][] solution(int[][] arr1, int[][] arr2) {
        int rows1 = arr1.length;
        int cols1 = arr1[0].length;
        int cols2 = arr2[0].length;

        int[][] answer = new int[rows1][cols2]; // 1번 행렬의 row 개수 X 2번 행렬의 col 개수

        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols2; j++) {
                answer[i][j] = 0; // 일단 0으로 초기화
                for (int k = 0; k < cols1; k++) { // 1번 행렬의 col 개수만큼 반복해서 크로스로 곱한 걸 더해주기
                    answer[i][j] += arr1[i][k] * arr2[k][j];
                }
            }
        }

        return answer;
    }

}
