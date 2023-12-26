package Programmers.LV_1;

/**
 * 12926) 시저_암호
 */
public class L015_12926 {
    // s(암호문 문자열)
    // n(거리)
    public String solution(String s, int n) {
        // answer(s를 n만큼 민 암호문)
        String answer = "";
        for (int i = 0; i < s.length(); i++) {
            // ch(암호)
            char ch = s.charAt(i);
            // ch가 공백이라면
            if (ch == ' ') {
                // answer에 ch 추가
                answer += ch;
            }
            // ch가 소문자라면
            else if (ch >= 'a' && ch <= 'z') {
                // ch + n이 소문자를 넘어간다면
                if (ch + n > 'z')
                    // answer에 ch - 26 + n 추가 (소문자 처음부터 시작하기 위해 -26)
                    answer += (char) (ch - 26 + n);
                else
                    // answer에 ch + n 추가
                    answer += (char) (ch + n);
            }
            // ch가 대문자라면
            else if (ch >= 'A' && ch <= 'Z') {
                // ch + n이 대문자를 넘어간다면
                if (ch + n > 'Z')
                    // answer에 ch - 26 + n 추가 (대문자 처음부터 시작하기 위해 -26)
                    answer += (char) (ch - 26 + n);
                else
                    // answer에 ch + n 추가
                    answer += (char) (ch + n);
            }
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L015_12926 solution = new L015_12926();

        String s = "AB";
        int n = 1;

        String result = solution.solution(s, n);

        System.out.println(result);
    }
}
