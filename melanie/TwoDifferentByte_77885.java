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
            answer[idx++] = f(number);
        }
        return answer;
    }

    private long f(long number) { // number보다 크고 number와 비트가 1 ~ 2개 다른 수들 중에서 제일 작은 수 (10, 11번 케이스 시간초과)
        for (long i = number + 1; ; i++) {
            long xorResult = number ^ i;
            if (Long.bitCount(xorResult) == 1 || Long.bitCount(xorResult) == 2) {
                return i;
            }
        }
    }

}
