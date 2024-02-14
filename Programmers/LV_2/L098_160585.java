package Programmers.LV_2;

/**
 * 160585) 혼자서_하는_틱택토
 */
public class L098_160585 {
    // board(틱택토 게임판의 정보를 담고 있는 문자열 배열)
    public int solution(String[] board) {
        // oCount(O의 개수)
        int oCount = 0;
        // xCount(X의 개수)
        int xCount = 0;
        // O와 X 개수 세기
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // board[i].char(j)가 O라면
                if (board[i].charAt(j) == 'O')
                    // oCount 증가
                    oCount++;
                // board[i].char(j)가 X라면
                else if (board[i].charAt(j) == 'X')
                    // xCount 증가
                    xCount++;
            }
        }
        // O가 선공이므로 X의 개수가 O의 개수보다 많다면
        if (xCount > oCount)
            // 규칙을 위반하므로 0 반환
            return 0;
        // 번갈아가며 진행해야 하므로 O가 X의 개수보다 1개 많다면
        if (oCount > xCount + 1)
            // 규칙을 위반하므로 0 반환
            return 0;
        // O가 이겼을 때 oCount와 xCount가 같다면
        if (hasWin(board, 'O'))
            if (oCount == xCount)
                // 규칙을 위반하므로 0 반환
                return 0;
        // X가 이겼을 때 oCount와 xCount + 1이 같다면
        if (hasWin(board, 'X'))
            if (oCount == xCount + 1)
                // 규칙을 위반하므로 0 반환
                return 0;
        // 1 반환
        return 1;
    }

    // 이겼는지 함수
    private boolean hasWin(String[] board, char target) {
        // 가로 확인
        for (int i = 0; i < 3; i++) {
            if (board[i].charAt(0) == target && board[i].charAt(1) == target && board[i].charAt(2) == target)
                // 가로 3개가 같은 표시이므로 true 반환
                return true;
        }
        // 세로 확인
        for (int i = 0; i < 3; i++) {
            if (board[0].charAt(i) == target && board[1].charAt(i) == target && board[2].charAt(i) == target)
                // 세로 3개가 같은 표시이므로 true 반환
                return true;
        }
        // 대각선 확인
        if (board[0].charAt(0) == target && board[1].charAt(1) == target && board[2].charAt(2) == target)
            // 대각선 3개가 같은 표시이므로 true 반환
            return true;
        if (board[0].charAt(2) == target && board[1].charAt(1) == target && board[2].charAt(0) == target)
            // 대각선 3개가 같은 표시이므로 true 반환
            return true;
        // 아직 이기지 못했으므로 false 반환
        return false;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L098_160585 solution = new L098_160585();

        String[] board = { "O.X", ".O.", "..X" };

        int result = solution.solution(board);

        System.out.println(result);
    }
}
