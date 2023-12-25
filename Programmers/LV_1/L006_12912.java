package Programmers.LV_1;

/**
 * 12912) 두_정수_사이의_합
 */
public class L006_12912 {
    // a, b(두 정수)
    public long solution(int a, int b) {
        // answer(a와 b 사이에 속한 모든 정수의 합)
        long answer = 0;
        // left(시작 정수)
        int left = Math.min(a, b);
        // right(끝 정수)
        int right = Math.max(a, b);
        // left와 right 정수 사이의 모든 정수 합하기
        for (int i = left; i <= right; i++) {
            answer += i;
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L006_12912 solution = new L006_12912();

        int a = 3;
        int b = 5;

        long result = solution.solution(a, b);

        System.out.println(result);
    }
}
