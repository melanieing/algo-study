// 17687. [3차] n진수 게임
public class NNumberGame_17687 {
    public static void main(String[] args) {
        int n = 16; // 진법
        int t = 16; // 미리 구할 숫자의 갯수
        int m = 2; // 게임에 참가하는 인원
        int p = 2; // 튜브의 순서

        NNumberGame_17687 q = new NNumberGame_17687();
        System.out.println(q.solution(n, t, m, p)); // "13579BDF01234567"
    }

    public String solution(int n, int t, int m, int p) {
        StringBuilder totalNum = new StringBuilder();
        StringBuilder answer = new StringBuilder();

        // 해당 진법의 수로 변환한 문자열 만들기
        for (int i = 0; i <= t * m; i++) {
            String radixNum = Integer.toString(i, n);
            totalNum.append(radixNum.toUpperCase());
        }

        // 튜브의 순서에 해당하는 문자만 뽑아서 이어붙이기
        for (int i = 0; i < totalNum.length(); i++) {
            if (i % m == (p-1)) answer.append(totalNum.charAt(i));
            if (answer.length() == t) break; // 구할 갯수에 도달하면 끝내기
        }

        return answer.toString();
    }
}
