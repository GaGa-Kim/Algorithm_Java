package Programmers.LV_2;

/**
 * 12924) 숫자의_표현
 */
public class L005_12924 {
    // n(자연수)
    public int solution(int n) {
        // answer(연속된 자연수들로 n을 표현하는 방법의 수)
        int answer = 1;
        // start(시작 포인터)
        int start = 1;
        // end(끝 포인터)
        int end = 1;
        // sum(연속된 자연수들의 합)
        int sum = 1;
        // right가 n이 아닌 동안
        while (end != n) {
            // sum이 n과 같다면
            if (sum == n) {
                // answer 증가, end 증가, sum값 변경
                answer++;
                end++;
                sum = sum + end;
            }
            // sum이 n보다 크다면
            else if (sum > n) {
                // sum 값 변경, start 증가
                sum = sum - start;
                start++;
            }
            // sum이 n보다 작다면
            else {
                // end 증가, sum 값 변경
                end++;
                sum = sum + end;
            }
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L005_12924 solution = new L005_12924();

        int n = 15;

        int result = solution.solution(n);

        System.out.println(result);
    }
}
