package CodeTree.royal_knight_duel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;;

/**
 * 왕실의_기사_대결
 */
public class Main {
    // Knight(기사 정보를 담은 클래스)
    static class Knight {
        int r, c; // r, c(위치)
        int h, w; // h, w(직사각형 형태의 높이, 너비)
        int health; // k(체력)
        int damage; // damage(대미지)

        public Knight(int r, int c, int h, int w, int health) {
            this.r = r;
            this.c = c;
            this.h = h;
            this.w = w;
            this.health = health;
        }
    }

    static int L, N, Q;
    // dr, dc(위쪽, 오른쪽, 아래쪽, 왼쪽 방향)
    static int[] dr = { -1, 0, 1, 0 };
    static int[] dc = { 0, 1, 0, -1 };
    static int[][] chessBoard;
    static Map<Integer, Knight> knightMap;
    static boolean[] mustMoveKnight;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // L(체스판의 크기)
        L = Integer.parseInt(st.nextToken());
        // N(기사들의 수)
        N = Integer.parseInt(st.nextToken());
        // Q(왕의 명령 수)
        Q = Integer.parseInt(st.nextToken());
        // chessBoard(체스판 배열)
        chessBoard = new int[L + 1][L + 1];
        // 체스판 정보 저장
        for (int i = 1; i <= L; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= L; j++) {
                chessBoard[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // knightMap(생존한 기사들의 정보를 담은 HashMap)
        knightMap = new HashMap<>();
        // 기사들의 정보를 저장
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            // r, c(처음 위치)
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            // h, w(세로 길이와 가로 길이)
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            // k(초기 체력)
            int k = Integer.parseInt(st.nextToken());
            knightMap.put((i + 1), new Knight(r, c, h, w, k));
        }
        // 왕의 명령을 수행
        for (int q = 0; q < Q; q++) {
            st = new StringTokenizer(br.readLine());
            // i(명령을 받은 기사의 번호)
            int i = Integer.parseInt(st.nextToken());
            // d(이동할 방향, 0-위쪽, 1-오른쪽, 2-아래쪽, 3-왼쪽)
            int d = Integer.parseInt(st.nextToken());
            // 명령을 받은 기사가 이동할 수 없다면
            if (!checkMovable(i, d)) {
                // 건너뜀
                continue;
            }
            // 명령을 받은 기사가 이동할 수 있다면 이동
            move(i, d);
        }
        // 대결이 모두 끝난 후 생존한 기사들이 받은 총 대미지의 합을 출력
        System.out.println(calculateDamageSum());
    }

    // BFS 탐색을 통해 명령을 받은 기사가 이동할 수 있는지 확인
    private static boolean checkMovable(int commandKnight, int direction) {
        // 명령을 받은 기사가 생존해 있는 기사가 아니라면
        if (!knightMap.containsKey(commandKnight)) {
            // 이동할 수 없으므로 false 반환
            return false;
        }
        // knightBoard(현재 기사들의 위치 배열)
        int[][] knightBoard = makeKnightBoard();
        // queue(이동해야 하는 기사들의 위치를 담은 큐)
        Queue<int[]> queue = new LinkedList<>();
        // visited(위치 방문 유무 배열)
        boolean[][] visited = new boolean[L + 1][L + 1];
        // 큐 자료구조에 명령을 받은 기사 기록
        Knight knight = knightMap.get(commandKnight);
        for (int r = knight.r; r < knight.r + knight.h; r++) {
            for (int c = knight.c; c < knight.c + knight.w; c++) {
                queue.add(new int[] { r, c });
                visited[r][c] = true;
            }
        }
        // mustMoveKnight(이동해야 하는 기사들의 유무를 담은 배열)
        mustMoveKnight = new boolean[N + 1];
        mustMoveKnight[commandKnight] = true;
        // 큐가 빌 때까지
        while (!queue.isEmpty()) {
            // now(현재 이동할 기사의 위치를 담은 배열)
            int[] now = queue.poll();
            // nr, nc(이동한 위치)
            int nr = now[0] + dr[direction];
            int nc = now[1] + dc[direction];
            // 이동한 위치가 체스판을 넘어가거나 벽이라면
            if (nr < 1 || nr > L || nc < 1 || nc > L || chessBoard[nr][nc] == 2) {
                // 연쇄적으로 밀려날 수 없으므로 false 반환
                return false;
            }
            // 이동한 위치에 다른 기사가 있다면
            else if (knightBoard[nr][nc] != 0 && knightBoard[nr][nc] != commandKnight && !visited[nr][nc]) {
                // 이 기사의 정보와 위치를 저장
                Knight next = knightMap.get(knightBoard[nr][nc]);
                for (int r = next.r; r < next.r + next.h; r++) {
                    for (int c = next.c; c < next.c + next.w; c++) {
                        queue.add(new int[] { r, c });
                        visited[r][c] = true;
                        mustMoveKnight[knightBoard[nr][nc]] = true;
                    }
                }
            }
        }
        // 명령 받은 기사가 연쇄적으로 이동할 수 있으므로 true 반환
        return true;
    }

    // 현재 기사들의 정보에 따른 기사 위치 배열 만들기
    private static int[][] makeKnightBoard() {
        // knightBoard(현재 기사들의 위치 배열)
        int[][] knightBoard = new int[L + 1][L + 1];
        // 기사들의 정보에 따른 위치를 저장
        for (int knightNum : knightMap.keySet()) {
            Knight knight = knightMap.get(knightNum);
            for (int r = knight.r; r < knight.r + knight.h; r++) {
                for (int c = knight.c; c < knight.c + knight.w; c++) {
                    knightBoard[r][c] = knightNum;
                }
            }
        }
        return knightBoard;
    }

    // 이동해야 하는 전체 기사 이동
    private static void move(int commandKnight, int direction) {
        // 이동한 전체 기사에 대해 대미지 갱신
        calculateDamage(commandKnight, direction);
        // 이동한 기사 위치 갱신
        for (int knightNum : knightMap.keySet()) {
            if (mustMoveKnight[knightNum]) {
                // knight(이동할 기사)
                Knight knight = knightMap.get(knightNum);
                // 변경된 기사의 위치로 갱신
                knight.r += dr[direction];
                knight.c += dc[direction];
                // 갱신한 기사를 다시 HashMap에 저장
                knightMap.put(knightNum, knight);
            }
        }
    }

    // 이동한 전체 기사에 따른 대미지 갱신
    private static void calculateDamage(int commandKnight, int direction) {
        for (int i = 1; i < mustMoveKnight.length; i++) {
            // 이동하지 않는 기사이거나 명령을 받은 기사라면
            if (!mustMoveKnight[i] || i == commandKnight) {
                // 대미지를 받지 않으므로 넘어감
                continue;
            }
            // knight(대미지를 계산할 기사)
            Knight knight = knightMap.get(i);
            // damage(이동에 따른 대미지)
            int damage = 0;
            for (int r = knight.r; r < knight.r + knight.h; r++) {
                for (int c = knight.c; c < knight.c + knight.w; c++) {
                    // nr, nc(이동한 위치)
                    int nr = r + dr[direction];
                    int nc = c + dc[direction];
                    // 함정이 있는 곳이라면
                    if (chessBoard[nr][nc] == 1) {
                        // damage 증가
                        damage++;
                    }
                }
            }
            // 기사의 체력을 대미지만큼 감소
            knight.health -= damage;
            // 기사의 체력이 0보다 작다면
            if (knight.health <= 0) {
                // 기사는 사라지게 됨
                knightMap.remove(i);
                continue;
            }
            // 기사의 체력이 0보다 크다면 기사에게 대미지 추가
            knight.damage += damage;
            // 감소한 체력과 증가한 대미지를 담은 기사를 다시 HashMap에 저장
            knightMap.put(i, knight);
        }
    }

    // 살아남은 기사들의 대미지의 합을 계산
    private static int calculateDamageSum() {
        // sum(대미지의 합)
        int sum = 0;
        // 살아남은 기사들만 가지고 오도록 함
        for (Knight knight : knightMap.values()) {
            sum += knight.damage;
        }
        return sum;
    }
}