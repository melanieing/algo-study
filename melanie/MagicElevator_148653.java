// 148653. 마법의 엘리베이터
public class MagicElevator_148653 {
    public static void main(String[] args) {
        int storey = 2554;
        MagicElevator_148653 q = new MagicElevator_148653();
        System.out.println(q.solution(storey));
    }

    public int solution(int storey) {
        int answer = 0;

        while (storey != 0) {
            int first = storey % 10; // 1의 자릿수
            int tenth = (storey / 10) % 10; // 10의 자릿수

            if (first > 5) { // 1의 자릿수가 5보다 크면 올리는 게 더 빠름
                answer += 10 - first; // 10
                storey += 10;
            } else if (first == 5) { // 정확히 5면 십의 자릿수롤 보고 판단
                answer += first;
                storey += tenth < 5 ? 0 : 10;
            } else { // 5보다 작으면 내리는 게 더 빠름
                answer += first;
            }

            storey /= 10;
        }

        return answer;

    }


}
