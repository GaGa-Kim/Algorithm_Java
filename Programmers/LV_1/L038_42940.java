package Programmers.LV_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 42940) 모의고사
 */
public class L038_42940 {
    // answers(1번 문제부터 마지막 문제까지의 정답이 순서대로 들은 배열)
    public int[] solution(int[] answers) {
        // first(1번 수포자의 찍는 방식)
        int[] first = { 1, 2, 3, 4, 5 };
        // second(2번 수포자의 찍는 방식)
        int[] second = { 2, 1, 2, 3, 2, 4, 2, 5 };
        // third(3번 수포자의 찍는 방식)
        int[] third = { 3, 3, 1, 1, 2, 2, 4, 4, 5, 5 };
        // score(3명의 수포자의 점수)
        int[] score = new int[3];
        // first, second, third에 따라 정답을 맞춘다면 점수 증가
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == first[i % 5])
                score[0]++;
            if (answers[i] == second[i % 8])
                score[1]++;
            if (answers[i] == third[i % 10])
                score[2]++;
        }
        // score에서 최대 점수 찾기
        int max = Math.max(score[0], Math.max(score[1], score[2]));
        // 최대 점수를 가진 수포자들의 배열 반환
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < score.length; i++) {
            if (score[i] == max)
                list.add(i + 1);
        }
        int[] answer = new int[list.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L038_42940 solution = new L038_42940();

        int[] answers = { 1, 2, 3, 4, 5 };

        int[] result = solution.solution(answers);

        System.out.println(Arrays.toString(result));
    }
}