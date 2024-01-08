package Programmers.LV_2;

import java.util.Arrays;

/**
 * 12941) 최솟값_만들기
 */
public class L016_12941 {
    // A, B(길이가 같은 배열)
    public int solution(int[] A, int[] B) {
        // answer(누적된 최솟값)
        int answer = 0;
        // A, B 배열 정렬
        Arrays.sort(A);
        Arrays.sort(B);
        // A 배열은 앞에서부터(작은 값), B 배열은 뒤에서부터(큰 값) 가져와서 곱하여 누적하기
        for (int i = 0; i < A.length; i++) {
            answer += A[i] * B[B.length - 1 - i];
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L016_12941 solution = new L016_12941();

        int[] A = { 1, 4, 2 };
        int[] B = { 5, 4, 4 };

        int result = solution.solution(A, B);

        System.out.println(result);
    }
}
