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
        int order = 1;
        for (int i = 0; i <= t*m; i++) {
            String radixNum = Integer.toString(i, n);
//            System.out.println(radixNum.toUpperCase());
            totalNum.append(radixNum.toUpperCase());
        }
        System.out.println("totalNum : " + totalNum);
        for (int i = 1; i < totalNum.length(); i++) {
            if (i % m == (p-1)) {
                answer.append(totalNum.charAt(i-1));
                System.out.print("i = " + i + " , ");
                System.out.println("answer : " + answer.toString());
            }
            if (answer.length() == t) {
                System.out.println("answer의 길이 : " + answer.length());
                break;
            }
        }

        return answer.toString();
    }
}
