package Programmers.LV_1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 142086) 가장_가까운_같은_글자
 */
public class L067_142086 {
    // s(문자열)
    public int[] solution(String s) {
        // map(문자와 인덱스를 담은 hashMap)
        Map<Character, Integer> map = new HashMap<>();
        int[] answer = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            // map에 존재한다면
            if (map.containsKey(ch)) {
                // for문의 인덱스 - hashMap의 인덱스를 배열에 저장
                answer[i] = i - map.get(ch);
            }
            // map에 존재하지 않다면
            else {
                // -1을 배열에 저장
                answer[i] = -1;
            }
            // map에 문자와 인덱스 저장(혹은 갱신)
            map.put(ch, i);
        }
        // 배열 리턴
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L067_142086 solution = new L067_142086();

        String s = "banana";

        int[] result = solution.solution(s);

        System.out.println(Arrays.toString(result));
    }
}
