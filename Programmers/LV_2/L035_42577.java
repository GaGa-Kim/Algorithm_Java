package Programmers.LV_2;

import java.util.HashMap;
import java.util.Map;

/**
 * 42577) 전화번호 목록
 */
public class L035_42577 {
    // phone_book(전화번호부에 적힌 전화번호를 담은 배열)
    public boolean solution(String[] phone_book) {
        // map(전화번호가 담긴 hashMap)
        Map<String, Integer> map = new HashMap<>();
        // map에 phone_book, 1 저장하기
        for (String phone : phone_book) {
            map.put(phone, 1);
        }
        for (int i = 0; i < phone_book.length; i++) {
            for (int j = 0; j < phone_book[i].length(); j++) {
                // map에 phone_book[i]의 부분 문자열이 존재한다면
                if (map.containsKey(phone_book[i].substring(0, j)))
                    // false 반환
                    return false;
            }
        }
        // 모두 접두어가 아니라면 true 반환
        return true;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L035_42577 solution = new L035_42577();

        String[] phone_book = { "119", "97674223", "1195524421" };

        boolean result = solution.solution(phone_book);

        System.out.println(result);
    }
}
