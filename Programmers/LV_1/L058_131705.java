package Programmers.LV_1;

/**
 * 131705) 삼총사
 */
public class L058_131705 {
    // answer(학생들 중 삼총사를 만들 수 있는 방법의 수)
    static int answer;
    // temp(세 명의 학생들의 번호를 가지는 임시 정수 배열)
    static int[] temp = new int[3];

    // number(한국중학교 학생들의 번호를 나타내는 정수 배열)
    public int solution(int[] number) {
        answer = 0;
        // DFS(0, 0, number)
        DFS(0, 0, number);
        // answer 반환
        return answer;
    }

    // DFS
    private void DFS(int start, int depth, int[] number) {
        // DFS의 깊이가 3이라면
        if (depth == 3) {
            // sum(번호들의 합)
            int sum = 0;
            for (int i = 0; i < 3; i++) {
                // sum에 temp[i] 합하기
                sum += temp[i];
            }
            // sum이 0이라면
            if (sum == 0)
                // answer 증가
                answer++;
            return;
        }
        for (int i = start; i < number.length; i++) {
            // 임시 정수 배열에 현재 번호 저장
            temp[depth] = number[i];
            // DFS(i + 1, depth + 1) 재귀
            DFS(i + 1, depth + 1, number);
        }
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L058_131705 solution = new L058_131705();

        int[] number = { -2, 3, 0, 2, -5 };

        int result = solution.solution(number);

        System.out.println(result);
    }
}
