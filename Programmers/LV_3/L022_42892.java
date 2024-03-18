package Programmers.LV_3;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 42892) 길_찾기_게임
 */
public class L022_42892 {
    int[][] answer;
    int index;

    // nodeinfo(이진트리를 구성하는 각 노드의 좌표가 1번 노드부터 순서대로 들어있는 2차원 배열)
    public int[][] solution(int[][] nodeinfo) {
        // tree(Node들로 구성된 이진트리 배열)
        Node[] tree = new Node[nodeinfo.length];
        // tree에 x, y, value 저장
        for (int i = 0; i < nodeinfo.length; i++) {
            tree[i] = new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1, null, null);
        }
        Arrays.sort(tree, new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2) {
                // 만약 y 값이 같을 경우 왼쪽 자식 노드부터 저장하기 위해 tree를 오름차순 정렬
                if (n1.y == n2.y)
                    return n1.x - n2.x;
                // 자식 노드의 y값은 부모 노드보다 작으므로 y값을 기준으로 tree를 내림차순 정렬
                else
                    return n2.y - n1.y;
            }
        });
        // tree에 left, right 저장
        for (int i = 1; i < nodeinfo.length; i++) {
            makeTree(tree[0], tree[i]);
        }
        // answer(노드들로 구성된 이진트리를 전위 순회, 후위 순회한 결과)
        answer = new int[2][nodeinfo.length];
        // index(순회 저장 인덱스)
        index = 0;
        // 전위 순회
        preOrder(tree[0]);
        // 후위 순회를 위해 다시 index 초기화
        index = 0;
        // 후위 순회
        postOrder(tree[0]);
        // answer 반환
        return answer;
    }

    // 자식 노드 채워넣기
    private void makeTree(Node parent, Node child) {
        // parent의 x축 좌표보다 child의 x축 좌표보다 크다면
        if (parent.x > child.x) {
            // parent의 오른쪽 자식 노드가 null이라면
            if (parent.left == null)
                // 오른쪽 자식 노드가 됨
                parent.left = child;
            // 이미 parent의 오른쪽 자식 노드가 있다면
            else
                // 오른쪽 서브 트리의 값 중 하나이므로 부모 노드를 다시 탐색
                makeTree(parent.left, child);
        }
        // parent의 x축 좌표보다 child의 x축 좌표보다 작다면
        else {
            // parent의 왼쪽 자식 노드가 null이라면
            if (parent.right == null)
                // 왼쪽 자식 노드가 됨
                parent.right = child;
            // 이미 parent의 왼쪽 자식 노드가 있다면
            else
                // 왼쪽 서브 트리의 값 중 하나이므로 부모 노드를 다시 탐색
                makeTree(parent.right, child);
        }
    }

    // 전위 순회
    private void preOrder(Node now) {
        // now가 null이 아니라면
        if (now != null) {
            // 현재 노드를 answer[0][index++]에 저장
            answer[0][index++] = now.value;
            // 왼쪽 자식 노드 탐색하기
            preOrder(now.left);
            // 오른쪽 자식 노드 탐색하기
            preOrder(now.right);
        }
    }

    // 후위 순회
    private void postOrder(Node now) {
        // now가 null이 아니라면
        if (now != null) {
            // 왼쪽 자식 노드 탐색하기
            postOrder(now.left);
            // 오른쪽 자식 노드 탐색하기
            postOrder(now.right);
            // 현재 노드를 answer[1][index++]에 저장
            answer[1][index++] = now.value;
        }
    }

    class Node {
        int x; // x(x축 좌표)
        int y; // y(y축 좌표)
        int value; // value(노드 번호)
        Node left; // left(왼쪽 자식 노드)
        Node right; // right(오른쪽 자식 노드)

        public Node(int x, int y, int value, Node left, Node right) {
            this.x = x;
            this.y = y;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L022_42892 solution = new L022_42892();

        int[][] nodeinfo = { { 5, 3 }, { 11, 5 }, { 13, 3 }, { 3, 5 }, { 6, 1 }, { 1, 3 }, { 8, 6 }, { 7, 2 },
                { 2, 2 } };

        int[][] result = solution.solution(nodeinfo);

        System.out.println(Arrays.toString(result));
    }
}
