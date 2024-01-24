package Programmers.LV_2;

import java.util.Arrays;

/**
 * 70129) 이진_변환_반복하기
 */
public class L061_70129 {
    // s(0과 1로 이루어진 어떤 문자열)
    public int[] solution(String s) {
        // answer(이진 변환의 횟수와 변환 과정에서 제거된 모든 0의 개수)
        int[] answer = new int[2];
        // s가 1이 될때까지
        while (!s.equals("1")) {
            // before(0을 제거하기 전의 s 길이)
            int before = s.length();
            // s = s에서 0을 공백으로 변환
            s = s.replaceAll("0", "");
            // after(0을 제거한 후의 s 길이)
            int after = s.length();
            // answer[1]을 before - answer만큼 증가
            answer[1] += before - after;
            // s의 길이만큼 이진법으로 변환한 값을 다시 s에 저장
            s = Integer.toBinaryString(s.length());
            // answer[0] 증가
            answer[0]++;
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L061_70129 solution = new L061_70129();

        String s = "110010101001";

        int[] result = solution.solution(s);

        System.out.println(Arrays.toString(result));
    }
}
