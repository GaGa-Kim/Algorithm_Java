package Baekjoon.B_20056;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 파이어볼의 정보에 따라 파이어볼을 배열에 저장한 후, 이를 이동하고 소멸하도록 함
 *   또한 조건에 따라 합쳐 4개의 파이어볼로 나누도록 함
 *   이를 K번 반복한 후, 남아있는 파이어볼 질량의 합을 구하도록 함
 */

/* 
 * 손으로 풀어보기
 * 1. 파이어볼의 정보에 따라 파이어볼을 저장
 * 2. 모든 파이어볼을 방향과 속력에 따라 이동
 *    이때 1번과 N번은 이어져 있으므로 이를 위해 N으로 나눈 나머지 값의 위치로 이동하도록 함
 * 3. 이동이 모두 끝난 뒤, 2개 이상의 파이어볼이 있다면 분열
 *    모든 파이어볼을 합쳐 질량, 속력을 구하고 이들의 방향에 따라 만들어진 파이어볼을 새로 저장
 *    이때 질량이 0이 된다면 파이어볼을 소멸되게 됨
 * 4. 이를 K번 반복한 후, 남아있는 파이어볼 질량의 합을 출력 
 */

/*
 * 20056) 마법사_상어와_파이어볼 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, M, K;
    static ArrayList<Fireball> fireballs; // fireballs(전체 파이어볼 리스트)
    static ArrayList<Fireball>[][] board; // board(파이어볼 좌표 정보)
    static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 }; // dr, dc(이동 좌표)
    static int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };

    /*
     * Fireball(파이어볼 정보를 담을 클래스)
     */
    static class Fireball {
        int r, c;
        int m, d, s;

        public Fireball(int r, int c, int m, int d, int s) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.d = d;
            this.s = s;
        }
    }

    /*
     * 데이터 준비하기
     */
    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        fireballs = new ArrayList<Fireball>(); // 파이어볼 저장
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            fireballs.add(new Fireball(r, c, m, d, s));
        }

        board = new ArrayList[N][N]; // 좌표 초기화하기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = new ArrayList<Fireball>();
            }
        }
    }

    /*
     * 모든 파이어볼을 이동하기
     */
    static void move() {
        for (Fireball fireball : fireballs) { // 모든 파이어볼을 이동하여 좌표에 저장
            int nr = (fireball.r + N + dr[fireball.d] * (fireball.s % N)) % N; // 1번과 N번은 연결되어 있음
            int nc = (fireball.c + N + dc[fireball.d] * (fireball.s % N)) % N;
            fireball.r = nr;
            fireball.c = nc;
            board[nr][nc].add(fireball);
        }
    }

    /*
     * 2개 이상의 파이어볼이 있다면 분열하기
     */
    static void split() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j].size() >= 2) { // 2개 이상의 파이어볼이 있는 칸이라면
                    int m_sum = 0; // m_sum(합쳐진 파이어볼 질량의 합)
                    int s_sum = 0; // s_sum(합쳐진 파이어볼 속력의 합)
                    int count = board[i][j].size(); // count(합쳐진 파이어볼의 개수)
                    boolean all_odd = true, all_even = true; // all_odd, all_even(합쳐지는 파이어볼의 방향이 모두 홀수인지 짝수인지 여부)

                    while (board[i][j].size() != 0) { // 같은 칸에 있는 파이어볼을 모두 하나로 합치도록 함
                        Fireball fireball = board[i][j].remove(0);
                        m_sum += fireball.m;
                        s_sum += fireball.s;
                        if (fireball.d % 2 == 0) {
                            all_odd = false;
                        } else {
                            all_even = false;
                        }
                        fireballs.remove(fireball);
                    }

                    int nm = m_sum / 5; // nm(나누어진 파이어볼의 질량)
                    if (nm == 0) { // 질량이 0인 파이어볼은 소멸
                        continue;
                    }

                    int ns = s_sum / count; // ns(나누어진 파이어볼의 속력)

                    if (all_odd || all_even) { // 합쳐지는 파이어볼의 방향이 모두 홀수이거나 모두 짝수라면
                        for (int d = 0; d < 8; d += 2) { // 0, 2, 4, 6
                            fireballs.add(new Fireball(i, j, nm, d, ns));
                        }
                    } else { // 그렇지 않으면
                        for (int d = 1; d < 8; d += 2) { // 1, 3, 5, 7
                            fireballs.add(new Fireball(i, j, nm, d, ns));
                        }
                    }
                } else { // 0개, 1개의 파이어볼이 있는 칸이라면
                    board[i][j].clear();
                }
            }
        }
    }

    /*
     * 남아있는 파이어볼 질량의 합 출력하기
     */
    static void print() {
        int answer = 0;
        for (Fireball fireball : fireballs) {
            answer += fireball.m;
        }
        System.out.print(answer);
    }

    public static void main(String[] args) throws IOException {
        init();
        for (int i = 0; i < K; i++) {
            move();
            split();
        }
        print();
    }
}