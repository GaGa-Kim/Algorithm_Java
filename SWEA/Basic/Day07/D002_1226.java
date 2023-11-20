package SWEA.Basic.Day07;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 1226) 미로1
 */
public class D002_1226 {
    static int[][] maze;

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        // T(테스트 케이스 수) = 10
        int T = 10;
        // T만큼
        for (int test_case = 1; test_case <= T; test_case++) {
            // t(테스트 케이스 번호)
            int t = sc.nextInt();
            // maze(미로를 저장할 16 x 16 배열)
            maze = new int[16][16];
            // x, y(시작점)
            int x = 0, y = 0;
            for (int i = 0; i < maze.length; i++) {
                // s = 숫자 문자열
                String s = sc.next();
                for (int j = 0; j < maze.length; j++) {
                    // 숫자 저장
                    maze[i][j] = s.charAt(j) - '0';
                    // 시작점 저장
                    if (maze[i][j] == 2) {
                        x = i;
                        y = j;
                    }
                }
            }
            // answer(도달 가능 여부) = 미로 이동 함수
            int answer = move(x, y);
            // #T와 answer 반환
            System.out.println("#" + t + " " + answer);
        }

    }

    // 미로 이동 함수
    private static int move(int x, int y) {
        // dx, dy(상하좌우)
        int[] dx = { -1, 1, 0, 0 };
        int[] dy = { 0, 0, -1, 1 };
        // visited(방문 유무 배열)
        boolean[][] visited = new boolean[maze.length][maze.length];
        // 큐 자료구조에 출발 노드 더하기(add 연산)
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(x, y));
        // visited 배열에 현재 노드 방문 기록하기
        visited[x][y] = true;
        // 큐가 빌 때까지
        while (!queue.isEmpty()) {
            // 큐에서 노드 데이터를 가져오기(poll 연산)
            Node now = queue.poll();
            // 상하좌우 방향으로
            for (int i = 0; i < 4; i++) {
                // newX, newY(x와 y의 상하좌우 방향)
                int newX = now.x + dx[i];
                int newY = now.y + dy[i];
                // 미로를 벗어나지 않으면서
                if (newX > 0 && newX <= 16 && newY > 0 && newY <= 16) {
                    // 방문하지 않았으면서
                    if (visited[newX][newY] != true) {
                        // 0일 경우
                        if (maze[newX][newY] == 0) {
                            // 큐에 데이터 삽입(add 연산)
                            queue.add(new Node(newX, newY));
                            // 방문 기록
                            visited[newX][newY] = true;
                        }
                        // 3일 경우
                        else if (maze[newX][newY] == 3) {
                            // 도착했으므로 1 반환
                            return 1;
                        }
                    }
                }
            }
        }
        // 끝까지 도착하지 못하면 0 반환
        return 0;
    }
}

// 노드
class Node {
    // x, y 좌표
    int x;
    int y;

    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
