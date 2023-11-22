package SWEA.Basic.Day09;

import java.util.Scanner;

/**
 * 1231) 중위순회
 */
public class D001_1231 {
    static int N;
    static String[] tree;

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        // T(테스트 케이스 수) = 10
        int T = 10;
        // T만큼
        for (int test_case = 1; test_case <= T; test_case++) {
            // N(트리가 갖는 정점의 총 수)
            N = sc.nextInt();
            sc.nextLine();
            // tree(트리의 알파벳 저장)
            tree = new String[N + 1];
            // 알파벳만 저장
            for (int i = 1; i <= N; i++) {
                String str = sc.nextLine();
                tree[i] = str.split(" ")[1];
            }
            // #T + 공백 출력
            System.out.printf("#%d ", test_case);
            // 중위순회(1)
            inOrder(1);
            System.out.println();
        }
    }

    // 중위순회
    private static void inOrder(int index) {
        // index * 2가 N보다 작거나 같을 때
        if (index * 2 <= N) {
            // 현재 노드의 왼쪽 트리 중위순회
            inOrder(index * 2);
        }
        // 현재 노드인 tree[index] 출력
        System.out.print(tree[index]);
        // index * 2 + 1이 N보다 작거나 같을 때
        if (index * 2 + 1 <= N) {
            // 현재 노드의 오른쪽 트리 중위순회
            inOrder(index * 2 + 1);
        }
    }
}
