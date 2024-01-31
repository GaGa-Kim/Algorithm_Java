package Programmers.LV_2;

/**
 * 92335) K진수에서_소수_개수_구하기
 */
public class L074_92335 {
    // n(양의 정수)
    // k(진수)
    public int solution(int n, int k) {
        // answer(n을 k진수로 바꿨을 때, 변환된 수 안에서 찾을 수 있는 조건에 맞는 소수의 개수)
        int answer = 0;
        // N(k진수로 변환한 n)
        String N = Integer.toString(n, k);
        // arr(N을 0을 기준으로 나눈 문자열 배열)
        String[] arr = N.split("0");
        for (String s : arr) {
            // s가 공백이라면
            if (s.equals(""))
                continue;
            // 소수 확인하기 함수(s)
            if (isPrime(s))
                // answer 증가
                answer++;
        }
        // answer 반환
        return answer;
    }

    // 소수 확인하기 함수
    private boolean isPrime(String s) {
        // origS(십진수로 변환한 s)
        Long origS = Long.parseLong(s, 10);
        // origS가 1보다 작다면
        if (origS <= 1)
            // 소수가 아니므로 false 반환
            return false;
        for (int i = 2; i <= Math.sqrt(origS); i++) {
            // origS가 i로 나누어진다면
            if (origS % i == 0)
                // 약수가 있어 소수가 아니므로 false 반환
                return false;
        }
        // true 반환
        return true;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L074_92335 solution = new L074_92335();

        int n = 437674;
        int k = 3;

        int result = solution.solution(n, k);

        System.out.println(result);
    }
}
