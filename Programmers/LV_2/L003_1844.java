package Programmers.LV_2;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1844) 게임_맵_최단거리
 */
public class L003_1844 {
    // maps(게임 맵의 상태가 들어있는 2차원 배열)
    public int solution(int[][] maps) {
        // answer(최단거리 또는 도착 여부) = 게임 이동 함수
        int answer = move(maps);
        // answer 반환
        return answer;
    }

    // 게임 이동 함수
    private int move(int[][] maps) {
        // dx, dy(상하좌우)
        int[] dx = { 0, 0, 1, -1 };
        int[] dy = { 1, -1, 0, 0 };
        // visited(방문 거리 배열)
        int[][] visited = new int[maps.length][maps[0].length];
        // visited 배열을 -1로 초기화하기
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[0].length; j++) {
                visited[i][j] = -1;
            }
        }
        // 큐 자료구조에 현재 노드 방문 기록하기
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] { 0, 0 });
        visited[0][0] = 1;
        // 큐가 빌 때까지
        while (!queue.isEmpty()) {
            // 큐에서 노드 데이터를 가져오기
            int[] now = queue.poll();
            // 상하좌우 방향으로
            for (int i = 0; i < 4; i++) {
                // newX, newY(x와 y의 상하좌우 방향)
                int newX = now[0] + dx[i];
                int newY = now[1] + dy[i];
                // 미로를 벗어나지 않으면서
                if (newX >= 0 && newX < maps.length && newY >= 0 && newY < maps[0].length) {
                    // 이동할 수 있으며, 방문하지 않았을 경우
                    if (maps[newX][newY] == 1 && visited[newX][newY] == -1) {
                        // 큐에 데이터 삽입(add 연산)
                        queue.add(new int[] { newX, newY });
                        // 이전 노드의 방문 거리 + 1로 방문 거리 갱신하기
                        visited[newX][newY] = visited[now[0]][now[1]] + 1;
                    }
                }
            }
        }
        // visited[n - 1][m - 1] 반환
        // 최단 거리가 있을 경우 최단 거리 반환, 없을 경우 -1이 반환됨
        return visited[maps.length - 1][maps[0].length - 1];
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L003_1844 solution = new L003_1844();

        int[][] maps = {
                { 1, 0, 1, 1, 1 },
                { 1, 0, 1, 0, 1 },
                { 1, 0, 1, 1, 1 },
                { 1, 1, 1, 0, 1 },
                { 0, 0, 0, 0, 1 } };

        int result = solution.solution(maps);

        System.out.println(result);
    }
}