package Programmers.LV_1;

import java.util.Arrays;

/**
 * 12950) 행렬의_덧셈
 */
public class L029_12950 {
    // arr1, arr2(행렬)
    public int[][] solution(int[][] arr1, int[][] arr2) {
        // answer(행렬 덧셈의 결과)
        int[][] answer = new int[arr1.length][arr1[0].length];
        // 두 행렬의 값을 계산
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr1[0].length; j++) {
                answer[i][j] = arr1[i][j] + arr2[i][j];
            }
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L029_12950 solution = new L029_12950();

        int[][] arr1 = { { 1, 2 }, { 2, 3 } };
        int[][] arr2 = { { 3, 4 }, { 5, 6 } };

        int[][] result = solution.solution(arr1, arr2);

        System.out.println(Arrays.toString(result));
    }
}
