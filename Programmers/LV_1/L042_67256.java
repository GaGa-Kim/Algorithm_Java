package Programmers.LV_1;

/**
 * 67256) 키패드_누르기
 */
public class L042_67256 {
    // numbers(순서대로 누를 번호가 담긴 배열)
    // hand(왼손잡이인지 오른손잡이인 지를 나타내는 문자열)
    public String solution(int[] numbers, String hand) {
        // answer(각 번호를 누른 엄지손가락이 왼손인 지 오른손인 지를 나타내는 StringBuilder)
        StringBuilder answer = new StringBuilder();
        // phone(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, *, # 순서로 폰 번호에 따른 x, y 좌표를 저장한 2차원 배열)
        int[][] phone = { { 3, 1 }, { 0, 0 }, { 0, 1 },
                { 0, 2 }, { 1, 0 }, { 1, 1 },
                { 1, 2 }, { 2, 0 }, { 2, 1 },
                { 2, 2 }, { 3, 0 }, { 3, 2 } };
        // lIndex(왼손 엄지손가락 인덱스) - *(phone[10])
        int[] lIndex = phone[10];
        // rIndex(오른손 엄지손가락 인덱스) - #(phone[11])
        int[] rIndex = phone[11];
        for (int number : numbers) {
            // number이 1, 4, 7이라면
            if (number == 1 || number == 4 || number == 7) {
                // answer에 L 추가
                answer.append("L");
                // lIndex를 phone[number]로 변경
                lIndex = phone[number];
            }
            // number이 3, 6, 9라면
            else if (number == 3 || number == 6 || number == 9) {
                // answer에 R 추가
                answer.append("R");
                // rIndex를 phone[number]로 변경
                rIndex = phone[number];
            } else {
                // lDistance(왼손과의 거리)
                int lDistance = Math.abs(phone[number][0] - lIndex[0]) + Math.abs(phone[number][1] - lIndex[1]);
                // rDistance(오른손과의 거리)
                int rDistance = Math.abs(phone[number][0] - rIndex[0]) + Math.abs(phone[number][1] - rIndex[1]);
                // 왼손과의 거리가 더 멀다면 오른손 사용
                if (lDistance > rDistance) {
                    // answer에 R 추가
                    answer.append("R");
                    // rIndex를 phone[number]로 변경
                    rIndex = phone[number];
                }
                // 오른손과의 거리가 더 멀다면 왼손 사용
                else if (rDistance > lDistance) {
                    // answer에 L 추가
                    answer.append("L");
                    // lIndex를 phone[number]로 변경
                    lIndex = phone[number];
                }
                // 두 손의 거리가 같다면
                else {
                    // 오른손잡이라면
                    if (hand.equals("right")) {
                        // answer에 R 추가
                        answer.append("R");
                        // rIndex를 phone[number]로 변경
                        rIndex = phone[number];
                    }
                    // 왼손잡이라면
                    else {
                        // answer에 L 추가
                        answer.append("L");
                        // lIndex를 phone[number]로 변경
                        lIndex = phone[number];
                    }
                }
            }
        }
        // answer을 문자열로 반환
        return answer.toString();
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L042_67256 solution = new L042_67256();

        int[] numbers = { 1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5 };
        String hand = "right";

        String result = solution.solution(numbers, hand);

        System.out.println(result);
    }
}
