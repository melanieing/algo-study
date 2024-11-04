import java.util.Arrays;

// 181916. 주사위 게임 3
public class DiceGame3_181916 {
    public static void main(String[] args) {
        int a = 6, b = 3, c = 3, d = 6;

        DiceGame3_181916 q = new DiceGame3_181916();
        System.out.println(q.solution(a, b, c, d));
    }

    public int solution(int a, int b, int c, int d) {
        int answer = 0;

        if (a == b && b == c && c == d) {
            answer = 1111 * a;
        } else if (a == b && b == c) {
            answer = (int) Math.pow(10 * a + d, 2);
        } else if (a == b && b == d) {
            answer = (int) Math.pow(10 * a + c, 2);
        } else if (a == c && c == d) {
            answer = (int) Math.pow(10 * a + b, 2);
        } else if (b == c && c == d) {
            answer = (int) Math.pow(10 * b + a, 2);
        } else if (a == b && c == d) {
            answer = (a + c) * Math.abs(a - c);
        } else if ((a == c && b == d) || (a == d && b == c)) {
            answer = (a + b) * Math.abs(a - b);
        } else if (a == b && c != d) {
            answer = c * d;
        } else if (a == c && b != d) {
            answer = b * d;
        } else if (a == d && b != c) {
            answer = b * c;
        } else if (b == d && a != c) {
            answer = a * c;
        } else if (b == c && a != d) {
            answer = a * d;
        } else if (c == d && a != b) {
            answer = a * b;
        } else {
            answer = Math.min(a, Math.min(b, Math.min(c, d)));
        }

        return answer;
    }

    public int solution2(int a, int b, int c, int d) {

        // 배열에 넣고 오름차순으로 정렬하기
        int[] arr = {a, b, c, d};
        Arrays.sort(arr);
        a = arr[0];
        b = arr[1];
        c = arr[2];
        d = arr[3];

        return
        a == d ? a * 1111 : // 네 수가 같을 때
        a == c ? (10 * a + d) * (10 * a + d) : // abc가 같을 때
        b == d ? (10 * b + a) * (10 * b + a) : // bcd가 같을 때
        a == b & c == d ? c * c - a * a : // 두 개씩 두 쌍이 같을 때
        a == b ? c * d : // ab가 같을 때
        b == c ? a * d : // bc가 같을 때
        c == d ? a * b : // cd가 같을 때
        a; // 모두 다를 때
    }
}
