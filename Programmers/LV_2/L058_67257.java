package Programmers.LV_2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 67257) 수식_최대화
 */
public class L058_67257 {
    // answer(우승 시 받을 수 있는 가장 큰 상금 금액)
    long answer = 0;
    // numbers(expression의 피연산자 문자열 리스트)
    List<Long> numbers = new ArrayList<>();
    // operators(expression의 연산자 문자열 리스트)
    List<Character> operators = new ArrayList<>();
    // visited(연산자 사용 유무 배열)
    boolean[] visited = new boolean[3];
    // op(연산자 +, -, *의 우선순위)
    int[] op = new int[3];
    // priority(연산자와 우선순위를 담은 HashMap)
    Map<Character, Integer> priority = new HashMap<>();

    // expression(참가자에게 주어진 연산 수식)
    public long solution(String expression) {
        // numbers와 operators에 expression의 피연산자, 연산자 저장
        StringTokenizer st = new StringTokenizer(expression, "+-*", true);
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if ("+-*".contains(token))
                operators.add(token.charAt(0));
            else
                numbers.add(Long.parseLong(token));
        }
        // 연산자 우선순위 경우의 수 구하기 함수
        dfs(0);
        // answer 반환
        return answer;
    }

    // 연산자 우선순위 경우의 수 구하기 함수
    private void dfs(int depth) {
        // depth가 3이라면 모든 연산자의 우선순위를 구한 것이므로
        if (depth == 3) {
            // priority에 연산자와 연산자의 우선순위 인덱스인 op의 값을 저장
            priority.put('+', op[0]);
            priority.put('-', op[1]);
            priority.put('*', op[2]);
            // 계산하기 함수
            calculate();
            return;
        }
        for (int i = 0; i < 3; i++) {
            // i번째 연산자를 이미 우선순위 경우의 수에 사용했다면
            if (visited[i])
                continue;
            // visited[i]를 true로 갱신
            visited[i] = true;
            // 연산자의 우선순위 갱신;
            op[depth] = i;
            // 연산자 우선순위 경우의 수 구하기 함수(depth + 1)
            dfs(depth + 1);
            // visited[i]를 false로 초기화
            visited[i] = false;
        }
    }

    // 계산하기 함수
    private void calculate() {
        // numberStack(피연산자를 담을 Stack)
        Stack<Long> numberStack = new Stack<>();
        // operatorStack(연산자를 담을 Stack)
        Stack<Character> operatorStack = new Stack<>();
        // numberStack에 numbers의 첫 번째 값을 담기
        numberStack.push(numbers.get(0));
        for (int i = 0; i < operators.size(); i++) {
            // nextOperator(operators의 i번째 연산자)
            char nextOperator = operators.get(i);
            // operatorStack이 비어있지 않으면서
            // operatorStack의 가장 상단의 연산자의 우선순위가 nextOperator보다 크거나 같을 동안
            while (!operatorStack.isEmpty() && priority.get(operatorStack.peek()) >= priority.get(nextOperator)) {
                // numberStack에 numberStack의 두 값에 대해 operatorStack의 연산자로 계산한 결과를 저장
                numberStack.push(cal(numberStack.pop(), numberStack.pop(), operatorStack.pop()));
            }
            // numberStack에 numbers의 다음 값을 담기
            numberStack.push(numbers.get(i + 1));
            // operatorStack에 nextOperator 저장
            operatorStack.push(nextOperator);
        }
        // numberStack이 1보다 클 동안
        while (numberStack.size() > 1) {
            // 나머지 남은 값을 계산하여 저장
            numberStack.push(cal(numberStack.pop(), numberStack.pop(), operatorStack.pop()));
        }
        // answer을 Math.max(answer, Math.abs(numberStack의 값))으로 갱신
        answer = Math.max(answer, Math.abs(numberStack.pop()));
    }

    // 연산자에 따른 계산
    private Long cal(Long number1, Long number2, char nowOperator) {
        if (nowOperator == '+')
            return number2 + number1;
        else if (nowOperator == '-')
            return number2 - number1;
        else
            return number2 * number1;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L058_67257 solution = new L058_67257();

        String expression = "100-200*300-500+20";

        long result = solution.solution(expression);

        System.out.println(result);
    }
}
