package Programmers.LV_2;

import java.util.HashMap;
import java.util.Map;

/**
 * 84512) 모음_사전
 */
public class L068_84512 {
    // words(알파벳 모음들)
    static String[] words = { "A", "E", "I", "O", "U" };
    static Map<String, Integer> map;
    static int index;

    // word(단어)
    public int solution(String word) {
        // index(단어 순서 인덱스)
        index = 0;
        // map(가능한 모든 단어들을 저장하는 hashMap)
        map = new HashMap<>();
        // dfs("", 0)
        dfs("", 0);
        // answer(사전에서의 단어 순번)
        int answer = map.get(word);
        // answer 반환
        return answer;
    }

    // DFS를 수행하며 가능한 모든 단어 저장하기
    private void dfs(String str, int length) {
        // hashMap.put(단어, index)
        map.put(str, index);
        // index 증가
        index++;
        // 단어의 길이가 5라면
        if (length == 5) {
            return;
        }
        for (int i = 0; i < 5; i++) {
            // dfs(현재 단어 + words[i], 현재 단어의 길이 + 1)
            dfs(str + words[i], length + 1);
        }
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L068_84512 solution = new L068_84512();

        String word = "AAAAE";
        int result = solution.solution(word);

        System.out.println(result);
    }
}