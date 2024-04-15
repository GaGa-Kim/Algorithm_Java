package CodeTree.stepping_on_grid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 격자_밟기
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static boolean[][] visited;
    static Person A, B;
    static int[] dr = { -1, 1, 0, 0 }; // dr, dc(상하좌우 좌표)
    static int[] dc = { 0, 0, -1, 1 };
    static int answer; // answer(가지수)

    static class Person {
        int r, c;

        public Person(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    /*
     * 1. 격자 밟기 준비
     */
    static void init(StringTokenizer st) throws IOException {
        int moveCount = 25; // moveCount(움직여야 하는 칸 수)
        visited = new boolean[5][5]; // visited(이동 유무 배열)

        int K = Integer.parseInt(st.nextToken()); // K(이동할 수 없는 칸 수)
        for (int i = 0; i < K; i++) { // 이동할 수 없는 칸 처리
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            visited[r][c] = true;
            moveCount--;
        }

        A = new Person(0, 0); // A 저장
        visited[A.r][A.c] = true;
        moveCount--;

        B = new Person(4, 4); // B 저장
        visited[B.r][B.c] = true;
        moveCount--;

        move("A", moveCount); // A부터 시작해 움직이기
    }

    /*
     * 2. 움직이기
     */
    static void move(String moveAorB, int moveCount) {
        if (moveAorB.equals("A")) {
            moveA(moveCount);
        }

        if (moveAorB.equals("B")) {
            moveB(moveCount);
        }
    }

    /*
     * A 이동
     */
    static void moveA(int moveCount) {
        int ar = A.r; // ar, ac(A의 기존 위치)
        int ac = A.c;

        for (int d = 0; d < 4; d++) {
            int anr = ar + dr[d]; // anr, anc(A의 다음 위치)
            int anc = ac + dc[d];

            if (anr < 0 || anr >= 5 || anc < 0 || anc >= 5 || visited[anr][anc]) { // 격자를 벗어났거나 방문한 곳이라면 이동 불가
                continue;
            }

            visited[anr][anc] = true;
            A.r = anr;
            A.c = anc;
            move("B", moveCount - 1); // 다음으로 B 이동

            visited[anr][anc] = false;
            A.r = ar;
            A.c = ac;
        }
    }

    /*
     * B 이동
     */
    static void moveB(int moveCount) {
        int br = B.r; // br, bc(B의 기존 위치)
        int bc = B.c;

        for (int d = 0; d < 4; d++) {
            int bnr = br + dr[d]; // bnr, bnc(B의 다음 위치)
            int bnc = bc + dc[d];

            if (bnr < 0 || bnr >= 5 || bnc < 0 || bnc >= 5) { // 격자를 벗어났다면 이동 불가 (A와 B가 만날 경우를 위해 방문한 유무는 추후 확인)
                continue;
            }

            if (moveCount == 0) { // 모두 움직인 후 A와 B의 위치가 만난다면 가지수 증가
                if (A.r == bnr && A.c == bnc) {
                    answer++;
                    return;
                } else {
                    continue; // A와 B의 위치가 만나지 않는다면 다음 방향 탐색
                }
            }

            if (visited[bnr][bnc]) { // 방문한 곳이라면 이동 불가
                continue;
            }

            visited[bnr][bnc] = true;
            B.r = bnr;
            B.c = bnc;
            move("A", moveCount - 1); // 다음으로 A 이동

            visited[bnr][bnc] = false;
            B.r = br;
            B.c = bc;
        }
    }

    /*
     * 3. 가지수 출력하기
     */
    static void print() {
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        init(st);
        print();
    }
}