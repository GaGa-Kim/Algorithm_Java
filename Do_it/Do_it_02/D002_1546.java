package Do_it.Do_it_02;

import java.util.Scanner;

/**
 * 1546) 평균
 */
public class D002_1546 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 변수 N에 과목의 수 입력받기
        int N = sc.nextInt();
        // 길이가 N인 1차원 배열 A[] 선언하기
        int A[] = new int[N];
        for (int i = 0; i < N; i++) {
            // A[i]에 각 점수 저장하기
            A[i] = sc.nextInt();
        }
        long sum = 0;
        long max = 0;
        for (int i = 0; i < N; i++) {
            // 최고점은 변수 max에 저장하기
            if (A[i] > max)
                max = A[i];
            // 총점은 변수 sum에 저장하기
            sum = sum + A[i];
        }
        // sum * 100 / max / N 출력하기
        System.out.println(sum * 100.0 / max / N);
    }
}
