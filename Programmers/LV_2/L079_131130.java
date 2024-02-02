package Programmers.LV_2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 131130) 혼자_놀기의_달인
 */
public class L079_131130 {
    List<Integer> list;
    boolean[] visited;

    // cards(상자 안에 들어있는 카드 번호)
    public int solution(int[] cards) {
        // list(상자 그룹에 속한 상자의 수들을 담은 리스트)
        list = new ArrayList<>();
        // visited(방문 배열)
        visited = new boolean[cards.length];
        for (int i = 0; i < cards.length; i++) {
            // i에 방문하지 않았다면
            if (!visited[i])
                // dfs(cards, i, 0)
                dfs(cards, i, 0);
        }
        // list의 크기가 1이라면
        if (list.size() == 1)
            // 그대로 게임이 종료되므로 획득 점수 0 반환
            return 0;
        // list를 내림차순 정렬
        list.sort(Collections.reverseOrder());
        // 1번 상자 그룹에 속한 상자의 수와 2번 상자 그룹에 속한 상자의 수를 곱한 값을 반환
        return list.get(0) * list.get(1);
    }

    // dfs
    private void dfs(int[] cards, int index, int count) {
        // 이미 index에 방문했다면
        if (visited[index]) {
            // list에 count(상자의 수) 저장
            list.add(count);
            return;
        }
        // visited[i]를 true로 갱신
        visited[index] = true;
        // dfs(cards, cards[i] - 1, count + 1)
        dfs(cards, cards[index] - 1, count + 1);
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L079_131130 solution = new L079_131130();

        int[] cards = { 8, 6, 3, 7, 2, 5, 1, 4 };

        int result = solution.solution(cards);

        System.out.println(result);
    }
}
