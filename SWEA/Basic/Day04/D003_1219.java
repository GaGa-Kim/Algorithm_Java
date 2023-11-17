package SWEA.Basic.Day04;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 1219) 길찾기
 */
public class D003_1219 {
    // visited(방문 유무 저장 배열)
    static boolean[] visited;
    // arr(그래프 데이터 저장 인접 리스트)
    static ArrayList<Integer> arr[];

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        // T(테스트 케이스 수) = 10
        int T = 10;
        // T만큼
        for (int test_case = 1; test_case <= T; test_case++) {
            // t(테스트 케이스의 번호)
            int t = sc.nextInt();
            // n(길의 총 개수)
            int n = sc.nextInt();
            // visited 배열 초기화하기
            visited = new boolean[100];
            // arr 인접 리스트의 각 ArrayList 초기화하기
            arr = new ArrayList[100];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = new ArrayList<>();
            }
            // arr 인접 리스트에 그래프 데이터 저장하기
            for (int i = 0; i < n; i++) {
                int n1 = sc.nextInt();
                int n2 = sc.nextInt();
                arr[n1].add(n2);
            }
            // DFS(0) 수행하기 (A 도시부터 출발)
            DFS(0);
            // answer(A도시에서 B도시로 가는 길 존재 유무) = 0
            int answer = 0;
            // visited[99]가 true라면 (B 도시에 도착했다면)
            if (visited[99] == true) {
                // answer 갱신
                answer = 1;
            }
            // #T와 answer 반환
            System.out.println("#" + t + " " + answer);
        }
    }

    // DFS
    private static void DFS(int node) {
        // visited 배열에 현재 노드 방문 기록하기
        visited[node] = true;
        // 현재 노드의 연결 노드 중 방문하지 않은 노드
        for (int i : arr[node]) {
            if (!visited[i]) {
                // DFS 실행하기(재귀 함수 형태)
                DFS(i);
            }
        }
    }
}
