package Programmers.LV_1;

/**
 * 155652) 둘만의_암호
 */
public class L070_155652 {
    // s(변환할 문자열)
    // skip(스킵할 문자열)
    // index(알파벳을 바꿔줄 인덱스)
    public String solution(String s, String skip, int index) {
        // answer(변환한 결과)
        String answer = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            for (int j = 0; j < index; j++) {
                // 문자 증가
                c += 1;
                // 변환할 문자인 c가 z보다 크다면
                if (c > 'z')
                    // a로 돌아가도록 함
                    c -= 26;
                // 변환할 문자인 c가 증가될 때 스킵할 문자열에 존재한다면
                if (skip.contains(String.valueOf(c)))
                    // j를 증가시키지 않음
                    j--;
            }
            // 변환한 결과에 추가
            answer += c;
        }
        // 문자열 변환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L070_155652 solution = new L070_155652();

        String s = "aukks";
        String skip = "wbqd";
        int index = 5;

        String result = solution.solution(s, skip, index);

        System.out.println(result);
    }
}
