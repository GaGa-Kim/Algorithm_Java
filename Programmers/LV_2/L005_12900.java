package Programmers.LV_2;

/**
 * 12900) 2xn_타일링
 */
public class L005_12900 {
    // n(가로의 길이)
    public int solution(int n) {
        // D(길이가 2 x n인 직사각형이 2 x 1, 1 x 2 타일을 붙일 수 있는 경우의 수)
        int[] D = new int[n + 1];
        // D 배열 초기화
        D[1] = 1;
        D[2] = 2;
        for (int i = 3; i <= n; i++) {
            // D[i] = D[i - 1] + D[i - 2]의 결과에 1000000007 나머지 연산을 수행하여 저장
            D[i] = (D[i - 1] + D[i - 2]) % 1000000007;
        }
        // D[n]값 반환하기
        return D[n];
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L005_12900 solution = new L005_12900();

        int n = 4;

        int result = solution.solution(n);

        System.out.println(result);
    }
}
