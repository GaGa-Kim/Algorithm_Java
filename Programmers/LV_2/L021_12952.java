package Programmers.LV_2;

/**
 * 12952) N-Queen
 */
public class L021_12952 {
    // answer(n개의 퀸이 조건에 만족 하도록 배치할 수 있는 방법의 수)
    static int answer;
    // visited(퀸 배치 유무)
    static boolean visited[][];

    // n(가로, 세로 길이이자 퀸의 개수)
    public int solution(int n) {
        answer = 0;
        visited = new boolean[n][n];
        // dfs(n, 0)
        dfs(n, 0);
        // answer 반환
        return answer;
    }

    private void dfs(int n, int row) {
        // row가 n과 같다면
        if (row == n) {
            // answer 증가
            answer++;
            return;
        }
        // row가 n과 같지 않다면
        else {
            for (int column = 0; column < n; column++) {
                // 퀸을 배치할 수 있다면
                if (possible(row, column)) {
                    // visited[row][column]에 퀸을 배치하므로 true로 변경
                    visited[row][column] = true;
                    dfs(n, row + 1);
                    // 다음 배치에는 이 위치에 배치되지 않으므로 visited[row][column]을 false로 초기화
                    visited[row][column] = false;
                }
            }
        }
    }

    // 퀸을 배치할 수 있는지
    private boolean possible(int row, int column) {
        // 배치하려고 했던 자리의 위쪽에 퀸이 있는지 확인
        for (int i = 0; i < row; i++) {
            if (visited[i][column]) {
                // 배치하려고 했던 자리의 위쪽에 퀸이 있으므로 배치 불가능 false 반환
                return false;
            }
        }
        // 배치하려고 했던 자리의 왼쪽 위 대각선쪽에 퀸이 있는지 확인
        for (int i = row, j = column; i >= 0 && j >= 0; i--, j--) {
            if (visited[i][j]) {
                // 배치하려고 했던 자리의 왼쪽 위 대각선쪽에 퀸이 있으므로 배치 불가능 false 반환
                return false;
            }
        }
        // 배치하려고 했던 자리의 오른쪽 위 대각선쪽에 퀸이 있는지 확인
        for (int i = row, j = column; i >= 0 && j < visited.length; i--, j++) {
            if (visited[i][j]) {
                // 배치하려고 했던 자리의 오른쪽 위 대각선쪽에 퀸이 있으므로 배치 불가능 false 반환
                return false;
            }
        }
        // 위쪽, 양쪽 대각선에 모두 퀸이 없다면 퀸을 배치할 수 있으므로 true 반환
        return true;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L021_12952 solution = new L021_12952();

        int n = 4;

        int result = solution.solution(n);

        System.out.println(result);
    }
}
