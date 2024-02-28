package Programmers.LV_3;

import java.util.Arrays;

/**
 * 1833) 캠핑
 */
public class L003_1833 {
    // n(쐐기의 개수)
    // data(캠핑장에 설치된 쐐기의 x좌표와 y좌표)
    public int solution(int n, int[][] data) {
        // answer(가능한 텐트의 쐐기의 쌍의 개수)
        int answer = 0;
        // x축을 기준으로 정렬한 후, 같을 경우 y축을 기준으로 커스텀 정렬
        Arrays.sort(data, (o1, o2) -> {
            if (o1[0] == o2[0])
                return o1[1] - o2[1];
            return o1[0] - o2[0];
        });
        // 쐐기 조합을 살펴봄
        for (int i = 0; i < data.length; i++) {
            for (int j = i + 1; j < data.length; j++) {
                // data[i][0]이 data[j][0]과 같거나 data[i][1]이 data[j][1]과 같다면
                if (data[i][0] == data[j][0] || data[i][1] == data[j][1])
                    // x 좌표 또는 y 좌표가 같아 넓이가 0이 되므로 continue
                    continue;
                // another(다른 쐐기 유무)
                boolean another = false;
                for (int k = i + 1; k < data.length; k++) {
                    // 텐트 안에 다른 쐐기가 있다면
                    if ((data[i][0] < data[k][0] && data[k][0] < data[j][0]) &&
                            Math.min(data[i][1], data[j][1]) < data[k][1] &&
                            Math.max(data[i][1], data[j][1]) > data[k][1]) {
                        // another을 true로 갱신한 후, break
                        another = true;
                        break;
                    }
                }
                // another이 false라면
                if (!another)
                    // 다른 쐐기가 없으므로 answer 증가
                    answer++;
            }
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L003_1833 solution = new L003_1833();

        int n = 4;
        int[][] data = { { 0, 0 }, { 1, 1 }, { 0, 2 }, { 2, 0 } };

        int result = solution.solution(n, data);

        System.out.println(result);
    }
}
