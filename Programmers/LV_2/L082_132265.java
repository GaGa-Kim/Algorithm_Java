package Programmers.LV_2;

import java.util.HashMap;
import java.util.Map;

/**
 * 132265) 롤케이크_자르기
 */
public class L082_132265 {
    // topping(롤케이크에 올려진 토핑들의 번호)
    public int solution(int[] topping) {
        // answer(롤케이크를 공평하게 자르는 방법의 수)
        int answer = 0;
        // cake1(앞의 케이크 조각의 토핑 종류와 갯수를 담은 HashMap)
        Map<Integer, Integer> cake1 = new HashMap<>();
        // cake2(뒤의 케이크 조각의 토핑 종류와 갯수를 담은 HashMap)
        Map<Integer, Integer> cake2 = new HashMap<>();
        // cake2에 모든 topping 저장
        for (int t : topping) {
            cake2.put(t, cake2.getOrDefault(t, 0) + 1);
        }
        for (int t : topping) {
            // cake2에서 t 삭제
            if (cake2.get(t) - 1 == 0)
                cake2.remove(t);
            else
                cake2.put(t, cake2.get(t) - 1);
            // cake1에 t 추가
            cake1.put(t, cake1.getOrDefault(t, 0) + 1);
            // cake1의 크기와 cake2의 크기가 같다면
            if (cake1.size() == cake2.size())
                // cake1과 cake2의 토핑 종류의 갯수가 같으므로 answer 증가
                answer++;
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L082_132265 solution = new L082_132265();

        int[] topping = { 1, 2, 1, 3, 1, 4, 1, 2 };

        int result = solution.solution(topping);

        System.out.println(result);
    }
}
