import java.util.Arrays;
// 77885. 2개 이하로 다른 비트
public class TwoDifferentByte_77885 {
    public static void main(String[] args) {
        long[] numbers = {2,7};

        TwoDifferentByte_77885 q = new TwoDifferentByte_77885();
        System.out.println(Arrays.toString(q.solution(numbers)));
    }

    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        int idx = 0;

        for (long number : numbers) {
            answer[idx++] = f2(number); // number보다 크고 number와 비트가 1 ~ 2개 다른 수들 중에서 제일 작은 수
        }
        return answer;
    }

    private long f(long number) { // 10, 11번 케이스 시간초과
        if (number % 2L == 0) return number + 1;
        for (long i = number; ; i++) {
            long xorResult = number ^ i;
            if (Long.bitCount(xorResult) == 1L || Long.bitCount(xorResult) == 2L) {
                return i;
            }
        }
    }

    private long f2(long number) { // 모든 케이스 통과
        for (long i = 1; ; i <<= 1) { // 1부터 비트를 왼쪽으로 이동
            if ((number & i) == 0) { // 0을 찾으면 (AND 연산)
                number |= i; // 해당 비트를 1로 바꾸기 (OR 연산)
                number ^= (i >> 1); // 그 이전의 비트는 0으로 바꿔주기 (XOR 연산)
                return number;
            }
        }
    }

}
