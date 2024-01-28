package Programmers.LV_2;

import java.util.Arrays;

/**
 * 81302) 거리두기_확인하기
 */
public class L067_81302 {
    boolean isAvailable;

    // places(자리에 앉아있는 응시자들의 정보와 대기실 구조를 대기실별로 담은 2차원 문자열 배열)
    public int[] solution(String[][] places) {
        // answer(각 대기실별로 거리두기를 지키고 있는지 여부)
        int[] answer = new int[5];
        for (int i = 0; i < 5; i++) {
            // map(places[i]를 저장할 대기실 지도)
            char[][] map = new char[5][5];
            // map에 대기실 정보 저장
            for (int j = 0; j < 5; j++) {
                map[j] = places[i][j].toCharArray();
            }
            // 거리두기를 유지하고 있다면
            if (isKeepingDistance(map))
                answer[i] = 1;
            // 거리두기를 유지하고 있지 않다면
            else
                answer[i] = 0;
        }
        // answer 반환
        return answer;
    }

    // 거리두기를 유지하고 있는지
    private boolean isKeepingDistance(char[][] map) {
        // isAvailable(거리두기 유지 여부)
        isAvailable = true;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                // map[i][j]가 P라면
                if (map[i][j] == 'P') {
                    // visited(방문 유무 배열)
                    boolean[][] visited = new boolean[5][5];
                    // 주변도 거리두기를 유지하는지 dfs
                    dfs(map, visited, i, j, 0);
                }
                // isAvailable이 false라면
                if (!isAvailable)
                    // 이미 거리두기를 지키지 못하므로 false 반환
                    return false;
            }
        }
        // 모두 거리두기를 지키므로 true 반환
        return true;
    }

    // dfs
    private void dfs(char[][] map, boolean[][] visited, int x, int y, int depth) {
        // dx, dy(상하좌우 배열)
        int[] dx = { 1, 0, -1, 0 };
        int[] dy = { 0, 1, 0, -1 };
        // depth가 2보다 크다면
        if (depth >= 2)
            // 맨해튼 거리를 2까지 확인했으므로 return;
            return;
        // visited[x][y]를 방문 갱신
        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            // nx, ny(x, y를 상하좌우 이동한 좌표)
            int nx = x + dx[i];
            int ny = y + dy[i];
            // nx, ny가 지도를 벗어나지 않으면서 방문하지 않았다면
            if (nx >= 0 && nx < 5 && ny >= 0 && ny < 5 && !visited[nx][ny]) {
                // map[nx][ny]가 O이면
                if (map[nx][ny] == 'O')
                    // 거리두기를 지키므로 dfs 계속 탐색
                    dfs(map, visited, nx, ny, depth + 1);
                // map[nx][ny]가 P라면
                else if (map[nx][ny] == 'P') {
                    // 거리두기를 지키지 않으므로 isAvailable을 false로 갱신하고 return;
                    isAvailable = false;
                    return;
                }
                // map[nx][ny]가 X라면
                else if (map[nx][ny] == 'X')
                    // 파티션이 있어 그 뒤에 응시자가 있어도 문제 없으므로 continue;
                    continue;
            }
        }
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L067_81302 solution = new L067_81302();

        String[][] places = {
                { "POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP" },
                { "POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP" },
                { "PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX" },
                { "OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO" },
                { "PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP" } };

        int[] result = solution.solution(places);

        System.out.println(Arrays.toString(result));
    }
}
