package Programmers.LV_3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 67258) 보석_쇼핑
 */
public class L039_67258 {
    // gems(진열대 번호 순서대로 보석들의 이름이 저장된 배열)
    public int[] solution(String[] gems) {
        // answer(모든 보석을 하나 이상 포함하는 가장 짧은 구간)
        int[] answer = new int[2];
        // kind(진열된 모든 종류의 보석 개수)
        int kind = new HashSet<>(Arrays.asList(gems)).size();
        // map(보석에 따른 개수를 저장하는 HashMap)
        Map<String, Integer> map = new HashMap<>();
        // start(시작 인덱스)
        int start = 0;
        // end(끝 인덱스)
        int end = 0;
        // length(구간의 길이)
        int length = Integer.MAX_VALUE;
        while (end < gems.length) {
            // map에 보석 이름과 보석의 개수 저장
            map.put(gems[end], map.getOrDefault(gems[end], 0) + 1);
            // 시작 인덱스의 보석과 중복된 보석이 있는 동안
            while (map.get(gems[start]) > 1) {
                // 앞의 보석을 제거
                map.put(gems[start], map.get(gems[start]) - 1);
                // 중복 보석을 포함하므로 시작 인덱스를 증가
                start++;
            }
            // 전체 보석의 종류를 1개씩 포함하면서 더 짧은 구간이라면
            if (map.size() == kind && (end - start) < length) {
                // 구간의 길이를 갱신
                length = end - start;
                // 시작 진열대 번호와 끝 진열대 번호 갱신
                answer[0] = start + 1;
                answer[1] = end + 1;
            }
            // 끝 인덱스 증가
            end++;
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L039_67258 solution = new L039_67258();

        String[] gems = { "DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA" };

        int[] result = solution.solution(gems);

        System.out.println(Arrays.toString(result));
    }
}
