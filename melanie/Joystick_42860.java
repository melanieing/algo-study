import org.junit.Assert;
import org.junit.Test;
// 42860. 조이스틱
public class Joystick_42860 {
    public int solution(String name) {
        int answer = 0; // 조이스틱 조작 횟수
        int move = name.length() - 1; // 기본 최소 좌우이동 횟수 (좌, 우 커서)

        // 해당 커서 알파벳 변경 최솟값 (위, 아래 커서)
        for (int i = 0; i < name.length(); i++) {
            answer += Math.min(name.charAt(i) - 'A', 26 - (name.charAt(i) - 'A')); // 반드시 이동해야 하는 최소 횟수 (A -> 타겟알파벳)
            if (i < name.length() - 1 && name.charAt(i + 1) == 'A') {
                int end = i + 1; // 연속된 'A'가 끝나는 지점 찾기
                while (end < name.length() && name.charAt(end) == 'A') {
                    end++;
                }
                move = Math.min(move, i * 2 + (name.length() - end)); // 오른쪽으로 갔다 다시 왼쪽으로 꺾기 ex) BBAAAAAYYY
                move = Math.min(move, i + (name.length() - end) * 2); // 왼쪽으로 갔다 다시 오른쪽으로 꺾기 ex) CCCAAAAAAY
            }
        }
        return answer + move;
    }

    @Test
    public void test() {
        String name = "JEROEN";
        Assert.assertEquals(56, solution(name));
    }
}
