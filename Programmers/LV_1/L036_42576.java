package Programmers.LV_1;

import java.util.HashMap;
import java.util.Map;

/**
 * 42576) 완주하지_못한_선수
 */
public class L036_42576 {
    // participant(마라톤에 참여한 선수들의 이름이 담긴 배열)
    // completion(완주한 선수들의 이름이 담긴 배열)
    public String solution(String[] participant, String[] completion) {
        // map(마라톤에 참여한 선수들의 이름과 완주 여부가 담긴 hashMap)
        Map<String, Integer> map = new HashMap<>();
        // map에 participant, +1 저장하기
        for (String player : participant) {
            map.put(player, map.getOrDefault(player, 0) + 1);
        }
        // map에서 completion, -1 갱신하기
        for (String finisher : completion) {
            map.put(finisher, map.get(finisher) - 1);
        }
        // map에서 0이 아닌 선수 찾아서 반환하기
        String answer = "";
        for (String key : map.keySet()) {
            if (map.get(key) != 0) {
                answer = key;
            }
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L036_42576 solution = new L036_42576();

        String[] participant = { "leo", "kiki", "eden" };
        String[] completion = { "eden", "kiki" };

        String result = solution.solution(participant, completion);

        System.out.println(result);
    }
}