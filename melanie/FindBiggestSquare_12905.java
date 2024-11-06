// 12905. 가장 큰 정사각형 찾기
public class FindBiggestSquare_12905 {

    public static void main(String[] args) {
        int[][] board = {{0,1,1,1},{1,1,1,1},{1,1,1,1},{0,0,1,0}};

        FindBiggestSquare_12905 q = new FindBiggestSquare_12905();
        System.out.println(q.solution(board));

    }

    public int solution(int[][] board) {
        // 가로나 세로가 1일 경우 정사각형은 무조건 1임으로 1로 초기화
        int answer = board.length == 1 || board[0].length == 1 ? 1 : 0;

        for (int i = 1; i < board.length; i++) {
            for (int j = 1; j < board[i].length; j++) {
                // 계산할 칸이 0이 아닐 경우 해당 칸에 주변 3개의 칸중에 최소값 + 1을 넣어줌
                if (board[i][j] != 0) {
                    board[i][j] = Math.min(board[i-1][j-1], Math.min(board[i-1][j], board[i][j-1])) + 1;
                    // 가장 변이 긴 정사각형은 answer에 저장
                    answer = Math.max(board[i][j], answer);
                }
            }
        }

        return answer * answer;
    }
}
