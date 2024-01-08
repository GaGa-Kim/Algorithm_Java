package Programmers.LV_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 12946) 하노이의_탑
 */
public class L010_12946 {
    // list(이동한 방법들 리스트)
    static List<int[]> list = new ArrayList<>();

    // n(1번 기둥에 있는 원판의 개수)
    public int[][] solution(int n) {
        // 하노이 함수(n, 1, 2, 3)
        hanoi(n, 1, 2, 3);
        // answer(n개의 원판을 3번 원판으로 최소로 옮기는 방법)
        int[][] answer = new int[list.size()][];
        // answer에 방법들 저장
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        // answer 반환
        return answer;
    }

    // 하노이 함수(원판의 개수, 시작 기둥, 거쳐갈 기둥, 도착 기둥)
    private void hanoi(int n, int start, int pass, int arrive) {
        // n이 1이라면
        if (n == 1) {
            // list에 [시작 기둥, 도착 기둥] 저장
            list.add(new int[] { start, arrive });
            return;
        }
        // 1번 기둥의 n - 1개를 3번 기둥을 걸쳐 2번 기둥으로 옮기기
        hanoi(n - 1, start, arrive, pass);
        // 1번의 가장 큰 n을 3번 기둥으로 옮기므로 list에 [시작 기둥, 도착 기둥] 저장
        list.add(new int[] { start, arrive });
        // 2번 기둥의 n - 1개를 1번 기둥을 걸쳐 3번 기둥으로 옮기기
        hanoi(n - 1, pass, start, arrive);
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L010_12946 solution = new L010_12946();

        int n = 2;

        int[][] result = solution.solution(n);

        System.out.println(Arrays.toString(result));
    }
}
