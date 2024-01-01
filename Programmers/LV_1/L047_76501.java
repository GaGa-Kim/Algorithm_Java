package Programmers.LV_1;

/**
 * 76501) 음양_더하기
 */
public class L047_76501 {
    // absolutes(정수들의 절댓값을 차례대로 담은 정수 배열)
    // signs(정수들의 부호를 차례대로 담은 불리언 배열)
    public int solution(int[] absolutes, boolean[] signs) {
        // answer(실제 정수들의 합)
        int answer = 0;
        for (int i = 0; i < absolutes.length; i++) {
            // signs[i]가 true라면
            if (signs[i] == true)
                // 덧셈
                answer += absolutes[i];
            // signs[i]가 false라면
            else
                // 뺄셈
                answer -= absolutes[i];
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L047_76501 solution = new L047_76501();

        int[] absolutes = { 4, 7, 12 };
        boolean[] signs = { true, false, true };

        int result = solution.solution(absolutes, signs);

        System.out.println(result);
    }
}
