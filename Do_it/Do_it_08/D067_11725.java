package Do_it.Do_it_08;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 11725) 트리의_부모_찾기
 */
public class D067_11725 {
    static boolean[] visited;
    static ArrayList<Integer> tree[];
    static int answer[];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // N(노드 개수)
        int N = sc.nextInt();
        // visited(방문 기록 저장 배열)
        visited = new boolean[N + 1];
        // tree(트리 데이터 저장 인접 리스트)
        tree = new ArrayList[N + 1];
        // answer(부모 노드 저장 정답 배열)
        answer = new int[N + 1];
        // tree 인접 리스트의 각 ArrayList 초기화하기
        for (int i = 0; i < tree.length; i++) {
            tree[i] = new ArrayList<>();
        }
        // tree 인접 리스트에 트리 데이터 저장하기
        for (int i = 1; i < N; i++) {
            int n1 = sc.nextInt();
            int n2 = sc.nextInt();
            tree[n1].add(n2);
            tree[n2].add(n1);
        }
        // 1번 노드(부모 노드)부터 DFS 실행하기
        DFS(1);
        // answer 배열 출력하기
        for (int i = 2; i <= N; i++) {
            System.out.println(answer[i]);
        }

    }

    // DFS
    public static void DFS(int number) {
        // visited 배열에 현재 노드 방문 기록하기
        visited[number] = true;
        // 현재 노드의 연결 노드 중 방문하지 않은 노드
        for (int i : tree[number]) {
            if (!visited[i]) {
                // 부모 노드 저장하기
                answer[i] = number;
                // DFS 실행하기(재귀 함수 형태)
                DFS(i);
            }
        }
    }
}
