package Programmers.LV_1;

import java.util.Arrays;

/**
 * 161990) 바탕화면_정리
 */
public class L074_161990 {

    // wallpaper(바탕화면의 칸의 상태를 나타내는 문자열 배열)
    public int[] solution(String[] wallpaper) {
        // lux, luy(드래그 시작점 좌표)
        int lux = Integer.MAX_VALUE;
        int luy = Integer.MAX_VALUE;
        // rdx, rdy(드래그 끝점 좌표)
        int rdx = Integer.MIN_VALUE;
        int rdy = Integer.MIN_VALUE;
        for (int i = 0; i < wallpaper.length; i++) {
            for (int j = 0; j < wallpaper[0].length(); j++) {
                if (wallpaper[i].charAt(j) == '#') {
                    // lux : #가 가장 처음으로 나온(작은) wallpaper
                    lux = Math.min(lux, i);
                    // luy : #가 가장 처음으로 나온(작은) wallpaper[i]
                    luy = Math.min(luy, j);
                    // rdx : #가 가장 마지막으로 나온(큰) wallpaper
                    rdx = Math.max(rdx, i);
                    // rdy : #가 가장 마지막으로 나온(큰) wallpaper[i]
                    rdy = Math.max(rdy, j);
                }
            }
        }
        // 좌표 lux, luy, rdx + 1, rdy + 1 리턴
        // #가 나온 좌표의 가장 우측 아래쪽을 리턴해야 하므로 + 1
        int[] answer = { lux, luy, rdx + 1, rdy + 1 };
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L074_161990 solution = new L074_161990();

        String[] wallpaper = { ".#...", "..#..", "...#." };

        int[] result = solution.solution(wallpaper);

        System.out.println(Arrays.toString(result));
    }
}
