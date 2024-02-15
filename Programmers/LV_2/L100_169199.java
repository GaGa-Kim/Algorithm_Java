package Programmers.LV_2;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 169199) 리코쳇_로봇
 */
public class L100_169199 {
    // dx, dy(상하좌우 좌표)
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };

    // board(게임판의 상태)
    public int solution(String[] board) {
        // answer(최소 이동 횟수)
        int answer = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length(); j++) {
                // board[i].chartAt(j)가 R이라면
                if (board[i].charAt(j) == 'R')
                    // R부터 시작하여 bfs 탐색
                    answer = bfs(board, i, j);
            }
        }
        // answer 반환
        return answer;
    }

    // bfs
    private int bfs(String[] board, int x, int y) {
        // 큐 자료구조에 현재 노드 방문 기록
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] { x, y, 0 });
        // visited(방문 유무 배열)
        boolean[][] visited = new boolean[board.length][board[0].length()];
        visited[x][y] = true;
        // 큐가 빌 때까지
        while (!queue.isEmpty()) {
            // 큐에서 노드 데이터 가져오기
            int[] now = queue.poll();
            // board의 위치가 목표지점인 G라면
            if (board[now[0]].charAt(now[1]) == 'G')
                // 이동 횟수 반환
                return now[2];
            // 상하좌우 탐색
            for (int i = 0; i < 4; i++) {
                int nx = now[0];
                int ny = now[1];
                // nx와 ny가 좌표를 벗어나지 않았으면서 장애물인 D를 만나지 않는 동안 이동
                while (nx >= 0 && nx < board.length && ny >= 0 && ny < board[0].length()
                        && board[nx].charAt(ny) != 'D') {
                    // nx, ny(x와 y의 상하좌우)
                    nx += dx[i];
                    ny += dy[i];
                }
                // nx, ny를 장애물을 만나기 직전의 상태로 갱신해야 하므로 dx, dy만큼 감소
                nx -= dx[i];
                ny -= dy[i];
                // nx와 ny에 방문하지 않았다면
                if (!visited[nx][ny]) {
                    // 방문 배열 갱신
                    visited[nx][ny] = true;
                    // 큐에 데이터 삽입
                    queue.add(new int[] { nx, ny, now[2] + 1 });
                }
            }
        }
        // 목표지점인 G에 도달하지 못하므로 -1 반환
        return -1;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L100_169199 solution = new L100_169199();

        String[] board = {
                "...D..R",
                ".D.G...",
                "....D.D",
                "D....D.",
                "..D...." };

        int result = solution.solution(board);

        System.out.println(result);
    }
}
