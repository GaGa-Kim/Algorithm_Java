package Programmers.Kit.Queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 12909) 올바른_괄호
 */
public class K002_12909 {
    // s('(' 또는 ')'로만 이루어진 문자열)
    boolean solution(String s) {
        boolean answer = true;
        // queue(괄호를 담을 queue)
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            // '('라면
            if (s.charAt(i) == '(') {
                // queue에 저장
                queue.add("(");
            }
            // ')'라면
            if (s.charAt(i) == ')') {
                // queue의 크기가 0이라면
                if (queue.size() == 0) {
                    return false;
                }
                // queue에서 짝이 맞는 '(' 제거
                queue.remove();
            }
        }
        // queue의 크기가 0이 아니라면
        if (queue.size() != 0) {
            answer = false;
        }
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        K002_12909 solution = new K002_12909();

        String s = "()()";
        boolean result = solution.solution(s);

        System.out.println(result);
    }
}
