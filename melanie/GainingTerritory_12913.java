import java.util.*;
// 12913. 땅따먹기
public class GainingTerritory_12913 {
    public static void main(String[] args) {
//        int[][] land = {{1,2,3,5},{5,6,7,8},{4,3,2,1}};
        int[][] land = {{6, 7, 1, 2}, {2, 3, 10, 8}, {1, 3, 9, 4}, {7, 11, 4, 9}};

        GainingTerritory_12913 q = new GainingTerritory_12913();
        System.out.println(q.solution(land)); // 마지막 행까지 모두 내려왔을 때, 얻을 수 있는 점수의 최대값 (7 + 10 + 4 + 11
    }
    int solution(int[][] land) {
        int rows = land.length;
        int cols = land[0].length;
        int[][] dp = new int[rows][cols];

        // 마지막 열(땅)에 대해서만 초기화
        System.arraycopy(land[rows - 1], 0, dp[rows - 1], 0, cols);

        // 마지막 행부터 첫 행까지 순서대로 채우기
        for (int i = rows - 2; i >= 0; i--) {
            for (int j = 0; j < cols; j++) {
                dp[i][j] = land[i][j] + getMax(dp[i + 1], j);
            }
        }

        // 첫 행에서 최댓값 구하기
        return Arrays.stream(dp[0]).max().orElse(Integer.MIN_VALUE);
    }

    int getMax(int[] nowLandlist, int nowIdx) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nowLandlist.length; i++) {
            if (i == nowIdx) continue; // 현재 밟은 열과 같은 열은 밟을 수 없음
            if (nowLandlist[i] > max) max = nowLandlist[i];
        }
        return max;
    }

}
