package Programmers.LV_3;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 12927) 야근_지수
 */
public class L010_12927 {
    // n(퇴근까지 남은 시간)
    // works(작업량)
    public long solution(int n, int[] works) {
        // answer(야근 피로도를 최소화한 값)
        long answer = 0;
        // worksQueue(작업량을 담을 최대값 우선순위 큐)
        PriorityQueue<Integer> worksQueue = new PriorityQueue<>(Collections.reverseOrder());
        // worksQueue에 works 저장
        for (int work : works) {
            worksQueue.offer(work);
        }
        for (int i = 0; i < n; i++) {
            // maxWork(worksQueue에서 가장 큰 작업량)
            int maxWork = worksQueue.poll();
            // maxWork가 0보다 작거나 같다면
            if (maxWork <= 0)
                // 모든 작업을 끝냈으므로 break
                break;
            // maxWork를 -1만큼 줄여서 다시 저장
            worksQueue.offer(maxWork - 1);
        }
        // worksQueue가 비지 않는 동안
        while (!worksQueue.isEmpty()) {
            // answer에 작업량을 제곱한 값을 더하여 갱신
            answer += Math.pow(worksQueue.poll(), 2);
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L010_12927 solution = new L010_12927();

        int n = 4;
        int[] works = { 4, 3, 3 };

        long result = solution.solution(n, works);

        System.out.println(result);
    }
}
