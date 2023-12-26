package Programmers.LV_1;

/**
 * 12916) 문자열_내_p와_y의_개수
 */
public class L008_12916 {
    // s(대문자와 소문자가 섞여있는 문자열)
    boolean solution(String s) {
        // pIndex(p의 개수 인덱스)
        int pIndex = 0;
        // yIndex(y의 개수 인덱스)
        int yIndex = 0;
        for (int i = 0; i < s.length(); i++) {
            // now(현재 문자)
            char now = s.charAt(i);
            // now가 p 또는 P라면
            if (now == 'p' || now == 'P')
                // pIndex 증가
                pIndex++;
            // now가 y 또는 Y라면
            else if (now == 'y' || now == 'Y')
                // yIndex 증가
                yIndex++;
            else
                // 통과
                continue;
        }
        // pIndex와 yIndex가 같다면
        if (pIndex == yIndex)
            // true 반환
            return true;
        // 다르다면
        else
            // false 반환
            return false;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L008_12916 solution = new L008_12916();

        String s = "pPoooyY";

        boolean result = solution.solution(s);

        System.out.println(result);
    }
}
