import org.junit.Assert;
import org.junit.Test;

// 160585. 혼자서 하는 틱택토
public class TickTackToeAlone_160585 {
    public int solution(String[] board) {
        int success = 1, fail = 0;

        char[][] realBoard = new char[board.length][board.length];

        // 0, X 개수 세기
        int oCnt = 0, xCnt = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                char c = board[i].charAt(j);
                if (c == 'O') oCnt++;
                else if (c == 'X') xCnt++;
                realBoard[i][j] = c;
            }
        }

        // 선공의 개수보다 후공의 개수가 더 많거나, 차이가 2개 이상 난다면 규칙 위반
        if (xCnt > oCnt || oCnt - xCnt > 1) return fail;

        boolean oWin = isBingo(realBoard, 'O');
        boolean xWin = isBingo(realBoard, 'X');

        // 선공과 후공이 동시에 이길 수는 없음
        if (oWin && xWin) return fail;
        // 선공이 이겼을 경우, 차이가 1만큼만 나야 함
        if (oWin && oCnt - xCnt != 1) return fail;
        // 후공이 이겼을 경우, 차이가 0이어야 함
        if (xWin && xCnt != oCnt) return fail;

        return success;
    }

    public boolean isBingo(char[][] realBoard, char target) {
        boolean isRowBingo = false;
        boolean isColBingo = false;
        boolean isDiaBingo = false;

        isRowBingo = isRowBingo(realBoard, target);
        isColBingo = isColBingo(realBoard, target);
        isDiaBingo = isDiaBingo(realBoard, target);

        return isRowBingo || isColBingo || isDiaBingo;

    }

    public boolean isRowBingo(char[][] realBoard, char target) {
        boolean isWin = false;
        for (int i = 0; i < realBoard.length; i++) {
            if (realBoard[i][0] == target && realBoard[i][1] == target && realBoard[i][2] == target) {
                isWin = true;
                break;
            }
        }
        return isWin;
    }

    public boolean isColBingo(char[][] realBoard, char target) {
        boolean isWin = false;
        for (int i = 0; i < realBoard.length; i++) {
            if (realBoard[0][i] == target && realBoard[1][i] == target && realBoard[2][i] == target) {
                isWin = true;
                break;
            }
        }
        return isWin;
    }

    public boolean isDiaBingo(char[][] realBoard, char target) {
        if (realBoard[0][0] == target && realBoard[1][1] == target && realBoard[2][2] == target) return true;
        if (realBoard[0][2] == target && realBoard[1][1] == target && realBoard[2][0] == target) return true;
        return false;
    }

    @Test
    public void test() {
        String[] board = {"O.X", ".O.", "..X"};
        Assert.assertEquals(1, solution(board));
    }
}
