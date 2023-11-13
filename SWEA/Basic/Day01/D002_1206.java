package SWEA.Basic.Day01;

import java.util.Scanner;

/**
 * 1206) View
 */
public class D002_1206 {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        // T(테스트 케이스 수) = 10
        int T = 10;
        // T만큼
        for (int test_case = 1; test_case <= T; test_case++) {
            // N(건물의 개수)
            int N = sc.nextInt();
            // building(건물 높이 저장 배열 (N만큼))
            int[] building = new int[N];
            // 빌딩의 높이 저장
            for (int i = 0; i < N; i++) {
                // building[i] = 건물의 높이
                building[i] = sc.nextInt();
            }
            // count(조망권 확보 세대 수) = 0
            int count = 0;
            for (int i = 2; i < N - 2; i++) {
                // max(왼쪽 두 칸, 오른쪽 두 칸의 건물 중 가장 큰 높이)
                int max = Math.max(building[i - 2], Math.max(building[i - 1],
                        Math.max(building[i + 1], building[i + 2])));
                // 현재 건물의 높이가 가장 큰 높이의 값보다 크다면
                if (building[i] > max) {
                    // 가장 큰 높이의 값보다 큰만큼 조망권을 획득할 수 있는 세대 수 증가
                    count += building[i] - max;
                }
            }
            // #T와 count 반환
            System.out.println("#" + test_case + " " + count);
        }
    }
}