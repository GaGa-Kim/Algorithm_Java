package Programmers.LV_2;

import java.util.Arrays;

/**
 * 68936) 쿼드압축_후_개수_세기
 */
public class L060_68936 {
    // answer(배열에 최종적으로 남는 0의 개수와 1의 개수)
    static int[] answer;
    // visited(방문 유무 배열)
    static boolean[][] visited;

    // arr(0과 1로 이루어진 2n x 2n 크기의 2차원 정수 배열)
    public int[] solution(int[][] arr) {
        answer = new int[2];
        // n(arr의 길이)
        int n = arr.length;
        visited = new boolean[n][n];
        // size(압축하고자 하는 사각형의 크기)
        int size = n;
        while (size > 0) {
            // 사각형 함수(arr, n, size)
            square(arr, n, size);
            // size를 size/2로 갱신
            size /= 2;
        }
        // answer 반환
        return answer;
    }

    // 사각형 함수
    private static void square(int[][] arr, int n, int size) {
        for (int i = 0; i < n; i += size) {
            for (int j = 0; j < n; j += size) {
                // visited[i][j]를 방문하지 않았으면서 모두 0인지 또는 1인지 확인 함수가 true라면
                if (!visited[i][j] && isAllZerosOrOnes(arr, i, j, size)) {
                    // 사각형을 하나로 압축 가능하므로 answer[arr[i][j]]의 값을 1 증가
                    answer[arr[i][j]] += 1;
                }
            }
        }
    }

    // 모두 0인지 또는 1인지 확인 함수
    private static boolean isAllZerosOrOnes(int[][] arr, int startX, int startY, int size) {
        // arr의 startX, startY좌표부터 startX + size, startY + size까지 모두 0이거나 1인지 확인
        int value = arr[startX][startY];
        for (int i = startX; i < startX + size; i++) {
            for (int j = startY; j < startY + size; j++) {
                if (arr[i][j] != value) {
                    return false;
                }
            }
        }
        // 모두 0이거나 1일 경우 그 좌표들을 모두 방문 처리
        for (int i = startX; i < startX + size; i++) {
            for (int j = startY; j < startY + size; j++) {
                visited[i][j] = true;
            }
        }
        return true;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L060_68936 solution = new L060_68936();

        int[][] arr = {
                { 1, 1, 1, 1, 1, 1, 1, 1 },
                { 0, 1, 1, 1, 1, 1, 1, 1 },
                { 0, 0, 0, 0, 1, 1, 1, 1 },
                { 0, 1, 0, 0, 1, 1, 1, 1 },
                { 0, 0, 0, 0, 0, 0, 1, 1 },
                { 0, 0, 0, 0, 0, 0, 0, 1 },
                { 0, 0, 0, 0, 1, 0, 0, 1 },
                { 0, 0, 0, 0, 1, 1, 1, 1 } };

        int[] result = solution.solution(arr);

        System.out.println(Arrays.toString(result));
    }
}
