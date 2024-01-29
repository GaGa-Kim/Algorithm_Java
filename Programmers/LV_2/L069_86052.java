package Programmers.LV_2;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 86052) 빛의_경로_사이클
 */
public class L069_86052 {
    boolean[][][] visited;

    // grid(격자의 정보)
    public int[] solution(String[] grid) {
        // answer(격자를 통해 만들어지는 빛의 경로 사이클의 모든 길이들을 저장하는 리스트)
        ArrayList<Integer> answer = new ArrayList<>();
        // visited(방문 유무 배열)
        visited = new boolean[grid.length][grid[0].length()][4];
        // 모든 칸에서 4가지 방향으로 빛 쏘기
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length(); j++) {
                for (int d = 0; d < 4; d++) {
                    // visited[i][j][k]를 방문하지 않았다면
                    if (!visited[i][j][d])
                        // answer에 move(grid, i, j, d) 저장
                        answer.add(move(grid, i, j, d));
                }
            }
        }
        // answer을 정렬하고 배열로 변환하여 반환
        return answer.stream().sorted().mapToInt(Integer::intValue).toArray();
    }

    // 빛 움직이기
    private Integer move(String[] grid, int i, int j, int d) {
        // dx, dy(아래쪽, 왼쪽, 위쪽, 오른쪽 상화좌우 좌표)
        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, -1, 0, 1 };
        // count(빛의 경로 사이클의 길이)
        int count = 0;
        while (true) {
            // visited[i][j][d]를 이미 방문했다면
            if (visited[i][j][d])
                break;
            // count 증가
            count++;
            // visited[i][j][d]를 방문 처리
            visited[i][j][d] = true;
            // 좌회전이라면
            if (grid[i].charAt(j) == 'L')
                d = (d + 3) % 4;
            // 우회전이라면
            else if (grid[i].charAt(j) == 'R')
                d = (d + 1) % 4;
            // 만약 배열 밖으로 나간 경우라면 반대편으로 이동시켜주기 위해 갱신
            i = (i + dx[d] + grid.length) % grid.length;
            j = (j + dy[d] + grid[0].length()) % grid[0].length();
        }
        // count 반환
        return count;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L069_86052 solution = new L069_86052();

        String[] grid = { "SL", "LR" };

        int[] result = solution.solution(grid);

        System.out.println(Arrays.toString(result));
    }
}
