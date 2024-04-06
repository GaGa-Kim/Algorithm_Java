package BAEKJOON.B001_13460;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 13460) 구슬_탈출_2
 */
public class Main {
    // Marbles(빨간 구슬과 파란 구슬 정보 그리고 이동 횟수를 담는 클래스)
    static class Marbles {
        Marble redMarble, blueMarble; // redMarble, blueMarble(빨간 구슬과 파란 구슬 정보)
        int count; // count (이동 횟수)

        public Marbles(Marble redMarble, Marble blueMarble, int count) {
            this.redMarble = redMarble;
            this.blueMarble = blueMarble;
            this.count = count;
        }
    }

    // Marble(각 구슬 정보를 담는 클래스)
    static class Marble {
        int x, y; // x, y(구슬의 x, y 좌표)

        public Marble(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N;
    static int M;
    static char[][] board;
    static boolean[][][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // N(보드의 세로 크기)
        N = Integer.parseInt(st.nextToken());
        // M(보드의 가로 크기)
        M = Integer.parseInt(st.nextToken());
        // board(보드 문자 배열)
        board = new char[N][M];
        // visited(방문 유무 배열)
        visited = new boolean[N][M][N][M];
        // N개의 줄의 보드 모양을 나타내는 길이 M 문자열을 board에 저장하기
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }
        // startMarbles(빨간 구슬과 파란 구슬 정보 그리고 이동 횟수 정보를 담은 클래스)
        Marbles startMarbles = findStartMarbles();
        // bfs 탐색을 통해 빨간 구슬만 빼낼 수 있는지 탐색
        System.out.println(bfs(startMarbles));
        br.close();
    }

    // 시작 위치의 구슬 정보 찾기
    private static Marbles findStartMarbles() {
        // startRedMarble(시작 위치의 빨간 구술)
        Marble startRedMarble = new Marble(0, 0);
        // startBlueMarble(시작 위치의 파란 구술)
        Marble startBlueMarble = new Marble(0, 0);
        // 빨간 구슬과 파란 구슬의 시작 위치 정보를 찾기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 'R') {
                    startRedMarble.x = i;
                    startRedMarble.y = j;
                    board[i][j] = '.';
                }
                if (board[i][j] == 'B') {
                    startBlueMarble.x = i;
                    startBlueMarble.y = j;
                    board[i][j] = '.';
                }
            }
        }
        // 찾은 빨간 구슬과 파란 구슬의 시작 정보를 가지고 Marbles 생성
        return new Marbles(startRedMarble, startBlueMarble, 0);
    }

    // bfs 탐색
    static int[] dx = { 0, 0, -1, 1 };
    static int[] dy = { -1, 1, 0, 0 };

    private static int bfs(Marbles startMarbles) {
        // 큐 자료구조에 현재 구슬 방문 기록
        Queue<Marbles> queue = new LinkedList<>();
        queue.add(startMarbles);
        visited[startMarbles.redMarble.x][startMarbles.redMarble.y][startMarbles.blueMarble.x][startMarbles.blueMarble.y] = true;
        // 큐가 빌 때까지
        while (!queue.isEmpty()) {
            // 큐에서 구슬 데이터 가져오기
            Marbles now = queue.poll();
            // 이동 횟수가 10번 이상이라면
            if (now.count >= 10) {
                // 10번 이하로 움직여서 빨간 구슬을 구멍을 통해 빼낼 수 없으므로 더 이상 탐색하지 않음
                break;
            }
            // 왼쪽, 오른쪽, 위쪽, 아래쪽 방향으로 기울이기
            for (int direction = 0; direction < 4; direction++) {
                // nextRedMarble(이동한 위치의 빨간 구슬 정보)
                Marble nextRedMarble = new Marble(now.redMarble.x, now.redMarble.y);
                // isRedHole(빨간 구슬을 구멍을 통해 빼낼 수 있는지 여부)
                boolean isRedHole = false;
                // 빨간 구슬이 벽에 닿지 않을 동안 이동
                while (board[nextRedMarble.x + dx[direction]][nextRedMarble.y + dy[direction]] != '#') {
                    nextRedMarble.x += dx[direction];
                    nextRedMarble.y += dy[direction];
                    // 빨간 구슬이 구멍에 도착한다면
                    if (board[nextRedMarble.x][nextRedMarble.y] == 'O') {
                        // isRedHole 갱신
                        isRedHole = true;
                        // 종료
                        break;
                    }
                }
                // nextBlueMarble(이동한 위치의 파란 구슬 정보)
                Marble nextBlueMarble = new Marble(now.blueMarble.x, now.blueMarble.y);
                // isBlueHole(파란 구슬을 구멍을 통해 빼낼 수 있는지 여부)
                boolean isBlueHole = false;
                // 파란 구슬이 벽에 닿지 않을 동안 이동
                while (board[nextBlueMarble.x + dx[direction]][nextBlueMarble.y + dy[direction]] != '#') {
                    nextBlueMarble.x += dx[direction];
                    nextBlueMarble.y += dy[direction];
                    // 파란 구슬이 구멍에 도착한다면
                    if (board[nextBlueMarble.x][nextBlueMarble.y] == 'O') {
                        // isBlueHole 갱신
                        isBlueHole = true;
                        // 종료
                        break;
                    }
                }
                // 파란 구슬을 구멍을 통해 빼낼 수 있다면
                if (isBlueHole) {
                    // 이 방향으로는 이동해서는 안되므로 건너뛰기
                    continue;
                }
                // 빨간 구슬을 구멍을 통해 빼낼 수 있으면서 파란 구슬은 빼낼 수 없다면
                else if (isRedHole && !isBlueHole) {
                    // 이 방향으로 이동해서 빼낼 수 있으므로 현재 이동 횟수에 + 1을 더해 반환
                    return now.count + 1;
                }
                // 이동한 두 구슬의 위치가 겹칠 경우
                if (nextRedMarble.x == nextBlueMarble.x && nextRedMarble.y == nextBlueMarble.y) {
                    // 왼쪽으로 이동한 경우
                    if (direction == 0) {
                        // 이동 전 빨간 구슬의 y 좌표가 파란 구슬의 y 좌표보다 작다면
                        if (now.redMarble.y < now.blueMarble.y) {
                            // 빨간 구슬에 막혀 파란 구슬이 왼쪽으로 한 칸 더 갈 수 없으므로 파란 구슬의 y 좌표 증가 (빨간 구슬 ↔︎ 파란 구슬)
                            nextBlueMarble.y++;
                        }
                        // 이동 전 빨간 구슬의 y 좌표가 파란 구슬의 y 좌표보다 크다면
                        else {
                            // 파란 구슬에 막혀 빨간 구슬이 왼쪽으로 한 칸 더 갈 수 없으므로 빨간 구슬의 y 좌표 증가 (파란 구슬 ↔︎ 빨간 구슬)
                            nextRedMarble.y++;
                        }
                    }
                    // 오른쪽으로 이동한 경우
                    else if (direction == 1) {
                        // 이동 전 빨간 구슬의 y 좌표가 파란 구슬의 y 좌표보다 작다면
                        if (now.redMarble.y < now.blueMarble.y) {
                            // 파란 구슬에 막혀 빨간 구슬이 오른쪽으로 한 칸 더 갈 수 없으므로 빨간 구슬의 y 좌표 감소 (빨간 구슬 ↔︎ 파란 구슬)
                            nextRedMarble.y--;
                        }
                        // 이동 전 빨간 구슬의 y 좌표가 파란 구슬의 y 좌표보다 크다면
                        else {
                            // 빨간 구슬에 막혀 파란 구슬이 오른쪽으로 한 칸 더 갈 수 없으므로 파란 구슬의 y 좌표 감소 (파란 구슬 ↔︎ 빨간 구슬)
                            nextBlueMarble.y--;
                        }
                    }
                    // 위쪽으로 이동한 경우
                    else if (direction == 2) {
                        // 이동 전 빨간 구슬의 x 좌표가 파란 구슬의 x 좌표보다 작다면
                        if (now.redMarble.x < now.blueMarble.x) {
                            // 빨간 구슬에 막혀 파란 구슬이 위쪽으로 한 칸 더 갈 수 없으므로 파란 구슬의 x 좌표 증가 (빨간 구슬 ↕︎ 파란 구슬)
                            nextBlueMarble.x++;
                        }
                        // 이동 전 빨간 구슬의 x 좌표가 파란 구슬의 x 좌표보다 크다면
                        else {
                            // 파란 구슬에 막혀 빨간 구슬이 위쪽으로 한 칸 더 갈 수 없으므로 빨간 구슬의 x 좌표 증가 (파란 구슬 ↕︎ 빨간 구슬)
                            nextRedMarble.x++;
                        }
                    }
                    // 아래쪽으로 이동한 경우
                    else if (direction == 3) {
                        // 이동 전 빨간 구슬의 x 좌표가 파란 구슬의 x 좌표보다 작다면
                        if (now.redMarble.x < now.blueMarble.x) {
                            // 파란 구슬에 막혀 빨간 구슬이 아래쪽으로 한 칸 더 갈 수 없으므로 빨간 구슬의 x 좌표 감소 (빨간 구슬 ↕︎ 파란 구슬)
                            nextRedMarble.x--;
                        }
                        // 이동 전 빨간 구슬의 x 좌표가 파란 구슬의 x 좌표보다 크다면
                        else {
                            // 빨간 구슬에 막혀 파란 구슬이 아래쪽으로 한 칸 더 갈 수 없으므로 파란 구슬의 x 좌표 감소 (파란 구슬 ↕︎ 빨간 구슬)
                            nextBlueMarble.x--;
                        }
                    }
                }
                // 다음 빨간 구슬과 파란 구슬의 위치를 방문한 적이 없다면
                if (!visited[nextRedMarble.x][nextRedMarble.y][nextBlueMarble.x][nextBlueMarble.y]) {
                    // 방문 배열 갱신
                    visited[nextRedMarble.x][nextRedMarble.y][nextBlueMarble.x][nextBlueMarble.y] = true;
                    // 큐에 데이터 삽입
                    queue.add(new Marbles(nextRedMarble, nextBlueMarble, now.count + 1));
                }
            }
        }
        // 10번 이하로 움직여서 빨간 구슬을 구멍을 통해 빼낼 수 없으면 -1을 반환
        return -1;
    }
}