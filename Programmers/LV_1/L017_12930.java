package Programmers.LV_1;

/**
 * 12930) 이상한_문자_만들기
 */
public class L017_12930 {
    // s(문자열)
    public String solution(String s) {
        // answer(바꾼 문자열 StringBuilder)
        StringBuilder answer = new StringBuilder();
        // i(s의 위치 인덱스)
        int i = 0;
        // j(홀수/ 짝수 구분 인덱스)
        int j = 0;
        while (i < s.length()) {
            // s의 현재 위치가 공백이라면
            if (s.substring(i, i + 1).equals(" ")) {
                // answer에 공백 추가
                answer.append(" ");
                // 새로운 단어가 시작되므로 j를 다시 0으로 초기화
                j = 0;
            }
            // j가 짝수번째라면
            else if (j % 2 == 0) {
                // answer에 대문자 추가
                answer.append(s.substring(i, i + 1).toUpperCase());
                // j 증가
                j++;
            }
            // j가 홀수번째라면
            else {
                // answer에 소문자 추가
                answer.append(s.substring(i, i + 1).toLowerCase());
                // j 증가
                j++;
            }
            // i 증가
            i++;
        }
        // answer을 문자열로 변환하여 반환
        return answer.toString();
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L017_12930 solution = new L017_12930();

        String s = "try hello world";

        String result = solution.solution(s);

        System.out.println(result);
    }
}
