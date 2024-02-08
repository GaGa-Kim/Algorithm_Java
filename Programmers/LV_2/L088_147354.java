package Programmers.LV_2;

import java.util.Arrays;

/**
 * 147354) 테이블_해시_함수
 */
public class L088_147354 {
    // data(테이블의 데이터)
    // col, row_begin, row_end(해시 함수에 대한 입력)
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        // answer(테이블의 해시 값)
        int answer = 0;
        // data를 커스텀 정렬하기
        Arrays.sort(data, (o1, o2) -> {
            // 값이 동일할 경우, 첫 번째 컬럼의 값을 기준으로 내림차순 정렬
            if (o1[col - 1] == o2[col - 1])
                return o2[0] - o1[0];
            // col번째 컬럼의 값을 기준으로 오름차순 정렬
            return o1[col - 1] - o2[col - 1];
        });
        // row_begin부터 row_end 행에 대해 i로 나눈 나머지들의 합 저장
        for (int i = row_begin - 1; i < row_end; i++) {
            // total(나머지들의 합)
            int total = 0;
            for (int d : data[i]) {
                // total에 d % (i + 1) 저장하기
                total += (d % (i + 1));
            }
            // 나머지들의 합을 가지고 XOR한 값 저장하기
            answer = (answer ^ total);
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L088_147354 solution = new L088_147354();

        int[][] data = { { 2, 2, 6 }, { 1, 5, 10 }, { 4, 2, 9 }, { 3, 8, 3 } };
        int col = 2;
        int row_begin = 2;
        int row_end = 3;

        int result = solution.solution(data, col, row_begin, row_end);

        System.out.println(result);
    }
}
