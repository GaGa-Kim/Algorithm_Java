package Programmers.LV_2;

/**
 * 148653) 마법의_엘리베이터
 */
public class L090_148653 {
    // storey(민수와 마법의 엘리베이터가 있는 층)
    public int solution(int storey) {
        // answer(마법의 돌의 최소값)
        int answer = 0;
        // sstorey(stroey를 문자열로 변환한 값)
        String sstorey = String.valueOf(storey);
        // arr(엘리베이터의 층수를 하나씩 저장할 배열)
        int[] arr = new int[sstorey.length()];
        // arr에 sstorey 저장
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Character.getNumericValue(sstorey.charAt(i));
        }
        // 가장 마지막 자리부터 가장 앞 자리까지
        for (int i = arr.length - 1; i >= 0; i--) {
            // arr[i]가 10이라면
            if (arr[i] == 10) {
                // 가장 앞 자리라면
                if (i - 1 < 0) {
                    // -10 버튼을 1번 누르므로 answer 1 증가 후 continue;
                    answer++;
                    continue;
                }
                // 앞의 자리를 1 증가시키므로 arr[i - 1]을 1 증가 후 continue;
                arr[i - 1]++;
                continue;
            }
            // arr[i]가 4보다 작다면
            if (arr[i] <= 4)
                // 0이 될 때까지 -1 버튼을 arr[i]번 누르므로 answer을 arr[i]만큼 증가
                answer += arr[i];
            // arr[i]가 5라면
            else if (arr[i] == 5) {
                // 가장 앞 자리라면
                if (i - 1 < 0) {
                    // 0이 될 때까지 -1 버튼을 5번 누르므로 answer을 5만큼 증가 후 continue;
                    answer += 5;
                    continue;
                } else {
                    // arr[i - 1]이 5보다 작다면
                    if (arr[i - 1] < 5)
                        // 0이 될 때까지 -1 버튼을 5번 누르므로 answer을 5만큼 증가
                        answer += 5;
                    else {
                        // 10이 될 때까지 +1 버튼을 5번 누르므로 answer을 5만큼 증가
                        answer += 5;
                        // 앞의 자리를 1 증가시키므로 arr[i - 1]을 1 증가
                        arr[i - 1]++;
                    }
                }
            }
            // arr[i]가 6보다 크다면
            else if (arr[i] >= 6) {
                // 10이 될 때까지 +1 버튼을 10 - arr[i]번 누르므로 answer을 10 - arr[i]만큼 증가
                answer += (10 - arr[i]);
                // 가장 앞 자리라면
                if (i - 1 < 0) {
                    // 앞의 자리를 1로 생성하므로 answer을 1 증가 후 continue;
                    answer++;
                    continue;
                }
                // 앞의 자리를 1 증가시키므로 arr[i - 1]을 1 증가
                arr[i - 1]++;
            }
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L090_148653 solution = new L090_148653();

        int storey = 2554;

        int result = solution.solution(storey);

        System.out.println(result);
    }
}
