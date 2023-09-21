package Do_it.Do_it_05;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 1931) 회의실_배정
 */
public class D035_1931 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // N(회의 개수)
        int N = sc.nextInt();
        // A(회의 정보 저장)
        int[][] A = new int[N][2];
        for (int i = 0; i < N; i++) {
            A[i][0] = sc.nextInt();
            A[i][1] = sc.nextInt();
        }
        // A 배열 정렬 수행하기
        // 종료 시간 기준으로 정렬, 종료 시간이 같으면 시작 시간 기준 정렬
        Arrays.sort(A, new Comparator<int[]>() {
            @Override
            public int compare(int[] S, int[] E) {
                if (S[1] == E[1]) {
                    return S[0] - E[0];
                }
                return S[1] - E[1];
            }
        });
        int count = 0;
        int end = -1;
        for (int i = 0; i < N; i++) {
            // 겹치치 않는 다음 회의가 나온 경우
            if (A[i][0] >= end) {
                // 현재 회의의 종료 시간으로 종료 시간 업데이트하기
                end = A[i][1];
                // 진행할 수 있는 회의 수 1 증가
                count++;
            }
        }
        // 총 진행 가능 회의 수 출력하기
        System.out.println(count);
    }
}