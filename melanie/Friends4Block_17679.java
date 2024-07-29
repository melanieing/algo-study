import java.util.*;
// 17679. [1차] 프렌즈4블록
public class Friends4Block_17679 {

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int getX() {
            return x;
        }

        int getY() {
            return y;
        }
    }

    static int[][] arr;
    static int answer = 0;

    public static void main(String[] args) {
        int m = 4;
        int n = 5;
        String[] board = {"CCBDE", "AAADE", "AAABF", "CCBBF"};
        Friends4Block_17679 q = new Friends4Block_17679();
        System.out.println(q.solution(m, n, board));
    }

    public int solution(int m, int n, String[] board) {

        // 2차원 배열 생성하고 숫자로 변환하여 넣기
        arr = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = board[i].charAt(j);
            }
        }

        // 게임을 진행하면서 블럭을 지우기 (더 이상 지울 블럭이 없으면 멈추기)
        while (deleteBlocksAndReturnCnt()) {
            // 위에서부터 아래로 내려서 정리하기
            clearBlocks();
        }

        return answer;
    }

    public static boolean deleteBlocksAndReturnCnt() {
        int deletedSize = 0;
        HashSet<Point> set = new HashSet<>();

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr[0].length - 1; j++) {
                if (arr[i][j] == 0) continue; // 이미 지운 블록은 건너뛰기
                if (arr[i][j] == arr[i][j+1] && arr[i][j+1] == arr[i+1][j+1] && arr[i+1][j+1] == arr[i+1][j]) {
                    set.add(new Point(i, j));
                    set.add(new Point(i, j + 1));
                    set.add(new Point(i + 1, j + 1));
                    set.add(new Point(i + 1, j));
                }
            }
        }

        for (Point p : set) {
            if (arr[p.getX()][p.getY()] == 0) continue;
            deletedSize++;
            arr[p.getX()][p.getY()] = 0; // 삭제
        }

        answer += deletedSize;
        return deletedSize != 0;
    }

    public static void clearBlocks() {
        // 0열부터 탐색
        for (int j = 0; j < arr[0].length; j++) {
            int space = 0;
            for (int i = arr.length-1; i >= 0; i--) {
                if (arr[i][j] == 0) {
                    space++;
                } else if (arr[i][j] != 0 && space != 0) {
                    arr[i + space][j] = arr[i][j];
                    arr[i][j] = 0;
                }
            }
        }
    }
}
