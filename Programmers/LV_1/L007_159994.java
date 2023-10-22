package Programmers.LV_1;

/**
 * 159994) 카드_뭉치
 */
public class L007_159994 {
    // cards1(문자열로 이루어진 배열1)
    // cards2(문자열로 이루어진 배열2)
    // goal(원하는 단어 배열)
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        // idx1, idx2(cards1과 cards2의 인덱스)
        int idx1 = 0;
        int idx2 = 0;
        String answer = "";
        for (String word : goal) {
            // goal의 단어가 cards1에 존재하는지
            if (idx1 < cards1.length && cards1[idx1].equals(word)) {
                idx1++;
            }
            // goal의 단어가 cards2에 존재하는지
            else if (idx2 < cards2.length && cards2[idx2].equals(word)) {
                idx2++;
            }
            // goal의 단어가 존재하지 않을 때
            else {
                // goal을 만들 수 없는 경우 No 리턴
                answer = "No";
                break;
            }
            // goal을 만들 수 있다면 Yes 리턴
            answer = "Yes";
        }
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L007_159994 solution = new L007_159994();

        String[] cards1 = { "i", "drink", "water" };
        String[] cards2 = { "want", "to" };
        String[] goal = { "i", "want", "to", "drink", "water" };

        String result = solution.solution(cards1, cards2, goal);

        System.out.println(result);
    }
}
