package Programmers.LV_1;

/**
 * 12925) 문자열을_정수로_바꾸기
 */
public class L014_12925 {
    // s(문자열)
    public int solution(String s) {
        // answer(문자열 s를 숫자로 변환한 결과)
        int answer = Integer.parseInt(s);
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L014_12925 solution = new L014_12925();

        String s = "-1234";

        int result = solution.solution(s);

        System.out.println(result);
    }
}
