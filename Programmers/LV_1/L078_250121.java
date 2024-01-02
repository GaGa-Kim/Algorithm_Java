package Programmers.LV_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 250121) 데이터_분석
 */
public class L078_250121 {
    // arr
    // (data에서 ext 값이 val_ext보다 작은 데이터만 뽑은 후,
    // sort_by에 해당하는 값을 기준으로 오름차순으로 정렬한 리스트)
    static ArrayList<int[]> arr;

    // data(정렬한 데이터들이 담긴 이차원 정수 배열
    // [코드 번호(code), 제조일(date), 최대 수량(maximum), 현재 수량(remain)] 형태)
    // ext(어떤 정보를 기준으로 데이터를 뽑아낼지를 의미하는 문자열)
    // val_ext(뽑아낼 정보의 기준값을 나타내는 정수)
    // sort_by(정보를 정렬할 기준이 되는 문자열)
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        arr = new ArrayList<>();
        // ext_list(ext의 분류를 담은 문자열 배열)
        String[] ext_list = { "code", "date", "maximum", "remain" };
        for (int i = 0; i < ext_list.length; i++) {
            // ext가 ext_list[i]라면
            if (ext.equals(ext_list[i])) {
                // 뽑아내서 저장하기(data, i, val_ext)
                addFromDataToArr(data, i, val_ext);
            }
        }
        for (int i = 0; i < ext_list.length; i++) {
            // sort_by가 ext_list[i]라면
            if (sort_by.equals(ext_list[i])) {
                // arr에 대해 i를 기준으로 오름차순하도록 커스텀 정렬
                final int sort_index = i;
                arr.sort(Comparator.comparing(a -> a[sort_index]));
            }
        }
        // answer(정답 배열)
        int[][] answer = arr.toArray(new int[0][]);
        // arr을 answer로 변환하여 반환
        return answer;
    }

    // 뽑아내서 저장하기 함수
    private void addFromDataToArr(int[][] data, int index, int val_ext) {
        for (int[] d : data) {
            // d[index]가 val_ext보다 작다면
            if (d[index] < val_ext) {
                // arr에 d 저장
                arr.add(d);
            }
        }
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L078_250121 solution = new L078_250121();

        int[][] data = { { 1, 20300104, 100, 80 },
                { 2, 20300804, 847, 37 },
                { 3, 20300401, 10, 8 } };
        String ext = "date";
        int val_ext = 20300501;
        String sort_by = "remain";

        int[][] result = solution.solution(data, ext, val_ext, sort_by);

        System.out.println(Arrays.toString(result));
    }
}
