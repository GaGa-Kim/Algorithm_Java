package Programmers.Kit.BruteForce;

/**
 * 86491) 최소직사각형
 */
public class K001_86491 {
    // sizes(모든 명함의 가로 길이와 세로 길이)
    public int solution(int[][] sizes) {
        // width(최대 가로 길이)
        int width = 0;
        // height(최대 세로 길이)
        int height = 0;
        for (int i = 0; i < sizes.length; i++) {
            // 가로 길이와 세로 길이 중 더 긴 것을 가로 길이로 변경
            int w = Math.max(sizes[i][0], sizes[i][1]);
            // 가로 길이와 세로 길이 중 더 짧은 것을 세로 길이로 변경
            int h = Math.min(sizes[i][0], sizes[i][1]);
            // 최대 가로 길이와 현재 가로 길이를 비교하여 더 큰 것으로 갱신
            width = Math.max(width, w);
            // 최대 세로 길이와 현재 세로 길이를 비교하여 더 큰 것으로 갱신
            height = Math.max(height, h);
        }
        // widht * height 리턴
        return width * height;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        K001_86491 solution = new K001_86491();

        int[][] sizes = { { 60, 50 }, { 30, 70 }, { 60, 30 }, { 80, 40 } };

        int result = solution.solution(sizes);

        System.out.println(result);
    }
}
