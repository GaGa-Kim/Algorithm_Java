package Programmers.LV_2;

/**
 * 49994) 방문_길이
 */
public class L053_49994 {
    // answer(게임 캐릭터가 처음 걸어본 길의 길이)
    int answer = 0;
    // visited(방문 유무 배열)
    boolean[][][] visited = new boolean[11][11][4];
    // dx, dy(상하좌우 좌표)
    int[] dx = { -1, 0, 1, 0 };
    int[] dy = { 0, -1, 0, 1 };
    // x, y(게임 캐릭터의 위치)
    int x = 5, y = 5;

    // dirs(명령어)
    public int solution(String dirs) {
        for (int i = 0; i < dirs.length(); i++) {
            // c(dirs의 i번째 명령어)
            char c = dirs.charAt(i);
            // c가 U라면
            if (c == 'U')
                // 이동하기 함수(0, 2)
                move(0, 2);
            // c가 L이라면
            if (c == 'L')
                // 이동하기 함수(1, 3)
                move(1, 3);
            // c가 D라면
            if (c == 'D')
                // 이동하기 함수(2, 0)
                move(2, 0);
            // c가 R이라면
            if (c == 'R')
                // 이동하기 함수(3, 1)
                move(3, 1);
        }
        // answer 반환
        return answer;
    }

    // 이동하기 함수(x, y, 이동 방향, 이동 방향의 반대 방향)
    private void move(int dir, int odir) {
        // nx, ny(게임 캐릭터의 이동 위치)
        int nx = x + dx[dir];
        int ny = y + dy[dir];
        // nx와 ny가 좌표를 벗어나지 않으면서
        if (nx >= 0 && nx < 11 && ny >= 0 && ny < 11) {
            // nx와 ny를 방문하지 않았다면
            if (!visited[nx][ny][dir]) {
                // visited[x][y][이동 방향의 반대 방향]을 true로 갱신
                visited[x][y][odir] = true;
                // visited[nx][ny][이동방향]을 true로 갱신
                visited[nx][ny][dir] = true;
                // answer 증가
                answer++;
            }
            // x, y를 nx, ny로 갱신
            x = nx;
            y = ny;
        }
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L053_49994 solution = new L053_49994();

        String dirs = "ULURRDLLU";

        int result = solution.solution(dirs);

        System.out.println(result);
    }
}
