package Baekjoon.B_1092;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 크레인은 1분에 박스를 하나씩 실을 수 있으므로, 가장 큰 무게를 들 수 있는 크레인이 가장 큰 무게를 들도록 함
 */

/* 
 * 손으로 풀어보기
 * 1. 크레인과 박스의 무게를 내림차순으로 정렬
 * 2. 가장 큰 무게의 박스를 가장 큰 무게를 들 수 있는 크레인에 할당하도록 하는 것을 반복
 * 3. 모든 크레인을 사용했을 때 시간을 1분 증가하고 다시 처음부터 크레인에 박스를 할당하도록 함
 * 4. 이를 반복하여 모든 박스를 옮기도록 함
 * 5. 만약 가장 큰 무게의 박스가 가장 큰 무게를 들 수 있는 크레인의 무게보다 크다면 모든 박스를 배로 옮길 수 없으므로 -1을 출력
 */

/*
 * 1092) 배
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, M; // N(크레인 개수), M(박스 개수)
    static Integer[] cranes; // cranes(각 크레인의 무게 제한)
    static ArrayList<Integer> boxes; // boxes(각 박스의 무게)
    static int answer = 0; // answer(모든 박스를 배로 옮기는데 드는 시간의 최솟값)

    /*
     * 크레인과 박스의 무게를 입력 받기
     */
    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        cranes = new Integer[N]; // 크레인 정보 입력 받기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cranes[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        boxes = new ArrayList<>(); // 박스 정보 입력 받기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            boxes.add(Integer.parseInt(st.nextToken()));
        }
    }
    
    /*
     * 크레인마다 박스 할당하기
     */
    static void move() {
        Arrays.sort(cranes, Collections.reverseOrder()); // 크레인과 박스의 무게를 내림차순으로 정렬
        Collections.sort(boxes, Collections.reverseOrder());

        if (cranes[0] < boxes.get(0)) { // 가장 큰 무게의 박스가 가장 큰 무게를 들 수 있는 크레인의 무게보다 크다면
            answer = -1; // 모든 박스를 배로 옮길 수 없음
            return;
        }
        while (!boxes.isEmpty()) { // 모든 박스를 옮길 동안 반복
            answer++; // 모든 크레인을 사용해 동시에 하나씩 배에 실을 때 1분 증가
            for (int crane_index = 0; crane_index < N; crane_index++) {
                for (int box_index = 0; box_index < boxes.size(); box_index++) {
                    if (cranes[crane_index] >= boxes.get(box_index)) { // 이번 크레인에 박스를 실을 수 있다면
                        boxes.remove(box_index); // 이번 박스는 배로 옮기고 다음 박스를 확인하도록 함
                        break;
                    }
                }
            }
        }
    }

    /*
     * 모든 박스를 배로 옮기는데 드는 시간의 최솟값 출력하기
     */
    static void print() {
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        init();
        move();
        print();
    }
}
