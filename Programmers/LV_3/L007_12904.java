package Programmers.LV_3;

/**
 * 12904) 가장_긴_팰린드롬
 */
public class L007_12904 {
    // s(문자열)
    public int solution(String s) {
        // size(가장 긴 팰린드롬의 길이)
        for (int size = s.length(); size > 0; size--) {
            // start(팰린드롬의 시작 위치 인덱스)
            for (int start = 0; start + size <= s.length(); start++) {
                // 팰린드롬이 맞다면
                if (isPalindrome(s, start, size)) {
                    // size 반환
                    return size;
                }
            }
        }
        // 가장 작은 팰린드롬의 길이인 1 반환
        return 1;
    }

    // 팰린드롬이 맞는지 함수
    private boolean isPalindrome(String s, int start, int size) {
        // size의 반절만큼 반복하며 앞과 뒤의 글자를 확인
        for (int i = 0; i < size / 2; i++) {
            // s의 start + i와 s의 start + size - i - 1이 다르다면
            if (s.charAt(start + i) != s.charAt(start + size - i - 1)) {
                // 팰린드롬이 아니므로 false 반환
                return false;
            }
        }
        // true 반환
        return true;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L007_12904 solution = new L007_12904();

        String s = "abcdcba";

        int result = solution.solution(s);

        System.out.println(result);
    }
}
