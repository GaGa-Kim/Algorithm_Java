package Programmers.LV_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 150370) 개인정보_수집_유효기간
 */
public class L009_150370 {
    // today(오늘 날짜 의미 문자열)
    // terms(약관의 유효기간을 담은 1차원 문자열 배열)
    // privacies(수집된 개인정보의 정보를 담은 1차원 문자열 배열)
    public int[] solution(String today, String[] terms, String[] privacies) {
        // map(약관의 유효기간을 담은 hashMap)
        Map<String, String> map = new HashMap<>();
        // map에 약관 종류와 유효기간 저장
        for (int i = 0; i < terms.length; i++) {
            map.put(terms[i].split(" ")[0], terms[i].split(" ")[1]);
        }

        // 오늘 날짜를 year, month, day에 저장
        int year = Integer.parseInt(today.split("\\.")[0]);
        int month = Integer.parseInt(today.split("\\.")[1]);
        int day = Integer.parseInt(today.split("\\.")[2]);

        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < privacies.length; i++) {
            String date = privacies[i].split(" ")[0];
            int term = Integer.parseInt(map.get(privacies[i].split(" ")[1])) * 28;
            int days = (year - Integer.parseInt(date.split("\\.")[0])) * 28 * 12
                    + (month - Integer.parseInt(date.split("\\.")[1])) * 28
                    + (day - Integer.parseInt(date.split("\\.")[2]));
            // 오늘 날짜 - 수집 날짜 >= 유효기간
            if (days >= term)
                answer.add(i + 1);
        }
        // 리스트를 배열로 변환하여 반환
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L009_150370 solution = new L009_150370();

        String today = "2022.05.19";
        String[] terms = { "A 6", "B 12", "C 3" };
        String[] privacies = { "2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C" };

        int[] result = solution.solution(today, terms, privacies);

        System.out.println(Arrays.toString(result));
    }
}
