import java.util.Arrays;

public class Friends4Block_17679 {
    public static void main(String[] args) {
        int m = 4;
        int n = 5;
        String[] board = {"CCBDE", "AAADE", "AAABF", "CCBBF"};
        Friends4Block_17679 q = new Friends4Block_17679();
        System.out.println(q.solution(m, n, board));
    }

    public int solution(int m, int n, String[] board) {
        int answer = 0;

        int[][] arr = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = board[i].charAt(j);
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                while (arr[i][j] == arr[i][j+1]) {

                }
            }
        }

        print2DArray(arr);

        System.out.println(arr[0][2]);

        return answer;
    }

    public static void print2DArray(int[][] array) {
        Arrays.stream(array).forEach(row -> {
            Arrays.stream(row).forEach(element -> System.out.printf("%4d", element));
            System.out.println();
        });
    }
}
