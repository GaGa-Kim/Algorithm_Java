package Programmers.LV_1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 160586) 대충_만든_자판
 */
public class L006_160586 {

    // keymap(할당된 문자들이 순서대로 담긴 문자열 배열)
    // targets(입력하려는 문자열들이 담긴 문자열 배열)
    public int[] solution(String[] keymap, String[] targets) {
        // keymaps(문자들과 클릭 횟수를 담은 hashMap)
        Map<Character, Integer> keymaps = new HashMap<>();
        // keymaps에 문자와 클릭 횟수 저장하기
        for (String key : keymap) {
            for (int i = 0; i < key.length(); i++) {
                char ch = key.charAt(i);
                // 이미 존재한다면 최소 클릭 횟수로 갱신
                if (keymaps.containsKey(ch)) {
                    if (i + 1 < keymaps.get(ch)) {
                        keymaps.put(ch, i + 1);
                    }
                } else {
                    keymaps.put(ch, i + 1);
                }
            }
        }
        // 입력하려는 문자열들에 따라 최소 몇 번 눌러야 하는지 합산하기
        int[] answer = new int[targets.length];
        for (int i = 0; i < targets.length; i++) {
            int sum = 0;
            for (int j = 0; j < targets[i].length(); j++) {
                char ch = targets[i].charAt(j);
                // 문자열이 존재한다면
                if (keymaps.containsKey(ch)) {
                    // 클릭 횟수 추가
                    sum += keymaps.get(ch);
                } else {
                    // 클릭 횟수 -1
                    sum = -1;
                    break;
                }
            }
            answer[i] = sum;
        }
        // 각 문자열을 작성하기 위해 키를 최소 몇 번 눌러야 하는지 순서대로 배열에 담아 반환하기
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L006_160586 solution = new L006_160586();

        String[] keymap = { "BC" };
        String[] targets = { "AC", "BC" };

        int[] result = solution.solution(keymap, targets);

        System.out.println(Arrays.toString(result));
    }
}
