package Programmers.LV_1;

import java.util.ArrayList;
import java.util.List;

/**
 * 68935) 3진법_뒤집기
 */
public class L044_68935 {
    // n(자연수)
    public int solution(int n) {
        // answer(n을 3진법 상에서 앞뒤로 뒤집은 후, 이를 다시 10진법으로 표현한 수)
        int answer = 0;
        // arr(3진수 변환 값을 담을 리스트)
        List<Integer> arr = new ArrayList<>();
        // n이 0이 아닐 동안
        while (n != 0) {
            arr.add(n % 3);
            n /= 3;
        }
        // 또는 Integer.parseInt(숫자, 3) 사용 가능
        // index(진수 값 인덱스)
        int index = 0;
        for (int i = arr.size() - 1; i >= 0; i--) {
            // 리스트의 값에 3의 index만큼 제곱하여 계산
            answer += arr.get(i) * Math.pow(3, index++);
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L044_68935 solution = new L044_68935();

        int n = 45;

        int result = solution.solution(n);

        System.out.println(result);
    }
}
