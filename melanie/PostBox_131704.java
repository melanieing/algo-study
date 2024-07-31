import java.util.*;
// 131704. 택배상자
public class PostBox_131704 {

    public static void main(String[] args) {
        int[] order = {4, 3, 1, 2, 5};
        PostBox_131704 q = new PostBox_131704();
        System.out.println(q.solution(order));
    }
    public int solution(int[] order) {
        int answer = 0;

        Stack<Integer> subCon = new Stack<>();
        int num = 0;
        for (int i = 1; i <= order.length; i++) {
            if (i == order[num]) { // 실을 수 있으면 싣기
                answer++;
                num++;
            } else { // 실을 수 없으면 보조 컨테이너에 넣기
                subCon.push(i);
            }
            while (!subCon.empty()) {
                if (subCon.peek() == order[num]) { // 실을 수 있으면 싣기
                    subCon.pop();
                    answer++;
                    num++;
                } else { // 실을 수 없으면 나오기
                    break;
                }
            }
        }

        return answer;
    }
}
