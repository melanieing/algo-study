import org.junit.Assert;
import org.junit.Test;

import java.util.*;

// 42890. 후보키
public class CandidateKey_42890 {
    List<String> candidateKeys = new ArrayList<>();

    public int solution(String[][] relation) {
        for (int i = 0; i < relation[0].length; i++) {
            boolean[] visited = new boolean[relation[0].length];
            dfs(visited, 0, 0, i + 1, relation);
        }

        return candidateKeys.size();
    }

    void dfs(boolean[] visited, int start, int depth, int end, String[][] relation) {
        if (depth == end) {
            List<Integer> list = new ArrayList<>();
            StringBuilder key = new StringBuilder();
            for (int i = 0; i < visited.length; i++) {
                if (visited[i]) {
                    key.append(i);
                    list.add(i);
                }
            }

            Map<String, Integer> map = new HashMap<>();

            for (String[] strings : relation) {
                StringBuilder s = new StringBuilder();
                for (Integer j : list) {
                    s.append(strings[j]);
                }

                if (map.containsKey(s.toString())) return;
                else map.put(s.toString(), 0);
            }

            // 후보키 추가
            for (String s : candidateKeys) {
                int count = 0;
                for (int i = 0; i < key.length(); i++) {
                    String subS = String.valueOf(key.charAt(i));
                    if (s.contains(subS)) count++;
                }
                if (count == s.length()) return;
            }

            candidateKeys.add(key.toString());

            return;
        }

        for (int i = start; i < visited.length; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            dfs(visited, i, depth+1, end, relation);
            visited[i] = false;
        }
    }

    @Test
    public void test() {
        String[][] relation = {
            {"100","ryan","music","2"},{"200","apeach","math","2"},{"300","tube","computer","3"},
            {"400","con","computer","4"},{"500","muzi","music","3"},{"600","apeach","music","2"}
        };
        Assert.assertEquals(2, solution(relation));
    }
}
