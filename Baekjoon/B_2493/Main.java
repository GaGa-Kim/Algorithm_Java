package Baekjoon.B_2493;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 자신보다 큰 높이의 탑을 발견할 경우 이 탑이 수신받도록 함
 */

/* 
 * 손으로 풀어보기
 * 1. 스택이 비어있다면 수신할 탑이 없으므로 0
 * 2. 스택의 맨 윗부분 탑의 높이가 지금 들어오는 탑의 높이보다 크다면 이 탑으로 수신 가능
 * 3. 스택의 맨 윗부분 탑의 높이가 지금 들어오는 탑의 높이보다 작거나 같다면 이 탑으로 수신할 수 없게 되므로 스택에서 제거
 * 4. 이를 반복하며 스택의 맨 윗부분 탑을 수신하는 탑으로 갱신
 */

/*
 * 2493) 탑 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static Top[] tops; // tops(탑들의 정보)
    static int[] answer; // answer(각각의 탑들에서 발사한 레이저 신호를 수신한 탑들의 번호)

    /*
     * Top (탑의 정보를 담은 클래스)
     */
    static class Top {
        int index;
        int height;

        public Top(int index, int height) {
            this.index = index;
            this.height = height;
        }
    }

    /*
     * 탑 수신 준비하기
     */
    static void init(StringTokenizer st) throws IOException {
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        tops = new Top[N];
        for (int i = 0; i < N; i++) {
            int height = Integer.parseInt(st.nextToken());
            tops[i] = new Top(i + 1, height);
        }

        answer = new int[N];
    }

    /*
     * 수신할 탑 구하기
     */
    static void find() {
        Stack<Top> stack = new Stack<Top>();
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty()) { // 스택이 비어있지 않다면 수신할 탑이 있는지 확인
                if (stack.peek().height > tops[i].height) { // 스택의 맨 윗부분 탑의 높이가 지금 들어오는 탑의 높이보다 크다면
                    answer[i] = stack.peek().index; // 이 탑으로 수신 가능
                    break;
                } else { // 스택의 맨 윗부분 탑의 높이가 지금 들어오는 탑의 높이보다 작거나 같다면
                    stack.pop(); // 이 탑으로 수신할 수 없게 되므로 스택에서 제거
                }
            }
            if (stack.isEmpty()) { // 스택이 비어있다면 수신할 탑이 없으므로 0
                answer[i] = 0;
            }
            stack.push(tops[i]); // 현재 탑을 스택에 넣어줌
        }
    }

    /*
     * 수신한 탑들의 번호를 출력하기
     */
    static void print() {
        for (int i = 0; i < N; i++) {
            System.out.print(answer[i] + " ");
        }
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        init(st);
        find();
        print();
    }
}