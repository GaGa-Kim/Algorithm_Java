package Baekjoon.B_14712;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 넴모가 올라간 칸 중 네 개가 2 x 2 사각형을 이루는지 확인하여 하나도 없다면 경우의 수 증가
 *   이후 비어 있는 칸을 임의로 골라 넴모를 하나 올리기를 반복하며 없앨 수 있는 넴모가 없어 게임을 그만둘 수 있는 경우의 수를 구함
 */

/* 
 * 손으로 풀어보기
 * 1. 넴모가 올라간 칸 중 2 x 2 사각형을 이루는지 확인
 *    격자판 위에 없앨 수 있는 넴모가 없어 게임을 그만둘 수 있다면 경우의 수 증가
 * 2. 비어 있는 칸을 임의로 골라 넴모를 하나 올리기
 * 3. 모든 칸만큼 넴모를 사용할 때까지 반복
 * 4. 경우의 수를 출력
 */

/*
 * 14712) 넴모넴모_(Easy)
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, M;
    static boolean[][] visited; // visited(격자 사용 유무 배열)
    static int answer = 0; // answer(게임을 그만두었을 때 나올 수 있는 배치의 가짓수)

    /*
     * 넴모넴모 게임 준비하기
     */
    static void init(StringTokenizer st) {
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N][M];
        dfs(0, 0);
    }

    /*
     * 넴모넴모 게임하기
     */
    static void dfs(int count, int start) {
        if (!findFour(count)) { // 격자판 위에 없앨 수 있는 넴모가 없다면, 게임을 그만둘 수 있는 경우의 수 증가
            answer++;
        }

        if (count == N * M) { // 모든 격자에 넴모가 올라왔다면, 종료
            return;
        }

        for (int i = start; i < N * M; i++) { // 격자를 선택해 넴모로 채움
            int r = i / M;
            int c = i % M;
            if (visited[r][c]) {
                continue;
            }
            visited[r][c] = true;
            dfs(count + 1, i + 1);
            visited[r][c] = false;
        }
    }

    /*
     * 2 x 2 사각형이 있는지 확인하기
     */
    static boolean findFour(int count) {
        if (count < 4) { // 넴모를 4개보다 적게 올렸을 경우
            return false; // 없앨 수 있는 넴모가 없음
        } else { // 넴모를 4개 이상 올렸을 경우
            for (int i = 0; i < N - 1; i++) {
                for (int j = 0; j < M - 1; j++) {
                    if (visited[i][j] && visited[i][j + 1] && visited[i + 1][j] && visited[i + 1][j + 1]) {
                        return true; // 없앨 수 있는 넴모가 있음
                    }
                }
            }
            return false; // 없앨 수 있는 넴모가 없음
        }
    }

    /*
     * 게임을 그만두었을 때 나올 수 있는 배치의 가짓수 출력하기
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