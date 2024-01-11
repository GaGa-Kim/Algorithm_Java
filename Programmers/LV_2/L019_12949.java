package Programmers.LV_2;

import java.util.Arrays;

/**
 * 12949) 행렬의_곱셈
 */
public class L019_12949 {
    // arr1, arr2(2차원 행렬)
    public int[][] solution(int[][] arr1, int[][] arr2) {
        // answer(arr1에 arr2를 곱한 결과)
        int[][] answer = new int[arr1.length][arr2[0].length];
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2[0].length; j++) {
                // k는 앞쪽의 열이자 뒤쪽의 행 인덱스
                for (int k = 0; k < arr1[0].length; k++) {
                    // answer[i][j]에 arr1[i][k](앞쪽의 행) * arr[k][j](뒤쪽의 열) 추가하기
                    answer[i][j] += arr1[i][k] * arr2[k][j];
                }
            }
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L019_12949 solution = new L019_12949();

        int[][] arr1 = { { 1, 4 }, { 3, 2 }, { 4, 1 } };
        int[][] arr2 = { { 3, 3 }, { 3, 3 } };

        int[][] result = solution.solution(arr1, arr2);

        System.out.println(Arrays.toString(result));
    }
}
