package Programmers.LV_1;

/**
 * 82612) 부족한_금액_계산하기
 */
public class L051_82612 {
    // price(놀이기구의 이용료)
    // money(처음 가지고 있던 금액)
    // count(놀이기구의 이용 횟수)
    public long solution(int price, int money, int count) {
        // answer(현재 자신이 가지고 있는 금액에서 얼마가 모자라는지) = money
        long answer = money;
        // count만큼 이용료 계산
        for (long i = 1; i <= count; i++) {
            answer -= price * i;
        }
        // 금액이 부족하다면
        if (answer < 0)
            return -answer;
        // 금액이 부족하지 않다면
        else
            return 0;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L051_82612 solution = new L051_82612();

        int price = 3;
        int money = 20;
        int count = 4;

        long result = solution.solution(price, money, count);

        System.out.println(result);
    }
}
