package Programmers.LV_2;

/**
 * 60058) 괄호_변환
 */
public class L055_60058 {
    // p('('와 ')'로만 이루어진 문자열)
    public String solution(String p) {
        // 1) 입력이 빈 문자열인 경우
        if (p.isEmpty())
            // 빈 문자열을 반환
            return p;
        // 2) 문자열 p를 두 균형잡힌 괄호 문자열 u, v로 분리
        int index = splitBracket(p);
        String u = p.substring(0, index + 1);
        String v = p.substring(index + 1);
        // 3) 문자열 u가 올바른 괄호 문자열이라면
        if (isCorrect(u))
            // v에 대해 1 단계부터 다시 수행하여 u에 이어 붙임
            return u + solution(v);
        // 4) 문자열 u가 올바른 괄호 문자열이 아니라면
        // 빈 문자열에 '('를 붙인 후 v에 대해 1단계부터 재귀적으로 수행한 결과를 이어 붙이고 ')'를 다시 붙임
        // 이후 u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙인 후 생성된 문자열을 반환
        return "(" + solution(v) + ")" + reverse(u);
    }

    // 괄호 분리 인덱스 찾기 함수
    private int splitBracket(String p) {
        // index(두 균형잡힌 괄호 문자열을 분리할 인덱스)
        int index = 0;
        // left(왼쪽 괄호 갯수)
        int left = 0;
        // right(오른쪽 괄호 갯수)
        int right = 0;
        for (int i = 0; i < p.length(); i++) {
            // c(p의 i번째 글자)
            char c = p.charAt(i);
            // c가 )인지, (인지에 따라 left, right 증가
            if (c == '(')
                left++;
            else
                right++;
            // left와 right가 같다면
            if (left == right) {
                // 균형잡힌 괄호 분리를 위한 index에 i를 저장하고 종료
                index = i;
                break;
            }
        }
        // index 반환
        return index;
    }

    // 올바른 괄호 문자열 확인 함수
    private boolean isCorrect(String u) {
        // left(왼쪽 괄호 갯수)
        int left = 0;
        // right(오른쪽 괄호 갯수)
        int right = 0;
        for (int i = 0; i < u.length(); i++) {
            // c(u의 i번째 글자)
            char c = u.charAt(i);
            // c가 )인지, (인지에 따라 left, right 증가
            if (c == '(')
                left++;
            else
                right++;
            // right가 left보다 크다면
            if (right > left)
                // 괄호가 올바르지 않으므로 false를 반환
                return false;
        }
        // true를 반환
        return true;
    }

    // 문자열 제거하고 뒤집기 함수
    private String reverse(String u) {
        // u의 첫 번째와 마지막 문자를 제거
        String reverseU = u.substring(1, u.length() - 1);
        // u의 괄호 방향을 뒤집어서 반환
        reverseU = reverseU.replace("(", ".");
        reverseU = reverseU.replace(")", "(");
        reverseU = reverseU.replace(".", ")");
        return reverseU;
    }

    public static void main(String[] args) {
        L055_60058 solution = new L055_60058();

        String p = "()))((()";

        String result = solution.solution(p);

        System.out.println(result);
    }
}
