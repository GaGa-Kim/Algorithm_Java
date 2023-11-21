package SWEA.Basic.Day08;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * 1228) 암호문1
 */
public class D001_1228 {
    static LinkedList<Integer> list;
    static Scanner sc;

    public static void main(String args[]) throws Exception {
        sc = new Scanner(System.in);
        // T(테스트 케이스 수) = 10
        int T = 10;
        // T만큼
        for (int test_case = 1; test_case <= T; test_case++) {
            // list(암호문을 저장할 LinkedList)
            list = new LinkedList<>();
            // N(원본 암호문의 길이)
            int N = sc.nextInt();
            // list에 암호 숫자 저장
            for (int i = 0; i < N; i++) {
                list.add(sc.nextInt());
            }
            // M(명령어의 갯수)
            int M = sc.nextInt();
            for (int i = 0; i < M; i++) {
                // command(명령어 저장)
                String command = sc.next();
                // x(삽입 시작 위치 저장)
                int x = sc.nextInt();
                // y(삽입 숫자 갯수 저장)
                int y = sc.nextInt();
                // command와 I가 같다면
                if (command.equals("I")) {
                    // 암호문 삽입 함수(x, y)
                    insertPW(x, y);
                }
            }
            // 결과 출력 함수(test_case)
            print(test_case);
        }
    }

    // 암호문 삽입 함수
    private static void insertPW(int x, int y) {
        for (int i = 0; i < y; i++) {
            // list.add(x, 삽입할 암호 숫자)
            list.add(x, sc.nextInt());
            x++;
        }
    }

    // 결과 출력 함수
    private static void print(int t) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            // sb.append(암호 숫자 + 공백)
            sb.append(list.get(i) + " ");
        }
        // #T와 sb 출력
        System.out.println("#" + t + " " + sb);
    }
}
