package Do_it;

import java.util.Scanner;

/**
 * 1717) 집합의_표현
 */
public class D50_1717 {
    public static int[] parent;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // N(원소 개수)
        int N = sc.nextInt();
        // M(질의 개수)
        int M = sc.nextInt();
        // parent(대표 노드 저장 배열)
        parent = new int[N + 1];
        // 대표 노드를 자기 자신으로 초기화하기
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < M; i++) {
            int question = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();
            // if(0이면) 집합 합치기 -> union 연산
            if (question == 0) {
                union(a, b);
            }
            // else와 같은 집합 원소인지 확인하고 결괏값 출력하기
            else {
                if (checkSame(a, b)) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }
        }
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

    // checkSame -> 두 원소가 같은 집합인지 확인
    public static boolean checkSame(int a, int b) {
        // a와 b의 대표 노드 찾기
        a = find(a);
        b = find(b);
        // 두 대표 노드가 같으면 true
        if (a == b) {
            return true;
        }
        // 아니면 false return
        else {
            return false;
        }
    }
}
