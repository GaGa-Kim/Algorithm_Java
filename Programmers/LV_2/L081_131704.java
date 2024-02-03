package Programmers.LV_2;

import java.util.Stack;

/**
 * 131704) 택배상자
 */
public class L081_131704 {
    // order(택배 기사님이 원하는 상자 순서)
    public int solution(int[] order) {
        // answer(영재가 실을 수 있는 상자의 개수)
        int answer = 0;
        // stack(상자를 담을 스택)
        Stack<Integer> stack = new Stack<>();
        // num(오름차순 자연수)
        int num = 1;
        for (int i = 0; i < order.length; i++) {
            // box(현재 상자의 값)
            int box = order[i];
            // box가 num보다 크거나 같다면
            if (box >= num) {
                // box와 num이 같아질 때까지
                while (box >= num) {
                    // 스택에 num++ 저장
                    stack.push(num++);
                }
                // stack의 가장 위의 수가 box와 같으므로 pop
                stack.pop();
                // answer 증가
                answer++;
            }
            // box가 num보다 작다면
            else {
                // top(스택의 가장 위의 수)
                int top = stack.pop();
                // top이 box보다 크다면
                if (top > box)
                    // 더이상 상자를 실을 수 없으므로 break;
                    break;
                else
                    // answer 증가
                    answer++;
            }
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L081_131704 solution = new L081_131704();

        int[] order = { 4, 3, 1, 2, 5 };

        int result = solution.solution(order);

        System.out.println(result);
    }
}
