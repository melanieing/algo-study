import java.util.*;
// 258709. 주사위 고르기
public class ChooseDice_258709 {

    private int[][] dice;
    private int[] combi;
    private ArrayList<int[]> diceCombi;
    private ArrayList<Integer> score;

    public static void main(String[] args) {
        int[][] dice = {{1, 2, 3, 4, 5, 6}, {3, 3, 3, 3, 4, 4}, {1, 3, 3, 4, 4, 4}, {1, 1, 4, 4, 5, 5}};

        ChooseDice_258709 q = new ChooseDice_258709();
        System.out.println(Arrays.toString(q.solution(dice))); // [1, 4]
    }

    public int[] solution(int[][] dice) {
        int[] answer = {};
        int diceCnt = dice.length;
        int maxWinCnt = 0;

        this.dice = dice;
        this.diceCombi = new ArrayList<>();
        this.score = new ArrayList<>();

        combi = new int[diceCnt / 2];

        calculateCombination(0, 0, diceCnt / 2); // 모든 점수조합 구하기

        for (int i = 0; i < diceCombi.size() / 2; i++) {
            int idx1 = i;
            int idx2 = diceCombi.size() - i - 1;

            int[] combi1 = diceCombi.get(idx1);
            int[] combi2 = diceCombi.get(idx2);

            // 중복된 점수가 있으니 점수조합 압축하기 (점수, 점수의 수)
            HashMap<Integer, Integer> scoreCnt1 = calculateScoreCnt(combi1, 0, diceCnt / 2);
            HashMap<Integer, Integer> scoreCnt2 = calculateScoreCnt(combi2, 0, diceCnt / 2);

            int winCnt = 0;
            int loseCnt = 0;

            for (int first : scoreCnt1.keySet()) {
                for (int second : scoreCnt2.keySet()) {
                    if (first > second) winCnt += scoreCnt1.get(first) * scoreCnt2.get(second);
                    else if (first < second) loseCnt += scoreCnt1.get(first) * scoreCnt2.get(second);
                }
            }
            if (maxWinCnt < winCnt) {
                maxWinCnt = winCnt;
                answer = combi1;
            }
            if (maxWinCnt < loseCnt) {
                maxWinCnt = loseCnt;
                answer = combi2;
            }
        }

        for (int i = 0; i < answer.length; i++) {
            answer[i]++; // index 0인 주사위를 1번 주사위로 변경
        }

        return answer;
    }

    private void calculateCombination(int start, int curDepth, int maxDepth) {
        if (curDepth == maxDepth) {
            diceCombi.add(arrayDeepCopy(combi));
            return;
        }
        for (int i = start; i < dice.length; i++) {
            combi[curDepth] = i;
            calculateCombination(i + 1, curDepth + 1, maxDepth);
        }

    }

    private HashMap<Integer, Integer> calculateScoreCnt(int[] combi, int curDepth, int maxDepth) {
        if (curDepth == maxDepth) {
            HashMap<Integer, Integer> scoreCnt = new HashMap<>();
            for (int s : score) {
                if (!scoreCnt.containsKey(s)) scoreCnt.put(s, 1);
                else scoreCnt.put(s, scoreCnt.get(s) + 1);
            }
            score.clear();
            return scoreCnt;
        }
        int idx = combi[curDepth];
        if (score.isEmpty()) {
            for (int i = 0; i < 6; i++) {
                score.add(dice[idx][i]);
            }
        } else {
            int size = score.size();
            for (int i = 0; i < size; i++) {
                int s = score.remove(0);
                for (int j = 0; j < 6; j++) {
                    score.add(dice[idx][j] + s);
                }
            }
        }
        return calculateScoreCnt(combi, curDepth + 1, maxDepth);
    }

    private int[] arrayDeepCopy(int[] arr) {
        return Arrays.stream(arr).toArray();
    }

}
