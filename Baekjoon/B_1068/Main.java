package Baekjoon.B_1068;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 노드들을 저장해준 후, 노드들의 리프 노드 개수를 탐색하며 만약 지워야 하는 노드가 나온다면 건너뛰도록 함
 */

/* 
 * 손으로 풀어보기
 * 1. 노드들을 저장 
 * 2. 각 노드들을 탐색하며 지워야 하는 노드일 경우 건너뜀
 * 3. 지워야 할 노드가 아니라면 노드를 탐색하며 자식 노드의 개수를 세도록 함
 * 4. 자식 노드를 가지지 않는 리프 노드라면 개수 증가
 * 5. 리프 노드의 개수를 출력 
 */

/*
 * 1068) 트리
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N; // N(노드의 개수)
    static ArrayList<Integer>[] tree; // tree(트리 데이터 저장 인접 리스트)
    static int rootNode; // rootNode(루트 노드)
    static int deleteNode; // deleteNode(지울 노드)
    static boolean[] visited; // visited(노드 방문 유무 배열)
    static int leafNodeCount; // leafNodeCount(리프 노드 개수)

    /*
     * 트리 데이터 저장하기
     */
    static void init(StringTokenizer st) throws IOException {
        N = Integer.parseInt(st.nextToken());

        tree = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            tree[i] = new ArrayList<Integer>();
        }

        st = new StringTokenizer(br.readLine());
        for (int node = 0; node < N; node++) { // 트리 데이터 저장
            int parent = Integer.parseInt(st.nextToken());
            if (parent != -1) {
                tree[node].add(parent);
                tree[parent].add(node);
            } else {
                rootNode = node;
            }
        }
    }

    /*
     * 지울 노드에 따라 리프 노드 개수 찾기
     */
    static void deleteNode(StringTokenizer st) throws IOException {
        st = new StringTokenizer(br.readLine());
        deleteNode = Integer.parseInt(st.nextToken());
        if (deleteNode == rootNode) {
            leafNodeCount = 0; // 지울 노드가 루트 노드라면, 리프 노드의 개수는 0
        } else {
            visited = new boolean[N];
            countLeafNode(rootNode); // 지울 노드가 루트 노드가 아니라면, 루트 노드부터 시작해 리프 노드의 개수를 찾도록 함
        }
    }

    /*
     * 루트 노드부터 시작해 리프 노드 개수 찾기
     */
    static void countLeafNode(int node) {
        visited[node] = true;
        int childNodeCount = 0; // childNodeCount(자식 노드 개수)
        for (int next : tree[node]) {
            if (visited[next] == false && next != deleteNode) {
                childNodeCount++; // 현재 노드의 연결 노드 중 방문하지 않은 노드이고 삭제 노드가 아니라면 자식 노드 개수 증가
                countLeafNode(next);
            }
        }
        if (childNodeCount == 0) { // 현재 노드의 자식 노드 개수가 0이라면 리프 노드
            leafNodeCount++;
        }
    }

    /*
     * 리프 노드 개수 출력
     */
    static void print() {
        System.out.println(leafNodeCount);
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        init(st);
        deleteNode(st);
        print();
    }
}
