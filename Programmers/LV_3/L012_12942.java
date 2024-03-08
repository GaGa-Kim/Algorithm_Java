package Programmers.LV_3;

/**
 * 12942) 최적의_행렬_곱셈
 */
public class L012_12942 {
    // matrix_sizes(행렬)
    public int solution(int[][] matrix_sizes) {
        // D(i번째 행렬부터 j번째 행렬까지의 최소 연산 횟수를 담은 DP 테이블)
        int[][] D = new int[matrix_sizes.length][matrix_sizes.length];
        // 최솟값을 찾기 위해 D[i][j]를 최댓값으로 갱신
        for (int i = 0; i < matrix_sizes.length; i++) {
            for (int j = 0; j < matrix_sizes.length; j++) {
                D[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < matrix_sizes.length; i++) {
            for (int j = 0; j < matrix_sizes.length - i; j++) {
                // a(행렬 곱셉 범위의 시작 인덱스)
                int a = j;
                // b(행렬 곱셈 범위의 끝 인덱스)
                int b = j + i;
                // a와 b가 같다면
                if (a == b) {
                    // 대각선 상의 값이며 해당 범위의 행렬을 곱하는 것이 필요하지 않으므로 0으로 갱신
                    D[a][b] = 0;
                }
                // a와 b가 다르다면
                else {
                    for (int k = a; k < b; k++) {
                        // 최소 곱셈 연산 횟수 갱신로 갱신
                        // a부터 k까지의 행렬을 곱하는데 필요한 최소 연산 횟수 +
                        // k + 1부터 b까지의 행렬을 곱하는데 필요한 최소 연산 횟수
                        // 두 행렬 곱셈 연산에 필요한 곱셈 연산의 횟수
                        D[a][b] = Math.min(D[a][b],
                                D[a][k] + D[k + 1][b] + matrix_sizes[a][0] * matrix_sizes[k][1] * matrix_sizes[b][1]);
                    }
                }
            }
        }
        // D[0][matrix_sizes의 길이 - 1]를 반환
        return D[0][matrix_sizes.length - 1];
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L012_12942 solution = new L012_12942();

        int[][] matrix_sizes = { { 5, 3 }, { 3, 10 }, { 10, 6 } };

        int result = solution.solution(matrix_sizes);

        System.out.println(result);
    }
}
