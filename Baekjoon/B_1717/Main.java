package Baekjoon.B_1717;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 0일 경우 두 원소를 합쳐 같은 집합이 되도록 대표 노드를 변경시켜주고, 
 *   1일 경우 두 원소의 대표 노드가 같은지 확인해 같다면 YES, 아니라면 NO를 출력함
 */

/* 
 * 손으로 풀어보기
 * 1. 0일 경우, 두 원소의 대표 노드를 찾아 합치도록 함
 * 2. 1일 경우, 두 원소의 대표 노드값이 같다면 YES, 아니라면 NO를 출력함
 */

/*
 * 1717) 집합의_표현 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n, m;
    static int[] parent; // parent(각 원소의 대표 노드 정보를 담은 배열)

    /*
     * 집합 표현 준비하기
     */
    static void init(StringTokenizer st) {
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) { // 우선 대표 노드를 자기 자신으로 초기화
            parent[i] = i;
        }
    }

    /*
     * 합집합 또는 포함여부 확인하기
     */
    static void query(StringTokenizer st) throws IOException {
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int commend = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (commend == 0) { // 합집합 연산일 경우
                union(a, b);
            } else { // 두 원소가 같은 집합에 포함되었는지 확인하는 연산일 경우
                if (find(a) == find(b)) {
                    print("YES");
                } else {
                    print("NO");
                }
            }
        }
    }

    /*
     * 합집합 만들기
     */
    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[b] = a;
        }
    }

    /*
     * 같은 집합에 포함되어 있는지 확인하기
     */
    static int find(int a) {
        if (a == parent[a]) {
            return a;
        } else {
            return parent[a] = find(parent[a]);
        }
    }

    /*
     * 같은 집합에 포함되어 있는지 확인해 결과 출력하기
     */
    static void print(String answer) {
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        init(st);
        query(st);
    }
}