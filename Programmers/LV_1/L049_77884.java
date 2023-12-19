package Programmers.LV_1;

/**
 * 77884) 약수의_개수와_덧셈
 */
public class L049_77884 {
    // left, right(두 정수)
    public int solution(int left, int right) {
        // answer(수의 개수가 짝수인 수는 더하고, 약수의 개수가 홀수인 수는 뺀 수)
        int answer = 0;
        for (int i = left; i <= right; i++) {
            // count(약수의 개수)
            int count = 0;
            // 약수의 개수 구하기
            for (int j = 1; j <= Math.sqrt(i); j++) {
                if (j * j == i)
                    count += 1;
                else if (i % j == 0)
                    count += 2;
            }
            // 약수의 개수가 짝수라면
            if (count % 2 == 0)
                answer += i;
            // 약수의 개수가 홀수라면
            else
                answer -= i;
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L049_77884 solution = new L049_77884();

        int left = 13;
        int right = 17;

        int result = solution.solution(left, right);

        System.out.println(result);
    }
}
