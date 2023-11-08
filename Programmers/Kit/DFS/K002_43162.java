package Programmers.Kit.DFS;

/**
 * 43162) 네트워크
 */
public class K002_43162 {
    // visited(방문 기록 저장 배열)
    static boolean[] visited;

    // n(컴퓨터의 개수)
    // computers(연결에 대한 정보가 담긴 2차원 배열)
    public int solution(int n, int[][] computers) {
        visited = new boolean[n + 1];
        // answer(네트워크 개수)
        int answer = 0;
        for (int i = 0; i < n; i++) {
            // 방문하지 않았다면
            if (!visited[i]) {
                // 각 컴퓨터에서 DFS 실행
                DFS(computers, i);
                // answer 증가
                answer++;
            }
        }
        // answer 반환
        return answer;
    }

    // DFS
    private void DFS(int[][] computers, int node) {
        // visited 배열에 현재 노드 방문 기록하기
        visited[node] = true;
        // 현재 노드의 연결 노드 중 방문하지 않은 노드로
        for (int next = 0; next < computers.length; next++) {
            if (visited[next] == false) {
                if (computers[node][next] == 1)
                    // DFS 실행하기 (재귀 형태)
                    DFS(computers, next);
            }
        }
    }

    // 테스트 케이스
    public static void main(String[] args) {
        K002_43162 solution = new K002_43162();

        int n = 3;
        int[][] computers = { { 1, 1, 0 }, { 1, 1, 0 }, { 0, 0, 1 } };

        int result = solution.solution(n, computers);

        System.out.println(result);
    }
}
