package Baekjoon.B_21944;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeSet;

/*
 * 문제 분석하기
 * : 추천 문제 리스트를 TreeSet에 저장한 후, 명령에 따라 문제를 추천, 추가, 제거하도록 함
 *   이를 위해 TreeSet의 last(), first(), ceiling(), lower() 메소드를 사용하도록 하며  
 *   각 알고리즘인 G에 대해서는 각 서브트리를 만들어서 알고리즘 별로 정렬해주도록 함
 */

/* 
 * 손으로 풀어보기
 * 1. recommend G x일 경우, 
 *    TresSet에 있는 문제들을 알고리즘 G에 따라서 분리하여 서브트리를 만든 후 TreeSet의 last(), first() 메소드를 사용해 문제 번호를 출력
 * 2. recommend2 x일 경우, 
 *    TreeSet의 last(), first() 메소드를 사용해 문제 번호를 출력
 * 3. recommend3 x L일 경우, 
 *    TreeSet의 ceiling(), lower() 메소드를 사용해 문제 번호를 출력
 * 4. add P G일 경우, 
 *    TreeSet에 문제를 추가
 * 5. solved P일 경우, 
 *    해당 P번호 문제를 삭제
 * 6. 명령어들의 출력 결과 출력하기
 */

/*
 * 21944) 문제_추천_시스템 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, M;
    static Problem[] problems = new Problem[100001]; // problems(문제 배열)
    static TreeSet<Problem> tree = new TreeSet<>(); // tree(문제 트리)
    static List<TreeSet<Problem>> subtrees = new ArrayList<TreeSet<Problem>>(); // subtrees(알고리즘 분류에 따른 문제 서브트리)
    static StringBuilder answer = new StringBuilder(); // answer(명령어들의 출력 결과)

    /*
     * Problem(문제 정보를 담을 클래스)
     */
    static class Problem implements Comparable<Problem> {
        int P;
        int L;
        int G;

        public Problem(int P, int L, int G) {
            this.P = P;
            this.L = L;
            this.G = G;
        }

        @Override
        public int compareTo(Problem other) {
            if (this.L == other.L) {
                return Integer.compare(this.P, other.P);
            }
            return Integer.compare(this.L, other.L);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Problem other = (Problem) obj;
            if (this.G != other.G) {
                return false;
            }
            if (this.L != other.L) {
                return false;
            }
            if (this.P != other.P) {
                return false;
            }
            return true;
        }
    }

    /*
     * 데이터 준비하기
     */
    static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i <= 100; i++) { // 1부터 100까지의 알고리즘 분류 서브 트리 초기화
            subtrees.add(new TreeSet<Problem>());
        }

        StringTokenizer st;
        for (int i = 0; i < N; i++) { // 문제 정보 저장
            st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            int G = Integer.parseInt(st.nextToken());
            Problem problem = new Problem(P, L, G);
            problems[P] = problem;
            tree.add(problem);
            subtrees.get(G).add(problem);
        }

        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) { // 명령문에 따라 수행
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            if (command.equals("recommend")) {
                int G = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                if (x == 1) { // 알고리즘 분류가 G인 문제 중 가장 어려운 문제 번호
                    answer.append(subtrees.get(G).last().P).append("\n");
                } else if (x == -1) { // 알고리즘 분류가 G인 문제 중 가장 쉬운 문제 번호
                    answer.append(subtrees.get(G).first().P).append("\n");
                }
            } else if (command.equals("recommend2")) {
                int x = Integer.parseInt(st.nextToken());
                if (x == 1) { // 가장 어려운 문제 번호
                    answer.append(tree.last().P).append("\n");
                } else if (x == -1) { // 가장 쉬운 문제 번호
                    answer.append(tree.first().P).append("\n");
                }
            } else if (command.equals("recommend3")) {
                int x = Integer.parseInt(st.nextToken());
                int L = Integer.parseInt(st.nextToken());
                if (x == 1) { // 난이도 L보다 크거나 같은 문제 중 가장 쉬운 문제 번호
                    Problem problem = new Problem(0, L, 0);
                    if (tree.ceiling(problem) == null) {
                        answer.append("-1").append("\n");
                    } else {
                        answer.append(tree.ceiling(problem).P).append("\n");
                    }
                } else if (x == -1) { // 난이도 L보다 작은 문제 중 가장 어려운 문제 번호
                    Problem problem = new Problem(0, L, 0);
                    if (tree.lower(problem) == null) {
                        answer.append("-1").append("\n");
                    } else {
                        answer.append(tree.lower(problem).P).append("\n");
                    }
                }
            } else if (command.equals("add")) { // 난이도가 L이고 알고리즘 분류가 G인 문제 번호 P를 추가
                int P = Integer.parseInt(st.nextToken());
                int L = Integer.parseInt(st.nextToken());
                int G = Integer.parseInt(st.nextToken());
                Problem problem = new Problem(P, L, G);
                problems[P] = problem;
                tree.add(problem);
                subtrees.get(G).add(problem);
            } else if (command.equals("solved")) { // 문제 번호 P를 제거
                int P = Integer.parseInt(st.nextToken());
                if (problems[P] != null) {
                    Problem problem = problems[P];
                    tree.remove(problem);
                    subtrees.get(problem.G).remove(problem);
                }
            }
        }
    }

    /*
     * 명령어들의 출력 결과 출력하기
     */
    static void print() {
        System.out.println(answer.toString());
    }

    public static void main(String[] args) throws IOException {
        init();
        print();
    }
}