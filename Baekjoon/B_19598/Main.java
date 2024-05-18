package Baekjoon.B_19598;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 우선순위 큐를 이용해 시작 시간이 빠른 회의부터 시작하며 
 *   이번 회의가 시작하기 전에 이전 회의가 끝났다면 이전 회의를 삭제
 *   그렇지 않다면 이번 회의를 우선순위 큐에 넣도록 하여 회의실의 개수를 늘려줌
 *   모든 회의를 진행한 후 우선순위 큐의 개수를 출력하도록 함 
 */

/* 
 * 손으로 풀어보기
 * 1. 우선순위 큐를 이용해 시작 시간이 빠른 회의부터 저장
 * 2. 이번 회의가 시작하기 전에 이전 회의가 끝났다면 이전 회의를 삭제
 * 3. 그렇지 않다면 이번 회의를 우선순위 큐에 넣도록 하여 회의실의 개수를 늘려줌
 * 4. 모든 회의를 진행한 후 우선순위 큐의 개수를 출력
 */

/*
 * 19598) 최소_회의실_개수 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static Meeting[] meetings; // meetings(회의 정보를 담은 배열)
    static PriorityQueue<Integer> pq; // pq(회의 종료 시간을 담을 우선순위 큐)

    /*
     * Meeting(회의 정보를 저장할 클래스)
     */
    static class Meeting implements Comparable<Meeting> {
        int start, end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Meeting other) {
            if (this.start != other.start) {
                return Integer.compare(this.start, other.start);
            }
            return Integer.compare(this.end, other.end);
        }
    }

    /*
     * 회의 정보 저장하기
     */
    static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        meetings = new Meeting[N]; // 회의 정보 저장하기
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            meetings[i] = new Meeting(start, end);
        }
    }

    /*
     * 회의실 개수 구하기
     */
    static void calculate() {
        Arrays.sort(meetings); // 회의 시작 시간에 따라 정렬하기
        
        pq = new PriorityQueue<Integer>();
        pq.add(meetings[0].end);
        for (int i = 1; i < N; i++) {
            Meeting meeting = meetings[i];
            if (meeting.start >= pq.peek()) { // 다음 회의 시작 시간이 이전 회의 종료 시간보다 뒤일 경우, 같은 회의실 사용 가능
                pq.poll();
            }
            pq.add(meeting.end);
        }
    }

    /*
     * 최소 회의실의 개수 구하기
     */
    static void print() {
        System.out.println(pq.size());
    }

    public static void main(String[] args) throws IOException {
        init();
        calculate();
        print();
    }
}