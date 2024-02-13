package Programmers.LV_2;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 155651) 호텔_대실
 */
public class L096_155651 {
    // book_time(예약시간이 문자열 형태로 담긴 2차원 배열)
    public int solution(String[][] book_time) {
        // books(예약시간을 변환하고 청소 시간을 더한 2차원 배열)
        int[][] books = new int[book_time.length][2];
        // 대실 시작 시간과 종료 시각을 계산해 저장
        for (int i = 0; i < book_time.length; i++) {
            books[i][0] = calculateTime(book_time[i][0]);
            // 종료 시각에는 청소 시간인 10분 추가
            books[i][1] = calculateTime(book_time[i][1]) + 10;
        }
        // 대실 시작 시간을 기준으로 arr 정렬
        Arrays.sort(books, (b1, b2) -> {
            return b1[0] - b2[0];
        });
        // calculateRoom 함수 반환
        return calculateRoom(books);
    }

    // 시간 계산 함수
    private int calculateTime(String book) {
        // hour(시간)
        int hour = Integer.parseInt(book.split(":")[0]) * 60;
        // min(분)
        int min = Integer.parseInt(book.split(":")[1]);
        // hour + min 반환
        return hour + min;
    }

    // 객실 계산 함수
    private int calculateRoom(int[][] books) {
        // rooms(예약을 담을 우선순위 큐)
        PriorityQueue<Integer> rooms = new PriorityQueue<>();
        for (int[] book : books) {
            // rooms이 비어있다면
            if (rooms.isEmpty()) {
                // 객실 할당
                rooms.add(book[1]);
                continue;
            }
            // book[0]이 rooms.peek보다 크다면
            if (book[0] >= rooms.peek()) {
                // 퇴실 후에 입장할 수 있으므로 rooms.poll
                rooms.poll();
                // rooms에 다음 예약 퇴실 시간인 book[1] 저장
                rooms.add(book[1]);
            } else {
                // 퇴실 후에 입장할 수 없으므로 rooms에 다음 예약 퇴실 시간인 book[1] 저장
                rooms.add(book[1]);
            }
        }
        // rooms의 크기만큼 반환
        return rooms.size();
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L096_155651 solution = new L096_155651();

        String[][] book_time = {
                { "15:00", "17:00" },
                { "16:40", "18:20" },
                { "14:20", "15:20" },
                { "14:10", "19:20" },
                { "18:20", "21:20" } };

        int result = solution.solution(book_time);

        System.out.println(result);
    }
}
