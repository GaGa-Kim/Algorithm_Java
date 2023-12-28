package Programmers.LV_1;

/**
 * 12947) 하샤드_수
 */
public class L027_12947 {
    // x(양의 정수)
    public boolean solution(int x) {
        // numbers(x를 문자열로 바꾼 값)
        String numbers = Integer.toString(x);
        // sum(자릿수의 합)
        int sum = 0;
        for (int i = 0; i < numbers.length(); i++) {
            // sum에 자릿수를 더하기
            sum += Character.getNumericValue(numbers.charAt(i));
        }
        // x가 sum으로 나누어 떨어진다면
        if (x % sum == 0)
            // true 반환
            return true;
        // x가 sum으로 나누어 떨어지지 않는다면
        else
            // false 반환
            return false;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L027_12947 solution = new L027_12947();

        int x = 10;

        boolean result = solution.solution(x);

        System.out.println(result);
    }
}
