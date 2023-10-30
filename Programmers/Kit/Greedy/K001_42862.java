package Programmers.Kit.Greedy;

import java.util.Arrays;

/**
 * 42862) 체육복
 */
public class K001_42862 {
    // n(전체 학생의 수)
    // lost(체육복을 도난당한 학생들의 번호)
    // reserve(여벌의 체육복을 가져온 학생들의 번호)
    public int solution(int n, int[] lost, int[] reserve) {
        // answer(체육수업을 들을 수 있는 학생의 최댓값) = n - lost.length
        int answer = n - lost.length;
        // lost 정렬
        Arrays.sort(lost);
        // reserve 정렬
        Arrays.sort(reserve);
        // 여벌을 가져온 학생 중 도난 당한 학생이 있을 경우
        // +) Set을 통해서도 중복 제거 가능
        for (int i = 0; i < lost.length; i++) {
            for (int j = 0; j < reserve.length; j++) {
                if (lost[i] == reserve[j]) {
                    answer++;
                    lost[i] = -1;
                    reserve[j] = -1;
                    break;
                }
            }
        }
        // 앞이나 뒤 사람이 여벌을 가지고 있다면
        for (int i = 0; i < lost.length; i++) {
            for (int j = 0; j < reserve.length; j++) {
                if (lost[i] - 1 == reserve[j] || lost[i] + 1 == reserve[j]) {
                    answer++;
                    reserve[j] = -1;
                    break;
                }
            }
        }
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        K001_42862 solution = new K001_42862();

        int n = 5;
        int[] lost = { 2, 4 };
        int[] reserve = { 1, 3, 5 };

        int result = solution.solution(n, lost, reserve);

        System.out.println(result);
    }
}
