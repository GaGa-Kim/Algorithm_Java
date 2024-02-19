package Programmers.LV_2;

/**
 * 181187) 두_원_사이의_정수_쌍
 */
public class L104_181187 {
    // r1, r2(반지름)
    public long solution(int r1, int r2) {
        // answer(두 원 사이의 공간에 x좌표와 y좌표가 모두 정수인 점의 개수)
        long answer = 0;
        // (1, 0)부터 (r2, 0)까지
        for (int x = 1; x <= r2; x++) {
            // minY(x에 대한 점의 최소 Y 값) - r1과 r2 사이에 있을 수 있으므로 올림 값
            long minY = (long) Math.ceil(Math.sqrt(1.0 * r1 * r1 - 1.0 * x * x));
            // maxY(x에 대한 점의 최대 Y 값) - r1과 r2 사이에 있을 수 있으므로 내림 값
            long maxY = (long) Math.floor(Math.sqrt(1.0 * r2 * r2 - 1.0 * x * x));
            // maxY와 minY 사이에 있는 점의 개수를 추가
            answer += (maxY - minY + 1);
        }
        // 한 분면의 4배를 반환
        return answer * 4;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L104_181187 solution = new L104_181187();

        int r1 = 2;
        int r2 = 3;

        long result = solution.solution(r1, r2);

        System.out.println(result);
    }
}
