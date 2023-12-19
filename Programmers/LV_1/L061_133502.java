package Programmers.LV_1;

import java.util.Stack;

/**
 * 133502) 햄버거_만들기
 */
public class L061_133502 {
    // ingredient(재료의 정보를 나타내는 정수 배열)
    public int solution(int[] ingredient) {
        // answer(햄버거의 개수)
        int answer = 0;
        // stack(재료를 순서대로 저장할 스택)
        Stack<Integer> stack = new Stack<>();
        for (int i : ingredient) {
            // stack에 재료 저장
            stack.push(i);
            // stack의 크기가 4 이상이라면
            if (stack.size() >= 4) {
                int size = stack.size();
                // 빵(1), 야채(2), 고기(3), 빵(1) 순서인지 확인
                if (stack.get(size - 1) == 1
                        && stack.get(size - 2) == 3
                        && stack.get(size - 3) == 2
                        && stack.get(size - 4) == 1) {
                    // 햄버거의 개수 증가
                    answer++;
                    // stack에서 빵, 야채, 고기, 빵 삭제
                    stack.pop();
                    stack.pop();
                    stack.pop();
                    stack.pop();
                }
            }
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L061_133502 solution = new L061_133502();

        int[] ingredient = { 2, 1, 1, 2, 3, 1, 2, 3, 1 };

        int result = solution.solution(ingredient);

        System.out.println(result);
    }
}
