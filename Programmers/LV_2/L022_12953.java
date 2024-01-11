package Programmers.LV_2;

/**
 * 12953) N개의_최소공배수
 */
public class L022_12953 {
    // arr(n개의 숫자를 담은 배열)
    public int solution(int[] arr) {
        // answer(arr의 최소공배수) = arr[0]
        int answer = arr[0];
        // 배열의 두 번째부터 탐색하며 answer과의 최소공배수로 answer을 갱신
        for (int i = 1; i < arr.length; i++) {
            // answer = answer * arr[i] / 최대 공약수 함수(answer, arr[i])
            answer = answer * arr[i] / gcd(answer, arr[i]);
        }
        // answer 반환
        return answer;
    }

    // 최대 공약수 함수
    private int gcd(int n, int m) {
        // a(n과 m 중 큰 수)
        int a = Math.max(n, m);
        // b(n과 m 중 작은 수)
        int b = Math.min(n, m);
        // b가 0이라면
        if (b == 0)
            // a가 최대 공약수
            return a;
        else
            // 최대 공약수 함수(작은 수, 큰 수 % 작은 수)
            return gcd(b, a % b);
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L022_12953 solution = new L022_12953();

        int[] arr = { 2, 6, 8, 14 };

        int result = solution.solution(arr);

        System.out.println(result);
    }
}
