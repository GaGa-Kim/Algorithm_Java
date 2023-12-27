package Programmers.LV_1;

/**
 * 12931) 자릿수_더하기
 */
public class L018_12931 {
    // n(자연수)
    public int solution(int n) {
        // answer(n의 각 자릿수의 합)
        int answer = 0;
        // numbers(각 자릿수의 숫자 저장 배열)
        String[] numbers = Integer.toString(n).split("");
        for (String number : numbers) {
            // answer에 number을 정수로 변환하여 합산
            answer += Integer.parseInt(number);
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L018_12931 solution = new L018_12931();

        int n = 123;

        int result = solution.solution(n);

        System.out.println(result);
    }
}
