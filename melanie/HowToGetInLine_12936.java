import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
// 12936. 줄 서는 방법
public class HowToGetInLine_12936 {
    static long cnt;
    static boolean[] visited;
    static int[] answer;
    public static void main(String[] args) {
        int n = 3;
        long k = 5;

        HowToGetInLine_12936 q = new HowToGetInLine_12936();
//        System.out.println(Arrays.toString(q.fullSearch(n, k)));
        System.out.println(Arrays.toString(q.solution(n, k)));
    }

    public int[] fullSearch(int n, long k) {
        answer = new int[n];
        visited = new boolean[n];
        dfs(0, new int[n], k);
        return answer;
    }

    public void dfs(int depth, int[] arr, long k) {
        if (depth == arr.length) {
            cnt++;
            if (cnt == k) {
                System.arraycopy(arr, 0, answer, 0, arr.length);
            }
            return;
        }
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                arr[depth] = i + 1;
                dfs(depth + 1, arr, k);
                visited[i] = false;
            }
        }
    }

    public int[] solution(int n, long k) {
        int[] answer = new int[n];
        List<Integer> list = new ArrayList<>(); // 사람 리스트
        long factorial = 1;
        int idx = 0;

        for (int i = 1; i <= n; i++) {
            list.add(i);
            factorial *= i;
        }

        k--;

        while (n > 0) {
            factorial /= n-- ;
            int val = (int) (k / factorial);
            answer[idx++] = list.remove(val);
            k %= factorial;
        }

        return answer;
    }

}
