package Programmers.LV_2;

/**
 * 1835) 단체사진_찍기
 */
public class L002_1835 {
    // friends(프렌즈들)
    static char[] friends = "ACFJMNRT".toCharArray();
    // answer(모든 조건을 만족하는 경우의 수)
    static int answer;

    // n(조건의 개수)
    // data(각 프렌즈가 원하는 조건)
    public int solution(int n, String[] data) {
        // answer 초기화
        answer = 0;
        // 모든 경로 탐색
        dfs("", data);
        // answer 반환
        return answer;
    }

    // dfs 탐색 함수
    private void dfs(String s, String[] data) {
        // s의 길이가 8보다 작다면
        if (s.length() < 8) {
            // 없는 프렌즈를 줄에 추가
            for (char c : friends) {
                if (s.indexOf(c) == -1)
                    dfs(s + c, data);
            }
        }
        // 모든 프렌즈들을 줄 세웠다면
        else {
            // 모든 조건에 맞는지 확인
            for (int i = 0; i < data.length; i++) {
                // friend1(조건을 제시한 프렌즈)
                char friend1 = data[i].charAt(0);
                // friend2(상대방 프렌즈)
                char friend2 = data[i].charAt(2);
                // distance(friend1과 friend2의 간격)
                int distance = Math.abs(s.indexOf(friend1) - s.indexOf(friend2)) - 1;
                // expression(조건식)
                char expression = data[i].charAt(3);
                // value(조건값)
                int value = data[i].charAt(4) - '0';
                // 조건 만족을 하지 않는다면
                if (!isCorrect(expression, distance, value))
                    return;
            }
            // 조건 만족을 한다면 answer 증가
            answer++;
        }
    }

    // 조건 만족 함수
    private boolean isCorrect(char expression, int distance, int value) {
        // expression가 '='라면
        if (expression == '=')
            return distance == value;
        // expression가 '>'라면
        else if (expression == '>')
            return distance > value;
        // expression가 '<'라면
        else
            return distance < value;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L002_1835 solution = new L002_1835();

        int n = 2;
        String[] data = { "N~F=0", "R~T>2" };

        int result = solution.solution(n, data);

        System.out.println(result);
    }
}
