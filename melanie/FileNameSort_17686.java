import java.util.ArrayList;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 17686. [3차] 파일명 정렬
public class FileNameSort_17686 {

    static class File {
        String name;

        public File(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    static class FileNameComparator implements Comparator<File> {
        @Override
        public int compare(File f1, File f2) {
            // 정규표현식으로 파일 이름을 세 부분으로 분리
            String regex = "(\\D+)(\\d{1,5})(.*)";
            Pattern pattern = Pattern.compile(regex);

            String[] parts1 = splitFileName(f1.getName(), pattern);
            String[] parts2 = splitFileName(f2.getName(), pattern);

            // 파일명은 우선 HEAD 부분을 기준으로 사전 순으로 정렬한다. 이때, 문자열 비교 시 대소문자 구분을 하지 않는다.
            int result = parts1[0].compareToIgnoreCase(parts2[0]);
            if (result != 0) {
                return result;
            }

            // 파일명의 HEAD 부분이 대소문자 차이 외에는 같을 경우, NUMBER의 숫자 순으로 정렬한다.
            // 9 < 10 < 0011 < 012 < 13 < 014 순으로 정렬된다. 숫자 앞의 0은 무시되며, 012와 12는 정렬 시에 같은 같은 값으로 처리된다.
            result = Integer.compare(Integer.parseInt(parts1[1]), Integer.parseInt(parts2[1]));
            if (result != 0) {
                return result;
            }

            // 두 파일의 HEAD 부분과, NUMBER의 숫자도 같을 경우, 원래 입력에 주어진 순서를 유지한다.
            // MUZI01.zip과 muzi1.png가 입력으로 들어오면, 정렬 후에도 입력 시 주어진 두 파일의 순서가 바뀌어서는 안 된다.
            return 0;
        }

        private String[] splitFileName(String fileName, Pattern pattern) {
            Matcher matcher = pattern.matcher(fileName);
            if (matcher.matches()) {
                return new String[] { matcher.group(1), matcher.group(2), matcher.group(3) };
            } else {
                throw new IllegalArgumentException("File name does not match the expected pattern: " + fileName);
            }
        }
    }

    public String[] solution(String[] files) {

        // 리스트에 담기
        ArrayList<File> fileList = new ArrayList<>();
        for (String file : files) {
            fileList.add(new File(file));
        }

        // 리스트 정렬
        fileList.sort(new FileNameComparator());

        // list -> array로 변환하여 반환
        return fileList.stream()
                .map(File::getName)
                .toArray(String[]::new);

    }

}
