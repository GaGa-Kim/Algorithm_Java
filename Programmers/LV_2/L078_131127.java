package Programmers.LV_2;

import java.util.HashMap;
import java.util.Map;

/**
 * 131127) 할인_행사
 */
public class L078_131127 {
    // want(정현이가 원하는 제품)
    // number(정현이가 원하는 제품의 수량)
    // discount(XYZ 마트에서 할인하는 제품)
    public int solution(String[] want, int[] number, String[] discount) {
        // answer(회원등록시 정현이가 원하는 제품을 모두 할인 받을 수 있는 회원등록 날짜의 총 일수)
        int answer = 0;
        for (int i = 0; i <= discount.length - 10; i++) {
            // 모두 할인 받을 수 있다면
            if (isDiscountAble(want, number, discount, i))
                // answer 증가
                answer++;
        }
        // answer 반환
        return answer;
    }

    // 모두 할인 받을 수 있는지 함수
    private boolean isDiscountAble(String[] want, int[] number, String[] discount, int index) {
        // map(제품과 제품의 수량을 저장할 HashMap)
        Map<String, Integer> map = new HashMap<>();
        // map에 want와 number 저장
        for (int i = 0; i < want.length; i++) {
            map.put(want[i], number[i]);
        }
        for (int i = index; i < index + 10; i++) {
            // map에 discount[i]가 존재한다면
            if (map.containsKey(discount[i])) {
                // map의 value 값이 1이라면
                if (map.get(discount[i]) == 1)
                    // map에서 삭제
                    map.remove(discount[i]);
                else
                    // map의 value 값을 1 감소
                    map.put(discount[i], map.get(discount[i]) - 1);
            }
        }
        // map이 비어있다면
        if (map.isEmpty())
            // 모두 할인을 받을 수 있으므로 true 반환
            return true;
        // false 반환
        return false;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L078_131127 solution = new L078_131127();

        String[] want = { "banana", "apple", "rice", "pork", "pot" };
        int[] number = { 3, 2, 2, 2, 1 };
        String[] discount = { "chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice",
                "pot", "banana", "apple", "banana" };

        int result = solution.solution(want, number, discount);

        System.out.println(result);
    }
}
