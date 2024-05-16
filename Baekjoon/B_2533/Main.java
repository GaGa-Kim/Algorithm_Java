package Baekjoon.B_2533;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 자식 노드부터 탐색하며 자신이 얼리 어답터가 아닐 경우와 얼리 어답터일 경우를 나누어 구하며 
 *   필요한 최소 얼리 어답터의 수를 구하도록 함
 */

/* 
 * 손으로 풀어보기
 * 1. 자식 노드부터 탐색하며 자신이 얼리 어답터가 아닐 경우와 얼리 어답터일 경우 나누어 구함
 * 2. 자신이 얼리 어답터가 아닐 경우(0), 자식 노드가 모두 얼리 어답터여야 함  
 *    자신이 얼리 어답터일 경우(1), 자식 노드들은 얼리 어답터일 수도 있고, 아닐 수도 있음
 * 3. 루트 노드까지 이를 구한 후, 필요한 최소 얼리 어답터의 수를 출력하도록 함
 */

/*
 * 2533) 사회망_서비스(SNS)
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static ArrayList<Integer>[] arr; // arr(친구 관계 정보)
    static boolean[] visited; // visited(노드 방문 유무 배열)
    static int[][] d; // d(해당 노드가 얼리 어답터가 아닐 경우(0)와 얼리 어답터일 경우(1)에 따른 얼리 어답터 수)

    /*
     * 얼리 어답터 수 찾기 준비하기
     */
    static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        arr = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        d = new int[N + 1][2];

        for (int i = 1; i <= N; i++) { // 친구 관계 정보를 담기 위한 연결 리스트 초기화
            arr[i] = new ArrayList<Integer>();
        }

        StringTokenizer st;
        for (int i = 1; i < N; i++) { // 친구 관계 정보 저장
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            arr[u].add(v);
            arr[v].add(u);
        }
    }

    /*
     * 현재 노드를 루트로 하는 트리의 얼리 어답터의 최소 수
     */
    static void dfs(int node) {
        visited[node] = true;
        d[node][1] = 1;

        for (int next : arr[node]) {
            if (visited[next]) {
                continue;
            }
            dfs(next);
            d[node][0] += d[next][1]; // 현재 노드가 얼리 어답터가 아닐 경우, 자식 노드가 모두 얼리 어답터여야 함
            d[node][1] += Math.min(d[next][0], d[next][1]); // 현재 노드가 얼리 어답터일 경우, 자식 노드들은 얼리 어답터일 수도 있고, 아닐 수도 있음
        }
    }

    /*
     * 필요한 최소 얼리 어답터의 수 출력하기
     */
    static void print() {
        System.out.println(Math.min(d[1][0], d[1][1])); // 루트 노드의 최소 얼리 어답터 수 출력
    }

    public static void main(String[] args) throws IOException {
        init();
        dfs(1);
        print();
    }
}