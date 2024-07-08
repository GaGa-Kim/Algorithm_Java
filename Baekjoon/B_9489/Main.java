package Baekjoon.B_9489;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : k와 조부모는 같으면서 부모는 다른 노드들의 개수를 찾도록 함
 */

/* 
 * 손으로 풀어보기
 * 1. 각 노드에 따른 부모 노드의 인덱스 위치를 저장하도록 함
 * 2. k와 조부모는 같으면서 부모는 다른 노드들의 개수를 찾도록 함
 * 3. 사촌의 수를 출력
 * 4. n과 k가 둘 다 0일 경우 종료
 */

/*
 * 9489) 사촌 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n, k;
    static int[] node; // node(노드의 정보를 저장하는 배열)
    static int[] parent; // parent(노드들의 부모 노드 번호)
    static int k_index; // k_index(노드 k의 위치 인덱스)
    static int answer; // answer(사촌의 수)

    /*
     * 데이터 준비하기
     */
    static void init() throws IOException {
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            if (n == 0 && k == 0) { // 0이 두 개 주어질 경우 마지막 줄이므로 종료
                break;
            }

            node = new int[n + 1]; // 노드 저장
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                node[i] = Integer.parseInt(st.nextToken());
                if (node[i] == k) {
                    k_index = i;
                }
            }

            find();
            print();
        }
    }

    /*
     * 사촌 찾기
     */
    static void find() {
        answer = 0;

        parent = new int[n + 1];
        parent[0] = -1;
        parent[1] = 0; // 1번째 노드는 루트 노드이므로 부모 노드가 없으므로 0

        int parent_index = 1; // parent_index(부모 노드의 위치 인덱스)
        for (int i = 2; i <= n; i++) {
            parent[i] = parent_index;
            if (i < n && node[i] + 1 != node[i + 1]) { // 연속된 수의 경우, 같은 부모 노드로 설정
                parent_index++;
            }
        }

        for (int i = 1; i <= n; i++) { // k와 조부모는 같으면서 부모는 다른 노드들의 개수를 찾도록 함
            if (parent[parent[i]] == parent[parent[k_index]]
                    && parent[i] != parent[k_index]) {
                answer++;
            }
        }
    }

    /*
     * 사촌의 수 출력하기
     */
    static void print() {
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        init();
    }
}