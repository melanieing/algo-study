import java.util.Arrays;
// 147354. 테이블 해시 함수
public class TableHashFunc_147354 {
    public static void main(String[] args) {
        int[][] data = {{2,2,6},{1,5,10},{4,2,9},{3,8,3}};
        int col = 2;
        int row_begin = 2;
        int row_end = 3;

        TableHashFunc_147354 q = new TableHashFunc_147354();
        System.out.println(q.solution(data, col, row_begin, row_end));

    }

    public int solution(int[][] data, int col, int row_begin, int row_end) {
        /**
         * 해시 함수는 col, row_begin, row_end을 입력으로 받습니다.
         * 테이블의 튜플을 col번째 컬럼의 값을 기준으로 오름차순 정렬을 하되, 만약 그 값이 동일하면 기본키인 첫 번째 컬럼의 값을 기준으로 내림차순 정렬합니다.
         * 정렬된 데이터에서 S_i를 i 번째 행의 튜플에 대해 각 컬럼의 값을 i 로 나눈 나머지들의 합으로 정의합니다.
         * row_begin ≤ i ≤ row_end 인 모든 S_i를 누적하여 bitwise XOR 한 값을 해시 값으로서 반환합니다.
         */
        int answer = 0;
        Arrays.sort(data, ((o1, o2) -> o1[col-1] != o2[col-1] ? o1[col-1] - o2[col-1] : o2[0] - o1[0]));
        for (int i = row_begin - 1; i <= row_end - 1; i++) {
            int S_i = 0;
            for (int d : data[i]) {
                S_i += (d % (i + 1)); // i 번째 행의 튜플에 대해 각 컬럼의 값을 i 로 나눈 나머지들의 합
            }
            answer ^= S_i;
        }

        return answer;
    }
}
