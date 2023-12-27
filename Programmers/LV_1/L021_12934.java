package Programmers.LV_1;

/**
 * 12934) 정수_제곱근_판별
 */
public class L021_12934 {
    // n(양의 정수)
    public long solution(long n) {
        // x(양의 정수)
        long x = 1;
        // x의 제곱이 n보다 작을 동안
        while (x * x <= n) {
            // x의 제곱이 n이라면
            if (x * x == n)
                // x + 1의 제곱 반환
                return (x + 1) * (x + 1);
            // x 증가
            x++;
        }
        // -1 반환
        return -1;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L021_12934 solution = new L021_12934();

        long n = 121;

        long result = solution.solution(n);

        System.out.println(result);
    }
}
