package Programmers.LV_2;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 250136) 석유_시추
 */
public class L107_250136 {
    // dx, dy(상하좌우 좌표)
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };

    // land(석유가 묻힌 땅과 석유 덩어리를 나타내는 2차원 정수 배열)
    public int solution(int[][] land) {
        // oils(각 열에 대한 석유량을 담은 배열)
        int[] oils = new int[land[0].length];
        for (int x = 0; x < land.length; x++) {
            for (int y = 0; y < land[0].length; y++) {
                // 빈 땅이라면 넘어감
                if (land[x][y] == 0)
                    continue;
                // columns(석유 덩어리가 있는 열들의 집합)
                Set<Integer> columns = new HashSet<>();
                // 큐 자료구조에 현재 노드 좌표 기록
                Queue<int[]> queue = new LinkedList<>();
                queue.add(new int[] { x, y });
                // 석유가 있는 땅을 빈 땅으로 갱신
                land[x][y] = 0;
                // area(석유 덩어리의 크기)
                int area = 1;
                // 석유 덩어리가 존재하는 열을 columns에 저장
                columns.add(y);
                // 큐가 빌 때까지
                while (!queue.isEmpty()) {
                    // 큐에서 노드 데이터 가져오기
                    int[] now = queue.poll();
                    for (int i = 0; i < 4; i++) {
                        // nx, ny(x와 y의 상하좌우)
                        int nx = now[0] + dx[i];
                        int ny = now[1] + dy[i];
                        // nx와 ny가 좌표를 벗어나지 않았으면서
                        if (nx >= 0 && nx < land.length && ny >= 0 && ny < land[0].length) {
                            // nx와 ny가 석유가 있는 땅이라면
                            if (land[nx][ny] == 1) {
                                // 석유가 있는 땅을 빈 땅으로 갱신
                                land[nx][ny] = 0;
                                // 큐에 데이터 삽입
                                queue.add(new int[] { nx, ny });
                                // area 증가
                                area++;
                                // 석유 덩어리가 존재하는 열을 columns에 저장
                                columns.add(ny);
                            }
                        }
                    }
                }
                // 각 열에 석유 덩어리 크기를 추가
                for (int column : columns)
                    oils[column] += area;
            }
        }
        // answer(시추관 하나를 설치해 뽑을 수 있는 가장 많은 석유량)
        int answer = Integer.MIN_VALUE;
        // 가장 많은 석유량을 가진 열로 갱신
        for (int i = 0; i < oils.length; i++) {
            answer = Math.max(answer, oils[i]);
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L107_250136 solution = new L107_250136();

        int[][] land = {
                { 0, 0, 0, 1, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 1, 1, 0, 0 },
                { 1, 1, 0, 0, 0, 1, 1, 0 },
                { 1, 1, 1, 0, 0, 0, 0, 0 },
                { 1, 1, 1, 0, 0, 0, 1, 1 } };

        int result = solution.solution(land);

        System.out.println(result);
    }
}
