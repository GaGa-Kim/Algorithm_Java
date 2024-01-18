package Programmers.LV_2;

import java.util.HashMap;
import java.util.Map;

/**
 * 42578) 의상
 */
public class L036_42578 {
    // clothes(의상의 이름과 의상의 종류 배열)
    public int solution(String[][] clothes) {
        // answer = 의상 조합하기 함수
        int answer = mixClothes(clothes);
        // answer 반환
        return answer;
    }

    // 의상 조합하기 함수
    private int mixClothes(String[][] clothes) {
        // map(의상의 종류와 갯수가 담긴 hashMap)
        Map<String, Integer> map = new HashMap<>();
        for (String[] clothe : clothes) {
            // 의상의 종류가 존재한다면
            if (map.containsKey(clothe[1])) {
                // map에 기존 갯수 + 1
                map.put(clothe[1], map.get(clothe[1]) + 1);
            } else {
                // map에 의상의 종류와 1 저장
                map.put(clothe[1], 1);
            }
        }
        // result(모든 경우의 수 갯수) = 1
        int result = 1;
        for (int key : map.values()) {
            // result *= map의 의상의 종류에 따른 갯수 + 1
            result *= (key + 1);
        }
        // result - 1(아무 것도 입지 않을 경우) 반환
        return result - 1;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L036_42578 solution = new L036_42578();

        String[][] clothes = { { "yellow_hat", "headgear" },
                { "blue_sunglasses", "eyewear" },
                { "green_turban", "headgear" } };

        int result = solution.solution(clothes);

        System.out.println(result);
    }
}
