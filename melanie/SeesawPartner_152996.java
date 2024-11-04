import java.util.*;

// 152996. 시소 짝꿍
public class SeesawPartner_152996 {
    private static class NumberSet {
        int first, second;

        public NumberSet(int first, int second) {
            this.first = first;
            this.second = second;
        }

        public int getFirst() {
            return first;
        }

        public int getSecond() {
            return second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            NumberSet numberSet = (NumberSet) o;
            if (first == numberSet.getFirst() && second == numberSet.getSecond()) {
                return true;
            } else if (first == numberSet.getSecond() && second == numberSet.getFirst()) {
                return true;
            } else {
                return false;
            }
        }

    }
    public static void main(String[] args) {
        int[] weights = {100,180,360,100,270};

        SeesawPartner_152996 q = new SeesawPartner_152996();
        System.out.println(q.solution2(weights)); // 4
    }

    public long solution1(int[] weights) { // 시간초과 풀이
        // 몸무게를 정렬
        Arrays.sort(weights); //100, 100, 180, 270, 360

        // 각 몸무게의 빈도를 저장하는 맵 (몸무게1, 몸무게2)
        Set<NumberSet> set = new HashSet<>();

        for (int i = 0; i < weights.length; i++) {
            for (int j = i + 1; j < weights.length; j++) {
                int firstWeight = weights[i];
                int secondWeight = weights[j];
                if (firstWeight == secondWeight || firstWeight * 2 == secondWeight * 3 || firstWeight * 2 == secondWeight * 4 || firstWeight * 3 == secondWeight * 4
                || firstWeight * 3 == secondWeight * 2 || firstWeight * 4 == secondWeight * 2 || firstWeight * 4 == secondWeight * 3) {
                    set.add(new NumberSet(firstWeight, secondWeight));
                }
            }
        }

        return set.size();
    }

    public long solution2(int[] weights) {
        long answer = 0;

        // 몸무게를 정렬
        Arrays.sort(weights); //100, 100, 180, 270, 360

        int prev = 0;

        for (int i = 0; i < weights.length - 1; i++) {
            if (i > 0 && weights[i] == weights[i-1]) {
                prev--;
                answer += prev;
                continue;
            }
            int j = findRight(weights, weights[i], i+1);
            prev = 0;
            for (; j > i; j--) { // 안쪽 j 의 최대 범위를 이분 탐색으로 찾는다.
                if (weights[i] == weights[j] || weights[i] * 2 == weights[j]
                || weights[i] * 3 == weights[j] * 2 || weights[i] * 4 == weights[j] * 3) { // 숫자 w[i], w[j] 가 문제의 조건을 맞으면 카운팅
                    prev++;
                }
            }
            answer += prev;
        }

        return answer;
    }

    public int findRight(int[] weight, int num, int idx) {
        int left = idx;
        int right = weight.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (weight[mid] > num * 2) {
                return mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }


}
