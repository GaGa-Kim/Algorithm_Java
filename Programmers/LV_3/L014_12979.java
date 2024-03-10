package Programmers.LV_3;

/**
 * 12979) 기지국_설치
 */
public class L014_12979 {
    // n(아파트의 개수)
    // stations(현재 기지국이 설치된 아파트의 번호)
    // w(전파의 도달 거리)
    public int solution(int n, int[] stations, int w) {
        // answer(모든 아파트에 전파를 전달하기 위해 증설해야 할 기지국 개수의 최솟값)
        int answer = 0;
        // aptIndex(현재 아파트 순서 인덱스)
        int aptIndex = 1;
        // stationIndex(기지국 순서 인덱스)
        int stationIndex = 0;
        // aptIndex가 n보다 작을 동안
        while (aptIndex <= n) {
            // 모든 기지국을 살펴보지 않았으면서 stationIndex번째 기지국의 전파에 현재 아파트가 포함된다면
            if (stationIndex < stations.length && stations[stationIndex] - w <= aptIndex) {
                // aptIndex를 stationIndex번째 기지국의 전파에 포함되는 않는 곳으로 이동
                aptIndex = stations[stationIndex] + w + 1;
                // 하나의 기지국을 살펴보았으므로 stationIndex 증가
                stationIndex++;
            }
            // 모든 기지국을 살펴보았거나 stationIndex번째 기지국의 전파에 현재 아파트가 포함되지 않는다면
            else {
                // 기지국을 한 개 증설해야하므로 answer 증가
                answer++;
                // 현재 위치의 아파트가 기지국의 전파 위치의 가장 끝에 위치하도록 가장 오른쪽의 위치에 기지국을 증설하여 이 위치로 aptIndex 이동
                aptIndex += w * 2 + 1;
            }
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L014_12979 solution = new L014_12979();

        int n = 11;
        int[] stations = { 4, 11 };
        int w = 1;

        int result = solution.solution(n, stations, w);

        System.out.println(result);
    }
}
