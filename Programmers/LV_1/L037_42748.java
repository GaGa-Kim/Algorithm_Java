package Programmers.LV_1;

import java.util.Arrays;

/**
 * 42748) K번째수
 */
public class L037_42748 {
    // array(숫자 배열)
    // commands(i, j, k가 담긴 배열)
    public int[] solution(int[] array, int[][] commands) {
        // answer(정답 배열)
        int[] answer = new int[commands.length];
        for (int i = 0; i < commands.length; i++) {
            // temp(임시 배열) = Arrays.copyOfRange(array, 복사 시작 인덱스, 복사 끝 인덱스)
            int[] temp = Arrays.copyOfRange(array, commands[i][0] - 1, commands[i][1]);
            // temp 정렬
            Arrays.sort(temp);
            // 정답 배열에 저장
            answer[i] = temp[commands[i][2] - 1];
        }
        // 정답 배열 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L037_42748 solution = new L037_42748();

        int[] array = { 1, 5, 2, 6, 3, 7, 4 };
        int[][] commands = { { 2, 5, 3 }, { 4, 4, 1 }, { 1, 7, 3 } };

        int[] result = solution.solution(array, commands);

        System.out.println(Arrays.toString(result));
    }
}