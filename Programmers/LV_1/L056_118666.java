package Programmers.LV_1;

/**
 * 118666) 성격_유형_검사하기
 */
public class L056_118666 {
    // survey(질문마다 판단하는 지표)
    // choices(검사자가 각 질문마다 선택한 선택지)
    public String solution(String[] survey, int[] choices) {
        // answer(검사자의 성격 유형 검사 결과)
        String answer = "";
        // point(RTCFJMAN에 따른 점수 정수 배열)
        int[] point = new int[8];
        // personality(성격 유형 RTCFJMAN)
        String personality = "RTCFJMAN";
        for (int i = 0; i < survey.length; i++) {
            // choices[i]가 4보다 크다면
            if (choices[i] > 4) {
                // 성격 유형 중 뒤 유형에 각 3 ~ 1점을 주도록 함 (선택 - 4)
                String p = survey[i].split("")[1];
                point[personality.indexOf(p)] += choices[i] - 4;
            }
            // choices[i]가 4라면
            else if (choices[i] == 4) {
                // 건너뜀
                continue;
            }
            // choices[i]가 4보다 작다면
            else {
                // 성격 유형 중 앞 유형에 각 1 ~ 3점을 주도록 함 (4 - 선택)
                String p = survey[i].split("")[0];
                point[personality.indexOf(p)] += 4 - choices[i];
            }
        }
        for (int i = 0; i < point.length / 2; i++) {
            // 성격 유형 중 앞의 성격 유형이 더 크거나 같을 경우
            if (point[i * 2] >= point[i * 2 + 1])
                // answer에 앞의 성격 유형 추가
                answer += personality.split("")[i * 2];
            else
                // answer에 뒤의 성격 유형 추가
                answer += personality.split("")[i * 2 + 1];
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L056_118666 solution = new L056_118666();

        String[] survey = { "TR", "RT", "TR" };
        int[] choices = { 7, 1, 3 };

        String result = solution.solution(survey, choices);

        System.out.println(result);
    }
}
