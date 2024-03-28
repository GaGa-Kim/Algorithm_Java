package Programmers.LV_3;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 60063) 블록_이동하기
 */
public class L036_60063 {
    // dx, dy(상하좌우 좌표)
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };

    // board(0과 1로 이루어진 지도)
    public int solution(int[][] board) {
        // answer(로봇이 도착점까지 이동하는데 필요한 최소 시간)
        int answer = bfs(board);
        // answer 반환
        return answer;
    }

    // 로봇 이동 bfs
    private int bfs(int[][] board) {
        // 큐 자료구조에 현재 노드 좌표 및 이동 시간 기록
        Queue<Info> queue = new LinkedList<>();
        queue.add(new Info(0, 0, 0, 1, 0, 0));
        int N = board.length;
        // visited(방문 유무 배열)
        boolean[][][] visited = new boolean[2][N][N];
        // 현재 노드 좌표 방문 표시
        visited[0][0][0] = true;
        visited[0][0][1] = true;
        while (!queue.isEmpty()) {
            // 큐에서 노드 데이터 가져오기
            Info now = queue.poll();
            // 도착점에 도착했다면
            if ((now.x1 == N - 1 && now.y1 == N - 1) || (now.x2 == N - 1 && now.y2 == N - 1)) {
                // 이동 시간 반환
                return now.time;
            }
            // 상하좌우 네 방향으로
            for (int i = 0; i < 4; i++) {
                // nx1, ny1(x1와 y1의 상하좌우)
                int nx1 = now.x1 + dx[i];
                int ny1 = now.y1 + dy[i];
                // nx2, ny2(x2와 y2의 상하좌우)
                int nx2 = now.x2 + dx[i];
                int ny2 = now.y2 + dy[i];
                // nx1, ny1, nx2, ny2가 좌표를 벗어나지 않았으면서
                if (nx1 >= 0 && nx1 < N && ny1 >= 0 && ny1 < N && nx2 >= 0 && nx2 < N && ny2 >= 0 && ny2 < N) {
                    // nx1, ny1, nx2, ny2가 벽이 아니면서
                    if (board[nx1][ny1] == 0 && board[nx2][ny2] == 0) {
                        // x1, ny1, nx2, ny2에 방문하지 않았다면
                        if (!visited[now.type][nx1][ny1] || !visited[now.type][nx2][ny2]) {
                            // 방문 배열 갱신
                            visited[now.type][nx1][ny1] = true;
                            visited[now.type][nx2][ny2] = true;
                            // 큐에 데이터 삽입
                            queue.add(new Info(nx1, ny1, nx2, ny2, now.type, now.time + 1));
                        }
                        // change_type(로봇이 회전할 방향)
                        int change_type = 0;
                        // 현재 방향이 가로라면 회전할 방향은 세로, 현재 방향이 세로라면 회전할 방향은 가로
                        if (now.type == 0) {
                            change_type = 1;
                        }
                        // 로봇이 가로로 있으며 상, 하 방향으로 이동한다면, 또는 로봇이 세로로 있으며 좌, 우 방향으로 이동한다면
                        if ((now.type == 0 && i < 2) || (now.type == 1 && i > 1)) {
                            // 왼쪽으로 90도 회전한 위치를 방문하지 않았다면
                            if (!visited[change_type][nx1][ny1] || !visited[change_type][now.x1][now.y1]) {
                                // 방문 배열 갱신
                                visited[change_type][nx1][ny1] = true;
                                visited[change_type][now.x1][now.y1] = true;
                                // 큐에 데이터 삽입
                                queue.add(new Info(nx1, ny1, now.x1, now.y1, change_type, now.time + 1));
                            }
                            // 오른쪽으로 90도 회전한 위치를 방문하지 않았다면
                            if (!visited[change_type][nx2][ny2] || !visited[change_type][now.x2][now.y2]) {
                                // 방문 배열 갱신
                                visited[change_type][nx2][ny2] = true;
                                visited[change_type][now.x2][now.y2] = true;
                                // 큐에 데이터 삽입
                                queue.add(new Info(nx2, ny2, now.x2, now.y2, change_type, now.time + 1));
                            }
                        }
                    }
                }
            }
        }
        // 도착하지 못하므로 0 반환
        return 0;
    }

    class Info {
        // x1, y1(로봇이 차지하는 첫 번째 칸의 좌표)
        int x1, y1;
        // x2, y2(로봇이 차지하는 두 번째 칸의 좌표)
        int x2, y2;
        // type(로봇의 방향, 0은 가로, 1은 세로)
        int type;
        // time(이동 시간)
        int time;

        public Info(int x1, int y1, int x2, int y2, int type, int time) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.type = type;
            this.time = time;
        }
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L036_60063 solution = new L036_60063();

        int[][] board = {
                { 0, 0, 0, 1, 1 },
                { 0, 0, 0, 1, 0 },
                { 0, 1, 0, 1, 1 },
                { 1, 1, 0, 0, 1 },
                { 0, 0, 0, 0, 0 } };

        int result = solution.solution(board);

        System.out.println(result);
    }
}
