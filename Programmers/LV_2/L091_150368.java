package Programmers.LV_2;

import java.util.Arrays;

/**
 * 150368) 이모티콘_할인행사
 */
public class L091_150368 {
    int[] answer;

    // users(카카오톡 사용자의 구매 기준)
    // emoticons(이모티콘의 정가)
    public int[] solution(int[][] users, int[] emoticons) {
        // answer(이모티콘 플러스 서비스 가입 수와 이모티콘 매출액)
        answer = new int[2];
        // discount_arr(할인률에 따른 이모티콘의 가격)
        int[] discount_arr = new int[emoticons.length];
        // 이모티콘 할인 경우의 수 구하기
        combi(discount_arr, users, emoticons, 0);
        // answer 반환
        return answer;
    }

    // 이모티콘 할인 경우의 수 구하기
    private void combi(int[] discount_arr, int[][] users, int[] emoticons, int depth) {
        // depth와 discount_arr의 길이와 같다면
        if (depth == discount_arr.length) {
            // 모든 이모티콘에 할인률을 적용했으므로 이모티콘 플러스 서비스 가입 수와 이모티콘 매출액 계산하기
            calculate(discount_arr, users, emoticons);
            return;
        }
        // 10%부터 40%까지, 할인율이 10%씩 증가
        for (int i = 10; i <= 40; i += 10) {
            // discount_arr[depth]를 i 할인율로 갱신
            discount_arr[depth] = i;
            // 이모티콘 할인 경우의 수 구하기
            combi(discount_arr, users, emoticons, depth + 1);
        }
    }

    // 이모티콘 플러스 서비스 가입 수와 이모티콘 매출액 계산하기
    private void calculate(int[] discount_arr, int[][] users, int[] emoticons) {
        // subscribers(이모티콘 플러스 서비스 가입 수)
        int subscribers = 0;
        // sales(이모티콘 매출액)
        int salses = 0;
        // 사용자마다 이모티콘 구매 가격 계산하기
        for (int[] user : users) {
            // price(한 사용자 당 이모티콘 구매 가격)
            int price = 0;
            for (int i = 0; i < discount_arr.length; i++) {
                // discount_arr이 user[0]보다 크다면
                if (discount_arr[i] >= user[0])
                    // 사용자의 할인 기준보다 크므로 price에 할인된 가격 추가
                    price += (emoticons[i] / 100) * (100 - discount_arr[i]);
            }
            // price가 user[1]보다 크다면
            if (price >= user[1])
                // 이모티콘 구매를 모두 취소하고 이모티콘 플러스 서비스에 가입하므로 subscribers 1 증가
                subscribers++;
            else
                // 이모티콘을 구매하므로 sales를 price만큼 증가
                salses += price;
        }
        // subscribers이 answer[0]보다 크다면
        if (subscribers > answer[0]) {
            // 1번 목표를 만족하므로 answer[0], answer[1]을 subscribers, sales로 갱신
            answer[0] = subscribers;
            answer[1] = salses;
        }
        // subscribers이 answer[0]과 같다면
        else if (subscribers == answer[0]) {
            // 2번 목표를 만족하므로 answer[1]을 answer[1]과 sales 중 더 큰 값으로 갱신
            answer[1] = Math.max(answer[1], salses);
        }
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L091_150368 solution = new L091_150368();

        int[][] users = { { 40, 10000 }, { 25, 10000 } };
        int[] emoticons = { 7000, 9000 };

        int[] result = solution.solution(users, emoticons);

        System.out.println(Arrays.toString(result));
        ;
    }
}
