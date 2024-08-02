// 148653. 마법의 엘리베이터
public class MagicElevator_148653 {
    public static void main(String[] args) {
        int storey = 80;
        MagicElevator_148653 q = new MagicElevator_148653();
        System.out.println(q.solution(storey));
    }

    public int solution(int storey) {
        // 909 => 900 => -1 * 9 + -10 * 0 + -100 * 9 = 0
        // 909 => 910 => +1 * 1 + -10 * 1 + -100 * 9 = 0
        // 80 =?
        int minToTenth = getTenthNum(storey, -1);
        int maxToTenth = getTenthNum(storey, 1);

        int minToZero = getNumToZero(storey);
        int maxToZero = getNumToZero(storey);

        int totalMinToZero = minToTenth + minToZero;
        int totalMaxToZero = maxToTenth + maxToZero;

        return Math.min(totalMinToZero, totalMaxToZero);
    }

    private int getNumToZero(int num) {
        int numToZero = 0;
        while (num != 0) {
            int digits = String.valueOf(num).length(); // 4
            int tmp = (int)(num / Math.pow(10, digits-1)); // 2550 / 1000
            numToZero += tmp;
            num -= tmp * Math.pow(10, digits-1);
        }
        return numToZero;
    }

    private int getTenthNum(int num, int direction) {
        int numToTenth = 0;

        if (num % 10 == 0) return num;

        do {
            if (direction == 1) num++;
            else num--;
            numToTenth++;
        } while (num % 10 != 0);

        return numToTenth;
    }
}
