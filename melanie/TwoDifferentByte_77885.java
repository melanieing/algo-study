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
        String binNum = Long.toBinaryString(number);
        if (number % 2L == 0) { // 짝수일 경우
            return number + 1;
        } else {
            int idx = binNum.lastIndexOf("0");
            String tmp = "";
            if (idx != -1) {
                tmp = binNum.substring(0, idx) + "1" + binNum.substring(idx+1);
            } else {
                tmp = "1" + binNum;
            }
            return Long.parseLong(tmp, 2);
        }
    }

}
