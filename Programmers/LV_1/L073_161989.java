package Programmers.LV_1;

/**
 * 161989) 덧칠하기
 */
public class L073_161989 {

    // n(페인트가 칠해질 수 있는 구역 갯수)
    // m(롤러의 길이)
    // section(다시 페인트를 칠하기로 정한 구역들의 번호)
    public int solution(int n, int m, int[] section) {
        // start(롤러 시작 위치)
        int start = section[0];
        // answer(최소 횟수)
        int answer = 1;
        for (int sec : section) {
            // 페인트를 칠할 때 같이 칠해질 수 있다면 넘어감
            if (start + m > sec)
                continue;
            // start 갱신
            start = sec;
            // answer 증가
            answer++;
        }
        // answer 리턴
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L073_161989 solution = new L073_161989();

        int n = 8;
        int m = 4;
        int[] section = { 2, 3, 6 };

        int result = solution.solution(n, m, section);

        System.out.println(result);
    }
}
