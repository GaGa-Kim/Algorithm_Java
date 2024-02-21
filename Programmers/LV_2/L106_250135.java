package Programmers.LV_2;

/**
 * 250135) 아날로그_시계
 */
public class L106_250135 {
    // h1, m1, s1(시작 시간)
    // h2, m2, s2(종료 시간)
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        // answer(알람이 울리는 횟수)
        // 종료 시간과 시작 시간에 대한 알람 수를 계산하고 이 차이를 계산
        int answer = countAlarm(h2, m2, s2) - countAlarm(h1, m1, s1);
        // 시작 시간이 정각이라면
        if (s1 == 0 && m1 == 0)
            // 종료 시간과 시작 시간 사이에 정각 알람까지 한 번 울리므로 1을 추가로 더함
            answer++;
        // answer 반환
        return answer;
    }

    // 알람 횟수 계산하기
    private int countAlarm(int h, int m, int s) {
        // 오후 12시 이후일 경우
        if (h >= 12) {
            // 오전을 제외하고 계산
            return countAlarm(11, 59, 59) + countAlarm(h - 12, m, s);
        }
        // allCount(전체 알람 횟수)
        int allCount = 0;
        // mCount(초침과 겹치는 분침의 횟수)
        int mCount = 0;
        // hCount(초침과 겹치는 시침의 횟수)
        int hCount = 0;
        // 1시간에 59번씩 h시간 동안 초침과 분침은 겹치므로 mCount 증가
        mCount += h * 59;
        // 1시간에 60번씩 h시간 동안 초침과 시침은 겹치므로 hCount 증가
        hCount += h * 60;
        // 1분에 1번씩 m분 동안 초침과 분침은 겹치므로 mCount 증가
        mCount += m;
        // 1분에 1번씩 m분 동안 초침과 시침은 겹치므로 hCount 증가
        hCount += m;
        // 초침, 분침, 시침이 함께 겹칠 경우 알람 횟수 1번 제외
        allCount -= 1;
        // 분침의 각도 (1분에 6도씩 회전)
        int mAngle = m * 6;
        // 시침의 각도 (1시간에 30도씩 회전, 1분에 0.5도씩 회전)
        double hAngle = 30 * (h % 12) + 0.5 * m;
        // 초침을 각도를 구해 분침의 각도와 비교하여 더 클 경우 초침이 분침을 넘어가므로 mCount 증가
        if (mAngle <= 5.9 * s)
            mCount += 1;
        // 초침을 각도를 구해 시침의 각도와 비교하여 더 클 경우 초침이 시침을 넘어가므로 hCount 증가
        if (hAngle <= (6 - 1.0 / 120) * s)
            hCount += 1;
        // 전체 알람 횟수 계산
        allCount += mCount + hCount;
        // allCount 반환
        return allCount;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L106_250135 solution = new L106_250135();

        int h1 = 0;
        int m1 = 5;
        int s1 = 30;
        int h2 = 0;
        int m2 = 7;
        int s2 = 0;

        int result = solution.solution(h1, m1, s1, h2, m2, s2);

        System.out.println(result);
    }
}
