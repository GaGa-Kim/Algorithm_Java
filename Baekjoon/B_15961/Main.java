package Baekjoon.B_15961;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 슬라이딩 윈도우로 이동하며 초밥의 종류를 최대 몇 개 먹을 수 있는지 찾도록 함
 */

/* 
 * 손으로 풀어보기
 * 1. 초밥 종류 사용 배열에 쿠폰에 담긴 초밥의 종류를 저장
 * 2. 슬라이딩 윈도우로 이동하며 이동한 곳의 초밥의 종류를 증가하고 이전의 초밥의 종류를 감소
 * 3. 이를 반복하며 최대 초밥의 종류 개수를 구하여 출력
 */

/*
 * 15961) 회전_초밥
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, d, k, c;
    static int[] shisi; // shisi(벨트 위의 초밥의 종류)
    static int[] type; // type(초밥의 종류에 따른 선택 개수 배열)
    static int answer; // answer(먹을 수 있는 초밥의 최대 가짓수)

    /*
     * 초밥 준비하기
     */
    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        shisi = new int[N]; // 벨트 위의 초밥의 종류 저장하기
        for (int i = 0; i < N; i++) {
            shisi[i] = Integer.parseInt(br.readLine());
        }
    }

    /*
     * 먹을 수 있는 초밥의 가짓수 찾기
     */
    static void find() {
        type = new int[d + 1];
        type[c]++; // 쿠폰에 담긴 초밥의 종류 증가
        answer = 1;

        for (int i = 0; i < k; i++) { // 초기 k만큼 이동하며 초밥을 선택해 초밥의 종류 증가
            if (type[shisi[i]] == 0) {
                answer++;
            }
            type[shisi[i]]++;
        }

        int count = answer; // count(현재 먹을 수 있는 초밥의 가짓수)
        for (int i = 1; i < N; i++) { // 슬라이딩 윈도우로 한 칸씩 이동하며 초밥의 선택과 종류를 갱신
            int remove = shisi[i - 1]; // remove(슬라이딩 윈도우에서 삭제될 초밥)
            type[remove]--;
            if (type[remove] == 0) {
                count--;
            }

            int add = shisi[(i + k - 1) % N]; // add(슬라이딩 윈도우에 추가될 초밥)
            if (type[add] == 0) {
                count++;
            }
            type[add]++;

            answer = Math.max(answer, count); // 더 많은 가짓수로 갱신
        }
    }

    /*
     * 먹을 수 있는 초밥의 최대 가짓수 출력하기
     */
    static void print() {
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        init();
        find();
        print();
    }
}