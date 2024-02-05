package Programmers.LV_2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 138476) 귤_고르기
 */
public class L085_138476 {
    // k(한 상자에 담으려는 귤의 개수)
    // tangerine(귤의 크기들)
    public int solution(int k, int[] tangerine) {
        // answer(k개를 고를 때 크기가 서로 다른 종류의 수의 최솟값)
        int answer = 0;
        // map(귤의 종류와 귤의 개수를 담을 HashMap)
        Map<Integer, Integer> map = new HashMap<>();
        // map에 귤의 종류에 따른 귤의 개수 담기
        for (int t : tangerine)
            map.put(t, map.getOrDefault(t, 0) + 1);
        // list(귤의 종류에 따른 귤의 개수를 담을 리스트)
        List<Integer> list = new ArrayList<>(map.values());
        // list를 내림차순 정렬
        list.sort(Collections.reverseOrder());
        // sum(귤의 개수)
        int sum = 0;
        for (int l : list) {
            // sum + l가 k보다 크다면
            if (sum + l >= k) {
                // answer 증가
                answer++;
                // k개의 귤을 골랐으므로 종료
                break;
            }
            // sum + l가 k보다 작다면
            else {
                // sum에 l 추가
                sum += l;
                // answer 증가
                answer++;
            }
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L085_138476 solution = new L085_138476();

        int k = 6;
        int[] tangerine = { 1, 3, 2, 5, 4, 5, 2, 3 };

        int result = solution.solution(k, tangerine);

        System.out.println(result);
    }
}
