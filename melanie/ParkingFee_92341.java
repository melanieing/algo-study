import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

// 92341. 주차 요금 계산

public class ParkingFee_92341 {

    static class ParkingRecord {
        int num; // 차량번호
        String inTm; // 입차시간
        String outTm; // 출차시간
        int time; // 누적주차시간
        int fee; // 주차요금

        ParkingRecord(int num) {
            this.num = num;
        }

        // Getter와 Setter 메소드
        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getInTm() {
            return inTm;
        }

        public void setInTm(String inTm) {
            this.inTm = inTm;
        }

        public String getOutTm() {
            return outTm;
        }

        public void setOutTm(String outTm) {
            this.outTm = outTm;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public int getFee() {
            return fee;
        }

        public void setFee(int fee) {
            this.fee = fee;
        }

        @Override
        public boolean equals(Object object) {
            ParkingRecord info = (ParkingRecord) object;
            // 차량번호만 같으면 true를 리턴함
            return info.num == this.num;
        }

    }

    public int[] solution(int[] fees, String[] records) {

        int basicTime = fees[0]; // 기본시간(분)
        int basicFee = fees[1]; // 기본요금
        int unitTime = fees[2]; // 단위시간(분)
        int unitFee = fees[3]; // 단위요금

        ArrayList<ParkingRecord> list = new ArrayList<>();

        for (String str : records) {
            String[] tmp = str.split(" ");
            String time = tmp[0];
            int num = Integer.parseInt(tmp[1]);
            String sign = tmp[2];

            // 새로운 차라면 리스트에 등록
            if (!list.contains(new ParkingRecord(num))) {
                list.add(new ParkingRecord(num));
            }

            // 이제부터는 무조건 리스트에 있음
            ParkingRecord target = findRecordByNum(list, num);
            if ("IN".equals(sign)) {
                target.setInTm(time);
                target.setOutTm(null);
            } else if ("OUT".equals(sign)) {
                target.setOutTm(time);
                target.setTime(target.getTime() + calculateParkingTime(target.getInTm(), target.getOutTm()));
            }

        }

        for (ParkingRecord record : list) {
            if (record.getOutTm() == null) { // 출차시간이 없으면 23:59으로 추정
                record.setOutTm("23:59");
                record.setTime(record.getTime() + calculateParkingTime(record.getInTm(), record.getOutTm()));
            }
            record.setFee(calculateParkingFee(record.getTime(), basicTime, basicFee, unitTime, unitFee));
        }

        // 차량번호 기준으로 오름차순 정렬
        list.sort(Comparator.comparingInt(ParkingRecord::getNum));

        int[] answer = new int[list.size()];

        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i).getFee(); // 각 주차요금만 담아서 반환
        }

        return answer;
    }

    // 특정 num을 가진 ParkingRecord를 찾는 메소드
    public static ParkingRecord findRecordByNum(ArrayList<ParkingRecord> records, int num) {
        for (ParkingRecord record : records) {
            if (record.getNum() == num) {
                return record;
            }
        }
        return null; // 해당 num을 가진 ParkingRecord가 없으면 null 반환
    }

    // inTm과 outTm을 받아 누적 주차시간을 계산하는 메소드
    public static int calculateParkingTime(String inTm, String outTm) {
        // 시간 형식 지정
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        // LocalTime 객체로 변환
        LocalTime inTime = LocalTime.parse(inTm, formatter);
        LocalTime outTime = LocalTime.parse(outTm, formatter);

        // Duration 객체를 사용하여 시간 차이 계산
        Duration duration = Duration.between(inTime, outTime);

        // 총 분으로 반환
        return (int) duration.toMinutes();
    }

    // 주차 요금을 계산하는 메소드
    public static int calculateParkingFee(int parkingTime, int basicTime, int basicFee, int unitTime, int unitFee) {

        // 기본 주차 시간을 초과하는 시간 계산
        if (parkingTime <= basicTime) {
            return basicFee;
        } else {
            int extraTime = parkingTime - basicTime;
            int extraUnits = (int) Math.ceil((double) extraTime / unitTime);
            int extraFee = extraUnits * unitFee;
            return basicFee + extraFee;
        }
    }
}
