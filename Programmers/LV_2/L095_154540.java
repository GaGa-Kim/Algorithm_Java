package Programmers.LV_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 154540) 무인도_여행
 */
public class L095_154540 {
    // dx, dy(상하좌우 좌표)
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };
    // visited(방문 유무 배열)
    static boolean[][] visited;

    // maps(지도를 나타내는 문자열 배열)
    public int[] solution(String[] maps) {
        // answer(각 섬에서 최대 며칠씩 머무를 수 있는지 리스트)
        List<Integer> answer = new ArrayList<>();
        visited = new boolean[maps.length][maps[0].length()];
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[0].length(); j++) {
                // visited[i][j]에 방문하지 않으면서 바다가 아니라면
                if (!visited[i][j] && maps[i].charAt(j) != 'X')
                    // answer에 bfs(maps, i, j) 저장
                    answer.add(bfs(maps, i, j));
            }
        }
        // answer의 크기가 0이라면
        if (answer.size() == 0)
            // answer에 -1 저장
            answer.add(-1);
        // answer 오름차순 정렬
        Collections.sort(answer);
        // answer을 배열로 변환하여 반환
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    // bfs
    private int bfs(String[] maps, int x, int y) {
        // sum(해당 무인도에서 머물 수 있는 일 수)
        int sum = 0;
        // 큐 자료구조에 현재 노드 방문 기록
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] { x, y });
        visited[x][y] = true;
        // 큐가 빌 때까지
        while (!queue.isEmpty()) {
            // 큐에서 노드 데이터 가져오기
            int[] now = queue.poll();
            // sum에 식량을 저장
            sum += Character.getNumericValue(maps[now[0]].charAt(now[1]));
            for (int i = 0; i < 4; i++) {
                // nx, ny(x와 y의 상하좌우)
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                // nx와 ny가 좌표를 벗어나지 않았으면서
                if (nx >= 0 && nx < maps.length && ny >= 0 && ny < maps[0].length()) {
                    // nx와 ny에 방문하지 않았으면서 maps[nx][ny]가 바다가 아니라면
                    if (!visited[nx][ny] && maps[nx].charAt(ny) != 'X') {
                        // 방문 배열 갱신
                        visited[nx][ny] = true;
                        // 큐에 데이터 삽입
                        queue.add(new int[] { nx, ny });
                    }
                }
            }
        }
        // sum 반환
        return sum;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L095_154540 solution = new L095_154540();

        String[] maps = { "X591X", "X1X5X", "X231X", "1XXX1" };

        int[] result = solution.solution(maps);

        System.out.println(Arrays.toString(result));
    }
}
