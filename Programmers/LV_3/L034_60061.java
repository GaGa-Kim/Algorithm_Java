package Programmers.LV_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 60061) 기둥과_보_설치
 */
public class L034_60061 {
    // n(벽면의 크기)
    // build_frame(기둥과 보를 설치하거나 삭제하는 작업이 순서대로 담긴 2차원 배열)
    public int[][] solution(int n, int[][] build_frame) {
        // maps(구조물을 담은 리스트)
        List<Frame> maps = new ArrayList<>();
        for (int[] frame : build_frame) {
            // x, y(기둥, 보를 설치 또는 삭제할 교차점의 좌표)
            int x = frame[0];
            int y = frame[1];
            // a(설치 또는 삭제할 구조물의 종류)
            int a = frame[2];
            // b(구조물을 설치할 지, 혹은 삭제할 지)
            int b = frame[3];
            // 구조물을 삭제하는 경우
            if (b == 0) {
                // 일단 구조물을 삭제하고
                maps.remove(new Frame(x, y, a));
                // 만약 규칙에 맞지 않는다면
                if (!isDelete(maps)) {
                    // 다시 구조물을 설치하여 연산을 무시
                    maps.add(new Frame(x, y, a));
                }
            }
            // 구조물을 설치하는 경우
            if (b == 1) {
                // 규칙에 맞는 경우 기둥 또는 보 생성
                if ((a == 0 && canBuildColumn(maps, x, y)) || (a == 1 && canBuildBeam(maps, x, y))) {
                    maps.add(new Frame(x, y, a));
                }
                // 만약 규칙에 맞지 않는다면 연산을 무시
            }
        }
        // answer(모든 명령어를 수행한 후 구조물의 상태)
        int[][] answer = new int[maps.size()][3];
        // answer에 구조물 지도 저장
        for (int i = 0; i < maps.size(); i++) {
            Frame frame = maps.get(i);
            answer[i][0] = frame.x;
            answer[i][1] = frame.y;
            answer[i][2] = frame.a;
        }
        // answer을 정렬해 반환
        Arrays.sort(answer, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                if (o1[1] == o2[1]) {
                    // x, y좌표가 모두 같은 경우 기둥이 보보다 앞에 오도록 정렬
                    return o1[2] - o2[2];
                }
                // x좌표가 같을 경우 y좌표 기준으로 오름차순 정렬
                return o1[1] - o2[1];
            }
            // x좌표 기준으로 오름차순 정렬
            return o1[0] - o2[0];
        });
        return answer;
    }

    // 삭제된 구조물에 따라 모든 규칙에 맞는지 확인하는 함수
    private boolean isDelete(List<Frame> maps) {
        // 모든 구조물의 규칙을 확인
        for (Frame map : maps) {
            // 구조물이 기둥인 경우
            if (map.a == 0 && !canBuildColumn(maps, map.x, map.y)) {
                return false;
            }
            // 구조물이 보인 경우
            else if (map.a == 1 && !canBuildBeam(maps, map.x, map.y)) {
                return false;
            }
        }
        // 규칙에 맞으므로 true 반환
        return true;
    }

    // 설치된/설치될 기둥이 규칙에 맞는지 확인하는 함수
    private boolean canBuildColumn(List<Frame> maps, int x, int y) {
        // 바닥 위에 있거나 보의 한쪽 끝 부분 위에 있거나, 또는 다른 기둥 위에 있는 경우 중 하나라면 정상
        if (y == 0
                || maps.contains(new Frame(x - 1, y, 1))
                || maps.contains(new Frame(x, y, 1))
                || maps.contains(new Frame(x, y - 1, 0))) {
            // 규칙에 맞으므로 true 반환
            return true;
        }
        // 모두 아니라면 규칙에 맞지 않으므로 false 반환
        return false;
    }

    // 설치된/설치될 보가 규칙에 맞는지 확인하는 함수
    private boolean canBuildBeam(List<Frame> maps, int x, int y) {
        // 한쪽 끝 부분이 기둥 위에 있거나, 또는 양쪽 끝 부분이 다른 보와 동시에 연결 되어 있는 경우 중 하나라면 정상
        if (maps.contains(new Frame(x, y - 1, 0))
                || maps.contains(new Frame(x + 1, y - 1, 0))
                || (maps.contains(new Frame(x - 1, y, 1)) && maps.contains(new Frame(x + 1, y, 1)))) {
            // 규칙에 맞으므로 true 반환
            return true;
        }
        // 모두 아니라면 규칙에 맞지 않으므로 false 반환
        return false;
    }

    class Frame {
        int x; // x, y(기둥, 보를 설치 또는 삭제할 교차점의 좌표)
        int y;
        int a; // a(설치 또는 삭제할 구조물의 종류)

        Frame(int x, int y, int a) {
            this.x = x;
            this.y = y;
            this.a = a;
        }

        // equals가 x, y, a에 따른 동등 비교를 하도록 재정의
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return false;
            }
            if (!(obj instanceof Frame)) {
                return false;
            }
            // 구조물의 x, y, a 값이 같으면 같은 객체
            Frame other = (Frame) obj;
            return x == other.x && y == other.y && a == other.a;
        }
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L034_60061 solution = new L034_60061();

        int n = 5;
        int[][] build_frame = { { 1, 0, 0, 1 }, { 1, 1, 1, 1 }, { 2, 1, 0, 1 }, { 2, 2, 1, 1 }, { 5, 0, 0, 1 },
                { 5, 1, 0, 1 }, { 4, 2, 1, 1 }, { 3, 2, 1, 1 } };

        int[][] result = solution.solution(n, build_frame);

        for (int[] res : result) {
            System.out.println(Arrays.toString(res));
        }
    }
}