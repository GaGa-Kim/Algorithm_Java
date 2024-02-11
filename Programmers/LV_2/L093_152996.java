package Programmers.LV_2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 152996) 시소_짝꿍
 */
public class L093_152996 {
    // weights(사람들의 몸무게 목록)
    public long solution(int[] weights) {
        // answer(시소 짝꿍 쌍의 개수)
        long answer = 0;
        // weights 오름차순 정렬
        Arrays.sort(weights);
        // map(몸무게와 갯수를 담을 HashMap)
        Map<Double, Integer> map = new HashMap<>();
        for (int weight : weights) {
            // 시소 짝꿍1과 시소 짝꿍2의 무게가 동일할 때
            double couple1 = (weight * 1.0);
            // 시소 짝꿍1 * 2와 시소 짝꿍2의 무게가 동일할 때
            double couple2 = (weight * 1.0) / 2.0;
            // 시소 짝꿍1 * 3과 시소 짝꿍2 * 2의 무게가 동일할 때
            double couple3 = (weight * 2.0) / 3.0;
            // 시소 짝꿍1 * 4와 시소 짝꿍2 * 3의 무게가 동일할 때
            double couple4 = (weight * 3.0) / 4.0;
            // map에 couple1, 2, 3, 4가 존재한다면 answer을 map.get만큼 증가
            if (map.containsKey(couple1))
                answer += map.get(couple1);
            if (map.containsKey(couple2))
                answer += map.get(couple2);
            if (map.containsKey(couple3))
                answer += map.get(couple3);
            if (map.containsKey(couple4))
                answer += map.get(couple4);
            // map에 weight 저장
            map.put((weight * 1.0), map.getOrDefault((weight * 1.0), 0) + 1);
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L093_152996 solution = new L093_152996();

        int[] weights = { 100, 180, 360, 100, 270 };

        long result = solution.solution(weights);

        System.out.println(result);
    }
}
