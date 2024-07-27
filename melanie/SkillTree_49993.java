import java.util.HashMap;
import java.util.Map;
// 49993. 스킬트리
public class SkillTree_49993 {

    public static void main(String[] args) {
        String skill = "CBD";
        String[] skill_trees = {"AAA"};
//        String[] skill_trees = {"BACDE", "CBADF", "AECB", "BDA"};

        SkillTree_49993 q = new SkillTree_49993();
        System.out.println(q.solution(skill, skill_trees));
    }
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;

        // (스킬, 순서)를 매핑하여 저장해두기
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < skill.length(); i++) {
            map.put(skill.charAt(i), i);
        }

        for (String skillTree : skill_trees) {
            // 순서와 상관없이 배울 수 있는 스킬들은 모두 제외하기
            StringBuilder sb = new StringBuilder();
            for (char c : skillTree.toCharArray()) {
                if (map.containsKey(c)) sb.append(c);
            }
            // 가능한 스킬트리만 세기
            String clearedSkillTree = sb.toString();
            if (clearedSkillTree.isEmpty() || clearedSkillTree.contains(skill) || (skill.contains(clearedSkillTree) && map.get(clearedSkillTree.charAt(0)) == 0)) {
                answer++;
            }
        }
        return answer;
    }
}
