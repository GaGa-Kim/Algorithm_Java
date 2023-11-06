package Programmers.Kit.Greedy;

/**
 * 42860) 조이스틱
 */
public class K002_42860 {
    // name(만들고자 하는 이름)
    public int solution(String name) {
        // converse(알파벳 변환 횟수)
        int converse = 0;
        // move(알파벳 이동 횟수)
        int move = name.length() - 1; // 단순 오른쪽 이동 횟수
        for (int i = 0; i < name.length(); i++) {
            // 다음 알파벳으로 이동하는 것을 반복했을 때의 횟수, 이전 알파벳으로 이동하는 것을 반복했을 때의 횟수 비교
            converse += Math.min(name.charAt(i) - 'A', 26 - (name.charAt(i) - 'A'));
            // 현재 알파벳의 다음 알파벳이 A일 경우
            if (i < name.length() - 1 && name.charAt(i + 1) == 'A') {
                // endA(마지막 A의 위치 인덱스)
                int endA = i + 1;
                // 다른 알파벳이 나오기 전의 마지막 A를 찾을 때까지
                while (endA < name.length() && name.charAt(endA) == 'A') {
                    // endA 증가
                    endA++;
                }
                // A를 넘어 다음 알파벳으로 단순 오른쪽 이동 횟수, 오른쪽으로 갔다가 A를 만난 후 다시 돌아와서 왼쪽으로 이동할 경우 비교
                move = Math.min(move, i * 2 + (name.length() - endA));
                // A를 넘어 다음 알파벳으로 단순 오른쪽 이동 횟수, 왼쪽으로 갔다가 A를 만난 후 다시 돌아와서 오른쪽으로 이동할 경우 비교
                move = Math.min(move, i + (name.length() - endA) * 2);
            }
        }
        // 알파벳 변환 횟수와 알파벳 이동 횟수 합산 반환
        return converse + move;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        K002_42860 solution = new K002_42860();

        String name = "JEROEN";

        int result = solution.solution(name);

        System.out.println(result);
    }
}
