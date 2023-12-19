package Programmers.LV_1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 131128) 숫자_짝꿍
 */
public class L057_131128 {
    // X, Y(짝꿍을 찾을 두 정수)
    public String solution(String X, String Y) {
        // yMap(Y의 값을 저장할 HashMap)
        Map<String, Integer> yMap = new HashMap<>();
        // yMap에 Y의 값을 저장
        for (String s : Y.split("")) {
            yMap.put(s, yMap.getOrDefault(s, 0) + 1);
        }
        // answer(두 정수의 짝꿍)
        List<String> answer = new ArrayList<>();
        // X가 yMap에 존재하는지 확인
        for (String s : X.split("")) {
            // yMap에 s가 존재하며 s의 개수가 0보다 많다면
            if (yMap.containsKey(s) && yMap.get(s) > 0) {
                // answer에 짝꿍 저장
                answer.add(s);
                // yMap에서 HashMap의 값을 -1 감소
                yMap.put(s, yMap.get(s) - 1);
            }
        }
        // answer이 비었다면
        if (answer.isEmpty()) {
            // -1 반환
            return "-1";
        } else {
            // answer 내림차순 정렬
            answer.sort(Collections.reverseOrder());
            // answer의 첫 번째 값이 0이라면
            if (answer.get(0).equals("0")) {
                // 0 반환
                return "0";
            } else {
                // sb(StringBuilder)
                StringBuilder sb = new StringBuilder();
                // answer의 값들을 붙여 문자열로 변환
                for (String s : answer) {
                    sb.append(s);
                }
                // sb 반환
                return sb.toString();
            }
        }
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L057_131128 solution = new L057_131128();

        String X = "5525";
        String Y = "1255";

        String result = solution.solution(X, Y);

        System.out.println(result);
    }
}
