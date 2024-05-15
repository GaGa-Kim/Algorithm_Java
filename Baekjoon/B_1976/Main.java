package Baekjoon.B_1976;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 연결된 도시들을 집합으로 묶은 후, 여행 계획에 속한 도시들이 모두 같은 집합에 속하는지 확인하도록 함
 */

/* 
 * 손으로 풀어보기
 * 1. 연결된 도시들을 집합으로 묶기
 * 2. 여행 계획에 속한 도시들이 모두 같은 집합에 속하는지 확인
 * 3. 모두 같은 집합이라면 YES, 아니라면 NO를 출력
 */

/*
 * 1976) 여행_가자
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, M;
    static int[][] city; // city(도시의 연결 정보 배열)
    static int[] parent; // parent(각 도시의 대표 노드 정보를 담은 배열)
    static int[] travel; // travel(여행 계획)

    /*
     * 여행 준비하기
     */
    static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        city = new int[N + 1][N + 1]; // 도시의 연결 정보 저장
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                city[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        travel = new int[M]; // 여행 계획 저장
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            travel[i] = Integer.parseInt(st.nextToken());
        }
    }

    /*
     * 연결된 도시끼리 집합으로 분리하기
     */
    static void split_city() {
        parent = new int[N + 1]; // 대표 노드를 자기 자신으로 초기화
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (city[i][j] == 1) { // 도시가 연결돼 있으면 같은 집합으로 합치기
                    union(i, j);
                }
            }
        }
    }

    /*
     * union 연산
     */
    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[b] = a;
        }
    }

    /*
     * find 연산
     */
    static int find(int a) {
        if (a == parent[a]) {
            return a;
        } else {
            return parent[a] = find(parent[a]);
        }
    }

    /*
     * 모두 같은 집합에 속하는지 여부에 따라 결과 출력하기
     */
    static void print() {
        int index = find(travel[0]); // index(여행 계획의 대표 노드)
        for (int i = 1; i < M; i++) {
            if (index != find(travel[i])) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }

    public static void main(String[] args) throws IOException {
        init();
        split_city();
        print();
    }
}