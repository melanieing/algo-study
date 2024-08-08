import java.util.Arrays;
import java.util.Stack;

// 154539. 뒤에 있는 큰 수 찾기
public class FindBigNum_154539 {
    public static void main(String[] args) {
        int[] numbers = {2, 3, 3, 5};
        FindBigNum_154539 q = new FindBigNum_154539();
        System.out.println(Arrays.toString(q.solution(numbers))); // [3, 5, 5, -1]
    }

    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Stack<Integer> stack = new Stack<>();
        // 배열을 역순으로 순회하면서 각 원소에 대해 더 큰 수를 찾아 스택에 저장
        for (int i = numbers.length - 1; i >= 0; i--) {
            // 스택의 top이 현재 수보다 크지 않으면 꺼내기
            while (!stack.isEmpty() && stack.peek() <= numbers[i]) {
                stack.pop();
            }

            if (stack.isEmpty()) { // 뒷 큰 수가 없다는 의미
                answer[i] = -1;
            } else { // 스택의 top이 현재 수의 뒷 큰 수
                answer[i] = stack.peek();
            }

            // 현재수를 스택에 넣기
            stack.push(numbers[i]);
        }

        return answer;
    }

    public int[] solution2(int[] numbers) {
        int[] answer = new int[numbers.length];

        int idx = 0;
        for (int i = 0; i < numbers.length - 1; i++) {
            answer[idx++] = getBigNum(numbers, i);
        }

        answer[answer.length - 1] = -1;

        return answer;
    }

    public int getBigNum(int[] numbers, int index) {
        int targetNum = numbers[index];
        int answer = -1;
        for (int i = index + 1; i < numbers.length; i++) {
            if (targetNum < numbers[i]) {
                answer = numbers[i];
                break;
            }
        }
        return answer;
    }
}
