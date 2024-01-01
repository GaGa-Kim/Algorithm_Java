package Programmers.LV_1;

/**
 * 70128) 내적
 */
public class L045_70128 {
    // a, b(길이가 같은 두 1차원 정수 배열)
    public int solution(int[] a, int[] b) {
        // answer(내적)
        int answer = 0;
        // a[0]*b[0] + a[1]*b[1] + ... + a[n-1]*b[n-1]
        for (int i = 0; i < a.length; i++) {
            // answer에 a[i] * b[i] 값 합산
            answer += a[i] * b[i];
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L045_70128 solution = new L045_70128();

        int[] a = { 1, 2, 3, 4 };
        int[] b = { -3, -1, 0, 2 };

        int result = solution.solution(a, b);

        System.out.println(result);
    }
}
