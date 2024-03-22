package Programmers.LV_3;

/**
 * 49191) 순위
 */
public class L032_49191 {
    // n(선수의 수)
    // results(경기 결과를 담은 2차원 배열)
    public int solution(int n, int[][] results) {
        // rank(경기 결과를 저장하는 인접 행렬)
        int rank[][] = new int[n + 1][n + 1];
        // 경기 결과 정보를 인접 행렬에 저장
        for (int[] result : results) {
            // 이겼을 경우 1, 졌을 경우 -1 저장
            rank[result[0]][result[1]] = 1;
            rank[result[1]][result[0]] = -1;
        }
        // 플로이드-워셜 알고리즘을 수행
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= n; k++) {
                    // i가 k를 이기고 k가 j를 이겼다면, i는 j를 이김
                    if (rank[i][k] == 1 && rank[k][j] == 1) {
                        rank[i][j] = 1;
                        rank[j][i] = -1;
                    }
                    // i가 k에게 지고 k가 j에게 졌다면, i는 j에게 짐
                    if (rank[i][k] == -1 && rank[k][j] == -1) {
                        rank[i][j] = -1;
                        rank[j][i] = 1;
                    }
                }
            }
        }
        // answer(정확하게 순위를 매길 수 있는 선수의 수)
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            // count(각 행와 순위를 매길 수 있는 선수의 수)
            int count = 0;
            for (int j = 1; j <= n; j++) {
                // 0이 아닐 경우
                if (rank[i][j] != 0)
                    // count 증가
                    count++;
            }
            // 다른 모든 선수들과 순위를 매길 수 있다면
            if (count == n - 1)
                // answer 증가
                answer++;
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L032_49191 solution = new L032_49191();

        int n = 5;
        int[][] results = { { 4, 3 }, { 4, 2 }, { 3, 2 }, { 1, 2 }, { 2, 5 } };

        int result = solution.solution(n, results);

        System.out.println(result);
    }
}