package Programmers.LV_1;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 12915) 문자열_내_마음대로_정렬하기
 */
public class L007_12915 {
    // strings(문자열로 구성된 리스트)
    // n(정수)
    public String[] solution(String[] strings, int n) {
        // strings 오름차순 정렬
        Arrays.sort(strings);
        // strings n번째 인덱스를 기준으로 정렬
        Arrays.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                if (s1.charAt(n) > s2.charAt(n))
                    return 1;
                else if (s1.charAt(n) < s2.charAt(n))
                    return -1;
                else
                    return 0;
            }
        });
        // strings 반환
        return strings;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L007_12915 solution = new L007_12915();

        String[] strings = {};
        int n = 1;

        String[] result = solution.solution(strings, n);

        System.out.println(Arrays.toString(result));
    }
}
