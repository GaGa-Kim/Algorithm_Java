package SWEA.Basic.Day01;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 1208) Flatten
 */
public class D003_1208 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // T(테스트 케이스 수) = 10
        int T = 10;
        // T만큼
        for (int test_case = 1; test_case <= T; test_case++) {
            // N(덤프의 개수)
            int N = sc.nextInt();
            // box(박스 높이 저장 배열 (100만큼))
            int[] box = new int[100];
            // 박스 높이 저장
            for (int i = 0; i < box.length; i++) {
                // box[i] = 박스의 높이
                box[i] = sc.nextInt();
            }
            // 덤프만큼
            for (int i = 0; i < N; i++) {
                // box 오름차순 정렬
                Arrays.sort(box);
                // 가장 낮은 높이의 상자 갯수에 + 1
                box[0]++;
                // 가장 가장 높은 높이의 상자 갯수를 -1
                box[99]--;
            }
            // box 오름차순 정렬
            Arrays.sort(box);
            // #T와 가장 가장 높은 높이의 상자 갯수 - 가장 낮은 높이의 상자 갯수 반환
            System.out.println("#" + test_case + " " + (box[99] - box[0]));
        }
    }
}