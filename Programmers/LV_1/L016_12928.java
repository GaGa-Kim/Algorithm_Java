package Programmers.LV_1;

/**
 * 12928) 약수의_합
 */
public class L016_12928 {
    // n(정수)
    public int solution(int n) {
        // answer(약수의 합)
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            // n이 i로 나누어 떨어진다면
            if (n % i == 0)
                // answer에 i 합하기
                answer += i;
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L016_12928 solution = new L016_12928();

        int n = 12;

        int result = solution.solution(n);

        System.out.println(result);
    }
}
