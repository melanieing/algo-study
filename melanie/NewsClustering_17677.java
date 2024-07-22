import java.util.ArrayList;
// 17677. [1차] 뉴스 클러스터링
public class NewsClustering_17677 {

    static int solution(String str1, String str2) {
        // 대소문자를 무시
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        // 각각의 집합 생성
        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();

        // 첫 번째 집합 만들기
        for (int i = 0; i < str1.length() - 1; i++) {
            String tmp = str1.substring(i, i + 2);
            if (!containsNonWordCharacterOrNumberOrUnderBar(tmp)) list1.add(tmp);
        }
        int size1 = list1.size();

        // 두 번째 집합 만들기
        for (int i = 0; i < str2.length() - 1; i++) {
            String tmp = str2.substring(i, i + 2);
            if (!containsNonWordCharacterOrNumberOrUnderBar(tmp)) list2.add(tmp);
        }
        int size2 = list2.size();

        // 첫 번째 집합과 두 번째 집합의 교집합 만들기
        int son = 0;
        for (String str : list2) {
            if (list1.contains(str)) { // 교집합일 경우
                list1.remove(str);
                son++; // 교집합 크기에 더해주기
            }
        }

        int mother = size1 + size2 - son; // 두 집합의 합집합 크기

        return (mother == 0 && son == 0) ? 65536 : (int) ((double) son/mother * 65536);
    }

    public static boolean containsNonWordCharacterOrNumberOrUnderBar(String input) {
        String regex = "[\\W\\d_]"; // 비단어문자(공백, 특수문자, 구두점 등)/숫자/언더바
        return input.matches(".*" + regex + ".*");
    }
}
