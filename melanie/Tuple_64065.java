import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashSet;
// 64065. 튜플
public class Tuple_64065 {
    public int[] solution(String s) {
        LinkedHashSet<Integer> set = new LinkedHashSet<>();

        // 양 끝에 있는 "{{"와 "}}"를 제거하기
        s = s.replaceAll("^\\{\\{|\\}\\}$", "");

        // "},{"를 기준으로 문자열 나누기
        String[] strings = s.split("\\},\\{");

        // 길이가 짧은 배열부터 오름차순 정렬
        Arrays.sort(strings, Comparator.comparingInt(String::length));

        // ","를 기준으로 나누어 차례대로 숫자로 변환하여 set에 넣기(중복제거)
        for (String str : strings) {
            String[] tmpArr = str.split(",");
            for (String tmp : tmpArr) {
                int num = Integer.parseInt(tmp);
                set.add(num);
            }
        }

        // set -> int[]로 변환하여 반환
        return set.stream().mapToInt(Integer::intValue).toArray();
    }
}
