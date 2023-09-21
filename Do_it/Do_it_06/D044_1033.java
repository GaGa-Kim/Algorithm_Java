package Do_it.Do_it_06;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 1033) 칵테일
 */
public class D044_1033 {
    static ArrayList<cNode>[] A;
    static long lcm;
    static boolean visited[];
    static long D[];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // N(재료 개수)
        int N = sc.nextInt();
        // A(인접 리스트)
        A = new ArrayList[N];
        // visited(DFS를 탐색할 때 탐색 여부 저장 배열)
        visited = new boolean[N];
        // D(각 노드값 저장 배열)
        D = new long[N];
        // lcm(최소 공배수)
        lcm = 1;
        // A 인접 리스트의 각 ArrayList 초기화하기
        for (int i = 0; i < N; i++) {
            A[i] = new ArrayList<cNode>();
        }
        // 인접 리스트 배열에 엣지 정보 저장하기
        for (int i = 0; i < N - 1; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int p = sc.nextInt();
            int q = sc.nextInt();
            A[a].add(new cNode(b, p, q));
            A[b].add(new cNode(a, q, p));
            // 최소 공배수 업데이트하기
            lcm *= (p * q / gcd(p, q));
        }
        // 0번 노드에 최소 공배수 저장하기
        D[0] = lcm;
        // 0번에서 DFS 탐색 수행하기
        DFS(0);
        // DFS를 이용해 업데이트된 D 배열의 값들의 최대 공약수 계산하기
        long mgcd = D[0];
        for (int i = 0; i < N; i++) {
            mgcd = gcd(mgcd, D[i]);
        }
        // D 배열의 각 값들을 최대 공약수로 나눠 정답 출력하기
        for (int i = 0; i < N; i++) {
            System.out.print(D[i] / mgcd + " ");
        }
    }

    // 최대 공약수 함수 구현하기
    public static long gcd(long a, long b) {
        // if(b가 0이면)
        if (b == 0)
            // a가 최대 공약수
            return a;
        else
            // gcd(작은 수, 큰 수 % 작은 수)
            return gcd(b, a % b);
    }

    // 탐색 함수 구현하기
    public static void DFS(int Node) {
        // visited 배열에 현재 노드 방문 기록하기
        visited[Node] = true;
        // 현재 노드의 연결 노드 중 방문하지 않은 노드로
        for (cNode i : A[Node]) {
            int next = i.getB();
            if (!visited[next]) {
                // 다음 노드의 값 = 현재 노드의 값 * 비율로 저장하기
                D[next] = D[Node] * i.getQ() / i.getP();
                // DFS 실행하기(재귀 형태)
                DFS(next);
            }
        }
    }
}

// 노드 클래스 선언하기
class cNode {
    // 다음 노드
    int b;
    // 비율 1
    int p;
    // 비율 2
    int q;

    public cNode(int b, int p, int q) {
        super();
        this.b = b;
        this.p = p;
        this.q = q;
    }

    public int getB() {
        return b;
    }

    public int getP() {
        return p;
    }

    public int getQ() {
        return q;
    }
}
