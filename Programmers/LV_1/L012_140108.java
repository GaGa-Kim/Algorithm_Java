package Programmers.LV_1;

/**
 * 140108) 문자열_나누기
 */
public class L012_140108 {
    // s(문자열)
    public int solution(String s) {
        // answer(분해한 문자열의 개수)
        int answer = 0;
        // x(문자열의 글자)
        char x = s.charAt(0);
        // same(동일 인덱스)
        int same = 0;
        // another(비동일 인덱스)
        int another = 0;
        for (int i = 0; i < s.length(); i++) {
            // same의 갯수와 another의 갯수가 동일하다면
            if (same == another) {
                // answer 증가
                answer++;
                // x 갱신
                x = s.charAt(i);
            }
            // s.charAt(i)가 x와 동일하다면
            if (s.charAt(i) == x)
                // same 증가
                same++;
            // 동일하지 않다면
            else
                // another 증가
                another++;
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L012_140108 solution = new L012_140108();

        String s = "banana";

        int result = solution.solution(s);

        System.out.println(result);
    }
}
