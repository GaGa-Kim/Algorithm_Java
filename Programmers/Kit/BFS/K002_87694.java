package Programmers.Kit.BFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 87694) 아이템_줍기
 */
public class K002_87694 {
    static int answer;
    static int[][] map;

    // rectangle(지형을 나타내는 직사각형이 담긴 2차원 배열)
    // characterX, characterY(초기 캐릭터의 위치)
    // itemX, itemY(아이템의 위치)
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        // answer(아이템을 줍기 위해 히동해야 하는 가장 짧은 거리)
        answer = Integer.MAX_VALUE;
        // map(좌표) - [51 * 2][51 * 2]
        map = new int[102][102];
        // 좌표 채우기
        for (int[] r : rectangle) {
            fill(r[0] * 2, r[1] * 2, r[2] * 2, r[3] * 2);
        }
        // 좌표 이동
        bfs(characterX * 2, characterY * 2, itemX * 2, itemY * 2);
        // answer/2 반환
        return answer / 2;
    }

    // 좌표 채우기 함수
    private void fill(int leftX, int leftY, int rightX, int rightY) {
        for (int i = leftX; i <= rightX; i++) {
            for (int j = leftY; j <= rightY; j++) {
                // map[i][j]가 이미 내부라면
                if (map[i][j] == 2)
                    continue;
                // map[i][j] = 내부인 2로 설정
                map[i][j] = 2;
                // 꼭짓점이 아니라면
                if (i == leftX || i == rightX || j == leftY || j == rightY) {
                    // map[i][j] = 테두리인 1로 설정
                    map[i][j] = 1;
                }
            }
        }
    }

    // 좌표 이동 함수
    private void bfs(int characterX, int characterY, int itemX, int itemY) {
        // dx, dy(상하좌우)
        int[] dx = { 0, 0, 1, -1 };
        int[] dy = { 1, -1, 0, 0 };
        // visited(방문 거리 배열)
        int[][] visited = new int[map.length][map.length];
        // 큐 자료구조에 현재 노드 방문 기록하기
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] { characterX, characterY });
        visited[characterX][characterY] = 1;
        // 큐가 빌 때까지
        while (!queue.isEmpty()) {
            // 큐에서 노드 데이터를 가져오기(poll)
            int[] now = queue.poll();
            // itemX, itemY 좌표라면
            if (now[0] == itemX && now[1] == itemY) {
                answer = Math.min(answer, visited[itemX][itemY]);
            }
            // 상하좌우 방향으로
            for (int i = 0; i < 4; i++) {
                // newX, newY(x와 y의 상하좌우 방향)
                int newX = now[0] + dx[i];
                int newY = now[1] + dy[i];
                // 좌표를 벗어나지 않으면서
                if (newX >= 0 && newX < map.length && newY >= 0 && newY <= map.length) {
                    // 방문하지 않았으면서 테두리일 경우
                    if (visited[newX][newY] == 0 && map[newX][newY] == 1) {
                        // 큐에 데이터 삽입(add 연산)
                        queue.add(new int[] { newX, newY });
                        // 이전 노드의 방문 거리 + 1로 방문 거리 갱신하기
                        visited[newX][newY] = visited[now[0]][now[1]] + 1;
                    }
                }
            }
        }
    }

    // 테스트 케이스
    public static void main(String[] args) {
        K002_87694 solution = new K002_87694();

        int[][] rectangle = { { 1, 1, 7, 4 }, { 3, 2, 5, 5 }, { 4, 3, 6, 9 }, { 2, 6, 8, 8 } };
        int characterX = 1;
        int characterY = 3;
        int itemX = 7;
        int itemY = 8;

        int result = solution.solution(rectangle, characterX, characterY, itemX, itemY);

        System.out.println(result);
    }
}
