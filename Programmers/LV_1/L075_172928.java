package Programmers.LV_1;

import java.util.Arrays;

/**
 * 172928) 공원_산책
 */
public class L075_172928 {

    // park(공원을 나타내는 문자열 배열)
    // routes(로봇 강아지가 수행할 명령이 담긴 문자열 배열)
    public int[] solution(String[] park, String[] routes) {

        // sx, sy(시작 위치 좌표)
        int sx = 0;
        int sy = 0;
        // parks(공원을 담은 문자열 배열)
        char[][] parks = new char[park.length][park[0].length()];
        // parks에 공원 정보 저장하기
        for (int i = 0; i < park.length; i++) {
            parks[i] = park[i].toCharArray();
            // 시작 위치 좌표 sx, sy 저장하기
            if (park[i].contains("S")) {
                sx = park[i].indexOf("S");
                sy = i;
            }
        }
        for (String route : routes) {
            // op(이동할 방향)
            String op = route.split(" ")[0];
            // n(이동할 칸의 수)
            int n = Integer.parseInt(route.split(" ")[1]);
            // 이동할 위치 좌표 dx, dy 저장하기
            int dx = sx;
            int dy = sy;
            // 이동할 칸의 수만큼
            for (int i = 0; i < n; i++) {
                // 이동할 방향에 따라 좌표 갱신
                if (op.equals("E")) // 동쪽
                    dx++;
                if (op.equals("W")) // 서쪽
                    dx--;
                if (op.equals("S")) // 남쪽
                    dy++;
                if (op.equals("N")) // 북쪽
                    dy--;
                // 이동할 때 공원을 벗어나는지
                if (dx >= 0 && dy >= 0 && dx < parks[0].length && dy < parks.length) {
                    // 이동할 때 장애물과 만난다면
                    if (parks[dy][dx] == 'X')
                        break;
                    // 이동할 칸의 수만큼 이동했다면
                    if (i == n - 1) {
                        // 시작 위치 좌표 sx, sy 갱신
                        sx = dx;
                        sy = dy;
                    }
                }
            }
        }
        // 시작 위치 좌표 sx, sy 리턴
        int[] answer = { sy, sx };
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L075_172928 solution = new L075_172928();

        String[] park = { "SOO", "OXX", "OOO" };
        String[] routes = { "E 2", "S 2", "W 1" };

        int[] result = solution.solution(park, routes);

        System.out.println(Arrays.toString(result));
    }
}
