package Baekjoon.B_16235;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : K번 동안 사계절의 나무 재테크를 하도록 한 후 살아있는 나무의 개수를 구하도록 함
 *   
 */

/* 
 * 손으로 풀어보기
 * 1. 각 칸에 추가되는 양분의 양을 저장
 * 2. 처음 칸의 양분을 5로 시작하도록 저장
 * 3. 상도가 심은 나무의 정보를 저장
 * 4. 나무가 자신의 나이만큼 양분을 먹고, 나이를 1 증가시키도록 함
 *    이때 하나의 칸에 여러 개의 나무가 있다면, 나이가 어린 나무부터 양분을 먹고
 *    양분이 부족해 자신의 나이만큼 먹을 수 없다면 이 나무를 죽인 후, 나이를 2로 나눈 값을 양분으로 만들도록 함
 * 5. 존재하는 나무의 나이가 5의 배수라면 인접한 8개의 칸에 나이가 1인 나무를 생성
 * 6. 각 칸에 추기되는 양분의 양만큼 양분을 추가
 * 7. 이를 K번 반복한 후 살아있는 나무의 개수를 출력
 */

/*
 * 16235) 나무_재테크 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, M, K;

    static int[][] A; // A(각 칸에 추가되는 양분의 양)
    static int[][] board; // board(각 칸의 양분 정보)

    static List<Tree> trees = new ArrayList<Tree>(); // trees(살아있는 나무들)
    static Queue<Tree> dead_trees;

    static int[] dr = { -1, -1, -1, 0, 0, 1, 1, 1 }; // dr, dc(인접한 8개의 칸의 위치)
    static int[] dc = { -1, 0, 1, -1, 1, -1, 0, 1 };

    /*
     * Tree(나무의 정보를 저장할 클래스)
     */
    static class Tree implements Comparable<Tree> {
        int r, c;
        int age;
        boolean dead;

        public Tree(int r, int c, int age) {
            this.r = r;
            this.c = c;
            this.age = age;
            this.dead = false;
        }

        public int compareTo(Tree other) { // 나무를 나이순으로 정렬
            return Integer.compare(this.age, other.age);
        }
    }

    /*
     * 데이터 준비하기
     */
    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[N][N];
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                board[i][j] = 5;
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());
            trees.add(new Tree(x, y, z));
        }

        Collections.sort(trees); // 나이가 어린 나무부터 양분을 먹을 수 있도록 정렬
    }

    /*
     * 양분에 따라 나이를 증가하거나 나무 죽이기
     */
    static void spring() {
        dead_trees = new LinkedList<Tree>();
        for (Tree tree : trees) {
            if (board[tree.r][tree.c] >= tree.age) {
                board[tree.r][tree.c] -= tree.age;
                tree.age++;
            } else {
                tree.dead = true;
                dead_trees.add(tree);
            }
        }
    }

    /*
     * 봄에 죽은 나무를 양분으로 바꾸기
     */
    static void summer() {
        while (!dead_trees.isEmpty()) {
            Tree tree = dead_trees.poll();
            board[tree.r][tree.c] += tree.age / 2;
        }
    }

    /*
     * 나무 번식하기
     */
    static void fall() {
        List<Tree> newTrees = new ArrayList<Tree>();
        for (Tree tree : trees) {
            if (tree.dead) {
                continue;
            }
            if (tree.age % 5 == 0) {
                for (int d = 0; d < 8; d++) {
                    int nr = tree.r + dr[d];
                    int nc = tree.c + dc[d];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                        continue;
                    }
                    newTrees.add(new Tree(nr, nc, 1));
                }
            }
        }
        for (Tree tree : trees) { // 새로 생성된 나이가 1인 나무들에 기존의 나무들을 추가
            if (!tree.dead) {
                newTrees.add(tree);
            }
        }
        trees = newTrees;
    }

    /*
     * 땅을 돌아다니면서 땅에 양분 추가하기
     */
    static void winter() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] += A[i][j];
            }
        }
    }

    /*
     * K번 반복한 후 살아있는 나무의 개수 출력하기
     */
    static void print() {
        System.out.println(trees.size());
    }

    public static void main(String[] args) throws IOException {
        init();
        for (int k = 0; k < K; k++) {
            spring();
            summer();
            fall();
            winter();
        }
        print();
    }
}