package Baekjoon.B_21611;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 마법에 따라 구슬을 제거하고, 구슬의 위치를 이동시키며 폭발한 구슬의 개수들을 찾도록 함
 */

/* 
 * 손으로 풀어보기
 * 1. 블리자드 마법 시전 : 4가지 방향과 거리에 따라 그 칸에 있는 구슬을 모두 파괴
 * 2. 블리자드 마법 시전 : 파괴된 칸이 없도록 달팽이 모양으로 회전하며 구슬 이동
 * 3. 구슬 폭발 : 달팽이 모양으로 4개 이상 연속하는 구슬이 있을 경우 모두 파괴
 * 4. 구슬 폭발 : 팔괴된 칸이 없도록 달팽이 모양으로 회전하며 구슬 이동
 * 5. 구슬 폭발 : 구슬을 이동하며 더 이상 폭발하는 구슬이 없을 때까지 반복
 * 6. 구슬 변화 : 달팽이 모양으로 돌아가며 그룹을 찾아, 그룹에 들어 있는 구슬의 개수와 그룹을 이루고 있는 구슬의 번호로 칸 채우기
 * 7. 위를 M번 시전한 후 폭발한 각 구슬의 개수에 따라 정답 출력
 */

/*
 * 21611) 마법사_상어와_블리자드
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, M;
    static int[][] board; // board(구슬을 담은 좌표 배열)
    static List<Point> moveWay = new ArrayList<Point>(); // moveWay(달팽이 모양으로 움직일 때의 좌표)
    static int sum = 0; // sum(번호에 따른 폭발한 구슬의 개수)

    /*
     * Point(좌표 정보를 담은 클래스)
     */
    static class Point {
        int r, c; // r, c(좌표 위치)

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    /*
     * Move(좌표에 따른 움직임 정보를 담은 클래스)
     */
    static class Move {
        Point point; // point(좌표 위치)
        int dir; // dir(움직이는 방향)
        int cycle; // cycle(같은 방향으로 움직이는 길이)

        public Move(int r, int c, int dir, int cycle) {
            this.point = new Point(r, c);
            this.dir = dir;
            this.cycle = cycle;
        }
    }

    /*
     * Group(연속하는 구슬을 묶은 하나의 그룹)
     */
    static class Group {
        int combo; // combo(그룹에 들어있는 구슬의 개수)
        int number; // number(그룹을 이루고 있는 구슬의 번호)

        public Group(int combo, int number) {
            this.combo = combo;
            this.number = number;
        }
    }

    /*
     * 마법 시전 준비하기
     */
    static void init(StringTokenizer st) throws IOException {
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][N]; // 좌표 저장하기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        calculateSnailCoord(); // 저장한 좌표 배열에 따라 달팽이 모양 순으로 좌표 저장하기

        for (int i = 0; i < M; i++) { // 블리자드 총 M번 시전
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            attack(d - 1, s); // 블리자드 마법 시전
            move(); // 구슬 이동
            while (bomb()) { // 구슬이 폭발 가능할 동안 계속 폭발
                move(); // 구슬 이동
            }
            change(); // 구슬 변화
        }
    }

    /*
     * 달팽이 모양의 좌표 구하기
     */
    static void calculateSnailCoord() {
        int[] snailR = { 0, 1, 0, -1 }; // snailR, snailC(달팽이 모양인 좌, 하, 우, 상 이동 방향)
        int[] snailC = { -1, 0, 1, 0 };

        Queue<Move> queue = new LinkedList<Move>(); // 상어 위치부터 시작해 달팽이 모양으로 움직이며 좌표 저장
        queue.add(new Move(N / 2, N / 2, 0, 0));

        int count = 0;
        while (true) {
            Move now = queue.poll();
            int r = now.point.r;
            int c = now.point.c;
            int dir = now.dir;
            int cycle = now.cycle;

            if (count % 2 == 0) { // 1, 2, 3, 4, ... 씩의 길이로 증가하며 2번씩 반복하여 이동
                cycle++;
            }
            count++;

            for (int i = 0; i < cycle; i++) {
                r += snailR[dir];
                c += snailC[dir];
                moveWay.add(new Point(r, c));

                if (r == 0 && c == 0) { // 가장 왼쪽 윗 칸에 도달할 경우 탐색 종료
                    return;
                }
            }
            dir++;
            dir %= 4;

            queue.add(new Move(r, c, dir, cycle));
        }
    }

    /*
     * 블리자드 마법 시전
     */
    static void attack(int direction, int length) {
        int[] dr = { -1, 1, 0, 0 }; // dr, dc(상하좌우 이동 방향)
        int[] dc = { 0, 0, -1, 1 };

        int r = N / 2;
        int c = N / 2;
        for (int i = 0; i < length; i++) {
            r += dr[direction];
            c += dc[direction];
            board[r][c] = 0;
        }
    }

    /*
     * 구슬 이동하기
     */
    static void move() {
        Queue<Integer> queue = new LinkedList<Integer>(); // 0이 아닌 구슬 번호들을 가져옴
        for (int i = 0; i < moveWay.size(); i++) {
            Point point = moveWay.get(i);
            if (board[point.r][point.c] != 0) {
                queue.add(board[point.r][point.c]);
            }
        }

        for (int i = 0; i < N; i++) { // 구슬을 다시 채우기 위해 좌표 초기화
            for (int j = 0; j < N; j++) {
                board[i][j] = 0;
            }
        }

        int index = 0;
        while (!queue.isEmpty()) { // 좌표를 새로운 구슬 번호로 갱신
            Point point = moveWay.get(index);
            int number = queue.poll();
            board[point.r][point.c] = number;
            index++;
        }
    }

    /*
     * 구슬 폭발하기
     */
    static boolean bomb() {
        boolean canBomb = false; // canBomb(구슬 폭발 여부, 더 이상 폭발하지 않는지 확인)

        Queue<Point> temp = new LinkedList<Point>(); // temp(하나의 구슬 번호에 대해 연속하는 구슬을 탐색하는 임시 큐)
        Queue<Point> remove = new LinkedList<Point>(); // remove(삭제되어야 하는 구슬들을 담은 큐)

        Point point = moveWay.get(0); // 상어 다음 위치부터 시작해 구슬 폭발이 가능한지 탐색
        int number = board[point.r][point.c];
        int combo = 1;
        temp.add(new Point(point.r, point.c));

        for (int i = 1; i < moveWay.size(); i++) {
            Point now = moveWay.get(i);

            if (board[now.r][now.c] == 0) { // 모든 구슬을 탐색했다면
                break;
            }

            if (board[now.r][now.c] == number) { // 연속하는 구슬이라면 계속 탐색
                combo++;
                temp.add(new Point(now.r, now.c));
            } else { // 연속하는 구슬이 아니라면 현재 구슬 번호 탐색 종료
                if (combo >= 4) {
                    canBomb = true;
                    while (!temp.isEmpty()) {
                        remove.add(temp.poll());
                    }
                }
                number = board[now.r][now.c]; // 새로운 구슬 번호로 다시 탐색 시작
                combo = 1;
                temp.clear();
                temp.add(new Point(now.r, now.c));
            }
        }

        if (combo >= 4) {
            canBomb = true;
            while (!temp.isEmpty()) {
                remove.add(temp.poll());
            }
        }

        while (!remove.isEmpty()) { // 폭발한 구슬 번호를 0으로 처리하고, 폭발한 구슬 개수를 sum에 추가
            Point removePoint = remove.poll();
            sum += board[removePoint.r][removePoint.c];
            board[removePoint.r][removePoint.c] = 0;
        }

        return canBomb;
    }

    /*
     * 구슬 변화하기
     */
    static void change() {
        Queue<Group> queue = new LinkedList<Group>();
        Point point = moveWay.get(0);
        int number = board[point.r][point.c];
        int combo = 1;

        for (int i = 1; i < moveWay.size(); i++) {
            Point now = moveWay.get(i);

            if (board[now.r][now.c] == 0) { // 모든 구슬을 탐색했다면
                if (number != 0) {
                    queue.add(new Group(combo, number));
                    break;
                }
            }

            if (board[now.r][now.c] == number) { // 동일한 구슬 번호라면 계속 탐색
                combo++;
            } else { // 동일한 구슬 번호가 아니라면 탐색 종료
                queue.add(new Group(combo, number));
                number = board[now.r][now.c]; // 새로운 구슬 번호로 다시 탐색 시작
                combo = 1;
            }
        }

        if (queue.size() == 0 && combo == 1) {
            if (number != 0) {
                queue.add(new Group(combo, number));
            }
        }

        for (int i = 0; i < N; i++) { // 구슬을 다시 채우기 위해 좌표 초기화
            for (int j = 0; j < N; j++) {
                board[i][j] = 0;
            }
        }

        for (int i = 0; i < moveWay.size(); i += 2) { // 구슬의 개수, 구슬의 번호로 칸 채우기
            Point point1 = moveWay.get(i);
            Point point2 = moveWay.get(i + 1);
            if (queue.size() == 0) {
                break;
            }
            Group group = queue.poll();
            board[point1.r][point1.c] = group.combo;
            board[point2.r][point2.c] = group.number;
        }
    }

    /*
     * 폭발한 각 구슬의 개수에 따라 정답 출력하기
     */
    static void print() {
        System.out.println(sum);
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        init(st);
        print();
    }
}