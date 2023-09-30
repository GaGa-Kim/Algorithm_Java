package Do_it.Do_it_08;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 1068) 트리
 */
public class D068_1068 {
    static ArrayList<Integer>[] tree;
    static boolean[] visited;
    // answer(리프 노드 개수 저장 변수)
    static int answer = 0;
    // deleteNode(삭제 노드)
    static int deleteNode = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // N(노드 개수)
        int N = sc.nextInt();
        // tree(트리 데이터 저장 인접 리스트)
        tree = new ArrayList[N];
        // visited(방문 기록 저장 배열)
        visited = new boolean[N];
        int root = 0;
        // tree 인접 리스트의 각 ArrayList 초기화하기
        for (int i = 0; i < tree.length; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int i = 0; i < N; i++) {
            int p = sc.nextInt();
            // 루트 노드가 아닌 경우(부모 노드가 있다면)
            if (p != -1) {
                // tree 인접 리스트에 트리 데이터 저장하기
                tree[i].add(p);
                tree[p].add(i);
            }
            // 루트 노드값 저장하기
            else {
                root = i;
            }
        }
        // 삭제 노드값 저장하기
        deleteNode = sc.nextInt();
        // 삭제 노드값이 0 (루트를 삭제해야 한다면)
        if (deleteNode == root)
            // 모두 삭제되므로 0을 출력하고 프로세스 끝냄
            System.out.println(0);
        // DFS(root)
        else {
            DFS(root);
            // answer 출력하기
            System.out.println(answer);
        }
    }

    // DFS
    public static void DFS(int number) {
        // visited 배열에 현재 노드 방문 기록하기
        visited[number] = true;
        // cNode(자식 노드 개수)
        int cNode = 0;
        // 현재 노드의 연결 노드 중 방문하지 않은 노드이고 삭제 노드가 아닐 때
        for (int i : tree[number]) {
            if (visited[i] == false && i != deleteNode) {
                // 자식 노드 개수 증가
                cNode++;
                // DFS 실행하기(재귀 함수 형태)
                DFS(i);
            }
        }
        // 만약 자식 노드 개수가 0이면 answer 변수 증가
        if (cNode == 0)
            answer++;
    }
}
