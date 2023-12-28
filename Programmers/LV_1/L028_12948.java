package Programmers.LV_1;

/**
 * 12948) 핸드폰_번호_가리기
 */
public class L028_12948 {
    // phone_number(전화번호)
    public String solution(String phone_number) {
        // answer(전화번호의 뒷 4자리를 제외한 나머지 숫자를 전부 *으로 가린 문자열)
        String answer = "*";
        // answer에 *를 전화번호의 길이 - 4만큼 반복하여 저장
        answer = answer.repeat(phone_number.length() - 4);
        // answer에 뒷 4자리 저장
        answer += phone_number.substring(phone_number.length() - 4, phone_number.length());
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L028_12948 solution = new L028_12948();

        String phone_number = "01033334444";

        String result = solution.solution(phone_number);

        System.out.println(result);
    }
}
