// 92335. k진수에서 소수 개수 구하기

public class CntPrimeNumFromKDigit_92335 {
    public int solution(int n, int k) {

        String num = Long.toString(n, k);
        int count = 0;

        for (int i = 0; i <= num.length(); i++) {
            for (int j = i + 1; j <= num.length(); j++) {
                String tmp = num.substring(i, j);
                if (!tmp.equals("") && isPrime(tmp)) {
                    if (
                        (i == 0 && j < num.length() && num.charAt(j) == 48) || // 소수 오른쪽에만 0이 있고 왼쪽에는 아무것도 없는 경우
                        (j == num.length() && i != 0 && num.charAt(i-1) == 48) || // 소수 왼쪽에만 0이 있고 오른쪽에는 아무것도 없는 경우
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

    public boolean isPrime(String num) { // 1과 자기 자신으로 밖에 나누어 떨어지지 않고 자기 자신의 곱셈의 역수가 없는 수
        if (num.contains("0")) return false; // 각 자릿수에 0을 포함하면 넘어가기

        long target = Long.parseLong(num);
        if (target == 1) return false;
        if (target == 2) return true;

        for (int i = 2; i <= Math.sqrt(target); i++) {
            if (target % i == 0) {
                return false;
            }
        }
        return true;
    }
}
