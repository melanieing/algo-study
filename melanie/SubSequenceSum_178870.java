import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
// 178870. 연속된 부분 수열의 합
public class SubSequenceSum_178870 {
    public static void main(String[] args) {
        int[] sequence = {1,2,3,4,5};
        int k = 7;

        SubSequenceSum_178870 q = new SubSequenceSum_178870();
        System.out.println(Arrays.toString(q.solution(sequence, k)));
    }

    private static class SubSequence implements Comparable<SubSequence> {
        int left, right, size;

        public SubSequence(int left, int right) {
            this.left = left;
            this.right = right;
            this.size = right - left;
        }

        @Override
        public int compareTo(SubSequence o) {
            if (this.size == o.size) { // 길이가 짧은 수열이 여러 개인 경우 앞쪽(시작 인덱스가 작은)에 나오는 수열
                return this.left - o.left;
            }
            return this.size - o.size; // 합이 k인 부분 수열이 여러 개인 경우 길이가 짧은 수열
        }
    }

    public int[] solution(int[] sequence, int k) {
        List<SubSequence> subSequences = new ArrayList<>();
        int left = 0;
        int right = 0;
        int sum = sequence[0]; // 부분수열의 합

        while (true) {
            if (sum == k) { // 부분수열 합이 k면 정답후보
                subSequences.add(new SubSequence(left, right));
            }

            // 더 이상 이동시킬 수 없으면 멈추기
            if (left == sequence.length && right == sequence.length) break;

            // 부분수열 합이 k보다 작으면 right가 이동 (이동할 수 있을 때만)
            if (sum <= k && right < sequence.length) {
                right++;
                if (right < sequence.length) { // 새로 추가된 원소를 더하기
                    sum += sequence[right];
                }
            } else { // 그외에는 left가 이동
                if (left < sequence.length) { // 기존 원소는 빼기
                    sum -= sequence[left];
                    left++;
                }
            }
        }

        subSequences.sort(SubSequence::compareTo); // 우선순위대로 정렬하기

        return new int[]{subSequences.get(0).left, subSequences.get(0).right};
    }
}
