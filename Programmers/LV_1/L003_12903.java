package Programmers.LV_1;

/**
 * 12903) 가운데_글자_가져오기
 */
public class L003_12903 {
    // s(단어)
    public String solution(String s) {
        // s의 길이가 짝수라면
        if (s.length() % 2 == 0)
            // s의 가운데 두 글자 반환
            return s.substring(s.length() / 2 - 1, s.length() / 2 + 1);
        // s의 길이가 홀수라면
        else
            // s의 가운데 한 글자 반환
            return s.substring(s.length() / 2, s.length() / 2 + 1);
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L003_12903 solution = new L003_12903();

        String s = "qwer";

        String result = solution.solution(s);

        System.out.println(result);
    }
}
