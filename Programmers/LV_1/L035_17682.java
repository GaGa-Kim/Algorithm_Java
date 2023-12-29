package Programmers.LV_1;

/**
 * 17682) 다트_게임
 */
public class L035_17682 {
    // dartResult("점수|보너스|[옵션]"으로 이루어진 문자열 3세트)
    public int solution(String dartResult) {
        // answer(3번의 기회에서 얻은 점수 합계)
        int answer = 0;
        // points(3번의 기회 점수 배열)
        int[] points = new int[3];
        // index(저장 위치 인덱스)
        int index = -1;
        for (int i = 0; i < dartResult.length(); i++) {
            // now(dartResult의 i 값)
            char now = dartResult.charAt(i);
            // now가 숫자라면
            if (Character.isDigit(now)) {
                // now가 0이라면 (0, 10)
                if (now == '0') {
                    // index가 -1이 아니고 points[index]가 1이라면
                    if (index != -1 && points[index] == 1)
                        // 10 저장
                        points[index] = 10;
                    // index가 -1 이거나 points[index]가 1이 아니라면
                    else
                        // 0 저장
                        points[++index] = 0;
                }
                // now가 0이 아니라면 (1 ~ 9)
                else {
                    // now 저장
                    points[++index] = Character.getNumericValue(now);
                }
            }
            // now가 S, D, T라면
            else if (now == 'S' || now == 'D' || now == 'T') {
                // S라면
                if (now == 'S') {
                    // 1제곱이므로 통과
                    continue;
                }
                // D라면
                else if (now == 'D') {
                    // 2의 제곱 저장
                    points[index] = (int) Math.pow(points[index], 2);
                }
                // T라면
                else {
                    // 3의 제곱 저장
                    points[index] = (int) Math.pow(points[index], 3);
                }
            }
            // now가 *, #라면
            else {
                // *라면
                if (now == '*') {
                    // 2배 저장
                    points[index] = points[index] * 2;
                    // 첫 번째 점수가 아니라면
                    if (index != 0)
                        // 바로 앞의 점수도 2배 저장
                        points[index - 1] = points[index - 1] * 2;
                }
                // #라면
                else {
                    // -1배 저장
                    points[index] = points[index] * -1;
                }
            }
        }
        // answer에 점수 합계 저장
        for (int i = 0; i < points.length; i++) {
            answer += points[i];
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L035_17682 solution = new L035_17682();

        String dartResult = "0D2S0T";

        int result = solution.solution(dartResult);

        System.out.println(result);
    }
}
