import java.util.HashMap;
import java.util.Map;
// 250137. [PCCP 기출문제] 1번 / 붕대 감기
public class Bandage_250137 {
    public static void main(String[] args) {
        int[] bandage = {1, 1, 1}; // 시전 시간, 초당 회복량, 추가 회복량
        int health = 5; // 최대 체력
        int[][] attacks = {{1, 2}, {3, 2}}; // 몬스터의 공격 시간과 피해량

        Bandage_250137 q = new Bandage_250137();
        System.out.println(q.solution(bandage, health, attacks)); // 모든 공격이 끝난 직후 남은 체력
    }

    public int solution(int[] bandage, int health, int[][] attacks) {
        int lastAttack = attacks[attacks.length-1][0];
        int combo = 0;
        int maxHealth = health;
        Map<Integer, Integer> attacksMap = new HashMap<>();
        for (int[] attack : attacks) {
            attacksMap.put(attack[0], attack[1]);
        }

        for (int t = 0; t <= lastAttack; t++) {
            // 체력이 0 이하가 되면 끝남
            if (health <= 0) break;

            // 1-1. 몬스터가 공격하면
            if (attacksMap.containsKey(t)) {
                combo = 0; // 콤보 초기화
                health -= attacksMap.get(t); // 피해량만큼 체력이 깎임
            } else { // 1-2. 공격하지 않으면
                combo++; // 붕대감기 시전해서 콤보 하나 올리기
                health += bandage[1]; // 초당회복량이 올라감

                // 콤보 성공하면
                if (combo == bandage[0]) {
                    health += bandage[2]; // 추가회복량이 올라감
                    combo = 0; // 콤보 초기화
                }

                // 체력은 최대체력을 넘어갈 수 없음
                if (health > maxHealth) health = maxHealth;
            }
        }

        return health <= 0 ? -1 : health;
    }

}
