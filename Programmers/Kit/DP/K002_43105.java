package Programmers.Kit.DP;

/**
 * 43105) 정수_삼각형
 */
public class K002_43105 {
    // triangle(삼각형의 정보가 담긴 배열)
    public int solution(int[][] triangle) {
        // D(D[i][j]은 i번째 줄의 j번째까지 거쳐간 숫자의 합이 가장 큰 경우)
        int[][] D = new int[triangle.length][triangle.length];
        // D[0][0] = triangle[0][0] 초기화
        D[0][0] = triangle[0][0];
        // 점화식으로 D 배열 채우기
        for (int i = 1; i < triangle.length; i++) {
            // 맨 왼쪽 점화식 (맨 왼쪽은 대각선 한 방향만 가능하므로 이전 합 + 자신의 수)
            D[i][0] = D[i - 1][0] + triangle[i][0];
            // 중간 점화식 (중간은 대각선 두 방향이 가능하므로 이전 합 + 자신의 수)
            for (int j = 1; j <= i; j++) {
                D[i][j] = Math.max(D[i - 1][j - 1], D[i - 1][j]) + triangle[i][j];
            }
            // 맨 오른쪽 점화식 (맨 오른쪽은 대각선 한 방향만 가능하므로 이접 합 + 자신의 수)
            D[i][i] = D[i - 1][i - 1] + triangle[i][i];
        }
        // answer(거쳐간 숫자의 최댓값)
        int answer = 0;
        // D 배열의 마지막 줄에서 최댓값을 구하기
        for (int i = 0; i < triangle.length; i++) {
            answer = Math.max(answer, D[triangle.length - 1][i]);
        }
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        K002_43105 solution = new K002_43105();

        int[][] triangle = { { 7 }, { 3, 8 }, { 8, 1, 0 }, { 2, 7, 4, 4 }, { 4, 5, 2, 6, 5 } };

        int result = solution.solution(triangle);

        System.out.println(result);
    }
}
