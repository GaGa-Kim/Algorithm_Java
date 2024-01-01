package Programmers.LV_1;

/**
 * 72410) 신규_아이디_추천
 */
public class L046_72410 {
    // new_id(신규 유저가 입력한 아이디)
    public String solution(String new_id) {
        // answer(7단계의 처리 과정을 거친 후의 추천 아이디)
        String answer = new_id;
        // answer을 모두 소문자로 치환 (규칙1)
        answer = answer.toLowerCase();
        // answer에서 정규표현식을 통해 소문자, 숫자, 빼기, 밑줄, 마침표를 제외한 모든 문자 제거 (규칙2)
        answer = answer.replaceAll("[^a-z0-9-_.]", "");
        // answer에서 정규표현식을 통해 마침표가 2번 이상 연속된 부분은 하나의 마침표로 치환 (규칙3)
        answer = answer.replaceAll("[.]{2,}", ".");
        // answer에서 정규표현식을 통해 마침표가 처음이나 끝에 위치한다면 제거 (규칙4)
        answer = answer.replaceAll("^[.]|[.]$", "");
        // answer이 빈 문자라면 a를 대입 (규칙5)
        if (answer.equals(""))
            answer += "a";
        // answer의 길이가 16 이상이라면, 첫 15개의 문자만 남기고 제거한 후 마침표가 끝에 위치한다면 제거 (규칙6)
        if (answer.length() >= 16) {
            answer = answer.substring(0, 15);
            answer = answer.replaceAll("[.]$", "");
        }
        // answer의 길이가 2 이하라면, 길이가 3이 될 때까지 마지막 문자를 반복해서 붙임 (규칙7)
        while (answer.length() < 3) {
            answer += answer.charAt(answer.length() - 1);
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L046_72410 solution = new L046_72410();

        String new_id = "...!@BaT#*..y.abcdefghijklm";

        String result = solution.solution(new_id);

        System.out.println(result);
    }
}
