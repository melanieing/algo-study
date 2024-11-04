import java.util.*;
// 155651. 호텔 대실
public class HotelRent_155651 {
    public static void main(String[] args) {
        String[][] book_time = {{"15:00", "17:00"}, {"16:40", "18:20"}, {"14:20", "15:20"}, {"14:10", "19:20"}, {"18:20", "21:20"}};

        HotelRent_155651 q = new HotelRent_155651();
        System.out.println(q.solution(book_time));
    }

    static class BookTime implements Comparable<BookTime> {
        int startTime;
        int endTime;

        public BookTime(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public int getStartTime() {
            return startTime;
        }

        public int getEndTime() {
            return endTime;
        }

        @Override
        public int compareTo(BookTime o) {
            int firstSt = this.startTime;
            int firstEt = this.endTime;
            int secondSt = o.startTime;
            int secondEt = o.endTime;

            if (firstSt == secondSt) {
                return firstEt - secondEt;
            } else {
                return firstSt - secondSt;
            }
        }
    }

    public int solution(String[][] book_time) {

        List<BookTime> list = new ArrayList<>();
        for (String[] time : book_time) {
            String[] startTimes = time[0].split(":");
            String[] endTimes = time[1].split(":");
            int start = Integer.parseInt(startTimes[0]) * 60 + Integer.parseInt(startTimes[1]);
            int end = Integer.parseInt(endTimes[0]) * 60 + Integer.parseInt(endTimes[1]) + 10;

            list.add(new BookTime(start, end));
        }

        Collections.sort(list); // 1. 시작시간 2. 종료시간으로 정렬

        PriorityQueue<Integer> rooms = new PriorityQueue<>();

        for (BookTime time : list) {
            if (!rooms.isEmpty() && rooms.peek() <= time.getStartTime()) { // 방이 있고 가장 빠른 종료시간이 현재 예약시작시간보다 앞이면 대실가능(재사용)
                rooms.poll();
            }
            rooms.add(time.getEndTime()); // 새로운 방에 대실, 새롭게 종료시간 업데이트
        }

        return rooms.size();

    }
}
