package Programmers.LV_1;

import java.util.ArrayList;
import java.util.List;

/**
 * 12977) 소수_만들기
 */
public class L032_12977 {
    static int answer;

    // nums(숫자들이 들어있는 배열)
    public int solution(int[] nums) {
        // arr(주어진 숫자 중 3개의 수를 더한 값들)
        List<Integer> arr = new ArrayList<>();
        // 3개의 수를 더하여 arr에 추가
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    arr.add(nums[i] + nums[j] + nums[k]);
                }
            }
        }
        // answer(소수가 되는 경우의 수)
        answer = 0;
        for (int i = 0; i < arr.size(); i++) {
            // 소수 판단 함수가 true라면
            if (isPrime(arr.get(i)))
                // answer 증가
                answer++;
        }
        // answer 반환
        return answer;
    }

    // 소수 판단 함수
    private boolean isPrime(int num) {
        for (int i = 2; i <= Math.sqrt(num); i++) {
            // 현재 값이 i로 나누어떨어진다면
            if (num % i == 0)
                // 소수가 아니므로 false 반환
                return false;
        }
        // 소수이므로 true 반환
        return true;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L032_12977 solution = new L032_12977();

        int[] nums = { 1, 2, 3, 4 };

        int result = solution.solution(nums);

        System.out.println(result);
    }
}
