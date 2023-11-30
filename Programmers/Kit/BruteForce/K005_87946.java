package Programmers.Kit.BruteForce;

/**
 * 87946) 피로도
 */
public class K005_87946 {
    static boolean[] visited;
    static int answer;

    // k(현재 피로도)
    // dungeons(최소 필요 피로도와 소모 피로도가 담긴 2차원 배열)
    public int solution(int k, int[][] dungeons) {
        answer = -1;
        // visited(방문 유무 배열)
        visited = new boolean[dungeons.length];
        // dfs(0, k, dungeons)
        dfs(0, k, dungeons);
        // answer 반환
        return answer;
    }

    // dfs
    private void dfs(int depth, int k, int[][] dungeons) {
        for (int i = 0; i < dungeons.length; i++) {
            // 방문하지 않은 던전이면서 최소 필요 피로도가 현재 피로도보다 작거나 같을 때
            if (!visited[i] && dungeons[i][0] <= k) {
                // visited[i] 방문 처리
                visited[i] = true;
                // dfs(depth + 1, k - dungeons[i][1], dungeons)
                dfs(depth + 1, k - dungeons[i][1], dungeons);
                // visited[i] 방문 초기화
                visited[i] = false;
            }
        }
        // 최대 던전 수로 갱신
        answer = Math.max(answer, depth);
    }

    // 테스트 케이스
    public static void main(String[] args) {
        K005_87946 solution = new K005_87946();

        int k = 80;
        int[][] dungeons = { { 80, 20 }, { 50, 40 }, { 30, 10 } };
        int result = solution.solution(k, dungeons);

        System.out.println(result);
    }
}
