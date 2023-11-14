package SWEA.Basic.Day02;

import java.util.Scanner;

/**
 * 1209) Sum
 */
public class D001_1209 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // T(테스트 케이스 수) = 10
        int T = 10;
        // T만큼
        for (int test_case = 1; test_case <= T; test_case++) {
            // t(테스트 번호)
            int t = sc.nextInt();
            // arr(숫자 저장 2차원 배열 (100 x 100 만큼))
            int[][] arr = new int[100][100];
            // 입력 숫자 저장
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr.length; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }
            // max(최댓값)
            int max = 0;
            // sum1(각 행의 합), sum2(각 열의 합), sum3(대각선의 합), sum4(대각선의 합)
            int sum1 = 0, sum2 = 0, sum3 = 0, sum4 = 0;
            // 각 합 구하기
            for (int i = 0; i < arr.length; i++) {
                sum1 = 0;
                sum2 = 0;
                sum3 += arr[i][i];
                sum4 += arr[i][99 - i];
                for (int j = 0; j < arr.length; j++) {
                    sum1 += arr[i][j];
                    sum2 += arr[j][i];
                }
                // 최댓값과 각 행, 열의 합 비교 및 갱신
                max = Math.max(max, sum1);
                max = Math.max(max, sum2);
            }
            // 최댓값과 양쪽 대각선의 합 비교 및 갱신
            max = Math.max(max, sum3);
            max = Math.max(max, sum4);
            // #T와 최댓값 반환
            System.out.println("#" + t + " " + max);
        }
    }
}
