package Programmers.LV_1;

/**
 * 12937) 짝수와_홀수
 */
public class L023_12937 {
    // num(정수)
    public String solution(int num) {
        // 2로 나누어서 나머지가 없을 경우
        if (num % 2 == 0)
            // 짝수
            return "Even";
        // 2로 나누어서 나머지가 있을 경우
        else
            // 홀수
            return "Odd";
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L023_12937 solution = new L023_12937();

        int num = 3;

        String result = solution.solution(num);

        System.out.println(result);
    }
}
