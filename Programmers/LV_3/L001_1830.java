package Programmers.LV_3;

import java.util.ArrayList;
import java.util.List;

/**
 * 1830) 브라이언의_고민
 */
public class L001_1830 {
    // sentence(주어진 광고 문구)
    public String solution(String sentence) {
        // answer(광고 문구의 규칙 적용 전 원래 문구)
        StringBuilder answer = new StringBuilder();
        // alphabetCheck(소문자 알파벳을 특수문자로 사용 유무 배열)
        boolean[] alphabetCheck = new boolean[26];
        // firstRule(규칙 1 적용 여부)
        boolean firstRule = false;
        // secondRule(규칙 2 적용 여부)
        boolean secondRule = false;
        // charRule1(규칙 1일 때 특수문자로 쓰이는 소문자)
        Character charRule1 = null;
        // charRule2(규칙 2일 때 특수문자로 쓰이는 소문자)
        Character charRule2 = null;
        // words(규칙이 적용되기 전의 단어들 리스트)
        List<String> words = new ArrayList<>();
        // word(규칙이 적용되기 전의 현재 단어)
        String word = "";
        // 문장을 한 글자씩 탐색
        for (int i = 0; i < sentence.length(); ++i) {
            // 모든 규칙(규칙 1과 규칙 2)이 적용된 경우
            if (firstRule && secondRule) {
                // 현재 문자가 대문자라면
                if (Character.isUpperCase(sentence.charAt(i))) {
                    // word에 대문자 추가
                    word += sentence.charAt(i);
                    // 규칙 1+2 단어는 시작했을 때의 그 소문자로 끝나야 하므로 현재 문자가 대문자이며 마지막 문자라면
                    // 예) aPbP
                    if (i + 1 == sentence.length()) {
                        // 규칙에 어긋나므로 invalid 반환
                        return "invalid";
                    }
                    // 현재 문자가 대문자이며 다음 문자가 대문자라면
                    // 예) aPbPP
                    if (Character.isUpperCase(sentence.charAt(i + 1))) {
                        // 규칙에 어긋나므로 invalid 반환
                        return "invalid";
                    }
                    // 현재 문자가 대문자이며 다음 문자가 소문자인데 현재 규칙의 특수문자인 소문자가 아니라면
                    // 예) aPbPc
                    if (charRule1 != null && charRule1 != sentence.charAt(i + 1)
                            && charRule2 != sentence.charAt(i + 1)) {
                        // 규칙에 어긋나므로 invalid 반환
                        return "invalid";
                    }
                }
                // 현재 문자가 소문자라면
                if (Character.isLowerCase(sentence.charAt(i))) {
                    // 현재 문자가 charRule2라면
                    if (charRule2 == sentence.charAt(i)) {
                        // 현재 문자를 담은 단어에 대해 모든 규칙 탐색을 마쳤으므로
                        // 규칙 1과 규칙 2를 해지시키고 특수문자로 사용한 소문자 사용 유무 갱신
                        firstRule = false;
                        secondRule = false;
                        alphabetCheck[charRule1 - 'a'] = true;
                        alphabetCheck[charRule2 - 'a'] = true;
                        charRule1 = null;
                        charRule2 = null;
                        // words에 word 추가 후, 초기화
                        words.add(word);
                        word = "";
                        continue;
                    }
                    // 규칙 1+2 단어는 시작했을 때의 그 소문자로 끝나야 하므로 현재 문자가 소문자이며 마지막 문자라면
                    // 예) aPbPb
                    if (i + 1 == sentence.length()) {
                        // 규칙에 어긋나므로 invalid 반환
                        return "invalid";
                    }
                    // 현재 문자가 소문자이며 다음 문자가 소문자일 때
                    // 예) aPbPbb
                    if (Character.isLowerCase(sentence.charAt(i + 1))) {
                        // 규칙에 어긋나므로 invalid 반환
                        return "invalid";
                    }
                }
            }
            // 규칙 1이 적용된 경우
            else if (firstRule) {
                // 현재 문자가 대문자라면
                if (Character.isUpperCase(sentence.charAt(i))) {
                    // word에 대문자 추가
                    word += sentence.charAt(i);
                    // 규칙 1의 경우 대문자로 문자가 끝나야 하므로 현재 문자가 마지막 문자라면
                    // 예) PaP
                    if (i + 1 == sentence.length()) {
                        // 현재 문자를 담은 단어에 대해 규칙 탐색을 마쳤으므로
                        // 규칙 1을 해지시키고 특수문자로 사용한 소문자를 사용 유무 갱신
                        firstRule = false;
                        alphabetCheck[charRule1 - 'a'] = true;
                        charRule1 = null;
                        // words에 word 추가 후, 초기화
                        words.add(word);
                        word = "";
                    }
                    // 규칙 1의 경우 현재 문자가 대문자이며 다음 문자가 대문자라면
                    // 예) PaPP
                    else if (Character.isUpperCase(sentence.charAt(i + 1))) {
                        // 현재 문자를 담은 단어에 대해 규칙 탐색을 마쳤으므로
                        // 규칙 1을 해지시키고 특수문자로 사용한 소문자를 사용 유무 갱신
                        firstRule = false;
                        alphabetCheck[charRule1 - 'a'] = true;
                        charRule1 = null;
                        // words에 word 추가 후, 초기화
                        words.add(word);
                        word = "";
                    }
                    // 규칙 1의 경우 현재 문자가 대문자이며 다음 문자가 소문자인데 현재 규칙의 특수문자인 소문자가 아니라면
                    // 예) PaPb
                    else if (charRule1 != sentence.charAt(i + 1)) {
                        // 현재 문자를 담은 단어에 대해 규칙 탐색을 마쳤으므로
                        // 규칙 1을 해지시키고 특수문자로 사용한 소문자를 사용 유무 갱신
                        firstRule = false;
                        alphabetCheck[charRule1 - 'a'] = true;
                        charRule1 = null;
                        // words에 word 추가 후, 초기화
                        words.add(word);
                        word = "";
                    }
                }
                // 현재 문자가 소문자라면
                if (Character.isLowerCase(sentence.charAt(i))) {
                    // 규칙 1의 경우 대문자로 문자가 끝나야 하므로 현재 문자가 마지막 문자라면
                    // 예) Pa
                    if (i + 1 == sentence.length()) {
                        // 규칙에 어긋나므로 invalid 반환
                        return "invalid";
                    }
                    // 규칙 1의 경우 현재 문자가 소문자이며 다음 문자가 소문자라면
                    // 예) Pab
                    else if (Character.isLowerCase(sentence.charAt(i + 1))) {
                        // 규칙에 어긋나므로 invalid 반환
                        return "invalid";
                    }
                }
            }
            // 규칙 2가 적용된 경우
            else if (secondRule) {
                // 현재 문자가 대문자라면
                if (Character.isUpperCase(sentence.charAt(i))) {
                    // word에 대문자 추가
                    word += sentence.charAt(i);
                    // 규칙 2의 경우 소문자로 문자가 끝나야 하므로 현재 문자가 마지막 문자라면
                    // 예) aP
                    if (i + 1 == sentence.length()) {
                        // 규칙에 어긋나므로 invalid 반환
                        return "invalid";
                    }
                    // 규칙 2의 경우 현재 문자가 대문자이며 다음 문자가 소문자인데 현재 규칙의 특수문자인 소문자가 아니라면
                    // 예) aPbPbPa
                    else if ((Character.isLowerCase(sentence.charAt(i + 1)) && charRule2 != sentence.charAt(i + 1))) {
                        // 이전 문자가 규칙 2 단어의 특수문자라면 규칙 1과 규칙 2가 모두 적용되기 시작된 것
                        // 예) aPbPbPa
                        if (charRule2 == sentence.charAt(i - 1)) {
                            // 다음 문자가 이미 사용된 특수문자 소문자라면
                            if (alphabetCheck[sentence.charAt(i + 1) - 'a'])
                                // 규칙에 어긋나므로 invalid 반환
                                return "invalid";
                            // 규칙 1을 적용하고 charRule1을 갱신
                            firstRule = true;
                            charRule1 = sentence.charAt(i + 1);
                        } else {
                            return "invalid";
                        }
                    }
                }
                // 현재 문자가 소문자라면
                if (Character.isLowerCase(sentence.charAt(i))) {
                    // 현재 문자를 담은 단어에 대해 규칙 탐색을 마쳤으므로
                    // 규칙 2를 해지시키고 특수문자로 사용한 소문자 사용 유무 갱신
                    secondRule = false;
                    alphabetCheck[charRule2 - 'a'] = true;
                    charRule2 = null;
                    // words에 word 추가 후, 초기화
                    words.add(word);
                    word = "";
                }
            }
            // 새로운 단어가 시작되어 아무런 규칙도 적용되지 않은 경우, 문자에 따라 새로운 규칙을 적용
            else {
                // 현재 문자가 대문자라면 규칙 1에 해당하는 단어가 시작되는 것
                if (Character.isUpperCase(sentence.charAt(i))) {
                    // word에 대문자 추가
                    word += sentence.charAt(i);
                    // 규칙 1을 적용
                    firstRule = true;
                    // 규칙 1의 경우 대문자로 문자가 끝나야 하므로, 현재 문자가 대문자이며 마지막 문자라면
                    // 예) O
                    if (i + 1 == sentence.length()) {
                        // 현재 문자까지가 하나의 단어 끝이므로, 규칙 1 탐색을 마치기 위해 규칙 1을 해지
                        firstRule = false;
                        // words에 word 추가 후, 초기화
                        words.add(word);
                        word = "";
                    }
                    // 규칙 1의 경우 대문자로 문자가 끝나야 하므로, 규칙 1의 경우 현재 문자가 대문자이며 다음 문자도 대문자라면
                    // 예) OA
                    else if (Character.isUpperCase(sentence.charAt(i + 1))) {
                        // 현재 문자까지가 하나의 단어 끝이므로, 규칙 1 탐색을 마치기 위해 규칙 1을 해지
                        firstRule = false;
                        // words에 word 추가 후, 초기화
                        words.add(word);
                        word = "";
                    }
                    // 규칙 1의 경우 현재 문자가 대문자이며 다음 문자가 소문자라면
                    else if (Character.isLowerCase(sentence.charAt(i + 1))) {
                        // 다음 문자가 이미 사용된 특수문자 소문자라면
                        if (alphabetCheck[sentence.charAt(i + 1) - 'a']) {
                            // 규칙에 어긋나므로 invalid 반환
                            return "invalid";
                        }
                        // 다음 문자가 사용된 적 없는 특수문자 소문자라면
                        // charRule1을 갱신
                        charRule1 = sentence.charAt(i + 1);
                        // position(중복된 문자 위치 찾기)
                        List<Integer> position = new ArrayList<>();
                        // 특수문자 소문자인 다음 문자와 동일한 소문자들의 위치를 모두 position에 저장
                        for (int j = i + 1; j < sentence.length(); ++j) {
                            if (sentence.charAt(j) == charRule1) {
                                position.add(j);
                            }
                        }
                        // 중복된 문자 위치가 1개라면 다음 문자인 소문자가 charRule1이 되어야하므로
                        // 예) OaO
                        if (position.size() == 1) {
                            // 넘어감
                            continue;
                        }
                        // 중복된 문자 위치가 3개 이상이라면 두 대문자 사이사이에 인덱스 2 차이로 있어야 하므로
                        // 예) OaOaOaO
                        else if (position.size() >= 3) {
                            boolean flag = true;
                            for (int j = 1; j < position.size(); ++j) {
                                // 소문자의 간격이 2 차이가 아니라면
                                if (position.get(j) - position.get(j - 1) != 2) {
                                    // 모든 글자 사이에 규칙 1이 적용된 것이 아니므로 flag 갱신
                                    flag = false;
                                    break;
                                }
                            }
                            if (flag) {
                                continue;
                            }
                        }
                        // 중복된 문자 위치가 2개라면
                        // 예) OaOa
                        else {
                            // 현재 문자까지가 하나의 단어 끝이므로, 규칙 1 탐색을 마치기 위해 규칙 1을 해지
                            firstRule = false;
                            charRule1 = null;
                            // words에 word 추가 후, 초기화
                            words.add(word);
                            word = "";
                        }
                    }
                }
                // 현재 문자가 소문자라면 규칙 2에 해당하는 단어가 시작되는 것
                if (Character.isLowerCase(sentence.charAt(i))) {
                    // 다음 문자가 이미 사용된 특수문자 소문자라면
                    if (alphabetCheck[sentence.charAt(i) - 'a']) {
                        // 규칙에 어긋나므로 invalid 반환
                        return "invalid";
                    }
                    // 규칙 2의 경우 소문자로 시작한 후 대문자가 나와야 하므로 현재 문자가 마지막 문자라면
                    // 예) a
                    if (i + 1 == sentence.length()) {
                        // 규칙에 어긋나므로 invalid 반환
                        return "invalid";
                    }
                    // 규칙 2의 경우 소문자로 시작한 후 대문자가 나와야 하므로 다음 문자가 소문자라면
                    // 예) ab
                    if (Character.isLowerCase(sentence.charAt(i + 1))) {
                        // 규칙에 어긋나므로 invalid 반환
                        return "invalid";
                    }
                    // 규칙 2를 적용
                    secondRule = true;
                    // 현재 문자를 규칙 2 단어의 첫 특수문자인 charRule2로 갱신
                    charRule2 = sentence.charAt(i);
                }
            }
        }
        // 단어들을 answer에 추가
        for (String w : words) {
            answer.append(w).append(" ");
        }
        // 마지막 공백 제거 후 answer 반환
        answer.deleteCharAt(answer.length() - 1);
        return answer.toString();
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L001_1830 solution = new L001_1830();

        String sentence = "HaEaLaLaObWORLDb";

        String result = solution.solution(sentence);

        System.out.println(result);
    }
}
