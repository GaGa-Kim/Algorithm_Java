package Do_it.Do_it_08;

import java.util.Scanner;

/**
 * 1991) 트리_순회
 */
public class D070_1991 {
    static int[][] tree;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // N(노드 개수)
        int N = sc.nextInt();
        // tree(tree 데이터 저장 2차원 배열)
        tree = new int[26][2];
        sc.nextLine();
        for (int i = 0; i < N; i++) {
            String[] temp = sc.nextLine().split(" ");
            int node = temp[0].charAt(0) - 'A';
            char left = temp[1].charAt(0);
            char right = temp[2].charAt(0);
            // 왼쪽 자식 노드가 없을 때
            if (left == '.')
                // tree 배열에 -1 저장하기
                tree[node][0] = -1;
            else
                // tree 배열에 왼쪽 자식 노드 인덱스 저장하기
                tree[node][0] = left - 'A';
            // 오른쪽 자식 노드가 없을 때
            if (right == '.')
                // tree 배열에 -1 저장하기
                tree[node][1] = -1;
            else
                // tree 배열에 오른쪽 자식 노드 인덱스 저장하기
                tree[node][1] = right - 'A';
        }
        // preOrder 실행하기
        preOrder(0);
        System.out.println();
        // inOrder 실행하기
        inOrder(0);
        System.out.println();
        // postOrder 실행하기
        postOrder(0);
        System.out.println();
    }

    // 전위 순회 함수
    public static void preOrder(int now) {
        // 현재 값이 -1이면 리턴(자식 노드가 없으면)
        if (now == -1)
            return;
        // 1. 현재 노드 출력하기
        System.out.print((char) (now + 'A'));
        // 2. 왼쪽 자식 노드 탐색하기
        preOrder(tree[now][0]);
        // 3. 오른쪽 자식 노드 탐색하기
        preOrder(tree[now][1]);
    }

    // 중위 순회 함수
    public static void inOrder(int now) {
        // 현재 값이 -1이면 리턴(자식 노드가 없으면)
        if (now == -1)
            return;
        // 1. 왼쪽 자식 노드 탐색하기
        inOrder(tree[now][0]);
        // 2. 현재 노드 출력하기
        System.out.print((char) (now + 'A'));
        // 3. 오른쪽 자식 노드 탐색하기
        inOrder(tree[now][1]);
    }

    // 후위 순회 함수
    public static void postOrder(int now) {
        // 현재 값이 -1이면 리턴(자식 노드가 없으면)
        if (now == -1)
            return;
        // 1. 왼쪽 자식 노드 탐색하기
        postOrder(tree[now][0]);
        // 2. 오른쪽 자식 노드 탐색하기
        postOrder(tree[now][1]);
        // 3. 현재 노드 출력하기
        System.out.print((char) (now + 'A'));
    }
}
