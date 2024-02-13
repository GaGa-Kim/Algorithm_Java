package Programmers.LV_2;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 159993) 미로_탈출
 */
public class L097_159993 {
    // dx, dy(상하좌우 좌표)
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };

    // maps(미로를 나타낸 문자열 배열)
    public int solution(String[] maps) {
        // answer(미로를 탈출하는데 필요한 최소 시간)
        int answer = 0;
        // start(미로 시작 좌표)
        int[] start = new int[2];
        // lever(레버 좌표)
        int[] lever = new int[2];
        // start, lever 좌표 찾기
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[0].length(); j++) {
                if (maps[i].charAt(j) == 'S')
                    start = new int[] { i, j };
                if (maps[i].charAt(j) == 'L')
                    lever = new int[] { i, j };
            }
        }
        // goLever(레버 도착 최소 시간)
        int goLever = bfs(maps, start, 'L');
        // goEnd(탈출 도착 최소 시간)
        int goEnd = bfs(maps, lever, 'E');
        // goLabor나 goEnd가 하나라도 -1이라면
        if (goLever == -1 || goEnd == -1)
            // 레버나 도착 지점에 도착하지 못하므로 -1 반환
            return -1;
        // goLabor + goEnd을 더해서 최소 시간을 갱신
        answer = goLever + goEnd;
        // answer 반환
        return answer;
    }

    // bfs
    private int bfs(String[] maps, int[] start, char target) {
        // 큐 자료구조에 현재 노드 좌표 및 이동 시간 기록
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] { start[0], start[1], 0 });
        // visited(방문 유무 배열)
        boolean[][] visited = new boolean[maps.length][maps[0].length()];
        visited[start[0]][start[1]] = true;
        // 큐가 빌 때까지
        while (!queue.isEmpty()) {
            // 큐에서 노드 데이터 가져오기
            int[] now = queue.poll();
            // 현재 위치가 target(L 또는 E)라면
            if (maps[now[0]].charAt(now[1]) == target)
                // 이동 시간 반환
                return now[2];
            for (int i = 0; i < 4; i++) {
                // nx, ny(x와 y의 상하좌우)
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                // nx와 ny가 좌표를 벗어나지 않았으면서
                if (nx >= 0 && nx < maps.length && ny >= 0 && ny < maps[0].length()) {
                    // nx와 ny에 방문하지 않았으면서 maps[nx][ny]가 벽이 아니라면
                    if (!visited[nx][ny] && maps[nx].charAt(ny) != 'X') {
                        // 방문 배열 갱신
                        visited[nx][ny] = true;
                        // 큐에 데이터 삽입
                        queue.add(new int[] { nx, ny, now[2] + 1 });
                    }
                }
            }
        }
        // 도착하지 못하므로 -1 반환
        return -1;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L097_159993 solution = new L097_159993();

        String[] maps = { "SOOOL", "XXXXO", "OOOOO", "OXXXX", "OOOOE" };

        int result = solution.solution(maps);

        System.out.println(result);
    }
}
