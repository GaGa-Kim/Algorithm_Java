package Programmers.LV_1;

/**
 * 133499) 옹알이_(2)
 */
public class L018_133499 {
    // babbling(문자열 배열)
    public int solution(String[] babbling) {
        // answer(발음할 수 있는 단어의 개수)
        int answer = 0;
        for (int i = 0; i < babbling.length; i++) {
            // 연속 발음이라면
            if (babbling[i].contains("ayaaya") ||
                    babbling[i].contains("yeye") ||
                    babbling[i].contains("woowoo") ||
                    babbling[i].contains("mama"))
                // 발음이 불가능하므로 통과
                continue;
            // 단어 안에 aya, ye, woo, ma가 있을 경우 " "로 치환
            babbling[i] = babbling[i].replace("aya", " ");
            babbling[i] = babbling[i].replace("ye", " ");
            babbling[i] = babbling[i].replace("woo", " ");
            babbling[i] = babbling[i].replace("ma", " ");
            // 단어 안에 " "가 있을 경우 ""로 치환
            babbling[i] = babbling[i].replaceAll(" ", "");
            // 단어의 길이가 0이라면
            if (babbling[i].length() == 0)
                // answer 증가
                answer++;
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L018_133499 solution = new L018_133499();

        String[] babbling = { "aya", "yee", "u", "maa" };

        int result = solution.solution(babbling);

        System.out.println(result);
    }
}