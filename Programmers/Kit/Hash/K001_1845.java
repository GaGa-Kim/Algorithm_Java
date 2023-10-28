package Programmers.Kit.Hash;

import java.util.HashSet;
import java.util.Set;

/**
 * 1845) 폰켓몬
 */
public class K001_1845 {
    // nums(폰켓몬의 종류 번호가 담긴 1차원 배열)
    public int solution(int[] nums) {
        // set(폰켓몬 종류를 담은 HashSet)
        Set<Integer> set = new HashSet<>();
        // set에 nums 저장
        for (int num : nums)
            set.add(num);
        // Set의 크기와 최대 가져갈 수 있는 폰켓몬의 수 비교
        int answer = nums.length / 2;
        answer = Math.min(answer, set.size());
        // 더 작은 값을 폰켓몬 종류 번호의 개수로 리턴
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        K001_1845 solution = new K001_1845();

        int[] nums = { 3, 1, 2, 3 };

        int result = solution.solution(nums);

        System.out.println(result);
    }
}
