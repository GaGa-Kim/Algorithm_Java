package SWEA.Basic.Day05;

import java.util.Scanner;

/**
 * 1220) Magnetic
 */
public class D001_1220 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        // T(테스트 케이스 수) = 10
        int T = 10;
        // T만큼
        for (int test_case = 1; test_case <= T; test_case++) {
            // n(정사각형 테이블의 한 변의 길이)
            int n = sc.nextInt();
            // arr(자성체 저장 2차원 배열 (100 x 100 만큼))
            int[][] arr = new int[n][n];
            // 자성체 저장
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr.length; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }
            // answer(교착상태 갯수)
            int answer = 0;
            // flag(1을 만났는지 여부)
            boolean flag = false;
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr.length; j++) {
                    // 자성체가 없을 경우
                    if (arr[j][i] == 0) {
                        continue;
                    }
                    // 자성체가 1(빨강 N극)일 경우
                    if (arr[j][i] == 1) {
                        flag = true;
                    }
                    // 자성체가 2(파랑 S극)이며 위에 자성체가 1(빨강 N극)인 것이 있을 경우
                    // 12가 되므로 교착 상태
                    if (arr[j][i] == 2 && flag) {
                        // 교착 상태 증가
                        answer++;
                        flag = false;
                    }
                }
                flag = false;
            }
            // #T와 answer 반환
            System.out.println("#" + test_case + " " + answer);
        }
    }
}
