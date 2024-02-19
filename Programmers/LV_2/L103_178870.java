package Programmers.LV_2;

import java.util.Arrays;

/**
 * 178870) 연속된_부분_수열의_합
 */
public class L103_178870 {
    // sequence(수열을 나타내는 정수 배열)
    // k(부분 수열의 합을 나타내는 정수)
    public int[] solution(int[] sequence, int k) {
        // answer(조건을 만족하는 수열의 시작 인덱스와 마지막 인덱스)
        int[] answer = new int[2];
        // start(시작 인덱스), end(마지막 인덱스)
        int start = 0, end = 0;
        // sum(부분 수열의 합)
        int sum = 0;
        // len(부분 수열의 최소 길이)
        int len = sequence.length;
        // start가 end보다 같거나 작을 동안
        while (start <= end) {
            // sum이 k보다 작다면
            if (sum < k) {
                // end가 sequence의 길이보다 크다면
                if (end >= sequence.length)
                    // 더 이상 부분 수열의 합을 증가시킬 수 없으므로 break;
                    break;
                // sum을 sequence[end++]만큼 증가
                sum += sequence[end++];
            }
            // sum이 k보다 크다면
            else {
                // sum을 sequence[start++]만큼 감소
                sum -= sequence[start++];
            }
            // sum이 k와 같다면
            if (sum == k) {
                // end - 1 - start가 len보다 작다면
                if (end - start - 1 < len) {
                    // 현재 부분 수열의 길이가 더 짧으므로 answer[0]을 start, answer[1]을 end -1로 갱신
                    answer[0] = start;
                    answer[1] = end - 1;
                    // len을 answer[1] - answer[0]의 길이로 갱신
                    len = answer[1] - answer[0];
                }
            }
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L103_178870 solution = new L103_178870();

        int[] sequence = { 1, 2, 3, 4, 5 };
        int k = 7;

        int[] result = solution.solution(sequence, k);

        System.out.println(Arrays.toString(result));
    }
}
