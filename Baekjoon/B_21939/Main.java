package Baekjoon.B_21939;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 추천 문제 리스트를 어려운 레벨, 쉬운 레벨을 기준으로 두 개의 우선순위 큐에 저장하고 명령어에 따라 추천하도록 함
 */

/* 
 * 손으로 풀어보기
 * 1. 명령어가 recommend일 경우, 1이면 최대값 우선순위 큐에서 -1이면 최소값 우선순위 큐에서 문제를 선택해 추천
 * 2. 명령어가 add일 경우, 추천 문제를 최소값, 최대값 우선순위 큐에 저장
 * 3. 명령어가 solved일 경우, 두 우선순위 큐에서 문제를 제거
 */

/*
 * 21939) 문제_추천_시스템_Version_1  
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, M;
    static Problem[] problems = new Problem[100001]; // problems(문제를 담은 배열)
    static PriorityQueue<Problem> maxPq; // maxPq(최대값 우선순위 큐)
    static PriorityQueue<Problem> minPq; // minPq(최소값 우선순위 큐)

    /*
     * Problem(문제 정보를 담을 클래스)
     */
    static class Problem {
        int pid;
        int level;

        public Problem(int pid, int level) {
            this.pid = pid;
            this.level = level;
        }
    }

    /*
     * 문제 추천 준비하기
     */
    static void init(StringTokenizer st) throws IOException {
        N = Integer.parseInt(st.nextToken());

        maxPq = new PriorityQueue<Problem>(new Comparator<Problem>() {
            @Override
            public int compare(Problem o1, Problem o2) {
                if (o1.level == o2.level) {
                    return Integer.compare(o2.pid, o1.pid);
                }
                return Integer.compare(o2.level, o1.level);
            }
        });
        minPq = new PriorityQueue<Problem>(new Comparator<Problem>() {
            @Override
            public int compare(Problem o1, Problem o2) {
                if (o1.level == o2.level) {
                    return Integer.compare(o1.pid, o2.pid);
                }
                return Integer.compare(o1.level, o2.level);
            }
        });
        for (int i = 0; i < N; i++) { // 문제 저장
            st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            Problem problem = new Problem(P, L);
            problems[P] = problem;
            maxPq.add(problem);
            minPq.add(problem);
        }
    }

    /*
     * 문제 추천하기
     */
    static void recommend(StringTokenizer st) throws IOException {
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            if (command.equals("recommend")) { // 명령어 recommend
                int X = Integer.parseInt(st.nextToken());
                if (X == 1) {
                    print(maxPq.peek().pid);
                } else if (X == -1) {
                    print(minPq.peek().pid);
                }
            }

            else if (command.equals("add")) { // 명령어 add
                int P = Integer.parseInt(st.nextToken());
                int L = Integer.parseInt(st.nextToken());
                Problem problem = new Problem(P, L);
                problems[P] = problem;
                maxPq.add(problem);
                minPq.add(problem);
            }

            else if (command.equals("solved")) { // 명령어 solved
                int P = Integer.parseInt(st.nextToken());
                Problem problem = problems[P];
                maxPq.remove(problem);
                minPq.remove(problem);
            }
        }
    }

    /*
     * 명령문에 따른 결과 출력하기
     */
    static void print(int pid) {
        System.out.println(pid);
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        init(st);
        recommend(st);
    }
}