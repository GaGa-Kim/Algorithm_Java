package CodeTree.maze_runner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 메이즈_러너
 */
public class Main {
    /*
     * 참가자의 정보를 담을 클래스
     */
    static class Participant {
        int r, c; // r, c(참가자의 위치)
        int count; // count(이동 횟수)
        boolean isEscaped; // isEscaped(탈출 여부)

        public Participant(int r, int c) {
            this.r = r;
            this.c = c;
            count = 0;
            isEscaped = false;
        }
    }

    static int N, M, K;
    static int[][] maze;
    static Participant[] participants;
    static int existR, existC;
    static int squareLength, squareR, squareC;
    static int dr[] = { -1, 1, 0, 0 }; // dr, dc(상, 하, 좌, 우)
    static int dc[] = { 0, 0, -1, 1 };

    /*
     * 모든 참가자들 이동
     */
    static void moveParticipants() {
        for (int i = 0; i < M; i++) {
            moveParticipant(i); // 참가자 이동
        }
    }

    /*
     * 참가자 이동
     */
    static void moveParticipant(int participantNum) {
        Participant participant = participants[participantNum]; // participant(이동할 참가자)
        if (participant.isEscaped) {
            return; // 이미 탈출한 참가자라면 건너뜀
        }
        int direction = selectParticipantDirection(participant); // direction(참가자가 이동할 방향)
        if (direction == -1) {
            return; // 움직일 수 있는 칸이 없다면 참가자는 움직이지 않음
        }
        participant.r += dr[direction]; // 움직일 수 있는 칸이 있다면 그 방향으로 위치 갱신
        participant.c += dc[direction];
        participant.count++; // 이동 횟수 증가
        checkParticipantEscape(participant); // 참가자 탈출 여부 확인
    }

    /*
     * 상하좌우로 탐색하여 참가자가 이동할 방향을 선택
     */
    static int selectParticipantDirection(Participant participant) {
        int minDistance = calculateDistance(participant.r, participant.c); // minDistance(최단 거리)
        int direction = -1; // direction(이동할 방향)
        for (int d = 0; d < 4; d++) { // 상하좌우로 이동
            int nr = participant.r + dr[d]; // nr, nc(방향에 따라 이동한 좌표)
            int nc = participant.c + dc[d];
            if (!isValid(nr, nc) || maze[nr][nc] != 0) {
                continue; // 이동한 위치가 미로 밖이거나 벽이라면 이동할 수 없으므로 건너뜀
            }
            int distance = calculateDistance(nr, nc); // distance(참가자와 출구 간의 거리)
            if (distance < minDistance) { // distance가 minDistance보다 작으면
                minDistance = direction; // 이동 방향을 갱신
                direction = d;
            }
        }
        return direction;
    }

    /*
     * 참가자 탈출 여부 확인
     */
    static void checkParticipantEscape(Participant participant) {
        if (participant.r == existR && participant.c == existC) {
            participant.isEscaped = true;
        }
    }

    /*
     * 가장 작은 정사각형을 찾아 미로 회전
     */
    static void rotateMaze() {
        findMinimumSquare(); // 가장 작은 정사각형의 길이와 좌표를 담은 배열 찾기
        rotateSquare(); // 벽 회전
        rotateParticipantExists(); // 참가자와 출구 좌표 회전
    }

    /*
     * 가장 작은 정사각형 찾기
     */
    static void findMinimumSquare() {
        for (int length = 2; length < N; length++) { // 가장 작은 정사각형의 길이인 2부터 시작해 정사각형을 찾도록 함
            for (int r1 = 0; r1 < N - length + 1; r1++) { // r1, c1(정사각형의 왼쪽 좌표들)
                for (int c1 = 0; c1 < N - length + 1; c1++) {
                    int r2 = r1 + length - 1; // r2, rc(정사각형의 오른쪽 좌표들)
                    int c2 = c1 + length - 1;
                    if (!(r1 <= existR && existR <= r2 && c1 <= existC && existC <= c2)) {
                        continue; // 가장 작은 정사각형이 출구 좌표를 포함되지 않는다면 넘어감
                    }
                    boolean containParticipant = false; // containParticipant(참가자 포함 여부)
                    for (int i = 0; i < M; i++) {
                        Participant participant = participants[i];
                        if (participant.isEscaped) {
                            continue; // 이미 탈출한 참가자라면 건너뜀
                        }
                        if (r1 <= participant.r && participant.r <= r2 && c1 <= participant.c && participant.c <= c2) {
                            containParticipant = true; // 가장 작은 정사각형이 참가자를 포함한다면
                        }
                    }
                    if (containParticipant) {
                        squareLength = length; // 한 명의 참가자라도 포함된다면 정사각형의 길이, 시작점을 저장
                        squareR = r1;
                        squareC = c1;
                        return;
                    }
                }
            }
        }
    }

    /*
     * 벽 회전
     */
    static void rotateSquare() {
        for (int r = squareR; r < squareR + squareLength; r++) {
            for (int c = squareC; c < squareC + squareLength; c++) {
                if (maze[r][c] != 0) {
                    maze[r][c] -= 1; // 벽의 내구도를 감소
                }
            }
        }
        int[][] rotateMaze = new int[N][N]; // rotateMaze(회전한 미로)
        for (int r = squareR; r < squareR + squareLength; r++) {
            for (int c = squareC; c < squareC + squareLength; c++) {
                int origR = r - squareR;
                int origC = c - squareC;
                int nr = origC;
                int nc = squareLength - 1 - origR;
                rotateMaze[nr + squareR][nc + squareC] = maze[r][c];
            }
        }
        for (int r = squareR; r < squareR + squareLength; r++) {
            for (int c = squareC; c < squareC + squareLength; c++) {
                maze[r][c] = rotateMaze[r][c]; // 기존의 미로 부분을 회전한 일부 미로로 변경
            }
        }
    }

    /*
     * 참가자와 출구 좌표 회전
     */
    static void rotateParticipantExists() {
        for (int i = 0; i < M; i++) {
            Participant participant = participants[i];
            if (participant.isEscaped) {
                continue; // 이미 탈출한 참가자라면 건너뜀
            }
            if (squareR <= participant.r && participant.r < squareR + squareLength
                    && squareC <= participant.c && participant.c < squareC + squareLength) { // 회전해야 하는 좌표에 참가자가 포함된다면
                int origR = participant.r - squareR;
                int origC = participant.c - squareC;
                int nr = origC;
                int nc = squareLength - 1 - origR;
                participant.r = nr + squareR; // 참가자의 좌표 갱신
                participant.c = nc + squareC;
            }
        }
        int origR = existR - squareR;
        int origC = existC - squareC;
        int nr = origC;
        int nc = squareLength - 1 - origR;
        existR = nr + squareR; // 출구 좌표 갱신
        existC = nc + squareC;
    }

    /*
     * 모든 참가자가 탈출했는지 확인
     */
    static boolean allParticipantsEscaped() {
        for (int i = 0; i < M; i++) {
            if (!participants[i].isEscaped) {
                return false;
            }
        }
        return true;
    }

    /*
     * 게임이 끝났을 때 모든 참가자들의 이동 거리 합과 출구 좌표를 출력
     */
    static void printResult() {
        System.out.println(calculateCounts());
        System.out.println((existR + 1) + " " + (existC + 1));
    }

    /*
     * 모든 참가자들의 이동 거리 합 구하기
     */
    static int calculateCounts() {
        int counts = 0;
        for (int i = 0; i < M; i++) {
            counts += participants[i].count;
        }
        return counts;
    }

    /*
     * 참가자와 출구 사이의 거리 계산
     */
    static int calculateDistance(int r, int c) {
        return Math.abs(existR - r) + Math.abs(existC - c);
    }

    /*
     * 미로 밖으로 나갔는지 아닌지 검증
     */
    static boolean isValid(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // N(미로의 크기)
        M = Integer.parseInt(st.nextToken()); // M(참가자의 수)
        K = Integer.parseInt(st.nextToken()); // K(게임 시간)

        maze = new int[N][N]; // maze(참가자가 이동할 미로판)
        squareR = 0; // squareR, squareC(가장 작은 정사각형의 좌표) 초기화
        squareC = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                maze[i][j] = Integer.parseInt(st.nextToken()); // maze에 미로 정보 저장
            }
        }

        participants = new Participant[M]; // participants(참가자들을 담을 배열)
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            participants[i] = new Participant(r, c); // r, c(위치)를 가지고 참가자 생성
        }

        st = new StringTokenizer(br.readLine());
        existR = Integer.parseInt(st.nextToken()) - 1; // existR, existC(출구 좌표) 저장
        existC = Integer.parseInt(st.nextToken()) - 1;

        for (int i = 0; i < K; i++) { // K초만큼 미로 게임 진행
            moveParticipants(); // 모든 참가자들 이동
            if (allParticipantsEscaped()) {
                break; // 모든 참가자가 탈출했다면 게임이 끝나므로 즉시 게임 종료
            }
            rotateMaze(); // 가장 작은 정사각형을 찾아 미로 회전
        }
        printResult(); // 게임이 끝났을 때 모든 참가자들의 이동 거리 합과 출구 좌표를 출력
        br.close();
    }
}