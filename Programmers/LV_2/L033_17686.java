package Programmers.LV_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 17686) 파일명_정렬
 */
public class L033_17686 {
    class File implements Comparable<File> {
        // original(기존 파일명)
        String original;
        // head(숫자가 아닐 때까지의 파일명을 대문자로 변환)
        String head;
        // number(파일명의 숫자)
        int number;

        File(String original, String head, int number) {
            this.original = original;
            this.head = head.toUpperCase();
            this.number = number;
        }

        // head, number 순으로 커스텀 정렬
        @Override
        public int compareTo(File f) {
            if (this.head.equals(f.head)) {
                return Integer.compare(this.number, f.number);
            }
            return this.head.compareTo(f.head);
        }
    }

    // list(파일들을 저장할 리스트)
    static List<File> list;

    // files(파일명을 포함하는 문자열 배열)
    public String[] solution(String[] files) {
        // answer(정렬된 파일 배열)
        String[] answer = new String[files.length];
        list = new ArrayList<>();
        for (String file : files) {
            // s(숫자가 아닐 때까지의 파일명 StringBuilder)
            StringBuilder s = new StringBuilder();
            // n(파일명의 숫자 부분 StringBuilder)
            StringBuilder n = new StringBuilder();
            // index(글자 인덱스)
            int index = 0;
            // c(file의 index번째 글자)
            char c;
            // c가 숫자가 아닐 동안
            while (index < file.length() && !Character.isDigit(c = file.charAt(index))) {
                // s에 c 저장
                s.append(c);
                index++;
            }
            // c가 숫자일 동안
            while (index < file.length() && Character.isDigit(c = file.charAt(index))) {
                // n에 c 저장
                n.append(c);
                index++;
            }
            // list에 File(file, s, n) 저장
            list.add(new File(file, s.toString(), Integer.parseInt(n.toString())));
        }
        // list 정렬
        Collections.sort(list);
        // answer에 list의 File의 original 값을 담아 반환
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i).original;
        }
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L033_17686 solution = new L033_17686();

        String[] files = { "img100.p2ng", "img202.png123" };

        String[] result = solution.solution(files);

        System.out.println(Arrays.toString(result));
    }
}
