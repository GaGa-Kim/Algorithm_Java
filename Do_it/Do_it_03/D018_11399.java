package Do_it.Do_it_03;

import java.util.Scanner;

/**
 * 11399) ATM
 */
public class D018_11399 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // N(사람 수)
        int N = sc.nextInt();
        // A(자릿수별로 구분해 저장한 배열)
        int[] A = new int[N];
        // S(A 합 배열: 각 사람이 인출을 완료하는데 필요한 시간을 저장하기)
        int[] S = new int[N];
        // A 배열 저장하기
        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }
        for (int i = 1; i < N; i++) {
            int insert_point = i;
            int insert_value = A[i];
            // 현재 범위에서 삽입 위치 찾기 (뒤에서부터 반복하기)
            for (int j = i - 1; j >= 0; j--) {
                if (A[j] < A[i]) {
                    insert_point = j + 1;
                    break;
                }
                if (j == 0) {
                    insert_point = 0;
                }
            }
            // 삽입을 위해 삽입 위치에서 i까지 데이터를 한 칸씩 뒤로 밀기 (뒤에서부터 반복하기)
            for (int j = i; j > insert_point; j--) {
                A[j] = A[j - 1];
            }
            // 삽입 위치에 현재 데이터 삽입하기
            A[insert_point] = insert_value;
        }
        // A 배열을 통한 합 배열 S 만들기
        S[0] = A[0];
        for (int i = 1; i < N; i++) {
            S[i] = S[i - 1] + A[i];
        }
        // S 배열의 각 데이터 값을 모두 합해 결과 출력하기
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum = sum + S[i];
        }
        System.out.println(sum);
    }
}
