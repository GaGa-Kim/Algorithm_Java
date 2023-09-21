package Do_it;

import java.util.Scanner;

/**
 * 1976) 여행_가자
 */
public class D051_1976 {
    static int[] parent;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // N(도시의 수)
        int N = sc.nextInt();
        // M(여행 계획에 속한 도시의 수)
        int M = sc.nextInt();
        // dosi(도시 연결 데이터 배열)
        int[][] dosi = new int[N + 1][N + 1];
        // dosi 데이터 저장하기
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                dosi[i][j] = sc.nextInt();
            }
        }
        // route(여행 계획 도시 저장 배열)
        int[] route = new int[M + 1];
        // route 데이터 저장하기
        for (int i = 1; i <= M; i++) {
            route[i] = sc.nextInt();
        }
        // parent(대표 노드 저장 배열)
        parent = new int[N + 1];
        // 대표 노드를 자기 자신으로 초기화하기
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                // 도시가 연결돼 있으면 union 연산하기
                if (dosi[i][j] == 1)
                    union(i, j);
            }
        }
        // route에 포함되는 노드들의 대표 노드가 모두 동일한지 확인한 후 결괏값 출력하기
        int index = find(route[1]);
        for (int i = 2; i < route.length; i++) {
            if (index != find(route[i])) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }

    // union 연산
    public static void union(int a, int b) {
        // a와 b의 대표 노드 찾기
        a = find(a);
        b = find(b);
        // 두 원소의 대표 노드끼리 연결하기
        if (a != b) {
            parent[b] = a;
        }
    }

    // find 연산
    public static int find(int a) {
        // a가 대표 노드면 리턴
        if (a == parent[a]) {
            return a;
        }
        // 아니면 a의 대표 노드값을 find(parent[a]) 값으로 저장 -> 재귀 함수 형태
        else {
            return parent[a] = find(parent[a]);
        }
    }
}
