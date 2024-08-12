import java.util.*;
// 250121. [PCCE 기출문제] 10번 / 데이터 분석
public class AnalyzeData_250121 {

    static class Data {
        int code;
        int date;
        int maximum;
        int remain;

        public Data(int code, int date, int maximum, int remain) {
            this.code = code;
            this.date = date;
            this.maximum = maximum;
            this.remain = remain;
        }

        public int getCode() {
            return code;
        }

        public int getDate() {
            return date;
        }

        public int getMaximum() {
            return maximum;
        }

        public int getRemain() {
            return remain;
        }

    }
    public static void main(String[] args) {
        // ["코드 번호(code)", "제조일(date)", "최대 수량(maximum)", "현재 수량(remain)"]
        int[][] data = {{1, 20300104, 100, 80}, {2, 20300804, 847, 37}, {3, 20300401, 10, 8}}; // 정렬한 데이터들이 담긴 이차원 정수 리스트
        String ext = "date"; //  어떤 정보를 기준으로 데이터를 뽑아낼지
        int val_ext = 20300501; // 뽑아낼 정보의 기준값
        String sort_by = "remain"; // 정보를 정렬할 기준

        AnalyzeData_250121 q = new AnalyzeData_250121();
        System.out.println(Arrays.deepToString(q.solution(data, ext, val_ext, sort_by)));
    }

    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        // Data의 List를 만든다.
        List<Data> list = new ArrayList<>();

        // ext 값이 val_ext보다 작은 데이터만 뽑아서 List에 넣는다.
        for (int[] dat : data) {
            int code = dat[0];
            int date = dat[1];
            int maximum = dat[2];
            int remain = dat[3];
            switch (ext) {
                case "code" -> {
                    if (code < val_ext) list.add(new Data(code, date, maximum, remain));
                }
                case "date" -> {
                    if (date < val_ext) list.add(new Data(code, date, maximum, remain));
                }
                case "maximum" -> {
                    if (maximum < val_ext) list.add(new Data(code, date, maximum, remain));
                }
                case "remain" -> {
                    if (remain < val_ext) list.add(new Data(code, date, maximum, remain));
                }
                default -> {
                }
            }
        }

        // List를 sort_by에 해당하는 값을 기준으로 오름차순으로 정렬한다.
        List<Data> tmpList = sortData(list, sort_by);

        // List -> 2차원 배열로 변환한다.
        int[][] answer = new int[list.size()][4];

        for (int i = 0; i < answer.length; i++) {
            Data tmpData = tmpList.get(i);
            answer[i][0] = tmpData.getCode();
            answer[i][1] = tmpData.getDate();
            answer[i][2] = tmpData.getMaximum();
            answer[i][3] = tmpData.getRemain();
        }

        return answer;
    }

    List<Data> sortData(List<Data> list, String sort_by) {
        switch (sort_by) {
            case "code" -> list.sort(Comparator.comparingInt(Data::getCode));
            case "date" -> list.sort(Comparator.comparingInt(Data::getDate));
            case "maximum" -> list.sort(Comparator.comparingInt(Data::getMaximum));
            case "remain" -> list.sort(Comparator.comparingInt(Data::getRemain));
            default -> {
            }
        }
        return list;
    }

}
