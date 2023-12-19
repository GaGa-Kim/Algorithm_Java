package Programmers.LV_1;

/**
 * 136798) 기사단원의_무기
 */
public class L064_136798 {
    // number(기사단원의 수)
    // limit(공격력의 제한수치)
    // power(제한수치를 초과한 기사가 사용할 무기의 공격력)
    public int solution(int number, int limit, int power) {
        // answer(필요한 철의 무게)
        int answer = 0;
        for (int i = 1; i <= number; i++) {
            // count(약수의 개수)
            int count = 0;
            // 1부터 number의 제곱근까지
            for (int j = 1; j <= Math.sqrt(i); j++) {
                if (j * j == i)
                    count += 1;
                else if (i % j == 0)
                    count += 2;
            }
            // 약수의 개수가 제한수치보다 크다면
            if (count > limit)
                answer += power;
            else
                answer += count;
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L064_136798 solution = new L064_136798();

        int number = 5;
        int limit = 3;
        int power = 2;

        int result = solution.solution(number, limit, power);

        System.out.println(result);
    }
}
