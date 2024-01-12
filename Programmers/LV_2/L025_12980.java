package Programmers.LV_2;

/**
 * 12980) 점프와_순간_이동
 */
public class L025_12980 {
    // n(이동하려는 거리)
    public int solution(int n) {
        // answer(건전지 사용량의 최솟값)
        int answer = 0;
        while (n >= 1) {
            // n이 2로 나누어 떨어진다면
            if (n % 2 == 0)
                // 순간이동 가능하므로 n / 2 위치로 갱신
                n /= 2;
            // n이 2로 나누어 떨어지지 않는다면
            else {
                // 점프하므로 answer 증가
                answer++;
                // n - 1 위치로 갱신
                n -= 1;
            }
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L025_12980 solution = new L025_12980();

        int n = 6;

        int result = solution.solution(n);

        System.out.println(result);
    }
}
