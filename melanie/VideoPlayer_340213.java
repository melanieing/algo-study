import org.junit.Assert;
import org.junit.Test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

// 340213. [PCCP 기출문제] 1번 / 동영상 재생기
public class VideoPlayer_340213 {
    static LocalTime videoTime;
    static LocalTime posTime;
    static LocalTime opStartTime;
    static LocalTime opEndTime;

    public static String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = "";

        String[] videoLen = video_len.split(":");
        String[] posStr = pos.split(":");
        String[] opStart = op_start.split(":");
        String[] opEnd = op_end.split(":");

        // 모두 시간으로 변환
        videoTime = getTime(videoLen);
        posTime = getTime(posStr);
        opStartTime = getTime(opStart);
        opEndTime = getTime(opEnd);

        // 시작점이 오프닝구간이면 건너뛰기
        skipOpening();

        for (String command : commands) {
            if (command.equals("prev")) moveToPrev();
            else moveToNext();
            // 결과적으로 오프닝구간이면 건너뛰기
            skipOpening();
        }

        return posTime.format(DateTimeFormatter.ofPattern("mm:ss"));
    }

    private static void moveToNext() { // 10초 후로 이동
        posTime = posTime.plusSeconds(10);
        if (posTime.isAfter(videoTime)) { // 전체 동영상 시간을 넘어가는 경우
            posTime = videoTime;
        }
    }

    private static void moveToPrev() { // 10초 전으로 이동
        int minute = posTime.getMinute();
        int second = posTime.getSecond();

        if (minute == 0 && second < 10) { // 현재 재생위치10초 전으로 이동하면 0초가 되는 경우
            posTime = LocalTime.of(0, 0, 0);
            return;
        }

        posTime = posTime.minusSeconds(10);
    }

    private static void skipOpening() { // 오프닝 건너뛰기
        if (!posTime.isBefore(opStartTime) && !posTime.isAfter(opEndTime)) posTime = opEndTime;
    }

    private static LocalTime getTime(String[] timeStr) {
        int min = Integer.parseInt(timeStr[0]);
        int sec = Integer.parseInt(timeStr[1]);
        return LocalTime.of(0, min, sec);
    }

    @Test
    public void test() {
        String video_len = "34:33", pos = "13:00", op_start = "00:55", op_end = "02:55";
        String[] commands = {"next", "prev"};
        Assert.assertEquals("13:00", solution(video_len, pos, op_start, op_end, commands));
    }

    // 아래는 다른 풀이
    public String solution2(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = "";

        int startTime = convertToTime(pos);
        int finishTime = convertToTime(video_len);
        int opStartTime = convertToTime(op_start);
        int opEndTime = convertToTime(op_end);

        // 오프닝 건너뛰기
        startTime = skipOpening(startTime, opStartTime, opEndTime);

        for (String command : commands) {
            // 10초 전으로 이동
            if (command.equals("prev")) {
                if (startTime - 10 >= 0) {
                    startTime -= 10;
                } else {
                    startTime = 0;
                }
                // 10초 후로 이동
            } else if (command.equals("next")) {
                if (startTime + 10 <= finishTime) {
                    startTime += 10;
                } else {
                    startTime = finishTime;
                }
            }
            // 오프닝 건너뛰기
            startTime = skipOpening(startTime, opStartTime, opEndTime);
        }

        return convertTimeToString(startTime);
    }

    public int skipOpening(int time, int opStartTime, int opEndTime) {
        // 시작위치가 오프닝구간이므로 오프닝 끝나는 위치로 이동
        if (time <= opEndTime && time >= opStartTime) {
            time = opEndTime;
        }
        return time;
    }

    public int convertToTime(String time) {
        String[] arr = time.split(":");
        int min = Integer.parseInt(arr[0]) * 60;
        int sec = Integer.parseInt(arr[1]);
        return min + sec;
    }

    public String convertTimeToString(int time) {
        int min = time / 60;
        int sec = time - min * 60;

        String minStr = Integer.toString(min);
        if (min == 0) {
            minStr = "00";
        } else if (minStr.length() == 1) {
            minStr = "0" + minStr;
        }

        String secStr = Integer.toString(sec);
        if (sec == 0) {
            secStr = "00";
        } else if (secStr.length() == 1) {
            secStr = "0" + secStr;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(minStr).append(":").append(secStr);

        return sb.toString();
    }

}

