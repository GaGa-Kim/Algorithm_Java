package Baekjoon.B_14567;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 위상 정렬 알고리즘을 이용해 각 과목의 수강 학기를 구하도록 함
 */

/* 
 * 손으로 풀어보기
 * 1. 위상 정렬을 하며 진입 차수가 0인 노드는 1학기 수강
 * 2. 이후 노드의 다음 연결된 노드일 경우 선수 과목의 수강 학기 + 1을 해주도록 함
 */

/*
 * 14567) 선수과목
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, M;
    static ArrayList<Integer>[] arr; // arr(선수과목들에 따른 다음 과목 정보)
    static int[] indegree; // indegree(진입차수 배열)
    static int[] semesters; // semesters(수강해야 하는 과목들의 수강학기)

    /*
     * Subject(과목 정보를 담은 클래스)
     */
    static class Subject {
        int id;
        int semester;

        public Subject(int id, int semester) {
            this.id = id;
            this.semester = semester;
        }
    }

    /*
     * 과목의 이수 학기 찾기 준비하기
     */
    static void init(StringTokenizer st) throws IOException {
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new ArrayList[N + 1]; // 각 과목에 연결된 과목 정보를 담기 위한 연결 리스트 초기화
        for (int i = 1; i <= N; i++) {
            arr[i] = new ArrayList<Integer>();
        }

        indegree = new int[N + 1]; // 모든 선수 과목 정보 저장하기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            arr[A].add(B);
            indegree[B]++;
        }

        semesters = new int[N + 1];
    }

    /*
     * 각 과목의 이수 학기 찾기
     */
    static void find() {
        Queue<Subject> queue = new LinkedList<Subject>();
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) { // 진입차수가 0인 과목을 큐에 삽입
                queue.add(new Subject(i, 1));
                semesters[i] = 1;
            }
        }

        while (!queue.isEmpty()) {
            Subject now = queue.poll();
            for (int next : arr[now.id]) { // 해당 선수과목과 연결된 다음 과목을 살펴보기
                indegree[next]--;
                if (indegree[next] == 0) { // 새롭게 진입차수가 0이 되는 과목을 큐에 삽입
                    queue.add(new Subject(next, now.semester + 1));
                    semesters[next] = now.semester + 1;
                }
            }
        }
    }

    /*
     * 각 과목의 이수 학기 출력하기
     */
    static void print() {
        for (int i = 1; i <= N; i++) {
            System.out.print(semesters[i] + " ");
        }
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        init(st);
        find();
        print();
    }
}