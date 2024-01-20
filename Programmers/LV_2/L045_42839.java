package Programmers.LV_2;

import java.util.HashSet;
import java.util.Set;

/**
 * 42839) 소수_찾기
 */
public class L045_42839 {
    static Set<Integer> set;

    // numbers(각 종이 조각에 적힌 숫자가 적힌 문자열)
    public int solution(String numbers) {
        // set(만들어진 소수 숫자 저장 집합)
        set = new HashSet<>();
        // 숫자 생성 함수 실행
        makeNumbers("", numbers);
        // set의 크기 반환
        int answer = set.size();
        return answer;
    }

    // 숫자 생성 함수
    private void makeNumbers(String madeNumbers, String remainNumbers) {
        // 현재 숫자 조합이 공백이 아니라면
        if (!madeNumbers.equals("")) {
            int number = Integer.valueOf(madeNumbers);
            // 숫자가 소수인지 확인
            if (isPrime(number)) {
                // set에 저장
                set.add(number);
            }
        }
        // 숫자 생성 함수(현재 숫자 조합 + 남은 문자열의 i, 남은 문자열에서 i를 제외한 문자열)
        for (int i = 0; i < remainNumbers.length(); i++) {
            makeNumbers(madeNumbers + remainNumbers.charAt(i),
                    remainNumbers.substring(0, i) + remainNumbers.substring(i + 1, remainNumbers.length()));
        }
    }

    // 소수 확인 함수
    private boolean isPrime(int number) {
        // 0이거나 1이면 소수가 아님
        if (number == 0 || number == 1) {
            return false;
        }
        // 에라토스테네스의 체 이용
        // 제곱근까지 반복하기
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                // 소수가 아니면 false 반환
                return false;
            }
        }
        // true 반환
        return true;
    }

    public static void main(String[] args) {
        L045_42839 solution = new L045_42839();

        String numbers = "17";

        int result = solution.solution(numbers);

        System.out.println(result);
    }
}