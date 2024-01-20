package Programmers.LV_2;

/**
 * 60057) 문자열_압축
 */
public class L054_60057 {
    // s(압축할 문자열)
    public int solution(String s) {
        // answer(1개 이상 단위로 문자열을 잘라 압축하여 표현한 문자열 중 가장 짧은 것의 길이)
        int answer = s.length();
        for (int i = 1; i <= s.length() / 2; i++) {
            // sb(압축 결과 문자열)
            StringBuilder sb = new StringBuilder();
            // prev(이전 문자열)
            String prev = s.substring(0, i);
            // now(현재 문자열)
            String now = "";
            // count(반복 횟수)
            int count = 1;
            for (int j = i; j <= s.length(); j += i) {
                // j + i가 s의 길이보다 크거나 같다면
                if (j + i >= s.length())
                    // s를 j부터 s의 길이까지 자른 문자열
                    now = s.substring(j, s.length());
                // j + i가 s의 길이보다 작다면
                else
                    // s를 j부터 j + i까지 자른 문자열
                    now = s.substring(j, j + i);
                // prev와 now가 같다면
                if (prev.equals(now))
                    // count 증가
                    count++;
                // count가 1이라면
                else if (count == 1) {
                    // sb에 prev 추가
                    sb.append(prev);
                    // prev를 now로 갱신
                    prev = now;
                }
                // count가 1보다 크다면
                else {
                    // sb에 count와 prev 추가
                    sb.append(count).append(prev);
                    // prev를 now로 갱신
                    prev = now;
                    // count를 1로 초기화
                    count = 1;
                }
            }
            // i와 prev의 길이가 다르다면
            if (i != prev.length())
                // 남는 문자열이므로 sb에 나머지인 prev 저장
                sb.append(prev);
            // answer을 Math.min(answer, sb의 길이)로 갱신
            answer = Math.min(answer, sb.length());
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L054_60057 solution = new L054_60057();

        String s = "aabbaccc";

        int result = solution.solution(s);

        System.out.println(result);
    }
}
