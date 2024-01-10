package Programmers.LV_2;

/**
 * 12905) 가장_큰_정사각형_찾기
 */
public class L007_12905 {
    // board(1와 0로 채워진 표)
    public int solution(int[][] board) {
        // answer(표에서 1로 이루어진 가장 큰 정사각형의 한 변의 길이)
        int answer = 0;
        // board의 길이 또는 board[0]의 길이가 2보다 작다면
        if (board.length < 2 || board[0].length < 2) {
            // 최대 넓이는 1이므로 1 반환
            return 1;
        }
        // board의 길이와 board[0]의 길이가 2보다 크다면
        for (int i = 1; i < board.length; i++) {
            for (int j = 1; j < board[0].length; j++) {
                // board[i][j]가 1이라면
                if (board[i][j] == 1) {
                    // 자신의 왼쪽 위 대각선, 위쪽, 왼쪽의 최솟값을 구하고 자신의 위치에 최솟값 + 1을 저장
                    board[i][j] = Math.min(board[i - 1][j - 1], Math.min(board[i - 1][j], board[i][j - 1])) + 1;
                }
                // 변의 길이 증가에 따른 answer 갱신
                answer = Math.max(answer, board[i][j]);
            }
        }
        // answer * answer 반환
        return answer * answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L007_12905 solution = new L007_12905();

        int[][] board = {
                { 0, 1, 1, 1 },
                { 1, 1, 1, 1 },
                { 1, 1, 1, 1 },
                { 0, 0, 1, 0 }
        };

        int result = solution.solution(board);

        System.out.println(result);
    }
}
