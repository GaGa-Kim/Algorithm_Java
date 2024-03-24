package Programmers.LV_3;

/**
 * 60059) 자물쇠와_열쇠
 */
public class L033_60059 {
    // key(열쇠를 나타내는 2차원 배열)
    // lock(자물쇠를 나타내는 2차원 배열)
    public boolean solution(int[][] key, int[][] lock) {
        // newLock(회전과 이동을 위해 자물쇠의 크기를 기존의 3배로 변환한 2차원 배열)
        int[][] newLock = new int[lock.length * 3][lock.length * 3];
        // 새로운 자물쇠의 중앙 부분에 기존의 자물쇠 넣기
        for (int i = 0; i < lock.length; i++) {
            for (int j = 0; j < lock.length; j++) {
                newLock[i + lock.length][j + lock.length] = lock[i][j];
            }
        }
        // 4가지 방향(0, 90, 180, 270도)에 대해서 확인
        for (int rotation = 0; rotation < 4; rotation++) {
            // key(회전한 열쇠)
            key = rotate(key);
            // 회전한 열쇠를 (1, 1)부터 (lock의 길이 * 2, lock의 길이 * 2)까지 이동시키며 확인
            for (int x = 0; x < lock.length * 2; x++) {
                for (int y = 0; y < lock.length * 2; y++) {
                    for (int i = 0; i < key.length; i++) {
                        for (int j = 0; j < key.length; j++) {
                            // 자물쇠에 열쇠를 끼워 넣기
                            newLock[x + i][y + j] += key[i][j];
                        }
                    }
                    // 새로운 자물쇠에 열쇠가 정확히 들어맞는지 검사
                    if (check(newLock)) {
                        // 열 수 있다면 true 반환
                        return true;
                    }
                    // 자물쇠에서 열쇠를 다시 빼기
                    for (int i = 0; i < key.length; i++) {
                        for (int j = 0; j < key.length; j++) {
                            newLock[x + i][y + j] -= key[i][j];
                        }
                    }
                }
            }
        }
        // 열 수 없다면 false 반환
        return false;
    }

    // 회전하기 함수
    private int[][] rotate(int[][] key) {
        // result(회전 결과 배열)
        int[][] result = new int[key[0].length][key.length];
        // 90도씩 회전
        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < key[0].length; j++) {
                result[j][key.length - i - 1] = key[i][j];
            }
        }
        // result 반환
        return result;
    }

    // 열쇠로 자물쇠를 열 수 있는지 확인 함수
    private boolean check(int[][] newLock) {
        // origLength(자물쇠의 크기를 3배로 변환하기 전의 길이)
        int origLength = newLock.length / 3;
        // 자물쇠의 중간 부분이 모두 1인지 확인
        for (int i = origLength; i < origLength * 2; i++) {
            for (int j = origLength; j < origLength * 2; j++) {
                // 하나라도 1이 아니라면 false 반환
                if (newLock[i][j] != 1) {
                    return false;
                }
            }
        }
        // 모두 1이라면 true 반환
        return true;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L033_60059 solution = new L033_60059();

        int[][] key = { { 0, 0, 0 }, { 1, 0, 0 }, { 0, 1, 1 } };
        int[][] lock = { { 1, 1, 1 }, { 1, 1, 0 }, { 1, 0, 1 } };

        boolean result = solution.solution(key, lock);

        System.out.println(result);
    }
}
