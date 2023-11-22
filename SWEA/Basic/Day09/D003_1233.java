package SWEA.Basic.Day09;

import java.util.Scanner;

/**
 * 1233) 사칙연산_유효성_검사
 */
public class D003_1233 {
    static int[][] tree;
    static String[] value;
    static int answer;

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        // T(테스트 케이스 수) = 10
        int T = 10;
        // T만큼
        for (int test_case = 1; test_case <= T; test_case++) {
            // N(트리가 갖는 정점의 총 수)
            int N = sc.nextInt();
            sc.nextLine();
            // tree(트리의 연결 정점 저장)
            tree = new int[N + 1][2];
            // value(트리의 값 저장)
            value = new String[N + 1];
            for (int i = 1; i <= N; i++) {
                String[] str = sc.nextLine().split(" ");
                // 트리의 값 저장
                value[i] = str[1];
                // 4개가 주어진다면 (연결 자식 정점 존재)
                if (str.length == 4) {
                    // tree의 연결 정점 저장
                    tree[i][0] = Integer.parseInt(str[2]);
                    tree[i][1] = Integer.parseInt(str[3]);
                }
                // 4개가 주어지지 않는다면 (연결 자식 정점 없음 - 리프 노드)
                else {
                    // tree의 연결 정점에 -1 저장
                    tree[i][0] = -1;
                    tree[i][1] = -1;
                }
            }
            // answer(연산 가능 여부)
            answer = 1;
            // 전위순회(1)
            preOrder(1);
            // #T와 answer 반환
            System.out.println("#" + test_case + " " + answer);
        }
    }

    // 전위순회
    private static void preOrder(int now) {
        // 현재 값이 -1이면 리턴(자식 정점이 없으면)
        if (now == -1)
            return;
        // 현재 정점의 값이 연산자일 경우
        if (value[now].equals("+") || value[now].equals("-") || value[now].equals("*") || value[now].equals("/")) {
            // 자식 정점이 없다면 연산 불가능
            if (tree[now][0] == -1 || tree[now][1] == -1) {
                answer = 0;
            }
        }
        // 왼쪽 자식 정점 탐색하기
        preOrder(tree[now][0]);
        // 오른쪽 자식 정점 탐색하기
        preOrder(tree[now][1]);
    }
}
