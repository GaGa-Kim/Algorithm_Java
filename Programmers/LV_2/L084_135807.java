package Programmers.LV_2;

/**
 * 135807) 숫자_카드_나누기
 */
public class L084_135807 {
    // arrayA(철수가 가진 카드에 적힌 숫자들)
    // arrayB(영희가 가진 카드에 적힌 숫자들)
    public int solution(int[] arrayA, int[] arrayB) {
        // answer(주어진 조건을 만족하는 가장 큰 양의 정수)
        int answer = 0;
        // gcdA(arrayA의 최대공약수) 구하기
        int gcdA = arrayA[0];
        // gcdB(arrayB의 최대공약수) 구하기
        int gcdB = arrayB[0];
        for (int i = 1; i < arrayA.length; i++) {
            gcdA = gcd(gcdA, arrayA[i]);
            gcdB = gcd(gcdB, arrayB[i]);
        }
        // arrayB 카드들에 적힌 모든 숫자를 gcdA로 나눌 수 없다면 조건을 만족하므로
        if (isNotDivisible(arrayB, gcdA))
            // answer을 갱신
            answer = Math.max(answer, gcdA);
        // arrayA 카드들에 적힌 모든 숫자를 gcdB로 나눌 수 없다면 조건을 만족하므로
        if (isNotDivisible(arrayA, gcdB))
            // answer을 갱신
            answer = Math.max(answer, gcdB);
        // answer 반환
        return answer;
    }

    // 최대 공약수 함수
    private int gcd(int a, int b) {
        // b가 0이라면
        if (b == 0)
            // a가 최대 공약수
            return a;
        else
            // 최대 공약수 함수(작은 수, 큰 수 % 작은 수)
            return gcd(b, a % b);
    }

    // 카드들에 적힌 모든 숫자를 나눌 수 없는지 함수
    private boolean isNotDivisible(int[] array, int gcd) {
        for (int a : array)
            // a를 gcd로 나눈 나머지가 0이라면
            if (a % gcd == 0)
                // false 반환
                return false;
        // true 반환
        return true;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L084_135807 solution = new L084_135807();

        int[] arrayA = { 10, 20 };
        int[] arrayB = { 5, 17 };

        int result = solution.solution(arrayA, arrayB);

        System.out.println(result);
    }
}
