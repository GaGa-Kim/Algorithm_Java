package Do_it;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 2251) 물통
 */
public class D049_2251 {
    // Sender, Receiver(6가지 경우를 탐색하기 위한 선언 배열)
    // A → B, A → C, B → A, B → C, C → A, C → B
    static int[] Sender = { 0, 0, 1, 1, 2, 2 };
    static int[] Receiver = { 1, 2, 0, 2, 0, 1 };
    // visited(방문 기록 저장 배열)
    static boolean visited[][];
    // answer(정답 배열)
    static boolean answer[];
    // now(A, B, C의 값을 저장하는 배열)
    static int now[];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // now 배열 저장하기
        now = new int[3];
        now[0] = sc.nextInt();
        now[1] = sc.nextInt();
        now[2] = sc.nextInt();
        // visited, answer 초기화 작업하기
        visited = new boolean[201][201];
        answer = new boolean[201];
        // BFS 수행하기
        BFS();
        // answer 배열에서 값이 true인 index를 정답으로 출력하기
        for (int i = 0; i < answer.length; i++) {
            if (answer[i])
                System.out.print(i + " ");
        }
    }

    public static void BFS() {
        // 큐 자료구조에 출발 노드 더하기
        Queue<AB> queue = new LinkedList<>();
        // A와 B가 0인 상태이므로 0, 0 노드에서 시작하기
        queue.add(new AB(0, 0));
        // visited 배열에 현재 노드 방문 기록하기
        visited[0][0] = true;
        // answer 배열에 현재 C의 값 체크하기
        answer[now[2]] = true;
        while (!queue.isEmpty()) {
            // 큐에서 노드 데이터를 가져오기
            AB p = queue.poll();
            // 데이터를 이용해 A, B, C의 값 초기화하기
            int A = p.A;
            int B = p.B;
            // C는 전체 물의 양에서 A와 B를 뺀 것
            int C = now[2] - A - B;
            for (int k = 0; k < 6; k++) {
                int[] next = { A, B, C };
                // 받는 물통에 보내려는 물통의 값 더하기
                next[Receiver[k]] += next[Sender[k]];
                // 보내려는 물통 값을 0으로 업데이트하기
                next[Sender[k]] = 0;
                // 받는 물통이 넘칠 때
                if (next[Receiver[k]] > now[Receiver[k]]) {
                    // 넘치는 만큼 보내는 물통에 다시 넣어 주고
                    next[Sender[k]] = next[Receiver[k]] - now[Receiver[k]];
                    // 받는 물통은 이 물통의 최댓값으로 저장
                    next[Receiver[k]] = now[Receiver[k]];
                }
                // 현재 노드의 연결 노드 중 방문하지 않은 노드로
                if (!visited[next[0]][next[1]]) {
                    // visited 배열에 방문 기록하기
                    visited[next[0]][next[1]] = true;
                    // 큐에 데이터 삽입
                    queue.add(new AB(next[0], next[1]));
                    // 1번째 물통이 비어 있을 때
                    if (next[0] == 0) {
                        // 3번째 물통의 물의 양을 answer 배열에 기록하기
                        answer[next[2]] = true;
                    }
                }
            }
        }
    }
}

// AB 클래스 선언
// A와 B의 값만 지니고 있으면 C는 유추할 수 있으므로 두 변수만 사용
class AB {
    // A, B 물통 무게를 변수로 가짐
    int A;
    int B;

    public AB(int A, int B) {
        this.A = A;
        this.B = B;
    }
}
