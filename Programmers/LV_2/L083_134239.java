package Programmers.LV_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 134239) 우박수열_정적분
 */
public class L083_134239 {
    // k(우박수의 초항)
    // ranges(정적분을 구하는 구간들의 목록)
    public double[] solution(int k, int[][] ranges) {
        // answer(정적분의 결과 목록)
        double[] answer = new double[ranges.length];
        // list(우박수열)
        List<Integer> list = new ArrayList<>();
        // k가 1보다 클 동안
        while (k > 1) {
            // list에 k 저장
            list.add(k);
            // k가 짝수라면
            if (k % 2 == 0)
                k /= 2;
            // k가 홀수라면
            else
                k = k * 3 + 1;
        }
        // list에 k 저장
        list.add(k);
        // sum(각 구간별 사다리꼴 넓이)
        double[] sum = new double[list.size()];
        // 각 구간별 사다리꼴의 넓이를 구해 저장
        for (int i = 0; i < list.size() - 1; i++) {
            sum[i] = (list.get(i) + list.get(i + 1)) / 2.0;
        }
        for (int i = 0; i < ranges.length; i++) {
            // ranges[i][0]이 ranges[i][1] + list의 크기 - 1보다 크다면
            if (ranges[i][0] > ranges[i][1] + list.size() - 1)
                // 주어진 구간의 시작점이 끝점보다 커서 유효하지 않은 구간이므로 answer[i]에 -1.0 저장
                answer[i] = -1.0;
            // range[i][0]이 ranges[i][1] + list의 크기 - 1과 같다면
            else if (ranges[i][0] == ranges[i][1] + list.size() - 1)
                // 주어진 구간의 시작점과 끝점이 같으므로 answer[i]에 0.0 저장
                answer[i] = 0.0;
            // range[i][0], range[i][1]이 유효한 구간이라면
            else {
                // answer[i]에 range[i][0]부터 ranges[i][1] + list.size() - 1까지의 사다리꼴 넓이 저장
                for (int j = ranges[i][0]; j < ranges[i][1] + list.size() - 1; j++) {
                    answer[i] += sum[j];
                }
            }
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L083_134239 solution = new L083_134239();

        int k = 5;
        int[][] ranges = { { 0, 0 }, { 0, -1 }, { 2, -3 }, { 3, -3 } };

        double[] result = solution.solution(k, ranges);

        System.out.println(Arrays.toString(result));
    }
}
