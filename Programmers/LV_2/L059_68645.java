package Programmers.LV_2;

import java.util.Arrays;

/**
 * 68645) 삼각_달팽이
 */
public class L059_68645 {
    // n(밑변의 길이와 높이)
    public int[] solution(int n) {
        // map(달팽이 채우기를 진행하는 2차원 배열)
        int[][] map = new int[n][n];
        // x, y(달팽이 채우기 좌표)
        int x = -1, y = 0;
        // number(달팽이 채우기 숫자)
        int number = 1;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (i % 3 == 0)
                    // 아래로 내려가므로 x 증가
                    x++;
                else if (i % 3 == 1)
                    // 오른쪽으로 가므로 y 증가
                    y++;
                else {
                    // 대각선 위로 가므로 x, y 감소
                    x--;
                    y--;
                }
                // map[x][y]에 number 저장 후 number 증가
                map[x][y] = number++;
            }
        }
        // answer(밑변의 길이와 높이가 n인 삼각형에서 맨 위 꼭짓점부터 반시계 방향으로 달팽이 채우기를 진행한 후,
        // 첫 행부터 마지막 행까지 모두 순서대로 합친 새로운 배열)
        int[] answer = new int[n * (n + 1) / 2];
        // index(answer의 위치 인덱스)
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 0)
                    break;
                // answer[index++]에 map[i][j] 저장
                answer[index++] = map[i][j];
            }
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L059_68645 solution = new L059_68645();

        int n = 4;

        int[] result = solution.solution(n);

        System.out.println(Arrays.toString(result));
    }
}
