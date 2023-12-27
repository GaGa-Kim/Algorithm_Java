package Programmers.LV_1;

import java.util.Arrays;

/**
 * 12932) 자연수_뒤집어_배열로_만들기
 */
public class L019_12932 {
    // n(자연수)
    public int[] solution(long n) {
        // number(n을 변환한 문자열)
        String number = Long.toString(n);
        // answer(각 자리 숫자를 원소로 가지는 배열)
        int[] answer = new int[number.length()];
        for (int i = 0; i < number.length(); i++) {
            // number.subString(i, i + 1)을 변환하여 뒤에서부터 저장
            answer[number.length() - 1 - i] = Integer.parseInt(number.substring(i, i + 1));
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L019_12932 solution = new L019_12932();

        long n = 12345;

        int[] result = solution.solution(n);

        System.out.println(Arrays.toString(result));
    }
}
