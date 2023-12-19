package Programmers.LV_1;

/**
 * 87389) 나머지가_1이_되는_수_찾기
 */
public class L054_87389 {
    // n(자연수)
    public int solution(int n) {
        // answer(나머지가 1이 되도록 하는 가장 작은 자연수)
        int answer = 0;
        for (int i = 2; i < n; i++) {
            // n을 i로 나누었을 때의 나머지가 1이라면
            if (n % i == 1) {
                // i는 나머지가 1이 되도록 하는 가장 작은 자연수
                answer = i;
                break;
            }
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L054_87389 solution = new L054_87389();

        int n = 10;

        int result = solution.solution(n);

        System.out.println(result);
    }
}
