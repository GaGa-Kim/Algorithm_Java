package Programmers.LV_2;

import java.util.Arrays;

/**
 * 77485) 행렬_테두리_회전하기
 */
public class L065_77485 {
    // rows, columns(행렬의 세로, 가로 길이)
    // queries(회전들의 목록)
    public int[] solution(int rows, int columns, int[][] queries) {
        // answer(회전에 의해 위치가 바뀐 숫자들 중 가장 작은 숫자들)
        int[] answer = new int[queries.length];
        // map(행렬들을 저장한 배열)
        int[][] map = new int[rows][columns];
        // map에 순서대로 숫자를 저장
        int index = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                map[i][j] = index++;
            }
        }
        for (int i = 0; i < queries.length; i++) {
            // x1, y1, x2, y2 구하기
            int x1 = queries[i][0] - 1;
            int y1 = queries[i][1] - 1;
            int x2 = queries[i][2] - 1;
            int y2 = queries[i][3] - 1;
            // min(최소값)
            int min = Integer.MAX_VALUE;
            // start(회전에 따라 사라지게 되는 첫 번째 값)
            int start = map[x1][y1];
            // ↑ 방향 회전하며 최솟값 찾기 (좌측 부분 회전)
            for (int j = x1; j < x2; j++) {
                min = Math.min(min, map[j][y1]);
                map[j][y1] = map[j + 1][y1];
            }
            // ← 방향 회전하며 최솟값 찾기 (하단 부분 회전)
            for (int j = y1; j < y2; j++) {
                min = Math.min(min, map[x2][j]);
                map[x2][j] = map[x2][j + 1];
            }
            // ↓ 방향 회전하며 최솟값 찾기 (우측 부분 회전)
            for (int j = x2; j > x1; j--) {
                min = Math.min(min, map[j][y2]);
                map[j][y2] = map[j - 1][y2];
            }
            // → 방향 회전하며 최솟값 찾기 (상단 부분 회전)
            for (int j = y2; j > y1; j--) {
                min = Math.min(min, map[x1][j]);
                map[x1][j] = map[x1][j - 1];
            }
            // 마지막 부분에 start 저장
            map[x1][y1 + 1] = start;
            // answer[i] = min 저장
            answer[i] = min;
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L065_77485 solution = new L065_77485();

        int rows = 3;
        int columns = 3;
        int[][] queries = {
                { 1, 1, 2, 2 },
                { 1, 2, 2, 3 },
                { 2, 1, 3, 2 },
                { 2, 2, 3, 3 }
        };

        int[] result = solution.solution(rows, columns, queries);

        System.out.println(Arrays.toString(result));
    }
}
