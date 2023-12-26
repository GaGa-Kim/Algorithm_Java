package Programmers.LV_1;

import java.util.Arrays;

/**
 * 12917) 문자열_내림차순으로_배치하기
 */
public class L009_12917 {
    // s(문자열)
    public String solution(String s) {
        // arr(char 배열)에 s 저장
        char[] arr = s.toCharArray();
        // arr 정렬
        Arrays.sort(arr);
        // answer(문자 stringbuilder)에 arr 저장
        StringBuilder answer = new StringBuilder(new String(arr));
        // answer를 뒤집고 string으로 변환하여 반환
        return answer.reverse().toString();
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L009_12917 solution = new L009_12917();

        String s = "Zbcdefg";

        String result = solution.solution(s);

        System.out.println(result);
    }
}
