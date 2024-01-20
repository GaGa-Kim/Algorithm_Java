package Programmers.LV_2;

/**
 * 43165) 타겟_넘버
 */
public class L051_43165 {
    // answer(타겟 넘버를 만드는 방법의 수)
    static int answer = 0;

    // numbers(사용할 수 있는 숫자가 담긴 배열)
    // target(타겟 넘버)
    public int solution(int[] numbers, int target) {
        // dfs(numbers, 0, target, 0) 수행
        dfs(numbers, 0, target, 0);
        return answer;
    }

    // 깊이 우선 탐색
    private void dfs(int[] numbers, int depth, int target, int sum) {
        // 마지막 노드까지 수행했다면
        if (depth == numbers.length) {
            // 타겟 넘버과 합의 값이 같다면
            if (target == sum) {
                // answer 증가
                answer++;
            }
            return;
        }
        // 현재 노드의 값 더하기
        dfs(numbers, depth + 1, target, sum + numbers[depth]);
        // 현재 노드의 값 빼기
        dfs(numbers, depth + 1, target, sum - numbers[depth]);
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L051_43165 solution = new L051_43165();

        int[] numbers = { 1, 1, 1, 1, 1 };
        int target = 3;

        int result = solution.solution(numbers, target);

        System.out.println(result);
    }
}