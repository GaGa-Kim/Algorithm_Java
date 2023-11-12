package SWEA.Basic.Day01;

import java.util.Scanner;

/**
 * 1204) 최빈수_구하기
 */
public class D001_1204 {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        // T(테스트 케이스 수)
        int T;
        T = sc.nextInt();
        // T만큼
        for (int test_case = 1; test_case <= T; test_case++) {
            // t(테스트 번호)
            int t = sc.nextInt();
            // score(점수 저장 배열 (0점부터 100점까지))
            int[] score = new int[101];
            // 1000명의 학생동안
            for (int i = 0; i < 1000; i++) {
                // score[점수]++
                score[sc.nextInt()]++;
            }
            // max(최댓값) = 0
            int max = 0;
            // index(최댓값의 인덱스) = 0
            int index = 0;
            for (int i = 0; i < score.length; i++) {
                // 최댓값보다 점수가 크거나 같다면
                if (score[i] >= max) {
                    // 최댓값, 인덱스 갱신
                    max = score[i];
                    index = i;
                }
            }
            // #t와 index 반환
            System.out.println("#" + t + " " + index);
        }
    }
}