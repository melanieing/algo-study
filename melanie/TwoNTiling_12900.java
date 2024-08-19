// 12900. 2 x n 타일링

public class TwoNTiling_12900 {
    public static void main(String[] args) {
        int n = 4;

        TwoNTiling_12900 q = new TwoNTiling_12900();
        System.out.println(q.solution(n));
    }

    public int solution(int n) {
        int[] arr = new int[n];
        arr[0] = 1;
        arr[1] = 2;

        for (int i = 2; i < arr.length; i++) {
            arr[i] = (arr[i - 1] + arr[i - 2]) % 1000000007;
        }

        return arr[n - 1];
    }
}
