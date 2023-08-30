package Do_it;

import java.util.Scanner;

/**
 * 2750) 수_정렬하기
 */
public class D015_2750 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // N(정렬할 수 개수)
        int N = sc.nextInt();
        // A(정렬할 배열 선언)
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }
        // 루프의 개수만큼 반복
        for (int i = 0; i < N - 1; i++) {
            // 정렬하는 범위만큼 반복
            for (int j = 0; j < N - 1 - i; j++) {
                // 현재 A 배열의 값보다 1칸 오른쪽 배열의 값이 더 작으면 두 수 바꾸기
                if (A[j] > A[j + 1]) {
                    int temp = A[j];
                    A[j] = A[j + 1];
                    A[j + 1] = temp;
                }
            }
        }
        // A 배열 출력
        for (int i = 0; i < N; i++) {
            System.out.println(A[i]);
        }
    }
}