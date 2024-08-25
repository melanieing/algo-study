// 42883. 큰 수 만들기
public class MakeBigNum_42883 {
    public static void main(String[] args) {
        String number = "1231234";
        int k = 3;

        MakeBigNum_42883 q = new MakeBigNum_42883();
        System.out.println(q.solution(number, k));
    }

    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
        int size = number.length() - k; // 구해야 하는 자릿수
        int start = 0;

        while (start < number.length() && sb.length() != size) {
            int leftNum = k + sb.length() + 1;
            int max = 0;
            for (int i = start; i < leftNum; i++) {
                if (max < number.charAt(i) - '0') {
                    max = number.charAt(i) - '0';
                    start = i + 1;
                }
            }
            sb.append(max);
        }

        return sb.toString();
    }
}
