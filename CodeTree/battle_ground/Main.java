package CodeTree.battle_ground;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 싸움땅
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, K;
    static Point[][] board;
    static Player[] players;
    static int[] answer;
    static int[] dr = { -1, 0, 1, 0 }; // dr, dc(상우하좌 좌표)
    static int[] dc = { 0, 1, 0, -1 };

    /*
     * Point(좌표 정보를 담을 클래스)
     */
    static class Point {
        List<Integer> guns = new ArrayList<>(); // guns(가지고 있는 총들의 공격력)
        Player player; // player(해당 좌표에 위치하는 플레이어)

        public Point(int gun) {
            guns.add(gun);
        }

        public int getBestGun() { // 가장 공격력이 쎈 총 선택
            if (guns.isEmpty()) {
                return 0;
            }
            int bestGunIndex = 0;
            int bestGun = Integer.MIN_VALUE;
            for (int i = 0; i < guns.size(); i++) {
                if (guns.get(i) > bestGun) {
                    bestGun = guns.get(i);
                    bestGunIndex = i;
                }
            }
            guns.remove(bestGunIndex);
            return bestGun;
        }
    }

    /*
     * Player(플레이어 정보를 담을 클래스)
     */
    static class Player {
        int id; // id(플레이어 번호)
        int r, c; // r, c(플레이어의 위치)
        int direction; // direction(방향)
        int ability; // ability(플레이어의 초기 능력치)
        int gun; // gun(가지고 있는 총의 공격력)

        public Player(int id, int r, int c, int direction, int ability) {
            this.id = id;
            this.r = r;
            this.c = c;
            this.direction = direction;
            this.ability = ability;
        }
    }

    /*
     * 싸움땅 준비하기
     */
    static void init(StringTokenizer st) throws IOException {
        N = Integer.parseInt(st.nextToken()); // N(격자의 크기)
        M = Integer.parseInt(st.nextToken()); // M(플레이어의 수)
        K = Integer.parseInt(st.nextToken()); // K(라운드의 수)

        board = new Point[N][N]; // board(좌표 정보를 담을 배열)
        for (int i = 0; i < N; i++) { // 총의 정보 저장
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 0) {
                    board[i][j] = new Point(0);
                } else {
                    board[i][j] = new Point(num);
                }
            }
        }

        players = new Player[M + 1]; // players(플레이어 정보를 담을 배열)
        for (int i = 0; i < M; i++) { // 플레이어 정보 저장
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1; // x, y(플레이어의 위치)
            int y = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()); // d(방향: 0-상, 1-우, 2-하, 3-좌)
            int s = Integer.parseInt(st.nextToken()); // s(초기 능력치)
            Player player = new Player(i + 1, x, y, d, s);
            board[x][y].player = player;
            players[i + 1] = player;
        }

        answer = new int[M]; // answer(각 플레이어들이 획득한 포인트를 담을 배열)
    }

    /*
     * 싸움땅 진행하기
     */
    static void play() {
        for (int i = 0; i < K; i++) {
            for (int j = 1; j <= M; j++) {
                move(players[j]);
            }
        }
    }

    /*
     * 플레이어 이동
     */
    static void move(Player player) {
        int nr = player.r + dr[player.direction]; // nr, nc(플레이어가 이동한 칸 좌표)
        int nc = player.c + dc[player.direction];
        if (!isValid(nr, nc)) { // 격자를 벗어난다면 정반대 방향으로 바꾸기
            player.direction = (player.direction + 2) % 4;
            nr = player.r + dr[player.direction];
            nc = player.c + dc[player.direction];
        }
        Point point = board[nr][nc]; // point(플레이어가 이동할 좌표)
        if (point.player == null) { // 이동한 방향에 플레이어가 없다면
            board[player.r][player.c].player = null; // 이동
            player.r = nr;
            player.c = nc;
            point.player = player;
            point.guns.add(player.gun); // 더 공격력이 쎈 총으로 교환
            player.gun = point.getBestGun();
        } else { // 이동한 방향에 플레이어가 있다면
            board[player.r][player.c].player = null; // 이동
            player.r = nr;
            player.c = nc;
            fight(point, player, point.player); // 두 플레이어가 싸움
        }
    }

    /*
     * 플레이어 싸움
     */
    static void fight(Point point, Player movePlayer, Player origPlayer) {
        Player winner = null; // winner(이긴 플레이어)
        Player loser = null; // loser(진 플레이어)

        int movePlayerPower = movePlayer.ability + movePlayer.gun; // movePlayerPower(이동해 온 플레이어의 수치)
        int origPlayerPower = origPlayer.ability + origPlayer.gun; // origPlayerPower(원래 있던 플레이어의 수치)
        int plus = Math.abs(movePlayerPower - origPlayerPower); // plus(점수)

        if (movePlayerPower == origPlayerPower) { // 수치가 같다면 초기 능력치로 결정
            if (movePlayer.ability > origPlayer.ability) {
                winner = movePlayer;
                loser = origPlayer;
            } else {
                winner = origPlayer;
                loser = movePlayer;
            }
        } else if (movePlayerPower > origPlayerPower) {
            winner = movePlayer;
            loser = origPlayer;
        } else if (movePlayerPower < origPlayerPower) {
            winner = origPlayer;
            loser = movePlayer;
        }

        forWinner(point, winner, loser, plus);
        forLoser(loser);
    }

    /*
     * 싸움 승자 플레이어를 위한 총 교환 및 포인트 획득
     */
    static void forWinner(Point point, Player winner, Player loser, int plus) {
        point.guns.add(loser.gun); // 진 플레이어는 총을 내려놓음
        loser.gun = 0;

        point.player = winner; // 이긴 플레이어는 더 공격력이 쎈 총으로 교환
        point.guns.add(winner.gun);
        winner.gun = point.getBestGun();
        answer[winner.id - 1] += plus; // 포인트 획득
    }

    /*
     * 싸움 패자 플레이어를 위한 이동
     */
    static void forLoser(Player loser) {
        int nr = loser.r + dr[loser.direction]; // nr, nc(플레이어가 이동한 칸 좌표)
        int nc = loser.c + dc[loser.direction];
        while (!isValid(nr, nc) || board[nr][nc].player != null) { // 격자 범위 밖이 아니면서 이동한 방향에 플레이어가 없을 때까지 이동
            loser.direction = (loser.direction + 1) % 4; // 오른쪽으로 90도 회전
            nr = loser.r + dr[loser.direction];
            nc = loser.c + dc[loser.direction];
        }
        Point point = board[nr][nc]; // point(이동할 좌표)
        point.player = loser; // 이동
        loser.r = nr;
        loser.c = nc;
        loser.gun = point.getBestGun(); // 더 공격력이 쎈 총으로 교환
    }

    /*
     * 격자 범위 안인지 확인
     */
    static boolean isValid(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    /*
     * 싸움땅 결과 출력하기
     */
    static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            sb.append(answer[i]).append(" ");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        init(st);
        play();
        print();
    }
}