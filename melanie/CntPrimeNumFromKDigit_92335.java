// 92335. k진수에서 소수 개수 구하기

public class CntPrimeNumFromKDigit_92335 {
    public static void main(String[] args) {
        System.out.println(solution(110011, 10));
    }
    static int solution(int n, int k) {

        String num = Long.toString(n, k);
        int count = 0;
        for (int i = 0; i <= num.length(); i++) {
            for (int j = i + 1; j <= num.length(); j++) {
                String tmp = num.substring(i, j); // 211 (i = 0, j = 3)
                if (isPrime(tmp)) {
                    if (
                            (i == 0 && num.charAt(j) == 48) || // 소수 오른쪽에만 0이 있고 왼쪽에는 아무것도 없는 경우
                            (j == num.length() && num.charAt(i-1) == 48) || // 소수 왼쪽에만 0이 있고 오른쪽에는 아무것도 없는 경우
                            (i == 0 && j == num.length()) || // 소수 양쪽에 아무것도 없는 경우
                            (i > 0 && num.charAt(i-1) == 48 && j < num.length() && num.charAt(j) == 48) // 소수 양쪽에 0이 있는 경우
                    )
                    {
                        count++;
                    }

                }
            }
        }

        return count;
    }

    static boolean isPrime(String num) { // 1과 자기 자신으로 밖에 나누어 떨어지지 않고 자기 자신의 곱셈의 역수가 없는 수
        if (num.contains("0")) return false;

        Long target = Long.parseLong(num);
        if (target == 1) return false;

        for (long i = 2; i <= (long)Math.sqrt(target); i++) {
            if (target % i == 0) {
                return false;
            }
        }
        return true;
    }
}
