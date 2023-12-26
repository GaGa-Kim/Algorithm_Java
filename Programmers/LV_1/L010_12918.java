package Programmers.LV_1;

/**
 * 12918) 문자열_다루기_기본
 */
public class L010_12918 {
    // s(문자열)
    public boolean solution(String s) {
        // s의 길이가 4 또는 6이라면
        if (s.length() == 4 || s.length() == 6) {
            for (int i = 0; i < s.length(); i++) {
                // i가 숫자가 아니라면
                if (!Character.isDigit(s.charAt(i)))
                    // false 반환
                    return false;
            }
            // s가 모두 숫자라면 true 반환
            return true;
        }
        // s의 길이가 4 또는 6이 아니라면
        else {
            // false 반환
            return false;
        }
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L010_12918 solution = new L010_12918();

        String s = "a234";

        boolean result = solution.solution(s);

        System.out.println(result);
    }
}
