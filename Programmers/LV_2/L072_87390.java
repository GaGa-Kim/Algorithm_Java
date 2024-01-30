package Programmers.LV_2;

import java.util.Arrays;

/**
 * 87390) n^2_배열_자르기
 */
public class L072_87390 {
    // n, left, right(정수)
    public int[] solution(int n, long left, long right) {
        // answer(주어진 과정대로 만들어진 1차원 배열)
        int[] answer = new int[(int) (right - left + 1)];
        // index(배열 위치 인덱스)
        int index = 0;
        for (long i = left; i <= right; i++) {
            // row(행 값)
            long row = i / n;
            // column(열 값)
            long column = i % n;
            // Math.max(row, column) + 1을 저장
            answer[index++] = (int) Math.max(row, column) + 1;
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L072_87390 solution = new L072_87390();

        int n = 3;
        long left = 2;
        long right = 5;

        int[] result = solution.solution(n, left, right);

        System.out.println(Arrays.toString(result));
    }
}
