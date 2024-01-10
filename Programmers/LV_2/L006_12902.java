package Programmers.LV_2;

/**
 * 12902) 3xn_타일링
 */
public class L006_12902 {
    // n(가로의 길이)
    public int solution(int n) {
        // D(길이가 3 x n인 직사각형이 2 x 1, 1 x 2 타일을 붙일 수 있는 경우의 수)
        // 이때 홀수는 만들 수 없으므로 짝수에 대해서만 n / 2로 배열을 만들게 됨
        long[] D = new long[n / 2];
        // n이 홀수라면
        if (n % 2 == 1)
            // 0 반환
            return 0;
        // n이 짝수라면
        else {
            // D 배열 초기화
            D[0] = 3;
            D[1] = 11;
            // sum(4의 배수마다 2개씩 발생하는 경우의 수에 대한 합)
            long sum = 0;
            // 2부터 ~ n / 2까지
            for (int i = 2; i < n / 2; i++) {
                // sum에 D[i - 2] * 2 추가
                sum += D[i - 2] * 2;
                // D[i] = D[i - 1] * 3 + 2 + sum의 결과에 1000000007 나머지 연산을 수행하여 저장
                D[i] = (D[i - 1] * 3 + 2 + sum) % 1000000007;
            }
            // D[n / 2 - 1]값 반환하기
            return (int) D[n / 2 - 1];
        }
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L006_12902 solution = new L006_12902();

        int n = 4;

        int result = solution.solution(n);

        System.out.println(result);
    }
}
