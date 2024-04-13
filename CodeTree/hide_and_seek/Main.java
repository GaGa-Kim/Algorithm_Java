package CodeTree.hide_and_seek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 술래잡기
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, H, K;
    static Point[][] board;
    static Person seeker;
    static Person[] hiders;
    static boolean opposite = false; // 술래 이동 방향 (true - 역방향, false - 순방향)
    static int seekerLength; // seekerLength(달팽이 모양의 한 획에서 술래가 현재 이동한 길이)
    static int seekerCount; // seekerCount(현재 길이에 대한 이동 횟수)
    static int moveLength = 1; // moveLength(달팽이 모양의 한 획에서 이동해야 하는 길이)
    static int[] dr = { -1, 0, 1, 0 }; // dr, dc(상, 우, 하, 좌 좌표)
    static int[] dc = { 0, 1, 0, -1 };
    static int answer;

    /*
     * Point(좌표 정보를 나타내는 클래스)
     */
    static class Point {
        boolean containTree; // containTree(나무 존재 여부)
        List<Person> hiders = new ArrayList<>(); // hiders(좌표에 위치하는 도망자들)

        public Point() {
            this.containTree = false;
        }
    }

    /*
     * Person(사람 정보를 나타내는 클래스)
     */
    static class Person {
        int id; // id(사람 순번)
        int r, c; // r, c(사람 위치)
        int d; // d(이동 방향)

        public Person(int id, int r, int c, int d) {
            this.id = id;
            this.r = r;
            this.c = c;
            this.d = d;
        }

        public void changeHiderDirection() { // 도망자 이동 방향 변경
            this.d = (this.d + 2) % 4;
        }
    }

    /*
     * 술래잡기 준비
     */
    static void init(StringTokenizer st) throws IOException {
        N = Integer.parseInt(st.nextToken()); // N(격자의 크기)
        M = Integer.parseInt(st.nextToken()); // M(도망자의 수)
        H = Integer.parseInt(st.nextToken()); // H(나무의 수)
        K = Integer.parseInt(st.nextToken()); // K(턴의 수)

        board = new Point[N][N]; // board(격자의 정보)
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = new Point();
            }
        }

        hiders = new Person[M]; // hiders(도망자들 저장 배열)
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());
            Person hider = new Person(i, x, y, d); // 도망자의 위치와 이동 방법 저장
            hiders[i] = hider;
            board[x][y].hiders.add(hider);
        }

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            board[x][y].containTree = true; // 나무의 위치를 저장
        }

        seeker = new Person(0, (N - 1) / 2, (N - 1) / 2, 0); // 술래 저장
        seekerLength = 0;
        seekerCount = 0;
    }

    /*
     * 술래잡기
     */
    static void move() {
        for (int i = 0; i < K; i++) {
            moveHiders(); // 도망자 이동하기
            moveSeeker(); // 술래 이동하기
            catchHiders(i + 1); // 도망자 잡기
        }
    }

    /*
     * 이동할 수 있는 도망자 이동하기
     */
    static void moveHiders() {
        for (int i = 0; i < M; i++) {
            Person hider = hiders[i];
            if (hider == null) {
                continue;
            }
            if (canMove(hider)) { // 이동할 수 있는 도망자라면
                int nr = hider.r + dr[hider.d];
                int nc = hider.c + dc[hider.d];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                    hider.changeHiderDirection(); // 격자를 벗어나는 경우, 방향을 바꿔 이동
                    nr = hider.r + dr[hider.d];
                    nc = hider.c + dc[hider.d];
                }
                if (nr == seeker.r && nc == seeker.c) {
                    continue; // 술래가 있는 경우, 움직이지 않음
                }
                board[hider.r][hider.c].hiders.remove(hider); // 해당 칸으로 이동
                hider.r = nr;
                hider.c = nc;
                board[hider.r][hider.c].hiders.add(hider);
            }
        }
    }

    /*
     * 도망자 이동 가능 여부
     */
    static boolean canMove(Person hider) {
        int distance = Math.abs(hider.r - seeker.r) + Math.abs(hider.c - seeker.c);
        if (distance <= 3) { // 현재 술래와의 거리가 3 이하인 도망자만 움직임
            return true;
        }
        return false;
    }

    /*
     * 술래 이동하기
     */
    static void moveSeeker() {
        int nr = seeker.r + dr[seeker.d]; // nr, nc(술래가 이동할 다음 방향)
        int nc = seeker.c + dc[seeker.d];
        seekerLength++;

        if (seekerLength == moveLength) { // 달팽이 모양의 한 획에서 현재 술래가 이동할 거리를 모두 이동했다면
            seekerLength = 0;
            seekerCount++;
            if (!opposite) { // 순방향일 때 이동 방향 틀어주기
                if (seeker.d == 3) {
                    seeker.d = 0; // 좌 방향이었다면 상 방향으로 틀어주기
                } else {
                    seeker.d++; // 상, 우, 하 방향이었다면, 우, 하, 좌 방향으로 틀어주기
                }
            } else { // 역방향일 때의 이동 방향으로 틀어주기
                if (seeker.d == 0) {
                    seeker.d = 3; // 상 방향이었다면 좌 방향으로 틀어주기
                } else {
                    seeker.d--; // 우, 하, 좌 방향이었다면, 상, 우, 좌 방향으로 틀어주기
                }
            }
            if (seekerCount == 2) { // 현재 길이로 2번 이동했다면
                seekerCount = 0;
                if (!opposite) { // 순방향이라면, 달팽이 모양의 한 획에서 이동해야 하는 길이 1 증가
                    moveLength++;
                } else { // 역방향이라면, 달팽이 모양의 한 획에서 이동해야 히는 길이 1 감소
                    moveLength--;
                }
            }
        }

        if (nr == 0 && nc == 0) { // 양끝(1행, 1열)에 도달한다면
            opposite = true; // 역방향으로 전환
            moveLength = N - 1;
            seeker.d = 2; // 하 방향부터 시작
            seekerCount = -1;
            seekerLength = 0;
        } else if (nr == (N - 1) / 2 && nc == (N - 1) / 2) { // 정중앙에 도달한다면
            opposite = false; // 순방향으로 전환
            moveLength = 1;
            seeker.d = 0; // 상 방향부터 시작
            seekerCount = 0;
            seekerLength = 0;
        }

        seeker.r = nr;
        seeker.c = nc;
    }

    /*
     * 도망자를 잡아 점수 획득
     */
    static void catchHiders(int turn) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            int nr = seeker.r + dr[seeker.d] * i; // nr, nc(도망자를 잡을 위치)
            int nc = seeker.c + dc[seeker.d] * i;
            if (nr < 0 || nr >= N || nc < 0 || nc >= N) { // 격자를 벗어났다면 넘어감
                continue;
            }
            Point point = board[nr][nc];
            if (point.containTree) { // 만약 나무가 놓여 있는 칸이라면 넘어감
                continue;
            }
            if (!point.hiders.isEmpty()) { // 도망자가 있을 경우 도망자를 잡음
                count += point.hiders.size();
                for (Person hider : point.hiders) { // 도망자 삭제
                    hiders[hider.id] = null;
                }
                point.hiders = new ArrayList<>();
            }
        }
        answer += (turn * count); // 현재 턴에서 잡힌 도망자의 수만큼의 점수 추가
    }

    /*
     * 술래잡기 결과 출력
     */
    static void print() {
        System.out.println(answer); // 술래가 얻게 되는 총 점수 출력
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        init(st);
        move();
        print();
    }
}