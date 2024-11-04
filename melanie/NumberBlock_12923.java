import java.util.Arrays;
// 12923. 숫자 블록
// 어떠한 위치 a에 배치되는 숫자는 "어떠한 위치 a 의 약수 중 자신을 제외한 가장 큰 수"
public class NumberBlock_12923 {
    public static void main(String[] args) {
        int begin = 1;
        int end = 10;

        NumberBlock_12923 q = new NumberBlock_12923();
        System.out.println(Arrays.toString(q.solution(begin, end)));

    }

    public long[] solution(long begin, long end) {
        long[] answer = new long[(int)(end-begin)+1];

        for (int i = 0; i < answer.length; i++) { // 0~9
            long num = begin + i; // 1~10

            //약수 구하기
            int maxDivisor = 1;
            for (long div = 2; div <= Math.sqrt(num); div++) {
                if (num % div == 0) {
                    //최대 10억의 제곱근이므로 약 3만으로, 천만 이하인지 검사X
                    int divisor1 = (int) div;
                    //최대 10억 / 2,  5억이므로 천만이하인지 검사 필요함
                    int divisor2 = (int) (num / div);

                    //가장 큰 약수 찾기
                    maxDivisor = Math.max(maxDivisor, divisor1);
                    if (divisor2 <= 10000000) {
                        maxDivisor = Math.max(maxDivisor, divisor2);
                    }
                }
            }
            answer[i] = maxDivisor;
        }

        if (begin == 1) {
            answer[0] = 0;
        }

        return answer;
    }

}
