import org.junit.Assert;
import org.junit.Test;

// 12952. N-Queen
public class NQueen_12952 {
    static int[] board;
    static int answer = 0;
    public int solution(int n) {
        board = new int[n];

        backtrack(0, n);

        return answer;
    }

    void backtrack(int depth, int n) {
        if (depth == n) {
            answer++;
            return;
        }
        for (int i = 0; i < n; i++) {
            board[depth] = i;
            if (isValid(depth)) backtrack(depth + 1, n);
        }
    }

    boolean isValid(int depth) {
        // 마지막으로 놓여진 것과 이전의 것들을 비교
        for (int i = 0; i < depth; i++) {
            // 직선
            if (board[depth] == board[i]) return false;
            // 대각선
            if (Math.abs(depth - i) == Math.abs(board[depth] - board[i])) return false;
        }
        return true;
    }

    @Test
    public void test() {
        Assert.assertEquals(2, solution(4));
    }
}
