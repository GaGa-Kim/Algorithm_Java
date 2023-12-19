package Programmers.LV_1;

/**
 * 81301) 숫자_문자열과_영단어
 */
public class L050_81301 {
    // s(숫자의 일부 자릿수가 영단어로 바뀌어졌거나, 혹은 바뀌지 않고 그대로인 문자열)
    public int solution(String s) {
        // numbers(영단어 배열)
        String[] numbers = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };
        for (int i = 0; i < numbers.length; i++) {
            // s가 numbers[i]를 가지고 있다면
            if (s.contains(numbers[i])) {
                // numbers[i]를 i로 변경
                s = s.replaceAll(numbers[i], Integer.toString(i));
            }
        }
        // s를 정수로 변환하여 반환
        return Integer.parseInt(s);
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L050_81301 solution = new L050_81301();

        String s = "one4seveneight";

        int result = solution.solution(s);

        System.out.println(result);
    }
}
