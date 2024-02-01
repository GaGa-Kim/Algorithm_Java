package Programmers.LV_2;

import java.util.Arrays;

/**
 * 92342) 양궁대회
 */
public class L076_92342 {
    // answer
    // (라이언이 가장 큰 점수 차이로 우승하기 위해 n발의 화살을 어떤 과녁 점수에 맞혀야 하는지에 대한 10점부터 0점까지의 정수 배열)
    int[] answer = new int[] { -1 };
    // lion(라이언의 화살 배열)
    int[] lion = new int[11];
    // max(점수 차이의 최대값)
    int max = 0;

    // n(화살의 개수)
    // info(어피치가 맞힌 과녁 점수의 개수를 10점부터 0점까지 순서대로 담은 정수 배열)
    public int[] solution(int n, int[] info) {
        // dfs(0, n, info)
        dfs(0, n, info);
        // answer 반환
        return answer;
    }

    // dfs
    private void dfs(int depth, int n, int[] info) {
        // depth가 n이라면
        if (depth == n) {
            // 화살을 모두 사용했으므로 diff(라이언과 어피치의 점수 차이)를 구함
            int diff = score(info);
            // max가 diff보다 작다면
            if (max <= diff) {
                // max를 diff로 갱신
                max = diff;
                // lion을 answer로 복제
                answer = lion.clone();
            }
            return;
        }
        // 라이언이 화살을 쏠 수 있는 모든 경우를 구함
        // 이때 라이언이 어피치를 이기는 법은 어피치보다 화살을 하나 더 쐈을 때이므로 딱 하나까지만 차이 나도록 화살을 사용
        for (int i = 0; i < info.length && lion[i] <= info[i]; i++) {
            // lion[i] 과녁 점수 1 증가
            lion[i]++;
            // dfs(depth + 1, n, info)
            dfs(depth + 1, n, info);
            // lion[i] 과녁 점수 이전 값으로 돌리기 위해 -1 감소
            lion[i]--;
        }
    }

    // 라이언과 어피치의 점수 구하기
    private int score(int[] info) {
        // apeach_score(어피치의 점수)
        int apeach_score = 0;
        // lion_socre(라이언의 점수)
        int lione_score = 0;
        for (int i = 0; i < info.length; i++) {
            // info[i]와 lion[i]가 모두 0이라면
            if (info[i] == 0 && lion[i] == 0)
                // 라이언과 어피치 모두 단 하나의 화살도 맞히지 못했으므로 continue;
                continue;
            // info[i]가 lion[i]보다 크거나 같다면
            else if (info[i] >= lion[i])
                // 어피치가 이기므로 apeach_score가 10 - i 증가
                apeach_score += 10 - i;
            // info[i]가 lion[i]보다 작다면
            else
                // 라이언이 이기므로 lion_score가 10 - i 증가
                lione_score += 10 - i;
        }
        // diff(어피치와 라이언의 점수 차이)
        int diff = lione_score - apeach_score;
        // diff가 0보다 작거나 같다면
        if (diff <= 0)
            // 라이언이 지므로 -1 반환
            return -1;
        // 라이언이 이기므로 diff 반환
        return diff;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L076_92342 solution = new L076_92342();

        int n = 5;
        int[] info = { 2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0 };

        int[] result = solution.solution(n, info);

        System.out.println(Arrays.toString(result));
    }
}
