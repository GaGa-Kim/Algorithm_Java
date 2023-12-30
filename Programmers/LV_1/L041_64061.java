package Programmers.LV_1;

import java.util.Stack;

/**
 * 64061) 크레인_인형뽑기_게임
 */
public class L041_64061 {
    // board(게임 화면의 격차의 상태가 담긴 2차원 배열)
    // moves(인형을 집기 위해 크레인을 작동시킨 위치가 담긴 배열)
    public int solution(int[][] board, int[] moves) {
        // answer(크레인을 모두 작동시킨 후 터트러져 사라진 인형의 개수)
        int answer = 0;
        // stack(집어 올린 인형을 담을 바구니)
        Stack<Integer> stack = new Stack<>();
        for (int i : moves) {
            for (int j = 0; j < board.length; j++) {
                // board[j][i - 1]가 0이 아니라면 (인형이 존재한다면)
                if (board[j][i - 1] != 0) {
                    // stack이 비어있지 않으면서 stack의 맨 위에 있는 인형이 현재 꺼낸 인형과 같다면
                    if (!stack.isEmpty() && stack.peek() == board[j][i - 1]) {
                        // 스택에서 인형 제거
                        stack.pop();
                        // 인형 개수 2 증가
                        answer += 2;
                    }
                    // 스택이 비어있거나, stack의 맨 위에 있는 인형이 현재 꺼낸 인형과 다르다면
                    else {
                        // stack에 현재 인형 넣기
                        stack.push(board[j][i - 1]);
                    }
                    // 인형을 뽑았으므로 0으로 변경
                    board[j][i - 1] = 0;
                    break;
                }
            }
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L041_64061 solution = new L041_64061();

        int[][] board = {
                { 0, 0, 0, 0, 0 },
                { 0, 0, 1, 0, 3 },
                { 0, 2, 5, 0, 1 },
                { 4, 2, 4, 4, 2 },
                { 3, 5, 1, 3, 1 } };
        int[] moves = { 1, 5, 3, 5, 1, 2, 1, 4 };

        int result = solution.solution(board, moves);

        System.out.println(result);
    }
}
