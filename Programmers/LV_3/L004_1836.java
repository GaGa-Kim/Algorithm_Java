package Programmers.LV_3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 1836) 리틀_프렌즈_사천성
 */
public class L004_1836 {
    char[][] boards;
    List<Tile> tileList;

    // m, n(게임판의 크기)
    // board(배치된 타일의 정보)
    public String solution(int m, int n, String[] board) {
        // answer(타일을 제거하는 순서대로 한 글자씩 이루어진 문자열)
        String answer = "";
        // boards(배치된 타일의 정보를 가진 문자 배열)
        boards = new char[m][n];
        // tileList(알파벳 문자 타일의 종류와 그에 따른 위치를 저장한 Tile 리스트)
        tileList = new ArrayList<>();
        // 알파벳 문자 타일의 종류와 그에 따른 위치를 저장
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char ch = board[i].charAt(j);
                boards[i][j] = ch;
                // 알파벳 문자 타일이라면
                if (Character.isAlphabetic(ch)) {
                    tileList.add(new Tile(ch, i, j));
                }
            }
        }
        // 알파벳 순으로 가장 먼저인 문자열 타일을 먼저 탐색해야 하므로 오름차순 정렬
        Collections.sort(tileList);
        // 반복문을 통해 각 타일이 삭제 가능한지 확인
        for (int i = 0; i < tileList.size(); i += 2) {
            Tile t1 = tileList.get(i);
            Tile t2 = tileList.get(i + 1);
            // t1과 t2가 함께 삭제될 수 있다면
            if (canDelete(t1, t2)) {
                // answer에 t1과 t2의 알파벳인 ch 저장
                answer += t1.ch;
                // tileList에서 t1, t2 제거
                tileList.remove(t1);
                tileList.remove(t2);
                // t1, t2 위치를 빈칸인 .으로 갱신
                boards[t1.x][t1.y] = '.';
                boards[t2.x][t2.y] = '.';
                // 2개의 타일을 삭제했으므로 타일의 개수 감소
                i = -2;
            }
        }
        // 타일을 모두 제거했다면
        if (tileList.size() == 0)
            // answer 반환
            return answer;
        // 타일이 남아있다면 모든 타일을 제거할 수 없으므로 IMPOSSIBLE 반환
        return "IMPOSSIBLE";
    }

    // 각 타일이 삭제 가능한지 함수
    private boolean canDelete(Tile t1, Tile t2) {
        // 첫 번째 타일의 열이 두 번째 타일의 열보다 작은 경우 (t1이 좌하, t2가 우상에 위치한 경우)
        if (t1.y < t2.y) {
            // 첫 번째 타일과 두 번째 타일이 가로로 연속되어 있는지 확인하고, 세로로 연속되어 있는지 확인하여 둘 다 만족하면 삭제 가능
            // 오른쪽 이동 + 위쪽 이동으로 도달할 수 있는지 확인
            if (linearColumnCheck(t1.y, t2.y, t1.x, t1.ch) && linearRowCheck(t1.x, t2.x, t2.y, t1.ch)) {
                return true;
            }
            // 첫 번째 타일과 두 번째 타일이 세로로 연속되어 있는지 확인하고, 가로로 연속되어 있는지 확인하여 둘 다 만족하면 삭제 가능
            // 위쪽 이동 + 오른쪽 이동으로 도달할 수 있는지 확인
            if (linearRowCheck(t1.x, t2.x, t1.y, t1.ch) && linearColumnCheck(t1.y, t2.y, t2.x, t1.ch)) {
                return true;
            }
        }
        // 첫 번째 타일의 열이 두 번째 타일의 열보다 크거나 같은 경우 (t1가 좌상, t2가 우하에 위치한 경우)
        else {
            // 첫 번째 타일과 두 번째 타일이 세로로 연속되어 있는지 확인하고, 가로로 연속되어 있는지 확인하여 둘 다 만족하면 삭제 가능
            // 아래쪽 이동 + 오른쪽 이동으로 도달할 수 있는지 확인
            if (linearRowCheck(t1.x, t2.x, t2.y, t1.ch) && linearColumnCheck(t2.y, t1.y, t1.x, t1.ch)) {
                return true;
            }
            // 첫 번째 타일과 두 번째 타일이 가로로 연속되어 있는지 확인하고, 세로로 연속되어 있는지 확인하여 둘 다 만족하면 삭제 가능
            // 오른쪽 이동 + 아래쪽 이동으로 도달할 수 있는지 확인
            if (linearColumnCheck(t2.y, t1.y, t2.x, t1.ch) && linearRowCheck(t1.x, t2.x, t1.y, t1.ch)) {
                return true;
            }
        }
        // 위의 조건들을 만족하지 않으면 삭제 불가능
        return false;
    }

    // 주어진 열에서 타일이 연속되어 있는지 확인
    private boolean linearColumnCheck(int y1, int y2, int x, char ch) {
        for (int i = y1; i < y2 + 1; i++) {
            // 빈칸이 아니거나 ch가 아니라면
            if (boards[x][i] != '.' && boards[x][i] != ch)
                // 타일이 연속되지 않으므로 false 반환
                return false;
        }
        return true;
    }

    // 주어진 행에서 타일이 연속되어 있는지 확인
    private boolean linearRowCheck(int x1, int x2, int y, char ch) {
        for (int i = x1; i < x2 + 1; i++) {
            // 빈칸이 아니거나 ch가 아니라면
            if (boards[i][y] != '.' && boards[i][y] != ch)
                // 타일이 연속되지 않으므로 false 반환
                return false;
        }
        return true;
    }

    // Tile(알파벳 문자 타일의 종류와 그에 따른 위치를 저장)
    class Tile implements Comparable<Tile> {
        // ch(알파벳 문자 타일의 종류)
        char ch;
        // x, y(타일의 위치)
        int x;
        int y;

        Tile(char ch, int x, int y) {
            this.ch = ch;
            this.x = x;
            this.y = y;
        }

        // 알파벳 순으로 기준으로 커스텀 정렬
        @Override
        public int compareTo(Tile other) {
            return Character.compare(this.ch, other.ch);
        }
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L004_1836 solution = new L004_1836();

        int m = 4;
        int n = 4;
        String[] board = { ".ZI.", "M.**", "MZU.", ".IU." };

        String result = solution.solution(m, n, board);

        System.out.println(result);
    }
}
