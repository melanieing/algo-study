import java.util.*;

public class MakeBigNum_42883 {
    public static void main(String[] args) {
        String number = "1231234";
        int k = 3;

        MakeBigNum_42883 q = new MakeBigNum_42883();
        System.out.println(q.solution(number, k));
    }

    public String solution(String number, int k) {

        Set<String> set = new HashSet<>(Arrays.asList(number.split("")));

        // Set을 List로 변환
        List<String> stringList = new ArrayList<>(set); // 1, 2, 3, 4

        // List를 숫자 값 기준으로 오름차순 정렬
        Collections.sort(stringList, Comparator.comparingInt(Integer::parseInt));

        StringBuilder sb = new StringBuilder();
        for (int i = stringList.size() - 1; i >= stringList.size() - k; i--) {
            sb.append(Integer.parseInt(stringList.get(i)));
        }

        return sb.toString();
    }
}
