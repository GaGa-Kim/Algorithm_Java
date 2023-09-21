package Do_it.Do_it_07;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 1043) 거짓말
 */
public class D052_1043 {
    static int[] parent;
    static int[] trueP;
    static ArrayList<Integer>[] party;
    static int result;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // N(사람 수)
        int N = sc.nextInt();
        // M(파티 개수)
        int M = sc.nextInt();
        // T(진실을 아는 사람)
        int T = sc.nextInt();
        result = 0;
        // trueP(진실을 아는 사람 데이터)
        trueP = new int[T];
        // 진실을 아는 사람 저장하기
        for (int i = 0; i < T; i++) {
            trueP[i] = sc.nextInt();
        }
        // party(파티 데이터)
        party = new ArrayList[M];
        // 파티 데이터 저장하기
        for (int i = 0; i < M; i++) {
            party[i] = new ArrayList<Integer>();
            int party_size = sc.nextInt();
            for (int j = 0; j < party_size; j++) {
                party[i].add(sc.nextInt());
            }
        }
        // parent(대표 노드 저장 배열)
        parent = new int[N + 1];
        // 대표 노드를 자기 자신으로 초기화하기
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }
        // 각 파티에 참여한 사람들을 1개의 그룹으로 만들기
        for (int i = 0; i < M; i++) {
            // firstPeople(i번째 파티의 1번째 사람)
            int firstPeople = party[i].get(0);
            for (int j = 1; j < party[i].size(); j++) {
                union(firstPeople, party[i].get(j));
            }
        }
        for (int i = 0; i < M; i++) {
            boolean isPossible = true;
            // firstPeople(i번째 파티의 사람)
            int firstPeople = party[i].get(0);
            for (int j = 0; j < trueP.length; j++) {
                // 각 파티의 대표 노드와 진실을 아는 사람들의 노드가 같다면 과장할 수 없음
                if (find(firstPeople) == find(trueP[j])) {
                    isPossible = false;
                    break;
                }
            }
            // 모두 다른 경우 결괏값 1 증가
            if (isPossible)
                result++;
        }
        System.out.println(result);
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
