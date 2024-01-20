package Programmers.LV_2;

import java.util.Arrays;

/**
 * 42885) 구명보트
 */
public class L048_42885 {
    // people(사람들의 몸무게를 담은 배열)
    // limit(구명보트의 무게 제한)
    public int solution(int[] people, int limit) {
        // answer(구명보트 개수의 최솟값)
        int answer = 0;
        // people 오름차순 정렬
        Arrays.sort(people);
        // index(앞에서부터 탐색하는 인덱스) = 0
        int index = 0;
        for (int i = people.length - 1; i >= index; i--) {
            // 두 몸무게가 무게 제한보다 작거나 같다면
            if (people[i] + people[index] <= limit) {
                // 함께 구출 가능하므로 index, answer 증가
                index++;
                answer++;
            } else {
                // 함께 구출 불가능하므로 answer만 증가
                answer++;
            }
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L048_42885 solution = new L048_42885();

        int[] people = { 70, 50, 80, 50 };
        int limit = 100;

        int result = solution.solution(people, limit);

        System.out.println(result);
    }
}