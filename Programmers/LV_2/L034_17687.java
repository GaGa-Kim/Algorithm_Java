package Programmers.LV_2;

/**
 * 17687) n진수 게임
 */
public class L034_17687 {
    // n(진법)
    // t(미리 구할 숫자의 개수)
    // m(게임에 참가하는 인원)
    // p(튜브의 순서)
    public String solution(int n, int t, int m, int p) {
        // answer(튜브가 말해야 하는 숫자 StringBuilder)
        StringBuilder answer = new StringBuilder();
        // game(n진법으로 미리 구해야 하는 숫자들 StringBuilder)
        StringBuilder game = new StringBuilder();
        // game에 i를 n진수 변환하여 대문자로 저장
        for (int i = 0; i <= m * t; i++) {
            game.append(Integer.toString(i, n).toUpperCase());
        }
        // answer에 t만큼 game의 i번째 글자를 저장
        for (int i = p - 1; answer.length() < t; i += m) {
            answer.append(game.charAt(i));
        }
        // answer을 문자열로 변환하여 반환
        return answer.toString();
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L034_17687 solution = new L034_17687();

        int n = 16;
        int t = 16;
        int m = 2;
        int p = 1;

        String result = solution.solution(n, t, m, p);

        System.out.println(result);
    }
}
