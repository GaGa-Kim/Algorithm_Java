package Programmers.LV_2;

/**
 * 12985) 예상_대진표
 */
public class L027_12985 {
    // n(게임 참가자 수)
    // a, b(참가자 번호와 경쟁자 번호)
    public int solution(int n, int a, int b) {
        // answer(A번 참가자가 B 참가자와 만나는 라운드)
        int answer = 0;
        // a와 b가 같지 않을 동안
        while (a != b) {
            // answer 증가
            answer++;
            // a를 다음 라운드 번호인 (a + 1) / 2로 갱신
            a = (a + 1) / 2;
            // b를 다음 라운드 번호인 (b + 1) / 2로 갱신
            b = (b + 1) / 2;
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L027_12985 solution = new L027_12985();

        int n = 8;
        int a = 4;
        int b = 7;

        int result = solution.solution(n, a, b);

        System.out.println(result);
    }
}
