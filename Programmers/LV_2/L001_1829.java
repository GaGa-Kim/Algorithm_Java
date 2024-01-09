package Programmers.LV_2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 1829) 카카오프렌즈_컬러링북
 */
public class L001_1829 {
    // dx, dy(상하좌우)
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };
    // visited(방문 유무 배열)
    static boolean[][] visited;

    // m, n(그림의 크기)
    // picture(그림)
    public int[] solution(int m, int n, int[][] picture) {
        // numberOfArea(영역의 개수)
        int numberOfArea = 0;
        // maxSizeOfOneArea(가장 큰 영역의 넓이)
        int maxSizeOfOneArea = 0;
        visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // picture[i][j]가 0이 아니면서 방문하지 않았다면
                if (picture[i][j] != 0 && !visited[i][j]) {
                    // 가장 큰 영역의 넓이를 Math.max(maxSizeOfOneArea, bfs(picture, i, j)) 중 하나로 갱신
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, bfs(picture, i, j));
                    // numberOfArea 증가
                    numberOfArea++;
                }
            }
        }
        // answer(영역의 개수와 가장 큰 영역의 넓이)
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        // answer 반환
        return answer;
    }

    // bfs 함수
    private int bfs(int[][] picture, int x, int y) {
        // area(영역의 넓이)
        int area = 1;
        // 큐 자료구조에 현재 노드 방문 기록하기
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] { x, y });
        visited[x][y] = true;
        // 큐가 빌 때까지
        while (!queue.isEmpty()) {
            // 큐에서 노드 데이터 가져오기
            int[] now = queue.poll();
            // 상하좌우 탐색하기
            for (int i = 0; i < 4; i++) {
                // nx, ny(i와 j의 상하좌우)
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                // nx와 ny가 좌표를 벗어나지 않았으면서
                if (nx >= 0 && nx < picture.length && ny >= 0 && ny < picture[0].length) {
                    // nx와 ny가 방문하지 않았으면서
                    if (!visited[nx][ny]) {
                        // picture의 좌표 값이 같다면
                        if (picture[nx][ny] == picture[x][y]) {
                            // 큐에 데이터 삽입
                            queue.add(new int[] { nx, ny });
                            // 방문 배열 갱신
                            visited[nx][ny] = true;
                            // area 증가
                            area++;
                        }
                    }
                }
            }
        }
        // area 반환
        return area;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L001_1829 solution = new L001_1829();

        int m = 6;
        int n = 4;
        int[][] picture = {
                { 1, 1, 1, 0 },
                { 1, 2, 2, 0 },
                { 1, 0, 0, 1 },
                { 0, 0, 0, 1 },
                { 0, 0, 0, 3 },
                { 0, 0, 0, 3 } };

        int[] result = solution.solution(m, n, picture);

        System.out.println(Arrays.toString(result));
    }
}