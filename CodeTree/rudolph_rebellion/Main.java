package CodeTree.rudolph_rebellion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 루돌프의_반란
 */
public class Main {
    static class Rudolph { // Rudolph(루돌프의 정보를 담을 클래스)
        int r, c; // r, c(루돌프의 위치)

        public Rudolph(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class Santa { // Santa(산타의 정보를 담을 클래스)
        int r, c; // r, c(산타의 위치)
        int point; // point(산타가 얻은 점수)
        boolean isRemove; // isRemove(탈락 여부)
        int faintTime; // faintTime(기절한 턴 번호)

        public Santa(int r, int c) {
            this.r = r;
            this.c = c;
            this.point = 0;
            this.isRemove = false;
            this.faintTime = -2;
        }
    }

    static int N, M, P, C, D;
    static Rudolph rudolph;
    static Santa[] santas;
    static int[][] board;
    static int[] dr = { -1, 0, 1, 0, 1, 1, -1, -1 }; // dr, dc(상, 우, 하, 좌, 하우, 하좌, 상우, 상좌 대각선 좌표)
    static int[] dc = { 0, 1, 0, -1, 1, -1, 1, -1 };

    /*
     * 루돌프의 움직임
     */
    static void moveRudolph(int turn) {
        Santa santa = selectSanta(); // 가장 가까운 산타 선택
        int direction = selectRudolphDirection(santa); // 가장 가까운 산타로 돌진할 방향 선택
        rudolph.r += dr[direction]; // 돌진한 방향에 따른 위치 변경
        rudolph.c += dc[direction];
        if (board[rudolph.r][rudolph.c] > 0) { // 루돌프와 산타가 충돌한다면
            collision(board[rudolph.r][rudolph.c] - 1, direction, C, turn);
        }
    }

    /*
     * 루돌프의 현재 위치를 기준으로 가장 가까운 산타 선택
     */
    static Santa selectSanta() {
        int targetSantaNum = -1; // targetSantaNum(탈락하지 않은 산타 중 가장 가까운 산타 번호)
        int minDistance = Integer.MAX_VALUE; // minDistance(산타와 루돌프 간의 거리 최솟값)
        for (int i = 0; i < P; i++) {
            Santa santa = santas[i];
            if (santa.isRemove) {
                continue; // santa가 탈락했다면 건너뜀
            }
            int distance = calculateDistance(rudolph.r, rudolph.c, santa.r, santa.c); // distance(루돌프와 산타 사이의 거리)
            if (distance < minDistance) { // distance가 minDistance보다 작으면
                minDistance = distance; // 산타를 갱신
                targetSantaNum = i;
            } else if (distance == minDistance) { // distance와 minDistance가 같으면
                // r 좌표가 큰 산타를 선택, r 좌표가 같은 경우 c 좌표가 더 큰 산타로 갱신
                if (santa.r > santas[targetSantaNum].r
                        || (santa.r == santas[targetSantaNum].r && santa.c > santas[targetSantaNum].c)) {
                    targetSantaNum = i;
                }
            }
        }
        return santas[targetSantaNum];
    }

    /*
     * 루돌프가 산타에게로 이동할 방향 선택
     */
    static int selectRudolphDirection(Santa santa) {
        int direction = 0; // direction(이동할 방향)
        int diffR = santa.r - rudolph.r; // diffR(산타와 루돌프 간의 행 차이)
        int diffC = santa.c - rudolph.c; // diffC(산타와 루돌프 간의 열 차이)
        if (diffR == 0 || diffC == 0) { // 행 또는 열의 차이가 없을 때는 상우하좌 이동
            for (int d = 0; d < 4; d++) { // 상, 우, 하, 좌 방향 중 유효한 방향 선택
                if (diffR * dr[d] > 0 || diffC * dc[d] > 0) {
                    direction = d;
                    break;
                }
            }
        } else { // 행 또는 열의 차이가 있을 때는 대각선 이동
            for (int d = 4; d < 8; d++) { // 하우, 하좌, 상우, 상좌 방향 중 유효한 방향 선택
                if (diffR * dr[d] > 0 && diffC * dc[d] > 0) {
                    direction = d;
                    break;
                }
            }
        }
        return direction;
    }

    /*
     * 산타의 움직임
     */
    static void moveSantas(int turn) {
        for (int i = 0; i < P; i++) {
            moveSanta(i, turn);
        }
    }

    /*
     * 산타 이동
     */
    static void moveSanta(int targetSantaNum, int turn) {
        Santa santa = santas[targetSantaNum]; // santa(이동할 산타)
        if (santa.isRemove) {
            return; // santa가 탈락했다면 건너뜀
        }
        if (santa.faintTime + 2 > turn) {
            return; // santa가 기절했다면 건너뜀
        }
        int direction = selectSantaDirection(santa); // direction(산타가 이동할 방향)
        if (direction == -1) {
            return; // 움직일 수 있는 칸이 없다면 산타는 움직이지 않음
        }
        int nr = santas[targetSantaNum].r + dr[direction]; // 움직일 수 있는 칸이 있다면 그 방향으로 이동
        int nc = santas[targetSantaNum].c + dc[direction];
        board[santas[targetSantaNum].r][santas[targetSantaNum].c] = 0; // 기존의 위치를 0으로 갱신하고 이동한 위치로 좌표
        santas[targetSantaNum].r = nr;
        santas[targetSantaNum].c = nc;
        board[nr][nc] = (targetSantaNum + 1);
        if (nr == rudolph.r && nc == rudolph.c) { // 산타와 루돌프가 충돌한다면
            collision(targetSantaNum, (direction + 2) % 4, D, turn);
        }
    }

    /*
     * 산타가 이동할 방향 선택
     */
    private static int selectSantaDirection(Santa santa) {
        int minDistance = calculateDistance(santa.r, santa.c, rudolph.r, rudolph.c); // minDistance(산타와 루돌프 간의 거리 최솟값)
        int direction = -1; // direction(이동할 방향)
        for (int d = 0; d < 4; d++) { // 상우하좌로 이동
            int nR = santa.r + dr[d]; // nr, nc(방향에 따라 이동한 좌표)
            int nC = santa.c + dc[d];
            if (!isValid(nR, nC) || board[nR][nC] > 0) {
                continue; // 이동한 위치가 게임판 밖이거나 이미 다른 산타가 있다면 이동할 수 없으므로 건너뜀
            }
            int distance = calculateDistance(nR, nC, rudolph.r, rudolph.c);
            if (distance < minDistance) {
                minDistance = distance; // 루돌프와의 거리가 더 작은 경우가 있을 경우 이동할 방향 갱신
                direction = d;
            }
        }
        return direction;
    }

    /*
     * 루돌프와 산타가 충돌했을 때
     */
    static void collision(int targetSantaNum, int direction, int point, int turn) {
        Santa santa = santas[targetSantaNum]; // santa(루돌프와 충돌한 산타)
        board[santa.r][santa.c] = 0; // 루돌프와 충돌한 산타는 이동해야 하므로 기존의 자리를 0으로 갱신
        santa.point += point; // 산타에게 C 또는 D 만큼의 점수를 추가
        santa.faintTime = turn; // 산타는 루돌프와 충돌 후 기절
        int nr = santa.r + point * dr[direction]; // nr, nc(산타가 충돌로 인해 밀려날 좌표)
        int nc = santa.c + point * dc[direction];
        if (!isValid(nr, nc)) {
            santa.isRemove = true; // 밀려난 위치가 게임판 밖이라면 산타는 게임에서 탈락
            return;
        }
        santa.r = nr; // 밀려난 위치가 게임판 안이라면 이 좌표로 갱신
        santa.c = nc;
        if (board[nr][nc] == 0) {
            board[nr][nc] = targetSantaNum + 1; // 밀려난 좌표에 다른 산타가 없다면 이동해온 산타의 번호로 변경
            return;
        }
        chainSantaMove(targetSantaNum, nr, nc, direction); // 밀려난 좌표에 다른 산타가 있다면 연쇄적으로 이동
    }

    /*
     * 이동한 산타로 인해 연쇄적으로 산타 이동
     */
    static void chainSantaMove(int targetSantaNum, int nr, int nc, int direction) {
        int prevSantaNum = board[nr][nc] - 1; // prevSantaNum(이전에 있던 산타 번호)
        board[nr][nc] = targetSantaNum + 1; // 이동해온 산타의 번호로 변경
        nr += dr[direction]; // nr, nc(산타가 충돌로 인해 밀려날 좌표)
        nc += dc[direction];
        if (!isValid(nr, nc)) {
            santas[prevSantaNum].isRemove = true; // 이동한 위치가 게임판 밖이라면 산타는 게임에서 탈락
            return;
        }
        Santa santa = santas[prevSantaNum];
        santa.r = nr;
        santa.c = nc;
        if (board[nr][nc] == 0) {
            board[nr][nc] = prevSantaNum + 1;
            return;
        }
        chainSantaMove(prevSantaNum, nr, nc, direction); // 계속해서 연쇄적으로 이동
    }

    /*
     * 매 턴 이후 탈락하지 않은 산타들에게 1점씩 추가 부여
     */
    static int addPointToSurvivingSantas() {
        int countAliveSanta = 0; // countAliveSanta(살아남아 있는 산타의 수)
        for (int i = 0; i < santas.length; i++) {
            Santa santa = santas[i];
            if (!santa.isRemove) {
                santa.point++;
                countAliveSanta++;
            }
        }
        return countAliveSanta;
    }

    /*
     * 산타와 루돌프 사이의 거리 계산
     */
    static int calculateDistance(int r1, int c1, int r2, int c2) {
        return (int) (Math.pow((r1 - r2), 2) + Math.pow((c1 - c2), 2));
    }

    /*
     * 게임판 밖으로 나갔는지 아닌지 검증
     */
    static boolean isValid(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    /*
     * 게임이 끝났을 때 각 산타가 얻은 최종 점수를 출력
     */
    static void printResult() {
        for (int i = 0; i < santas.length; i++) {
            System.out.print(santas[i].point);
            if (i != santas.length - 1) {
                System.out.print(" ");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // N(게임판의 크기)
        M = Integer.parseInt(st.nextToken()); // M(게임 턴 수)
        P = Integer.parseInt(st.nextToken()); // P(산타의 수)
        C = Integer.parseInt(st.nextToken()); // C(루돌프의 힘)
        D = Integer.parseInt(st.nextToken()); // D(산타의 힘)

        board = new int[N][N]; // board(루돌프와 산타가 움직일 게임판)
        santas = new Santa[P]; // santas(산타들을 담을 배열)

        st = new StringTokenizer(br.readLine());
        int Rr = Integer.parseInt(st.nextToken()) - 1; // Rr, Rc(루돌프의 초기 위치)
        int Rc = Integer.parseInt(st.nextToken()) - 1;
        rudolph = new Rudolph(Rr, Rc); // 루돌프 생성

        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int Pn = Integer.parseInt(st.nextToken()); // Pn(산타의 번호)
            int Sr = Integer.parseInt(st.nextToken()) - 1; // Sr, Sc(산타의 초기 위치)
            int Sc = Integer.parseInt(st.nextToken()) - 1;
            santas[Pn - 1] = new Santa(Sr, Sc);
            board[Sr][Sc] = Pn;
        }

        for (int m = 0; m < M; m++) { // M개의 턴에 걸쳐 게임 진행
            moveRudolph(m); // 루돌프의 움직임
            moveSantas(m); // 산타의 움직임
            if (addPointToSurvivingSantas() == 0) { // 매 턴 이후 탈락하지 않은 산타들에게 1점씩 추가 부여
                break; // 모두 게임에서 탈락하게 된다면 즉시 게임 종료
            }
        }
        printResult(); // 게임이 끝났을 때 각 산타가 얻은 최종 점수를 출력
        br.close();
    }
}