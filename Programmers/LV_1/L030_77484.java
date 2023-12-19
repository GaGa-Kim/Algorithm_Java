package Programmers.LV_1;

import java.util.Arrays;

/**
 * 77484) 로또의_최고_순위와_최저_순위
 */
public class L030_77484 {
    // lottos(구매한 로또 번호를 담은 배열)
    // win_nums(당첨 번호를 담은 배열)
    public int[] solution(int[] lottos, int[] win_nums) {
        // answer(일치하는 번호 갯수 / 당첨 가능한 최고 순위와 최저 순위)
        int[] answer = new int[2];
        // rank(순위에 따른 당첨 개수를 담은 배열)
        /*
         * 0개 일치 - 6등,
         * 1개 일치 - 6등,
         * 2개 일치 - 5등,
         * 3개 일치 - 4등,
         * 4개 일치 - 3등,
         * 5개 일치 - 2등,
         * 6개 일치 - 1등
         */
        int[] rank = { 6, 6, 5, 4, 3, 2, 1 };
        for (int i = 0; i < lottos.length; i++) {
            // 구매한 로또 번호가 0이라면
            if (lottos[i] == 0) {
                answer[0]++;
                continue;
            }
            for (int j = 0; j < win_nums.length; j++) {
                // 구매한 로또 번호와 당첨 번호가 일치한다면
                if (lottos[i] == win_nums[j]) {
                    answer[0]++;
                    answer[1]++;
                }
            }
        }
        // 일치하는 번호 개수에 따라 순위로 변환
        answer[0] = rank[answer[0]];
        answer[1] = rank[answer[1]];
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L030_77484 solution = new L030_77484();

        int[] lottos = { 44, 1, 0, 0, 31, 25 };
        int[] win_nums = { 31, 10, 45, 1, 6, 19 };

        int[] result = solution.solution(lottos, win_nums);

        System.out.println(Arrays.toString(result));
    }
}
