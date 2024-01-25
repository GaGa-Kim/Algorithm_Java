package Programmers.LV_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 72411) 메뉴_리뉴얼
 */
public class L062_72411 {
    // combiMap(각 코스 갯수 별 코스 요리와 주문량을 담은 HashMap)
    Map<String, Integer> combiMap;
    // answer(새로 추가하게 될 코스요리의 메뉴 구성)
    List<String> answer = new ArrayList<>();

    // orders(각 손님들이 주문한 단품메뉴들)
    // course(추가하고 싶어하는 코스요리를 구성하는 단품메뉴들의 갯수)
    public String[] solution(String[] orders, int[] course) {
        for (int i = 0; i < orders.length; i++) {
            // charArray(orders[i]에 대한 단품메뉴들로 문자 배열 생성)
            char[] charArray = orders[i].toCharArray();
            // charArray 정렬
            Arrays.sort(charArray);
            // 정렬한 charArray를 다시 orders[i]에 저장
            orders[i] = String.valueOf(charArray);
        }
        for (int targetCourse : course) {
            // combiMap 초기화
            combiMap = new HashMap<>();
            // maxCount(각 코스요리 길이에 대한 최대 주문량)
            int maxCount = Integer.MIN_VALUE;
            for (String order : orders) {
                // combi(코스 요리 조합 StringBuilder)
                StringBuilder combi = new StringBuilder();
                // dfs(order, combi, 0, 0, targetCourse)
                dfs(order, combi, 0, 0, targetCourse);
            }
            // combiMap에서 각 코스 요리의 길이에 대한 조합 중 가장 많은 주문량으로 maxCount 갱신
            for (Integer count : combiMap.values()) {
                maxCount = Math.max(maxCount, count);
            }
            // combiMap에서 maxCount와 동일한 주문량을 가지는 코스 요리 조합을 answer에 저장
            for (String key : combiMap.keySet()) {
                if (maxCount >= 2 && combiMap.get(key) == maxCount)
                    answer.add(key);
            }
        }
        // answer을 정렬 후 문자열 배열로 변환해 반환
        Collections.sort(answer);
        return answer.stream().toArray(String[]::new);
    }

    // dfs
    private void dfs(String order, StringBuilder combi, int index, int count, int targetCourse) {
        // count가 targetCourse와 동일하다면
        if (count == targetCourse) {
            // targetCourse의 갯수만큼 코스요리 조합을 만들었으므로 combiMap에 저장, 만약 이미 존재한다면 주문량 증가
            combiMap.put(combi.toString(), combiMap.getOrDefault(combi.toString(), 0) + 1);
            return;
        }
        for (int i = index; i < order.length(); i++) {
            // combi에 order의 i번째 단품메뉴 추가
            combi.append(order.charAt(i));
            // dfs(order, combi, i + 1, count + 1, targetCourse)
            dfs(order, combi, i + 1, count + 1, targetCourse);
            // combi에서 order의 i번째 단품메뉴 삭제
            combi.delete(count, count + 1);
        }
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L062_72411 solution = new L062_72411();

        String[] orders = { "ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH" };
        int[] course = { 2, 3, 4 };

        String[] result = solution.solution(orders, course);

        System.out.println(Arrays.toString(result));
    }
}
