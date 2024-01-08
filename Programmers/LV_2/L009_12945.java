package Programmers.LV_2;

/**
 * 12945) 피보나치_수
 */
public class L009_12945 {
    // n(2 이상의 자연수)
    public int solution(int n) {
        // answer(n번째 피보나치 수를 1234567으로 나눈 나머지 값)
        int answer = 0;
        // D(피보나치 수를 저장할 DP 테이블)
        int[] D = new int[n + 1];
        // 이미 아는 가장 작은 문제를 저장
        D[0] = 0;
        D[1] = 1;
        // 구한 값을 D 테이블에 저장
        for (int i = 2; i <= n; i++) {
            // 오버플로우를 방지하기 위해 미리 1234567으로 나눈 나머지 값을 저장
            D[i] = (D[i - 1] + D[i - 2]) % 1234567;
        }
        // answer애 n의 피보나치 수를 저장
        answer = D[n];
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L009_12945 solution = new L009_12945();

        int n = 3;

        int result = solution.solution(n);

        System.out.println(result);
    }
}
