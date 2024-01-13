package Programmers.LV_2;

import java.util.HashMap;
import java.util.Map;

/**
 * 17677) 뉴스_클러스터링
 */
public class L028_17677 {
    // intersection(교집합의 크기)
    static int intersection;
    // union(합집합의 크기)
    static int union;

    // str1, str2(자카드 유사도를 계산할 문자열)
    public int solution(String str1, String str2) {
        // answer(두 문자열 사이의 자카드 유사도)
        double answer;
        // str1, str2를 소문자로 변환
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        // a(str1의 다중집합 HashMap)
        Map<String, Integer> a = new HashMap<>();
        // 다중집합 a의 원소 저장하기
        addElements(a, str1);
        // b(str2의 다중집합 HashMap)
        Map<String, Integer> b = new HashMap<>();
        // 다중집합 b의 원소 저장하기
        addElements(b, str2);
        // 합집합과 교집합의 개수 초기화
        intersection = 0;
        union = 0;
        // 교집합과 합집합 계산하기
        calculate(a, b);
        // union이 0라면 answer에 1 저장, 0이 아니라면 intersection / union 저장
        answer = union == 0 ? 1 : (double) intersection / (double) union;
        // answer * 65536 반환
        return (int) (answer * 65536);
    }

    // 다중집합 원소 저장하기
    private void addElements(Map<String, Integer> map, String str) {
        for (int i = 0; i < str.length() - 1; i++) {
            // str의 i번째 글자와 i + 1번째 글자가 문자라면
            if (Character.isAlphabetic(str.charAt(i)) && Character.isAlphabetic(str.charAt(i + 1))) {
                // map에 이미 존재한다면 1 증가하여 저장, 아니면 1로 저장
                String s = str.charAt(i) + "" + str.charAt(i + 1);
                map.put(s, map.getOrDefault(s, 0) + 1);
            }
        }
    }

    // 교집합과 합집합 계산하기
    /*
     * A ∩ B → 두 다중집합의 원소 중 같은 원소가 있을 경우 더 적은 개수만큼 원소로 사용
     * A ∪ B = A + B - A ∩ B → 두 다중집합의 전체 원소 - 교집합에 사용한 원소만큼 원소로 사용
     */
    private void calculate(Map<String, Integer> a, Map<String, Integer> b) {
        // a의 key만큼
        for (String str : a.keySet()) {
            // union을 a의 value만큼 증가
            union += a.get(str);
            // b가 a의 key를 포함한다면
            if (b.containsKey(str))
                // intersection을 둘 중 더 작은 value만큼 증가
                intersection += Math.min(a.get(str), b.get(str));
        }
        // b의 key만큼
        for (String str : b.keySet()) {
            // union을 b의 value만큼 증가
            union += b.get(str);
        }
        // union에서 intersection만큼 감소
        union -= intersection;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L028_17677 solution = new L028_17677();

        String str1 = "aa1+aa2";
        String str2 = "AAAA12";

        int result = solution.solution(str1, str2);

        System.out.println(result);
    }
}
