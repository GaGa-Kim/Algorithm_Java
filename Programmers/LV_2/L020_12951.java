package Programmers.LV_2;

/**
 * 12951) JadenCase_문자열_만들기
 */
public class L020_12951 {
    // s(문자열)
    public String solution(String s) {
        // answer(s를 JadenCase로 바꾼 문자열 StringBuilder)
        StringBuilder answer = new StringBuilder();
        // index(단어의 인덱스)
        int index = 0;
        // s의 길이만큼 반복
        for (int i = 0; i < s.length(); i++) {
            // c(s의 i번째 글자)
            char c = s.charAt(i);
            // c가 공백이라면
            if (c == ' ') {
                // answer에 공백 추가
                answer.append(" ");
                // 다음 글자가 단어의 첫 번째 글자이므로 index를 0으로 초기화
                index = 0;
                continue;
            }
            // index가 0이라면
            else if (index == 0) {
                // answer에 c를 대문자로 변환해서 추가
                answer.append(Character.toUpperCase(c));
                // index 증가
                index++;
            }
            // index가 0이 아니라면
            else {
                // answer에 c를 소문자로 변환해서 추가
                answer.append(Character.toLowerCase(c));
                // index 증가
                index++;
            }
        }
        // answer을 문자열로 변환하여 반환
        return answer.toString();
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L020_12951 solution = new L020_12951();

        String s = "3people unFollowed me";

        String result = solution.solution(s);

        System.out.println(result);
    }
}
