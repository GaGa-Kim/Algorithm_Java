package Programmers.LV_1;

/**
 * 132267) 콜라_문제
 */
public class L059_132267 {
    // a(콜라를 받기 위해 마트에 주어야 하는 병 수)
    // b(빈 병 a개를 가져다 주면 마트가 주는 콜라의 병 수)
    // n(상빈이가 가지고 있는 빈 병의 개수)
    public int solution(int a, int b, int n) {
        // answer(상빈이가 받을 수 있는 콜라의 병 수)
        int answer = 0;
        // bonus(한 번 빈 병을 가져다 주고 받는 콜라의 병 수)
        int bonus = 0;
        // 가지고 있는 병의 갯수가 마트에 주어야 하는 병의 개수보다 많을 동안
        while (n >= a) {
            // 나누어지는 만큼(n / a * b) 콜라를 받음
            bonus = n / a * b;
            // bonus만큼 증가
            answer += bonus;
            // 나머지(n % a)만큼은 마트에 가지고 가지 않고 가지고 있도록 함
            n = bonus + (n % a);
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L059_132267 solution = new L059_132267();

        int a = 3;
        int b = 1;
        int n = 20;

        int result = solution.solution(a, b, n);

        System.out.println(result);
    }
}
