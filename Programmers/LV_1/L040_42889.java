package Programmers.LV_1;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 42889) 실패율
 */
public class L040_42889 {
    // N(전체 스테이지의 개수)
    // stages(게임을 이용하는 사용자가 현재 멈춰있는 스테이지의 번호가 담긴 배열)
    public int[] solution(int N, int[] stages) {
        // answer(실패율이 높은 스테이지부터 내림차순으로 스테이지의 번호가 담겨있는 배열)
        int[] answer = new int[N];
        // arr(스테이지 도전 중인 사용자의 수와 스테이지 클리어 사용자의 수를 담은 배열)
        // 배열 대신 HashMap을 사용해 시간복잡도 낮추기 가능
        int[][] arr = new int[N][2];
        for (int i : stages) {
            // 모든 스테이지를 클리어 했다면
            if (i == N + 1) {
                // 클리어한 스테이지들 증가
                for (int j = 0; j < i - 1; j++)
                    arr[j][1]++;
            }
            // 모든 스테이지를 클리어하지 못했다면
            else {
                // 클리어한 스테이지들 증가
                for (int j = 0; j < i; j++)
                    arr[j][1]++;
                // 현재 도전하는 스테이지 증가
                arr[i - 1][0]++;
            }
        }
        // failure(스테이지의 번호와 실패율을 담은 배열)
        double[][] failure = new double[N][2];
        for (int i = 0; i < arr.length; i++) {
            // 스테이지 번호 저장
            failure[i][0] = i + 1;
            // 클리어한 사용자가 있다면
            if (arr[i][1] != 0)
                // 스테이지 실패율을 계산해서 저장 (클리어한 사용자가 없다면 그대로 실패율은 0)
                failure[i][1] = (double) arr[i][0] / arr[i][1];
        }
        // failure를 실패율을 기준으로 내림차순 정렬한 후,
        // 실패율이 같다면 작은 번호의 스테이지가 먼저 오도록 정렬 커스텀
        Arrays.sort(failure, new Comparator<double[]>() {
            @Override
            public int compare(double[] o1, double[] o2) {
                int result = Double.compare(o2[1], o1[1]);
                if (result == 0)
                    return Double.compare(o1[0], o2[0]);
                return result;
            }
        });
        // failures의 스테이지 번호를 순서대로 answer에 저장
        for (int i = 0; i < failure.length; i++) {
            answer[i] = (int) failure[i][0];
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L040_42889 solution = new L040_42889();

        int N = 2;
        int[] stages = { 1, 1, 1, 1 };

        int[] result = solution.solution(N, stages); // [1, 2]

        System.out.println(Arrays.toString(result));
    }
}
