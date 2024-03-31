package Programmers.LV_3;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 64064) 불량_사용자
 */
public class L038_64064 {
    Set<String> set;
    boolean[] visited;

    // user_id(이벤트 응모자 아이디 목록이 담긴 배열)
    // banned_id(불량 사용자 아이디 목록이 담긴 배열)
    public int solution(String[] user_id, String[] banned_id) {
        // answer(당첨에서 제외되어야 할 제재 아이디 목록의 경우의 수)
        int answer = 0;
        // 정규 표현식을 위해 *를 .로 변경
        for (int i = 0; i < banned_id.length; i++) {
            banned_id[i] = banned_id[i].replace('*', '.');
        }
        // set(제재 아이디에 대한 경우의 수를 담은 HashSet)
        set = new HashSet<>();
        // visited(user_id 방문 배열)
        visited = new boolean[user_id.length];
        dfs(0, "", user_id, banned_id);
        // set의 크기로 갱신
        answer = set.size();
        // answer 반환
        return answer;
    }

    // 불량 아이디에 따른 제재 아이디 경우의 수 완전 탐색
    private void dfs(int depth, String result, String[] user_id, String[] banned_id) {
        // depth가 banned_id의 길이와 같다면
        if (depth == banned_id.length) {
            // 제재 아이디의 경우의 수가 완성되었으므로 집합에서 중복을 제거하기 위해 result를 String[] 배열로 변환한 후 정렬
            String[] permuted = result.split(" ");
            Arrays.sort(permuted);
            // permuted을 문자열로 붙여 set에 저장
            StringBuilder str = new StringBuilder();
            for (String s : permuted) {
                str.append(s);
            }
            set.add(str.toString());
            return;
        }
        for (int i = 0; i < user_id.length; i++) {
            // i를 방문하지 않았으면서 불량 아이디라면
            if (!visited[i] && user_id[i].matches(banned_id[depth])) {
                // visited[i]를 true로 갱신
                visited[i] = true;
                // result에 user_id[i]를 붙여 계속 탐색
                dfs(depth + 1, user_id[i] + " " + result, user_id, banned_id);
                // visited[i]를 false로 초기화
                visited[i] = false;
            }
        }
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L038_64064 solution = new L038_64064();

        String[] user_id = { "frodo", "fradi", "crodo", "abc123", "frodoc" };
        String[] banned_id = { "fr*d*", "*rodo", "******", "******" };

        int result = solution.solution(user_id, banned_id);

        System.out.println(result);
    }
}
