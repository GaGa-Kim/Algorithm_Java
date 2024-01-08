package Programmers.LV_2;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 12923) 숫자_블록
 */
public class L012_12923 {
    // begin, end(구간을 나타내는 두 정수)
    public int[] solution(long begin, long end) {
        // answer(구간에 깔려 있는 블록의 숫자 배열)
        int[] answer = new int[(int) (end - begin) + 1];
        // index(위치 저장 인덱스)
        int index = 0;
        for (int i = (int) begin; i <= (int) end; i++) {
            // answer[index++] = 약수의 최대값 구하기 함수(i)
            answer[index++] = getDivisorMax(i);
        }
        // answer 반환
        return answer;
    }

    // 약수의 최대값 구하기 함수
    private int getDivisorMax(int n) {
        // 1이라면
        if (n == 1)
            // 0 반환
            return 0;
        // divisor(n의 약수를 담은 리스트)
        ArrayList<Integer> divisor = new ArrayList<>();
        for (int i = 2; i <= Math.sqrt(n); i++) {
            // n이 i로 나누어진다면
            if (n % i == 0) {
                // i는 n의 약수이므로 divisor에 i 저장
                divisor.add(i);
                // n / i의 값이 10000000보다 작거나 같다면
                if (n / i <= 10000000)
                    // 가장 큰 약수인 n / i를 바로 반환
                    return n / i;
            }
        }
        // divisor가 비어있지 않다면
        if (!divisor.isEmpty())
            // divisor 중 가장 큰 값 반환
            return divisor.get(divisor.size() - 1);
        // 약수가 없는 소수라면 1 반환
        return 1;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L012_12923 solution = new L012_12923();

        long begin = 1;
        long end = 10;

        int[] result = solution.solution(begin, end);

        System.out.println(Arrays.toString(result));
    }
}
