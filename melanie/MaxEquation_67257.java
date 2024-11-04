import java.util.ArrayList;
// 67257. 수식 최대화
public class MaxEquation_67257 {

    static char[] operations = {'+', '-', '*'};
    static long answer = 0;
    static boolean[] visited = new boolean[operations.length];
    static ArrayList<Long> numberList = new ArrayList<>();
    static ArrayList<Character> operationList = new ArrayList<>();

    public static void main(String[] args) {
        String expression = "100-200*300-500+20";

        MaxEquation_67257 q = new MaxEquation_67257();
        System.out.println(q.solution(expression));
    }

    public long solution(String expression) {
        StringBuilder num = new StringBuilder();

        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) >= '0' && expression.charAt(i) <= '9') {
                num.append(expression.charAt(i));
            } else {
                numberList.add(Long.parseLong(num.toString()));
                num = new StringBuilder();
                operationList.add(expression.charAt(i));
            }
        }
        numberList.add(Long.parseLong(num.toString()));

        dfs(0, new char[operations.length]);

        return answer;
    }

    public void dfs(int cnt, char[] tmpOperations) {
        if (cnt == operations.length) {
            ArrayList<Long> numList = new ArrayList<>(numberList);
            ArrayList<Character> opList = new ArrayList<>(operationList);

            for (char tmpOperation : tmpOperations) {
                for (int j = 0; j < opList.size(); j++) {
                    if (tmpOperation == opList.get(j)) {
                        Long result = calculate(numList.remove(j), numList.remove(j), tmpOperation);
                        numList.add(j, result);
                        opList.remove(j);
                        j--;
                    }
                }
            }
            answer = Math.max(answer, Math.abs(numList.get(0)));
        }

        for (int i = 0; i < operations.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                tmpOperations[cnt] = operations[i];
                dfs(cnt+1, tmpOperations);
                visited[i] = false;
            }
        }
    }

    public long calculate(long num1, long num2, char operation) {
        if (operation == '+') {
            return num1 + num2;
        } else if (operation == '-') {
            return num1 - num2;
        } else {
            return num1 * num2;
        }
    }
}
