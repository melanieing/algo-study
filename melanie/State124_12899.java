// 12899. 124 나라의 숫자
public class State124_12899 {

    public static void main(String[] args) {
        int n = 50;
        State124_12899 q = new State124_12899();
        System.out.println(q.solution(n));

    }
    public String solution(int n) {
        StringBuilder answer = new StringBuilder();
        String[] nums = {"4", "1", "2"};

        while (n > 0) {
            answer.insert(0, nums[n % 3]);
            n = (n - 1) / 3;
        }

        return answer.toString();
    }
}
