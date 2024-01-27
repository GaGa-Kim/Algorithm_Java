package Programmers.LV_2;

import java.util.Arrays;

/**
 * 77885) 2개_이하로_다른_비트
 */
public class L066_77885 {
    // numbers(정수들이 담긴 배열)
    public long[] solution(long[] numbers) {
        // answer(numbers의 모든 수들에 대하여 각 수의 f값)
        long[] answer = new long[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            // numbers[i]가 짝수라면
            if (numbers[i] % 2 == 0)
                // numbers[i] + 1을 저장
                answer[i] = numbers[i] + 1;
            // numbers[i]가 홀수라면
            else {
                // convertNum(numbers[i]를 이진수로 변환한 수)
                String convertNum = Long.toBinaryString(numbers[i]);
                // index(가장 먼저 0이 나오는 인덱스)
                int index = convertNum.lastIndexOf("0");
                // 비트에 0이 존재하지 않는다면
                if (index == -1) {
                    // convertNum를 "10" + 기존 비트의 두 번째 자리부터의 비트로 갱신
                    convertNum = "10" + convertNum.substring(1, convertNum.length());
                    // convertNum을 십진수 Long형으로 변환한 수를 저장
                    answer[i] = Long.parseLong(convertNum, 2);
                }
                // 비트에 0이 존재한다면
                else {
                    // convertNum를
                    // 비트에서 0이 나오기 전까지의 기존 비트 + "10" + 비트에서 0이 나온 후와 그 다음 비트를 제외한 나머지 비트로 갱신
                    convertNum = convertNum.substring(0, index) + "10"
                            + convertNum.substring(index + 2, convertNum.length());
                    // convertNum을 십진수 Long형으로 변환한 수를 저장
                    answer[i] = Long.parseLong(convertNum, 2);
                }
            }
        }
        // answer 변환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L066_77885 solution = new L066_77885();

        long[] numbers = { 2, 7 };

        long[] result = solution.solution(numbers);

        System.out.println(Arrays.toString(result));
    }
}
