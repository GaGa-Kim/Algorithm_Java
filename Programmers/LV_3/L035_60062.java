package Programmers.LV_3;

/**
 * 60062) 외벽_점검
 */
public class L035_60062 {
    int answer;
    int[] weak_append;

    // n(외벽의 길이)
    // weak(취약 지점의 위치가 담긴 배열)
    // dist(각 친구가 1시간 동안 이동할 수 있는 거리가 담긴 배열)
    public int solution(int n, int[] weak, int[] dist) {
        // answer(취약 지점을 점검하기 위해 보내야 하는 친구 수의 최소값)
        answer = Integer.MAX_VALUE;
        // weak_append(원형인 취약 지점을 일자 형태로 만들어준 배열)
        weak_append = new int[weak.length * 2];
        // 원형인 취약 지점을 펼쳐서 일자 형태로 저장
        for (int i = 0; i < weak.length; i++) {
            weak_append[i] = weak[i];
            weak_append[i + weak.length] = weak[i] + n;
        }
        // 취약점의 처음 위치부터 시작하여 마지막 위치에서 시작할 때까지에 대한 친구들 경우의 수에 따라 점검
        for (int i = 0; i < weak.length; i++) {
            // visited(dist 방문 배열)
            boolean[] visited = new boolean[dist.length];
            // permuted(dist에 따른 배치 경우의 수 배열)
            int[] permuted = new int[dist.length];
            dfs(i, 0, dist, visited, permuted);
        }
        // answer이 초기값과 동일하다면
        if (answer == Integer.MAX_VALUE) {
            // 어떤 경우의 수로도 취약 지점을 전부 점검할 수 없으므로 -1 반환
            return -1;
        }
        // answer 반환
        return answer;
    }

    // 친구들의 경우의 수에 따른 취약점 점검 완전 탐색
    private void dfs(int start, int depth, int[] dist, boolean[] visited, int[] permuted) {
        // depth가 dist의 길이와 같다면
        if (depth == dist.length) {
            // 각 친구들이 취약 지점을 점검할 수 있는 배치 경우의 수가 완성되었으므로
            // 취약 지점을 모두 점검하기 위한 친구의 수를 계산해 answer을 갱신
            answer = Math.min(answer, countCheckNumber(start, start + weak_append.length / 2, permuted));
            return;
        }
        for (int i = 0; i < dist.length; i++) {
            // i를 이미 방문했다면
            if (visited[i]) {
                // 다시 사용할 수 없는 친구이므로 넘어감
                continue;
            }
            // 사용한 친구에 대해 true로 갱신
            visited[i] = true;
            // 경우의 수에 사용한 친구를 저장
            permuted[depth] = dist[i];
            // 나머지 경우의 수를 탐색
            dfs(start, depth + 1, dist, visited, permuted);
            // 사용한 친구에 대해 false로 초기화
            visited[i] = false;
        }
    }

    // 취약점 점검을 위한 친구들의 수 계산
    private int countCheckNumber(int start, int end, int[] permuted) {
        // count(취약 지점을 점검하기 위해 보내야 하는 친구 수)
        int count = 1;
        // position(취약점의 첫 위치에서 시작하여 첫 번째 친구가 이동한 위치)
        int position = weak_append[start] + permuted[count - 1];
        // 취약점의 처음 위치부터 마지막 위치까지
        for (int i = start; i < end; i++) {
            // position이 weak_append[i]보다 작다면
            if (position < weak_append[i]) {
                // 그 사이의 위치를 점검하지 못하므로 count 증가
                count++;
                // count가 permuted의 길이보다 클 경우
                if (count > permuted.length) {
                    // 친구들을 모두 투입해도 취약 지점을 전부 점검할 수 없으므로 초기값 반환
                    return Integer.MAX_VALUE;
                }
                // position을 취약점에서 시작하여 다른 친구가 이동한 위치로 갱신
                position = weak_append[i] + permuted[count - 1];
            }
        }
        // 취약 지점을 전부 점검할 수 있으므로 count 반환
        return count;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L035_60062 solution = new L035_60062();

        int n = 12;
        int[] weak = { 1, 5, 6, 10 };
        int[] dist = { 1, 2, 3, 4 };

        int result = solution.solution(n, weak, dist);

        System.out.println(result);
    }
}
