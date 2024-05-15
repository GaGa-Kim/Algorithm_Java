package Baekjoon.B_11265;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 파티장끼리 연결된 도로를 저장한 후,   
 *   플로이드 워셜 알고리즘을 통해 다른 파티장에 시간 안에 도착할 수 있는지 확인
 */

/* 
 * 손으로 풀어보기
 * 1. 파티장끼리 연결된 도로를 저장
 * 2. 플로이드 워셜 알고리즘을 통해 다른 파티장에 시간 안에 도착할 수 있는지 확인
 * 3. 시간내에 다른 파티장에 도착할 수 있으면 "Enjoy other party" 출력
 * 4. 시간내에 도착할 수 없다면 "Stay here" 출력
 */

/*
 * 11265) 끝나지_않는_파티
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, M;
    static int[][] dist; // dist(파티장끼리 연결된 도로 정보)

    /*
     * 파티 준비하기
     */
    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dist = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                dist[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        floyd_warshall();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            find(A, B, C);
        }
    }

    /*
     * 플로이드 워셜 알고리즘으로 다른 파티를 거쳐 최단 경로로 이동할 수 있도록 갱신
     */
    static void floyd_warshall() {
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
    }

    /*
     * 시간내에 다른 파티장에 도착할 수 있는지 찾기
     */
    static void find(int now, int next, int time) {
        if (dist[now - 1][next - 1] <= time) {
            print(true);
        } else {
            print(false);
        }
    }

    /*
     * 시간내에 다른 파티장에 도착할 수 있는지 여부 출력하기
     */
    static void print(boolean canArrive) {
        if (canArrive) {
            System.out.println("Enjoy other party");
        } else {
            System.out.println("Stay here");
        }
    }

    public static void main(String[] args) throws IOException {
        init();
    }
}