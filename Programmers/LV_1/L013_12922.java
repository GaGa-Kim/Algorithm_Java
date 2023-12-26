package Programmers.LV_1;

/**
 * 12922) 수박수박수박수박수박수?
 */
public class L013_12922 {
    // n(길이)
    public String solution(int n) {
        // answer(결과 문자 StringBuilder)
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < n; i++) {
            // i가 짝수라면
            if (i % 2 == 0)
                // answer에 '수' 추가
                answer.append("수");
            // i가 홀수라면
            else
                // answer에 '박' 추가
                answer.append("박");
        }
        // answer을 문자열로 변환하여 반환
        return answer.toString();
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L013_12922 solution = new L013_12922();

        int n = 3;

        String result = solution.solution(n);

        System.out.println(result);
    }
}
