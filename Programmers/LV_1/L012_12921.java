package Programmers.LV_1;

/**
 * 12921) 소수_찾기
 */
public class L012_12921 {
    // n(숫자)
    public int solution(int n) {
        // answer(소수의 개수)
        int answer = 0;
        // arr(소수 배열)
        int[] arr = new int[n + 1];
        // arr 배열 초기화하기 (자기 자신의 값으로 채우기)
        for (int i = 2; i <= n; i++) {
            arr[i] = i;
        }
        // n의 제곱근까지 반복하기
        for (int i = 2; i <= Math.sqrt(n); i++) {
            // 이미 소수가 아니면 넘어감
            if (arr[i] == 0)
                continue;
            // 소수의 배수 값을 n까지 반복하기
            for (int j = i + i; j <= n; j = j + i)
                // 이 수가 소수가 아니라는 것을 표시하기
                arr[j] = 0;
        }
        // 1 ~ n까지 반복하기
        for (int i = 1; i <= n; i++) {
            // arr 배열에서 소수인 경우 answer 증가
            if (arr[i] != 0)
                answer++;
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L012_12921 solution = new L012_12921();

        int n = 10;

        int result = solution.solution(n);

        System.out.println(result);
    }
}
