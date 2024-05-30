package Baekjoon.B_16562;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 친구의 친구들을 모두 모아 같은 집합으로 합치도록 한 후, 집합의 대표 노드에 해당하는 친구와 친구를 하도록 함
 */

/* 
 * 손으로 풀어보기
 * 1. 친구 관계에 따라 친구비가 더 적은 친구를 대표 노드로 하여 집합으로 묶도록 함
 * 2. 집합의 대표 노드에 해당하는 친구와 친구를 하도록 하여 비용을 증가
 * 3. 가지고 있는 돈으로 모든 학생을 친구로 만들 수 있다면, 친구로 만드는데 드는 최소비용을 출력
 * 4. 만약 친구를 다 사귈 수 없다면 Oh no를 출력
 */

/*
 * 16562) 친구비 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, M, K;
    static int[] cost; // cost(각각의 학생이 원하는 친구비)
    static int[] parent; // parent(각 원소의 대표 노드 정보를 담은 배열)
    static long answer; // answer(친구로 만드는데 드는 최소비용)

    /*
     * 데이터 준비하기
     */
    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        cost = new int[N + 1]; // 친구비 저장
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        parent = new int[N + 1]; // 대표 노드를 자기 자신으로 초기화
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) { // 친구 관계에 따라 집합으로 묶어주기
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            union(v, w);
        }
    }

    /*
     * union 연산
     */
    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            if (cost[a] > cost[b]) { // 친구비가 더 적은 친구를 대표 노드로 하여 집합을 묶도록 함
                parent[a] = b;
            } else {
                parent[b] = a;
            }
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
     * 친구로 만드는데 드는 최소비용 찾기
     */
    static void calculate() {
        boolean[] visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            int index = find(i); // index(집합의 대표 노드 인덱스)
            if (!visited[index]) {
                answer += cost[index];
                visited[index] = true;
            }
            visited[i] = true;
        }
    }

    /*
     * 친구로 만드는데 드는 최소비용 출력
     */
    static void print() {
        if (answer <= K) {
            System.out.println(answer);
            return;
        }
        System.out.println("Oh no");
    }

    public static void main(String[] args) throws IOException {
        init();
        calculate();
        print();
    }
}
