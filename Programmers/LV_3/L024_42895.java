package Programmers.LV_3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 42895) N으로_표현
 */
public class L024_42895 {
    // N, number(숫자)
    public int solution(int N, int number) {
        // DP(집합 8개를 가지고 있는 동적 테이블 리스트)
        List<Set<Integer>> dp = new ArrayList<>();
        // DP 초기화
        for (int i = 0; i < 9; i++) {
            dp.add(new HashSet<>());
        }
        // DP의 첫 번째 집합에 N 저장
        dp.get(1).add(N);
        // 2번째 집합부터 8번째 집합까지
        // 이전에 집합에 있던 수를 가지고 사칙연산(+, -, *, /)한 값을 저장
        /*
         * 예) 3번째 집합
         * (i = 3, j = 1)
         * 1개의 5(dp.get(1))를 가지고 만든 숫자 (+, -, *, /) 2개의 5(dp.get(2))를 가지고 만든 숫자
         * +
         * (i = 3, j = 2)
         * 2개의 5(dp.get(2))를 가지고 만든 숫자 (+, -, *, /) 1개의 5(dp.get(1))를 가지고 만든 숫자
         */
        for (int i = 2; i < 9; i++) {
            Set<Integer> nowSet = dp.get(i);
            for (int j = 1; j <= i; j++) {
                Set<Integer> preSet = dp.get(j);
                Set<Integer> postSet = dp.get(i - j);
                for (int preNum : preSet) {
                    for (int postNum : postSet) {
                        nowSet.add(preNum + postNum);
                        nowSet.add(preNum - postNum);
                        nowSet.add(preNum * postNum);
                        if (preNum != 0 && postNum != 0)
                            nowSet.add(preNum / postNum);
                    }
                }
            }
            // 반복한 수 저장 (예 : 55, 555, ...)
            nowSet.add(Integer.parseInt(String.valueOf(N).repeat(i)));
        }
        // 집합이 number를 가지고 있다면
        for (Set<Integer> set : dp) {
            if (set.contains(number))
                // 집합의 인덱스 리턴
                return dp.indexOf(set);
        }
        // number를 만들 수 없다면 -1 리턴
        return -1;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L024_42895 solution = new L024_42895();

        int N = 5;
        int number = 12;

        int result = solution.solution(N, number);

        System.out.println(result);
    }
}