import java.util.Stack;
// 60058. 괄호 변환
public class ChangeBrackets_60058 {
    public static void main(String[] args) {
        String p = "(()())()";

        ChangeBrackets_60058 q = new ChangeBrackets_60058();
        System.out.println(q.solution(p));
    }

    public String solution(String p) {
        if (isRight(p)) return p;
        else return dfs(p);
    }

    public boolean isRight(String w) {
        Stack<Character> stack = new Stack<>();

        for (char c : w.toCharArray()) {
            if (c == '(') stack.push(c); // 왼쪽괄호는 넣기
            else { // 오른쪽괄호는 뽑기
                if (stack.isEmpty()) return false; // 근데 뽑을 게 없으면 짝이 안 맞는 것
                else stack.pop();
            }
        }

        return stack.isEmpty(); // 짝이 다 맞아서 비었으면 올바른 괄호
    }

    public String dfs(String w) {
        // 1. 입력이 빈 문자열인 경우, 빈 문자열을 반환합니다.
        if (w.isEmpty()) return w;

        // 2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다. 단, u는 "균형잡힌 괄호 문자열"로 더 이상 분리할 수 없어야 하며, v는 빈 문자열이 될 수 있습니다.
        String u = "", v = "";
        int cnt1 = 0, cnt2 = 0;
        for (int i = 0; i < w.length(); i++) {
            char c = w.charAt(i);

            if (c == '(') cnt1++;
            else cnt2++;

            if (cnt1 > 0 && cnt1 == cnt2) {
                u = w.substring(0, i + 1);
                if (i < w.length() - 1) {
                    v = w.substring(i + 1);
                    break;
                }
            }
        }
        // 3. 문자열 u가 "올바른 괄호 문자열" 이라면 문자열 v에 대해 1단계부터 다시 수행합니다.
        if (isRight(u)) {
            //   3-1. 수행한 결과 문자열을 u에 이어 붙인 후 반환합니다.
            return u + dfs(v);
        } else {
            // 4. 문자열 u가 "올바른 괄호 문자열"이 아니라면 아래 과정을 수행합니다.
            String tmp = "(" + dfs(v) + ")";
            u = u.substring(1, u.length()-1);
            u = u.replace("(", ".");
            u = u.replace(")", "(");
            u = u.replace(".", ")");
            tmp += u;
            return tmp;
        }
    }
}
