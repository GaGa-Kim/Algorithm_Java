package Programmers.Kit.DFS;

/**
 * 43163) 단어_변환
 */
public class K003_43163 {
    static int answer;
    static boolean[] visited;

    // begin(시작 단어)
    // target(변환 단어)
    // words(단어의 집합)
    public int solution(String begin, String target, String[] words) {
        // answer(단계 최소 횟수)
        answer = 0;
        // visited(방문 유무 집합)
        visited = new boolean[words.length];
        // dfs(begin, target, words, 0)
        dfs(begin, target, words, 0);
        // answer 반환
        return answer;
    }

    private void dfs(String begin, String target, String[] words, int count) {
        // begin이 target과 동일하다면
        if (begin.equals(target)) {
            // 현재 횟수로 변경
            answer = count;
            return;
        }
        // 단어의 집합을 완전 탐색
        for (int i = 0; i < words.length; i++) {
            // 이미 방문한 단어라면
            if (visited[i] == true) {
                continue;
            }
            // same(현재 단어에서 같은 글자 갯수)
            int same = 0;
            // 현재 단어와 단어의 집합의 단어를 비교
            for (int j = 0; j < begin.length(); j++) {
                // 글자가 같다면
                if (begin.charAt(j) == words[i].charAt(j)) {
                    // same 증가
                    same++;
                }
            }
            // 한 글자를 제외하고 모두 동일하다면
            if (same == begin.length() - 1) {
                // 방문 유무 처리
                visited[i] = true;
                // 현재 단어로 변경
                dfs(words[i], target, words, count + 1);
                // 방문 유무 초기화
                visited[i] = false;
            }
        }
    }

    // 테스트 케이스
    public static void main(String[] args) {
        K003_43163 solution = new K003_43163();

        String begin = "hit";
        String target = "cog";
        String[] words = { "hot", "dot", "dog", "lot", "log", "cog" };

        int result = solution.solution(begin, target, words);

        System.out.println(result);
    }
}
