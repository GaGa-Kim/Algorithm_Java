package Programmers.LV_1;

/**
 * 250125) 이웃한_칸
 */
public class L079_250125 {
    // dh(h의 변화량)
    static int[] dh = { 0, 1, -1, 0 };
    // dw(w의 변화량)
    static int[] dw = { 1, 0, 0, -1 };

    // board(각 칸마다 색이 칠해진 2차원 격자 보드판)
    // h, w(고른 칸의 위치를 나타내는 두 정수)
    public int solution(String[][] board, int h, int w) {
        // answer(board[h][w]와 이웃한 칸들 중 같은 색으로 칠해져 있는 칸의 개수)
        int answer = 0;
        // n(board의 길이)
        int n = board.length;
        for (int i = 0; i <= 3; i++) {
            // h_check(체크할 칸의 h)
            int h_check = h + dh[i];
            // w_check(체크할 칸의 w)
            int w_check = w + dw[i];
            // h_check와 w_check가 보드를 벗어나지 않는다면
            if (h_check >= 0 && h_check < n && w_check >= 0 && w_check < n)
                // board[h][w]와 board[h_check][w_check]가 같다면
                if (board[h][w].equals(board[h_check][w_check]))
                    // answer 증가
                    answer++;
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L079_250125 solution = new L079_250125();

        String[][] board = { { "blue", "red", "orange", "red" },
                { "red", "red", "blue", "orange" },
                { "blue", "orange", "red", "red" },
                { "orange", "orange", "red", "blue" } };
        int h = 1;
        int w = 1;

        int result = solution.solution(board, h, w);

        System.out.println(result);
    }
}
