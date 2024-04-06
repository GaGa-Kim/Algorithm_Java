package CodeTree.two_candies;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 2개의_사탕
 */
public class Main {
    // Candies(빨간 사탕과 파란 사탕 정보 그리고 이동 횟수를 담는 클래스)
    static class Candies {
        Candy redCandy, blueCandy; // redCandy, blueCandy(빨간 사탕과 파란 사탕 정보)
        int count; // count (이동 횟수)

        public Candies(Candy redCandy, Candy blueCandy, int count) {
            this.redCandy = redCandy;
            this.blueCandy = blueCandy;
            this.count = count;
        }
    }

    // Candy(각 사탕 정보를 담는 클래스)
    static class Candy {
        int x, y; // x, y(사탕의 x, y 좌표)

        public Candy(int x, int y) {
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
        // startCandies(빨간 사탕과 파란 사탕 정보 그리고 이동 횟수 정보를 담은 클래스)
        Candies startCandies = findStartCandies();
        // bfs 탐색을 통해 빨간 사탕만 나올 수 있는지 탐색
        System.out.println(bfs(startCandies));
        br.close();
    }

    // 시작 위치의 사탕 정보 찾기
    private static Candies findStartCandies() {
        // startRedCandy(시작 위치의 빨간 사탕)
        Candy startRedCandy = new Candy(0, 0);
        // startBlueCandy(시작 위치의 파란 사탕)
        Candy startBlueCandy = new Candy(0, 0);
        // 빨간 사탕과 파란 사탕의 시작 위치 정보를 찾기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 'R') {
                    startRedCandy.x = i;
                    startRedCandy.y = j;
                    board[i][j] = '.';
                }
                if (board[i][j] == 'B') {
                    startBlueCandy.x = i;
                    startBlueCandy.y = j;
                    board[i][j] = '.';
                }
            }
        }
        // 찾은 빨간 사탕과 파란 사탕의 시작 정보를 가지고 Candies 생성
        return new Candies(startRedCandy, startBlueCandy, 0);
    }

    // bfs 탐색
    static int[] dx = { 0, 0, -1, 1 };
    static int[] dy = { -1, 1, 0, 0 };

    private static int bfs(Candies startCandies) {
        // 큐 자료구조에 현재 사탕 사용 기록
        Queue<Candies> queue = new LinkedList<>();
        queue.add(startCandies);
        visited[startCandies.redCandy.x][startCandies.redCandy.y][startCandies.blueCandy.x][startCandies.blueCandy.y] = true;
        // 큐가 빌 때까지
        while (!queue.isEmpty()) {
            // 큐에서 사탕 데이터 가져오기
            Candies now = queue.poll();
            // 이동 횟수가 10번 이상이라면
            if (now.count >= 10) {
                // 10번 이하로 움직여서 빨간 사탕이 구멍을 통해 나올 수 없으므로 더 이상 탐색하지 않음
                break;
            }
            // 왼쪽, 오른쪽, 위쪽, 아래쪽 방향으로 기울이기
            for (int direction = 0; direction < 4; direction++) {
                // nextRedCandy(이동한 위치의 빨간 사탕 정보)
                Candy nextRedCandy = new Candy(now.redCandy.x, now.redCandy.y);
                // isRedHole(빨간 사탕이 구멍을 통해 나올 수 있는지 여부)
                boolean isRedHole = false;
                // 빨간 사탕이 벽에 닿지 않을 동안 이동
                while (board[nextRedCandy.x + dx[direction]][nextRedCandy.y + dy[direction]] != '#') {
                    nextRedCandy.x += dx[direction];
                    nextRedCandy.y += dy[direction];
                    // 빨간 사탕이 구멍에 도착한다면
                    if (board[nextRedCandy.x][nextRedCandy.y] == 'O') {
                        // isRedHole 갱신
                        isRedHole = true;
                        // 종료
                        break;
                    }
                }
                // nextBlueCandy(이동한 위치의 파란 사탕 정보)
                Candy nextBlueCandy = new Candy(now.blueCandy.x, now.blueCandy.y);
                // isBlueHole(파란 사탕이 구멍을 통해 나올 수 있는지 여부)
                boolean isBlueHole = false;
                // 파란 사탕이 벽에 닿지 않을 동안 이동
                while (board[nextBlueCandy.x + dx[direction]][nextBlueCandy.y + dy[direction]] != '#') {
                    nextBlueCandy.x += dx[direction];
                    nextBlueCandy.y += dy[direction];
                    // 파란 사탕이 구멍에 도착한다면
                    if (board[nextBlueCandy.x][nextBlueCandy.y] == 'O') {
                        // isBlueHole 갱신
                        isBlueHole = true;
                        // 종료
                        break;
                    }
                }
                // 파란 사탕이 구멍을 통해 나올 수 있다면
                if (isBlueHole) {
                    // 이 방향으로는 이동해서는 안되므로 건너뛰기
                    continue;
                }
                // 빨간 사탕이 구멍을 통해 나올 수 있으면서 파란 사탕은 나올 수 없다면
                else if (isRedHole && !isBlueHole) {
                    // 이 방향으로 이동해서 나올 수 있으므로 현재 이동 횟수에 + 1을 더해 반환
                    return now.count + 1;
                }
                // 이동한 두 사탕의 위치가 겹칠 경우
                if (nextRedCandy.x == nextBlueCandy.x && nextRedCandy.y == nextBlueCandy.y) {
                    // 왼쪽으로 이동한 경우
                    if (direction == 0) {
                        // 이동 전 빨간 사탕의 y 좌표가 파란 사탕의 y 좌표보다 작다면
                        if (now.redCandy.y < now.blueCandy.y) {
                            // 빨간 사탕에 막혀 파란 사탕이 왼쪽으로 한 칸 더 갈 수 없으므로 파란 사탕의 y 좌표 증가 (빨간 사탕 ↔︎ 파란 사탕)
                            nextBlueCandy.y++;
                        }
                        // 이동 전 빨간 사탕의 y 좌표가 파란 사탕의 y 좌표보다 크다면
                        else {
                            // 파란 사탕에 막혀 빨간 사탕이 왼쪽으로 한 칸 더 갈 수 없으므로 빨간 사탕의 y 좌표 증가 (파란 사탕 ↔︎ 빨간 사탕)
                            nextRedCandy.y++;
                        }
                    }
                    // 오른쪽으로 이동한 경우
                    else if (direction == 1) {
                        // 이동 전 빨간 사탕의 y 좌표가 파란 사탕의 y 좌표보다 작다면
                        if (now.redCandy.y < now.blueCandy.y) {
                            // 파란 사탕에 막혀 빨간 사탕이 오른쪽으로 한 칸 더 갈 수 없으므로 빨간 사탕의 y 좌표 감소 (빨간 사탕 ↔︎ 파란 사탕)
                            nextRedCandy.y--;
                        }
                        // 이동 전 빨간 사탕의 y 좌표가 파란 사탕의 y 좌표보다 크다면
                        else {
                            // 빨간 사탕에 막혀 파란 사탕이 오른쪽으로 한 칸 더 갈 수 없으므로 파란 사탕의 y 좌표 감소 (파란 사탕 ↔︎ 빨간 사탕)
                            nextBlueCandy.y--;
                        }
                    }
                    // 위쪽으로 이동한 경우
                    else if (direction == 2) {
                        // 이동 전 빨간 사탕의 x 좌표가 파란 사탕의 x 좌표보다 작다면
                        if (now.redCandy.x < now.blueCandy.x) {
                            // 빨간 사탕에 막혀 파란 사탕이 위쪽으로 한 칸 더 갈 수 없으므로 파란 사탕의 x 좌표 증가 (빨간 사탕 ↕︎ 파란 사탕)
                            nextBlueCandy.x++;
                        }
                        // 이동 전 빨간 사탕의 x 좌표가 파란 사탕의 x 좌표보다 크다면
                        else {
                            // 파란 사탕에 막혀 빨간 사탕이 위쪽으로 한 칸 더 갈 수 없으므로 빨간 사탕의 x 좌표 증가 (파란 사탕 ↕︎ 빨간 사탕)
                            nextRedCandy.x++;
                        }
                    }
                    // 아래쪽으로 이동한 경우
                    else if (direction == 3) {
                        // 이동 전 빨간 사탕의 x 좌표가 파란 사탕의 x 좌표보다 작다면
                        if (now.redCandy.x < now.blueCandy.x) {
                            // 파란 사탕에 막혀 빨간 사탕이 아래쪽으로 한 칸 더 갈 수 없으므로 빨간 사탕의 x 좌표 감소 (빨간 사탕 ↕︎ 파란 사탕)
                            nextRedCandy.x--;
                        }
                        // 이동 전 빨간 사탕의 x 좌표가 파란 사탕의 x 좌표보다 크다면
                        else {
                            // 빨간 사탕에 막혀 파란 사탕이 아래쪽으로 한 칸 더 갈 수 없으므로 파란 사탕의 x 좌표 감소 (파란 사탕 ↕︎ 빨간 사탕)
                            nextBlueCandy.x--;
                        }
                    }
                }
                // 다음 빨간 사탕과 파란 사탕의 위치를 방문한 적이 없다면
                if (!visited[nextRedCandy.x][nextRedCandy.y][nextBlueCandy.x][nextBlueCandy.y]) {
                    // 방문 배열 갱신
                    visited[nextRedCandy.x][nextRedCandy.y][nextBlueCandy.x][nextBlueCandy.y] = true;
                    // 큐에 데이터 삽입
                    queue.add(new Candies(nextRedCandy, nextBlueCandy, now.count + 1));
                }
            }
        }
        // 10번 이하로 움직여서 빨간 사탕이 구멍을 통해 나올 수 없으면 -1을 반환
        return -1;
    }
}