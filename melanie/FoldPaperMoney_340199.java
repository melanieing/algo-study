import java.util.Arrays;
// 340199. [PCCE 기출문제] 9번 / 지폐 접기
public class FoldPaperMoney_340199 {
    public static void main(String[] args) {
        int[] wallet = {30, 15};
        int[] bill = {26, 17};

        FoldPaperMoney_340199 q = new FoldPaperMoney_340199();
        System.out.println(q.solution(wallet, bill));
    }

    public int solution(int[] wallet, int[] bill) {
        int answer = 0;

        Arrays.sort(wallet);
        Arrays.sort(bill);

        while ((bill[0] > wallet[0] || bill[1] >= wallet[1]) && (bill[0] > wallet[1] || bill[1] > wallet[0])) {
            Arrays.sort(bill);
            bill[1] /= 2;
            answer++;
        }

        return answer;
    }
}
