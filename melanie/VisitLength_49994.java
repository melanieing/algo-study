import java.util.*;
// 49994. 방문 길이
public class VisitLength_49994 {
    public static void main(String[] args) {
        String dirs = "ULURRDLLU";
        VisitLength_49994 home = new VisitLength_49994();
        System.out.println(home.solution(dirs));
    }

    class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }

    class Move {
        Point source;
        Point destination;

        public Move(Point source, Point destination) {
            this.source = source;
            this.destination = destination;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Move move = (Move) o;
            return Objects.equals(source, move.source) && Objects.equals(destination, move.destination);
        }

        @Override
        public int hashCode() {
            return Objects.hash(source, destination);
        }
    }

    int solution(String dirs) {
        int answer = 0; // 처음 가는 길의 길이(개수)

        // 이동로그를 기록할 set 생성(중복제거)
        Set<Move> moveSet = new HashSet<>();

        // 시작점
        int x = 0, y = 0;

        for (int i = 0; i < dirs.length(); i++) {
            char direction = dirs.charAt(i);
            Point source = new Point(x, y);
            Point destination = null;

            switch (direction) {
                case 'U' -> destination = new Point(x, y + 1); // 올라갈 경우
                case 'D' -> destination = new Point(x, y - 1); // 내려갈 경우
                case 'R' -> destination = new Point(x + 1, y); // 오른쪽으로 갈 경우
                case 'L' -> destination = new Point(x - 1, y); // 왼쪽으로 갈 경우
            }

            // 좌표평면의 경계를 벗어나면 이동하지 않음
            assert destination != null;
            if (isOutOfRange(destination)) continue;

            // 처음 가보는 길이라면
            if (!moveSet.contains(new Move(source, destination)) && !moveSet.contains(new Move(destination, source))) {
                answer++;
                moveSet.add(new Move(source, destination));
                moveSet.add(new Move(destination, source));
            }

            // 현재위치 업데이트
            x = destination.x;
            y = destination.y;

        }
        return answer;
    }

    boolean isOutOfRange(Point destination) {
        return destination.getX() > 5 || destination.getX() < -5 || destination.getY() > 5 || destination.getY() < -5;
    }
}
