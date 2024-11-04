import java.util.Arrays;
// 135807. 숫자 카드 나누기
public class ShareNumberCard_135807 {
    public static void main(String[] args) {
        int[] arrayA = {10, 17};
        int[] arrayB = {5, 20};

        ShareNumberCard_135807 q = new ShareNumberCard_135807();
        System.out.println(q.solution(arrayA, arrayB)); // 0
    }

    public int solution1(int[] arrayA, int[] arrayB) { // 특정 케이스에서 시간초과
        int answer = 0;
        int maxA = Arrays.stream(arrayA).max().getAsInt();
        int maxB = Arrays.stream(arrayB).max().getAsInt();
        int max = Math.max(maxA, maxB);

        A : for (int i = 1; i <= max; i++) {
            boolean flag = true;
            B : for (int a : arrayA) {
                if (a % i == 0) {
                    C : for (int b : arrayB) {
                        if (b % i == 0) {
                            flag = false;
                            break B;
                        }
                    }
                } else {
                    flag = false;
                    break B;
                }
            }
            if (flag) answer = i;
        }

        A : for (int i = 1; i <= max; i++) {
            boolean flag = true;
            B : for (int b : arrayB) {
                if (b % i == 0) {
                    C : for (int a : arrayA) {
                        if (a % i == 0) {
                            flag = false;
                            break B;
                        }
                    }
                } else {
                    flag = false;
                    break B;
                }
            }
            if (flag) answer = Math.max(i, answer);
        }

        return answer;
    }

    public boolean isNotDivisible(int[] arr, int num) { // 해당 배열의 모든 값이 num으로 나누어 떨어지지 않음
        for (int n : arr) {
            if (n % num == 0) return false;
        }
        return true;
    }

    public int getGcd(int a, int b) { // 최대공약수 구하기
        if (a % b == 0) return b;
        return getGcd(b, a % b);
    }

    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        int gcdA = arrayA[0];
        int gcdB = arrayB[0];

        // 각 배열의 최대공약수 구하기
        for (int i = 1; i < arrayA.length; i++) {
            gcdA = getGcd(gcdA, arrayA[i]);
            gcdB = getGcd(gcdB, arrayB[i]);
        }

        // 나눌 수 없는지 확인 (최대공약수만 보면 됨)
        if (isNotDivisible(arrayA, gcdB)) {
            answer = Math.max(answer, gcdB);
        }
        if (isNotDivisible(arrayB, gcdA)) {
            answer = Math.max(answer, gcdA);
        }

        return answer;
    }
}
