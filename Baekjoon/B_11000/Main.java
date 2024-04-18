package Baekjoon.B_11000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 빨리 시작하는 수업부터 배정하도록 함. 만약 수업이 겹칠 경우 강의실을 하나 증가시킴
 */

/* 
 * 손으로 풀어보기
 * 1. 수업에 따른 강의실 배정 준비하기
 * 2. 강의 시작 시간을 기준으로 정렬. 만약 시작 시간이 같을 경우 종료 시간이 빠른 순으로 정렬
 * 3. 앞 수업의 종료 시간보다 다음 수업의 시작 시간이 빠를 경우 강의실을 증가시켜주도록 함
 * 4. 사용할 수 있는 최소 강의실의 개수 출력
 */

/*
 * 11000) 강의실_배정
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static Class[] classes; // classes(수업 정보를 담은 배열)
    static PriorityQueue<Integer> pq; // pq(수업 종료 시간을 담을 우선순위 큐)

    /*
     * Class(수업 정보를 담은 클래스)
     */
    static class Class implements Comparable<Class> {
        int start;
        int end;

        public Class(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Class o) {
            if (this.start != o.start) {
                return Integer.compare(this.start, o.start);
            }
            return Integer.compare(this.end, o.end);
        }
    }

    /*
     * 수업에 따른 강의실 배정 준비하기
     */
    static void init(StringTokenizer st) throws IOException {
        N = Integer.parseInt(st.nextToken());

        classes = new Class[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            classes[i] = new Class(start, end);
        }
    }

    /*
     * 수업 정렬하기
     */
    static void sortClasses() {
        Arrays.sort(classes);
    }

    /*
     * 수업 시간에 따라 강의실 배정하기
     */
    static void selectClasses() {
        pq = new PriorityQueue<Integer>();
        pq.add(classes[0].end);
        for (int i = 1; i < N; i++) {
            Class c = classes[i];
            if (c.start >= pq.peek()) {
                pq.poll(); // 다음 수업의 시작 시간이 이전 수업의 종료 시간보다 뒤일 경우, 같은 강의실에서 수업이 가능하므로 이전 수업을 제거하고 다음 수업을 넣어줌
            }
            pq.add(c.end); // 다음 수업의 시작 시간이 이전 수업의 종료 시간보다 앞일 경우, 같은 강의실에서 수업이 불가능하므로 이전 수업을 놔두고 다음 수업을 넣어줌
        }
    }

    /*
     * 최소 강의실의 개수 출력
     */
    static void print() {
        System.out.println(pq.size());
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        init(st);
        sortClasses();
        selectClasses();
        print();
    }
}
