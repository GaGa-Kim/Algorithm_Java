package Baekjoon.B_16987;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 자신을 제외한 계란들 중 계란치기를 하는 경우의 수를 모두 구해 가장 많이 계란이 깨지는 경우를 찾도록 함
 */

/* 
 * 손으로 풀어보기
 * 1. 계란치기를 하는 모든 경우의 수를 구하도록 함
 * 2. 가장 많이 계란이 깨지는 경우의 깰 수 있는 계란의 최대 개수를 출력
 */

/*
 * 16987) 계란으로_계란치기
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static Egg[] eggs; // eggs(계란 정보들을 담은 배열)
    static int answer; // answer(깰 수 있는 계란의 최대 개수)

    /*
     * Egg (계란 정보를 나타내는 클래스)
     */
    static class Egg {
        int S;
        int W;

        public Egg(int s, int w) {
            this.S = s;
            this.W = w;
        }
    }

    /*
     * 계란치기 준비하기
     */
    static void init(StringTokenizer st) throws IOException {
        N = Integer.parseInt(st.nextToken());

        eggs = new Egg[N];
        for (int i = 0; i < N; i++) { // 계란 저장하기
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            eggs[i] = new Egg(s, w);
        }
    }

    /*
     * 계란치기 모든 경우의 수
     */
    static void beat(int depth, int count) {
        if (depth == N) { // 모든 계란치기를 끝냈다면 가장 많이 깨뜨린 계란의 최대 개수로 갱신
            answer = Math.max(answer, count);
            return;
        }

        if (eggs[depth].S <= 0 || count == N - 1) { // 손에 들고 있는 계란이 이미 깨졌거나 모든 계란이 이미 깨져있다면
            beat(depth + 1, count); // 다음 계란 탐색
            return;
        }

        for (int i = 0; i < N; i++) {
            if (i == depth) { // 손에 들고 있는 계란과 상대 계란이 같은 계란이라면 넘어감
                continue;
            }

            if (eggs[i].S <= 0) { // 상대 계란이 이미 깨져있다면 넘어감
                continue;
            }

            eggs[depth].S -= eggs[i].W; // 계란치기
            eggs[i].S -= eggs[depth].W;

            int nowCount = count;
            if (eggs[depth].S <= 0) { // 계란치기 결과에 따라 깰 수 있는 계란의 수 갱신
                nowCount++;
            }
            if (eggs[i].S <= 0) {
                nowCount++;
            }

            beat(depth + 1, nowCount); // 다음 계란치기 반복

            eggs[depth].S += eggs[i].W; // 다음 경우의 수 탐색을 위해 복구
            eggs[i].S += eggs[depth].W;
        }
    }

    /*
     * 깰 수 있는 계란의 최대 개수 출력하기
     */
    static void print() {
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        init(st);
        beat(0, 0);
        print();
    }
}