package Programmers.LV_1;

/**
 * 134240) 푸드_파이트_대회
 */
public class L016_134240 {
    // food(준비한 음식의 양을 칼로리가 적은 순서대로 나타내는 정수 배열)
    public String solution(int[] food) {
        // answer(대회를 위한 음식의 배치를 나타내는 문자열)
        String answer = "";
        // sb(음식 배치를 저장할 문자열 빌더)
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < food.length; i++) {
            // count(한 선수가 먹을 수 있는 음식의 개수)o
            int count = 0;
            // food[i]가 짝수라면
            if (food[i] % 2 == 0) {
                count = food[i] / 2;
            } else {
                count = (food[i] - 1) / 2;
            }
            // sb.append(i번째 음식을 먹을 수 있는 개수만큼 반복하여 저장)
            sb.append(String.valueOf(i).repeat(count));
        }
        // 갯수에 따라 한 선수가 먹을 수 있는 음식 배치와 물을 저장
        answer = sb + "0";
        // sb를 뒤집어서 다른 한 선수가 먹을 수 있는 음식 배치를 저장
        answer += sb.reverse();
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L016_134240 solution = new L016_134240();

        int[] food = { 1, 7, 1, 2 };

        String result = solution.solution(food);

        System.out.println(result);
    }
}
