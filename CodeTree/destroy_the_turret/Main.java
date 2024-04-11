package CodeTree.destroy_the_turret;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 포탑_부수기
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, K;
    static Turret[][] turrets;
    static int[] dr = { 0, 1, 0, -1, -1, 1, -1, 1 }; // dr, dc(우하좌상, 대각선)
    static int[] dc = { 1, 0, -1, 0, -1, -1, 1, 1 };

    /*
     * Turret(포탑 정보를 담을 클래스)
     */
    static class Turret {
        int r, c; // r, c(포탑의 위치)
        int power; // power(공격력)
        int attackTurn; // attackTurn(공격한 턴 번호)
        boolean isRelated; // isRelated(공격과 유관한지 여부)

        public Turret(int r, int c, int power) {
            this.r = r;
            this.c = c;
            this.power = power;
            attackTurn = -1;
            isRelated = false;
        }
    }

    /*
     * 공격자 선정을 위한 커스텀 정렬
     */
    static Comparator<Turret> weakTurret = new Comparator<Turret>() {
        @Override
        public int compare(Turret t1, Turret t2) {
            if (t1.power != t2.power)
                return Integer.compare(t1.power, t2.power); // 공격력이 가장 낮은 포탑
            if (t1.attackTurn != t2.attackTurn)
                return Integer.compare(t2.attackTurn, t1.attackTurn); // 가장 최근에 공격한 포탑
            if (t1.r + t1.c != t2.r + t2.c)
                return Integer.compare(t2.r + t2.c, t1.r + t1.c); // 행과 열의 합이 가장 큰 포탑
            return Integer.compare(t2.c, t1.c); // 열 값이 가장 큰 포탑
        }
    };

    /*
     * 공격 대상 포탑 선정을 위한 커스텀 정렬
     */
    static Comparator<Turret> strongTurret = new Comparator<Turret>() {
        @Override
        public int compare(Turret t1, Turret t2) {
            if (t1.power != t2.power)
                return Integer.compare(t2.power, t1.power); // 공격력이 가장 높은 포탑
            if (t1.attackTurn != t2.attackTurn)
                return Integer.compare(t1.attackTurn, t2.attackTurn); // 공격한 지 가장 오래된 포탑
            if (t1.r + t1.c != t2.r + t2.c)
                return Integer.compare(t1.r + t1.c, t2.r + t2.c); // 행과 열의 합이 가장 작은 포탑
            return Integer.compare(t1.c, t2.c); // 열 값이 가장 작은 포탑
        }
    };

    /*
     * Point(공격자의 이동 위치를 담을 클래스)
     */

    static class Point {
        int r, c; // r, c(행, 열)
        int distance; // distance(이동 거리)
        List<Turret> passedTurrets; // passedTurrets(거쳐온 포탑들)

        public Point(int r, int c, int distance, List<Turret> passedTurrets) {
            this.r = r;
            this.c = c;
            this.distance = distance;
            this.passedTurrets = passedTurrets;
        }
    }

    /*
     * 포탑 부수기 준비
     */
    static void init(StringTokenizer st) throws IOException {
        N = Integer.parseInt(st.nextToken()); // N, M(격자 크기)
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken()); // K(턴의 개수)

        turrets = new Turret[N][M]; // turrets(포탑을 담은 배열)
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int power = Integer.parseInt(st.nextToken());
                if (power == 0) { // 부서진 포탑이라면 넘어감
                    continue;
                }
                turrets[i][j] = new Turret(i, j, power); // 포탑을 저장
            }
        }
    }

    /*
     * 포탑 부수기
     */
    static void destroyTurret() {
        for (int i = 0; i < K; i++) {
            Turret attacker = selectAttacker(i); // 1. 공격자 선정

            Turret attackTarget = selectAttackTarget(); // 2. 공격 대상 포탑 선정

            if (!laserAttack(attacker, attackTarget)) { // 2-1. 레이저 공격이 불가능하다면
                shellAttack(attacker, attackTarget, i); // 2-2. 포탄 공격
            }

            brokenTurrets(); // 3. 포탑 부서짐
            repairTurrets(i); // 4. 포탑 정비

            if (isOver()) { // 부서지지 않은 포탑이 1개가 된다면 그 즉시 중지
                break;
            }
        }
    }

    /*
     * 공격자 선정
     */
    static Turret selectAttacker(int turn) {
        PriorityQueue<Turret> weakPq = new PriorityQueue<Turret>(weakTurret); // weakPq(가장 약한 포탑 우선순위 큐)
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (turrets[i][j] != null)
                    weakPq.add(turrets[i][j]);
            }
        }
        Turret turret = weakPq.poll(); // turret(공격자)
        turret.attackTurn = turn; // 공격한 턴 번호 갱신
        turret.isRelated = true; // 공격과 유관하므로 갱신
        return turret;
    }

    /*
     * 공격 대상 포탑 선정
     */
    static Turret selectAttackTarget() {
        PriorityQueue<Turret> strongPq = new PriorityQueue<Turret>(strongTurret); // strongPq(가장 강한 포탑 우선순위 큐)
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (turrets[i][j] != null)
                    strongPq.add(turrets[i][j]);
            }
        }
        Turret turret = strongPq.poll(); // turret(공격 대상 포탑)
        turret.isRelated = true; // 공격과 유관하므로 갱신
        return turret;
    }

    /*
     * 레이저 공격
     */
    private static boolean laserAttack(Turret attacker, Turret attackTarget) {
        attacker.power += (N + M); // 공격력 증가
        attackTarget.power -= attacker.power; // 공격자 공격력 만큼의 공격력 감소

        int minDistance = Integer.MAX_VALUE; // minDistance(최단 거리)
        Point minDistancePoint = null; // minDistancePoint(최단 거리일 때의 공격자의 마지막 위치와 거쳐온 포탑들)
        boolean canLaserAttack = false; // canLaserAttack(레이저 공격 가능 여부)

        Queue<Point> queue = new LinkedList<>(); // 우하좌상 BFS 탐색
        queue.add(new Point(attacker.r, attacker.c, 0, new ArrayList<>()));
        boolean[][] visited = new boolean[N][M];
        visited[attacker.r][attacker.c] = true;

        while (!queue.isEmpty()) {
            Point now = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = (now.r + dr[d] + N) % N;
                int nc = (now.c + dc[d] + M) % M;
                Turret turret = turrets[nr][nc];
                if (turret == null || visited[nr][nc]) { // 부서진 포탑이거나 이미 방문한 포탑일 경우 넘어감
                    continue;
                }
                visited[nr][nc] = true;
                List<Turret> passedTurrets = new ArrayList<>(now.passedTurrets);
                passedTurrets.add(turret);
                if (nr == attackTarget.r && nc == attackTarget.c) {
                    if (now.distance < minDistance) { // 최단 거리로 갱신
                        minDistance = now.distance;
                        minDistancePoint = now;
                    }
                    canLaserAttack = true;
                    continue;
                }
                queue.add(new Point(nr, nc, now.distance + 1, passedTurrets));
            }
        }
        if (canLaserAttack) {
            for (Turret turret : minDistancePoint.passedTurrets) { // 레이저 경로에 있는 포탑도 공격
                turret.isRelated = true; // 공격과 유관하므로 갱신
                turret.power -= (attacker.power / 2);
            }
        }
        return canLaserAttack;
    }

    /*
     * 포탄 공격
     */

    static void shellAttack(Turret attacker, Turret attackTarget, int turn) {
        for (int d = 0; d < 8; d++) { // 우하좌상, 대각선의 공격력 감소
            int nr = (attackTarget.r + dr[d] + N) % N;
            int nc = (attackTarget.c + dc[d] + M) % M;
            Turret turret = turrets[nr][nc];
            if (turret == null) {
                continue; // 부서진 포탑일 경우 넘어감
            }
            if (turret.r == attacker.r && turret.c == attacker.c) {
                continue; // 공격자는 해당 공격에 영향을 받지 않음
            }
            turret.isRelated = true;
            turret.power -= (attacker.power / 2);
        }
    }

    /*
     * 포탑 부서짐
     */
    static void brokenTurrets() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Turret turret = turrets[i][j];
                if (turret == null) {
                    continue;
                }
                if (turret.power <= 0) { // 공격력이 0 이하가 된 포탑은 부서진 포탑이 됨
                    turrets[i][j] = null;
                }
            }
        }
    }

    /*
     * 포탑 정비
     */
    static void repairTurrets(int turn) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Turret turret = turrets[i][j];
                if (turret == null) {
                    continue;
                }
                if (turret.isRelated) {
                    turret.isRelated = false; // 무관하도록 초기화
                    continue;
                }
                turret.power++; // 공격과 무관하다면 공격력 증가
            }
        }
    }

    /*
     * 포탑 부수기 중지 여부
     */
    static boolean isOver() {
        int count = 0; // count(부서지지 않은 포탑의 개수)
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (turrets[i][j] != null)
                    count++;
            }
        }
        return count <= 1;
    }

    /*
     * 남아있는 포탑 중 가장 강한 포탑 선정
     */
    static void selectBestTurret() {
        int maxPower = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Turret turret = turrets[i][j];
                if (turret == null) {
                    continue;
                }
                if (maxPower < turret.power) {
                    maxPower = turret.power;
                }
            }
        }
        System.out.println(maxPower);
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        init(st);
        destroyTurret();
        selectBestTurret();
    }
}