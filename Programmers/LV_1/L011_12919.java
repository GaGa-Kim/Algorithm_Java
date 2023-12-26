package Programmers.LV_1;

/**
 * 12919) 서울에서_김서방_찾기
 */
public class L011_12919 {
    // seoul(String형 배열)
    public String solution(String[] seoul) {
        // index(Kim의 위치 인덱스)
        int index = 0;
        while (true) {
            // seoul[i]가 Kim이라면
            if (seoul[index].equals("Kim"))
                break;
            // index 증가
            index++;
        }
        // return "김서방은 index에 있다"
        return "김서방은 " + index + "에 있다";
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L011_12919 solution = new L011_12919();

        String[] seoul = { "Jane", "Kim" };

        String result = solution.solution(seoul);

        System.out.println(result);
    }
}
