package Programmers.LV_2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 12981) 영어_끝말잇기
 */
public class L026_12981 {
    // n(사람의 수)
    // words(사람들이 순서대로 말한 단어)
    public int[] solution(int n, String[] words) {
        // answer(가장 먼저 탈락하는 사람의 번호와 탈락한 차례)
        int[] answer = new int[2];
        // set(단어 집합)
        Set<String> set = new HashSet<>();
        for (int i = 0; i < words.length; i++) {
            // set이 비어있다면
            if (set.isEmpty()) {
                // set에 words[i] 저장
                set.add(words[i]);
            } else {
                // front(앞의 단어)
                String front = words[i - 1];
                // set에 words[i]가 포함되어 있지 않으면서 front의 끝 알파벳과 words[i]의 첫 알파벳이 같다면
                if (!set.contains(words[i]) && front.charAt(front.length() - 1) == words[i].charAt(0)) {
                    // set에 words[i] 저장
                    set.add(words[i]);
                }
                // 이전에 등장했던 단어를 사용했거나, 끝말잇기가 이어지지 않을 경우
                else {
                    // 탈락이므로 answer[0]에 탈락한 사람의 번호, answer[1]에 탈락한 차례 저장
                    answer[0] = i % n + 1;
                    answer[1] = i / n + 1;
                    break;
                }
            }
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L026_12981 solution = new L026_12981();

        int n = 3;
        String[] words = { "tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank" };

        int[] result = solution.solution(n, words);

        System.out.println(Arrays.toString(result));
    }
}
