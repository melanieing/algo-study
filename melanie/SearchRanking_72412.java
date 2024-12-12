import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 72412. 순위 검색
public class SearchRanking_72412 {

    public int[] solution(String[] info, String[] query) {
        Map<String, ArrayList<Integer>> map = new HashMap<>();

        for (String ifo : info) {
            String[] splitArr = ifo.split(" ");
            // 가능한 모든 경우의 수를 조합해서 key를 만들기
            String[] languages = {splitArr[0], "-"};
            String[] positions = {splitArr[1], "-"};
            String[] careers = {splitArr[2], "-"};
            String[] foods = {splitArr[3], "-"};
            int score = Integer.parseInt(splitArr[4]);

            for (String language : languages) {
                for (String position : positions) {
                    for (String career : careers) {
                        for (String food : foods) {
                            String[] keydata = {language, position, career, food};
                            String key = String.join(" ", keydata);
                            // value : 점수를 리스트에 담기
                            ArrayList<Integer> scoreList = map.getOrDefault(key, new ArrayList<>());
                            scoreList.add(score);
                            map.put(key, scoreList);
                        }
                    }
                }
            }
        }

            // value의 점수를 오름차순으로 정렬
            map.values().forEach(scoreList -> scoreList.sort(Integer::compareTo));

            int[] answer = new int[query.length];
            int i = 0;
            for (String q : query) {
                String[] tmp = q.split(" and ");

                int scoreTarget = Integer.parseInt(tmp[3].split(" ")[1]);
                // food만 남기기
                tmp[3] = tmp[3].split(" ")[0];
                // key 생성하기
                String key = String.join(" ", tmp);

                if (map.containsKey(key)) {
                    // 점수 목록 조회
                    ArrayList<Integer> list = map.get(key);
                    // 최하점 찾아서 개수를 빠르게 구하기
                    int left = 0;
                    int right = list.size();
                    while (left < right) {
                        int mid = (left + right) / 2;
                        if (list.get(mid) >= scoreTarget) right = mid;
                        else left = mid + 1;
                    }
                    answer[i] = list.size() - left;
                }
                i++;
            }

        return answer;
    }

    @Test
    public void test() {
        String[] info = { "java backend junior pizza 150", "python frontend senior chicken 210",
                "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80",
                "python backend senior chicken 50" };
        String[] query = { "java and backend and junior and pizza 100",
                "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250",
                "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150" };
        Assert.assertArrayEquals(new int[]{1,1,1,1,2,4}, solution(info, query));
    }
}
