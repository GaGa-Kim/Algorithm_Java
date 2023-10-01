package Do_it.Do_it_08;

import java.util.Scanner;

/**
 * 14425) 문자열_집합
 */
public class D069_14425 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // N(집합 S의 문자열 개수)
        int N = sc.nextInt();
        // M(검사할 문자열 개수)
        int M = sc.nextInt();
        tNode root = new tNode();
        while (N > 0) {
            // text(집합 S의 문자열)
            String text = sc.next();
            // 현재 노드를 루트 노드로 설정하기
            tNode now = root;
            // i를 text 문자열 길이만큼 반복하기
            for (int i = 0; i < text.length(); i++) {
                // c(i번째 문자)
                char c = text.charAt(i);
                // c 변수에 해당하는 다음 노드가 null
                if (now.next[c - 'a'] == null) {
                    // 신규 노드 생성하기
                    now.next[c - 'a'] = new tNode();
                }
                // 현재 노드를 c 변수 노드로 변경하기
                now = now.next[c - 'a'];
                // i가 문자열의 마지막이면
                if (i == text.length() - 1)
                    // isEnd 변수를 true로 설정하기
                    now.isEnd = true;
            }
            N--;
        }
        // count(정답 변수)
        int count = 0;
        while (M > 0) {
            // text(검색 문자열)
            String text = sc.next();
            // 현재 노드를 루트 노드로 설정하기
            tNode now = root;
            // i를 text 문자열 길이만큼 반복하기
            for (int i = 0; i < text.length(); i++) {
                // c(i번째 문자)
                char c = text.charAt(i);
                // (c 변수에 해당하는 다음 노드가 null
                if (now.next[c - 'a'] == null) {
                    // 이 문자열 검색 종료
                    break;
                }
                // 현재 노드를 c 변수 노드로 변경하기
                now = now.next[c - 'a'];
                // i가 문자열의 마지막이고, 현재 노드의 isEnd값이 true이면
                if (i == text.length() - 1 && now.isEnd)
                    // count값 올리기
                    count++;
            }
            M--;
        }
        // count 출력하기
        System.out.println(count);
    }
}

class tNode {
    // next(다음 노드 배열)
    // 알파벳 소문자로만 구성된 문자열을 저장하는 트라이의 각 노드는 각 노드가 26개짜리 포인터 배열을 가지고 있음
    // 알파벳 소문자 개수는 26개
    tNode[] next = new tNode[26];
    // isEnd(마지막 문자열 여부 표시하기)
    boolean isEnd;
}
