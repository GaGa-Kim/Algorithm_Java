package Programmers.LV_1;

import java.util.Arrays;

/**
 * 12933) 정수_내림차순으로_배치하기
 */
public class L020_12933 {
    // n(정수)
    public long solution(long n) {
        // number(정수의 값을 담은 char 배열)
        char[] number = Long.toString(n).toCharArray();
        // number 정렬
        Arrays.sort(number);
        // answer(n의 각 자릿수를 큰것부터 작은 순으로 정렬한 새로운 StringBuilder)
        StringBuilder answer = new StringBuilder();
        for (char ch : number) {
            // answer에 ch 저장
            answer.append(ch);
        }
        // answer을 뒤집은 후 형변환하여 반환
        return Long.parseLong(answer.reverse().toString());
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L020_12933 solution = new L020_12933();

        long n = 118372;

        long result = solution.solution(n);

        System.out.println(result);
    } 
}
