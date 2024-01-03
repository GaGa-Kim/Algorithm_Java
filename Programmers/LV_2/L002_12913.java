package Programmers.LV_2;

/**
 * 12913) 땅따먹기
 */
public class L002_12913 {
    // land(땅따먹기 게임의 땅)
    int solution(int[][] land) {
        // answer(마지막 행까지 모두 내려왔을 때, 얻을 수 있는 점수의 최대값)
        int answer = 0;
        // 자신이 사용할 수 있는 이전 열 중 가장 큰 값으로 갱신 (DP)
        for (int i = 0; i < land.length - 1; i++) {
            land[i + 1][0] += Math.max(Math.max(land[i][1], land[i][2]), land[i][3]); // 이전 열에서 0을 사용하지 못함
            land[i + 1][1] += Math.max(Math.max(land[i][0], land[i][2]), land[i][3]); // 이전 열에서 1을 사용하지 못함
            land[i + 1][2] += Math.max(Math.max(land[i][0], land[i][1]), land[i][3]); // 이전 열에서 2를 사용하지 못함
            land[i + 1][3] += Math.max(Math.max(land[i][0], land[i][1]), land[i][2]); // 이전 열에서 3을 사용하지 못함
        }
        // 마지막 열에서 가장 큰 점수로 갱신
        for (int i = 0; i < 4; i++) {
            answer = Math.max(answer, land[land.length - 1][i]);
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L002_12913 solution = new L002_12913();

        int[][] land = { { 1, 2, 3, 5 },
                { 5, 6, 7, 8 },
                { 4, 3, 2, 1 } };

        int result = solution.solution(land);

        System.out.println(result);
    }
}
