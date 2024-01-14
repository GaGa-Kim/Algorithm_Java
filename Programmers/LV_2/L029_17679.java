package Programmers.LV_2;

/**
 * 17679) 프렌즈4블록
 */
public class L029_17679 {
    // cBoard(board를 담을 문자 배열)
    static char[][] cBoard;

    // m, n(판의 높이와 폭)
    // board(판의 배치 정보)
    public int solution(int m, int n, String[] board) {
        // answer(지워지는 블록의 총 개수)
        int answer = 0;
        // cBoard에 한 줄씩 board를 저장
        cBoard = new char[m][n];
        for (int i = 0; i < board.length; i++) {
            cBoard[i] = board[i].toCharArray();
        }
        while (true) {
            // count(현재 지워질 블록 갯수) = 지워질 블록 갯수 찾기 함수
            int count = findBlock();
            // count가 0이라면
            if (count == 0)
                // 더 이상 지워질 블록이 없으므로 종료
                break;
            // 블록 지우고 갱신하기 함수
            updateBlock();
            // answer에 count 추가
            answer += count;
        }
        // answer 반환
        return answer;
    }

    // 지워질 블록 갯수 찾기 함수
    private int findBlock() {
        // count(현재 지워질 블록 갯수)
        int count = 0;
        // visited(방문 유무 배열)
        boolean[][] visited = new boolean[cBoard.length][cBoard[0].length];
        // 판을 모두 탐색하면서
        for (int i = 0; i < cBoard.length - 1; i++) {
            for (int j = 0; j < cBoard[0].length - 1; j++) {
                // cBoard[i][j]가 X라면
                if (cBoard[i][j] == 'X')
                    // 이미 지워진 블록이므로 넘어감
                    continue;
                // 주변 블록들이 같은 블록인지 찾기 함수
                checkSurrounding(visited, i, j);
            }
        }
        // 판을 모두 탐색한 후 지워질 수 있는 블록의 개수를 찾고 X로 변경
        for (int i = 0; i < cBoard.length; i++) {
            for (int j = 0; j < cBoard[0].length; j++) {
                // visited[i][j]가 true라면
                if (visited[i][j]) {
                    // 지워질 수 있으므로 count 증가
                    count++;
                    // cBoard[i][j]를 X로 변경
                    cBoard[i][j] = 'X';
                }
            }
        }
        // count 반환
        return count;
    }

    // 주변 블록들이 같은 블록인지 찾기 함수
    private void checkSurrounding(boolean[][] visited, int x, int y) {
        // now(현재 블록의 값)
        char now = cBoard[x][y];
        // 오른쪽, 아래, 오른쪽 아래의 값을 탐색
        for (int i = x; i < x + 2; i++) {
            for (int j = y; j < y + 2; j++) {
                // cBoard[x][y]의 값이 now 값과 같지 않다면
                if (cBoard[i][j] != now)
                    // 다른 블록이 존재해 지울 수 없음
                    return;
            }
        }
        // 지워질 수 있는 블록이므로 블록들을 visited[i][j]를 true로 갱신
        for (int i = x; i < x + 2; i++) {
            for (int j = y; j < y + 2; j++) {
                visited[i][j] = true;
            }
        }
    }

    // 블록 지우고 갱신하기 함수
    private void updateBlock() {
        // 맨 아랫줄부터 시작하여 위로 올라가며 블록을 갱신
        for (int i = 0; i < cBoard[0].length; i++) {
            for (int j = cBoard.length - 1; j >= 0; j--) {
                // cBoard[j][i]가 X라면
                if (cBoard[j][i] == 'X') {
                    for (int k = j - 1; k >= 0; k--) {
                        // cBoard[j][i]의 윗줄에 있는 cBoard[k][i]가 X가 아니라면
                        if (cBoard[k][i] != 'X') {
                            // cBoard[j][i]의 값을 cBoard[k][i]의 값으로 바꾸어 블록을 내려주기
                            cBoard[j][i] = cBoard[k][i];
                            // cBoard[k][i]의 값은 비게 되므로 X로 변경
                            cBoard[k][i] = 'X';
                            // 내려주었으므로 종료
                            break;
                        }
                    }
                }
            }
        }
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L029_17679 solution = new L029_17679();

        int m = 4;
        int n = 5;
        String[] board = { "CCBDE", "AAADE", "AAABF", "CCBBF" };

        int result = solution.solution(m, n, board);

        System.out.println(result);
    }
}
