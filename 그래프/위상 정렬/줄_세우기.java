import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 2252) 줄_세우기
 */
public class 줄_세우기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // N(학생 수)
        int N = sc.nextInt();
        // M(비교 횟수)
        int M = sc.nextInt();
        // A(데이터 저장 인접 리스트)
        ArrayList<ArrayList<Integer>> A = new ArrayList<>();
        // 학생 수만큼 인접 리스트 초기화하기
        for (int i = 0; i <= N; i++) {
            A.add(new ArrayList<>());
        }
        // 진입 차수 배열 초기화하기
        int[] indegree = new int[N + 1];
        for (int i = 0; i < M; i++) {
            // 인접 리스트 데이터 저장하기
            int S = sc.nextInt();
            int E = sc.nextInt();
            A.get(S).add(E);
            // 진입 차수 배열 초기 데이터 저장하기
            indegree[E]++;
        }
        // 큐 생성하기
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            // 진입 차수 배열의 값이 0인 학생(노드)을 큐에 삽입하기
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            // 현재 노드 = 큐에서 데이터 poll
            int now = queue.poll();
            // 현재 노드값 출력하기
            System.out.println(now + " ");
            // 현재 노드에서 갈 수 있는 노드의 개수
            for (int next : A.get(now)) {
                // 타깃 노드 진입 차수 배열 --
                indegree[next]--;
                // 타깃 노드의 진입 차수가 0이면
                if (indegree[next] == 0) {
                    // 큐에 타깃 노드 추가하기
                    queue.offer(next);
                }
            }
        }
    }
}