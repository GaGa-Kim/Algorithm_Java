package Programmers.LV_3;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 67259) 경주로_건설
 */
public class L040_67259 {
    // dx, dy(상하좌우 좌표)
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };
    static int N;
    static boolean[][][] visited;

    // board(2차원 정사각 배열)
    public int solution(int[][] board) {
        // N(board의 길이)
        N = board.length;
        // visited(방문 유무 배열)
        visited = new boolean[N][N][4];
        // bfs 반환
        return bfs(board);
    }

    // bfs 탐색
    private int bfs(int[][] board) {
        // min(경주로를 건설하는데 필요한 최소 비용)
        int min = Integer.MAX_VALUE;
        // 큐 자료구조에 현재 노드 방문 기록
        Queue<Info> queue = new LinkedList<>();
        queue.add(new Info(0, 0, -1, 0)); // 출발점이므로 direct를 -1로 지정
        while (!queue.isEmpty()) {
            // 큐에서 노드 데이터 가져오기
            Info now = queue.poll();
            // 도착점에 도착했다면
            if (now.x == N - 1 && now.y == N - 1) {
                // min을 더 작은 최소 비용으로 갱신
                min = Math.min(min, now.cost);
            }
            // 상하좌우 탐색
            for (int dir = 0; dir < 4; dir++) {
                // nx, ny(x와 y의 상하좌우)
                int nx = now.x + dx[dir];
                int ny = now.y + dy[dir];
                // nextCost(상하좌우 탐색에 따른 다음 위치 건설 비용)
                int nextCost = now.cost;
                // 이전 위치가 시작점이거나 직선 도로를 건설할 경우
                if (now.direct == -1 || dir == now.direct) {
                    // 건설 비용은 100원 소요
                    nextCost += 100;
                }
                // 코너를 건설할 경우
                else {
                    // 건설 비용은 100원에 500원이 추가 소요
                    nextCost += 600;
                }
                // nx와 ny가 좌표를 벗어나지 않았으면서 벽이 아닐 경우
                if (nx >= 0 && nx < N && ny >= 0 && ny < N && board[nx][ny] != 1) {
                    // nx와 ny에 방문하지 않았거나 nx, ny 위치를 지나는 비용을 최소 비용으로 업데이트가 가능할 경우
                    if (!visited[nx][ny][dir] || board[nx][ny] >= nextCost) {
                        // 방문 배열 갱신
                        visited[nx][ny][dir] = true;
                        // nx, ny 위치를 지나는 비용을 최소 비용으로 갱신
                        board[nx][ny] = nextCost;
                        // 큐에 데이터 삽입
                        queue.add(new Info(nx, ny, dir, nextCost));
                    }
                }
            }
        }
        // min 반환
        return min;
    }

    class Info {
        int x, y; // x, y(위치)
        int direct; // direct(건설 방향)
        int cost; // cost(x, y 위치까지의 건설 비용)

        public Info(int x, int y, int direct, int cost) {
            this.x = x;
            this.y = y;
            this.direct = direct;
            this.cost = cost;
        }
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L040_67259 solution = new L040_67259();

        int[][] board = {
                { 0, 0, 0, 0, 0, 0, 0, 1 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 1, 0, 0 },
                { 0, 0, 0, 0, 1, 0, 0, 0 },
                { 0, 0, 0, 1, 0, 0, 0, 1 },
                { 0, 0, 1, 0, 0, 0, 1, 0 },
                { 0, 1, 0, 0, 0, 1, 0, 0 },
                { 1, 0, 0, 0, 0, 0, 0, 0 } };

        int result = solution.solution(board);

        System.out.println(result);
    }
}
