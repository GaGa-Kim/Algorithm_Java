package Baekjoon.B_5639;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 문제 분석하기
 * : 전위 순회의 경우, 처음 탐색한 값이 루트이므로 이를 이용해 작으면 왼쪽 자식, 크면 오른쪽 자식으로 순회하며 재귀 탐색
 */

/* 
 * 손으로 풀어보기
 * 1. 루트 노드를 설정
 * 2. 루트보다 작으면 왼쪽 자식, 루트보다 클 경우 오른쪽 자식으로 하며 탐색
 */

/*
 * 5639) 이진_검색_트리 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static Node root; // root(루트 노드)

    /*
     * Node(트리의 노드 정보를 담은 클래스)
     */
    static class Node {
        int num;
        Node left, right;

        public Node(int num) {
            this.num = num;
        }

        void insert(int n) {
            if (n < this.num) { // 현재 노드 값보다 작으면 왼쪽 서브트리
                if (this.left == null) {
                    this.left = new Node(n);
                } else {
                    this.left.insert(n);
                }
            } else { // 현재 노드 값보다 크면 오른쪽 서브트리
                if (this.right == null) {
                    this.right = new Node(n);
                } else {
                    this.right.insert(n);
                }
            }
        }
    }

    /*
     * 트리 저장하기
     */
    static void init() throws IOException {
        root = new Node(Integer.parseInt(br.readLine())); // 루트 노드
        while (true) { // 전위 순회 정보를 가지고 트리 저장
            String s = br.readLine();
            if (s == null || s.equals("")) {
                break;
            }
            root.insert(Integer.parseInt(s));
        }
    }

    /*
     * 후위 순회
     */
    static void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.left); // 왼쪽 서브트리
        postOrder(node.right); // 오른쪽 서브트리
        System.out.println(node.num); // 루트 노드
    }

    public static void main(String[] args) throws IOException {
        init();
        postOrder(root);
    }
}