package Programmers.LV_1;

import java.time.LocalDate;

/**
 * 12901) 2016년
 */
public class L002_12901 {
    // a, b(월, 일)
    public String solution(int a, int b) {
        // week(요일 값을 담은 문자열 배열)
        String week[] = { "MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN" };
        // localDate(2016년 a월 b일)
        LocalDate localDate = LocalDate.of(2016, a, b);
        // dayOfWeek(2016년 a월 b일의 요일 값 - 1)
        int dayOfWeek = localDate.getDayOfWeek().getValue() - 1;
        // week[datOfWeek 반환]
        return week[dayOfWeek];
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L002_12901 solution = new L002_12901();

        int a = 5;
        int b = 24;

        String result = solution.solution(a, b);

        System.out.println(result);
    }
}
