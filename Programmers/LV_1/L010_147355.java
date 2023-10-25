package Programmers.LV_1;

/**
 * 147355) 크기가_작은_부분문자열
 */
public class L010_147355 {
    // t(숫자로 이루어진 문자열)
    // p(숫자로 이루어진 문자열)
    public int solution(String t, String p) {
        int answer = 0;
        for (int i = 0; i < t.length() - p.length() + 1; i++) {
            // t를 p의 길이만큼으로 자르기
            long num = Long.parseLong(t.substring(i, i + p.length()));
            // p보다 작거나 같으면
            if (num <= Long.parseLong(p))
                // 리턴값 증가
                answer++;
        }
        // 리턴값 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L010_147355 solution = new L010_147355();

        String t = "3141592";
        String p = "271";

        int result = solution.solution(t, p);

        System.out.println(result);
    }
}
