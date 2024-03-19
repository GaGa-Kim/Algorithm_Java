package Programmers.LV_3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 42893) 매칭_점수
 */
public class L023_42893 {
    // word(검색어)
    // pages(HTML 형식의 웹페이지가 문자열 형태로 들어있는 배열)
    public int solution(String word, String[] pages) {
        // pageMap(한 웹 페이지에 대한 url과 웹 페이지 정보를 담은 HashMap)
        HashMap<String, Page> pageMap = new HashMap<>();
        // urlPattern(한 웹페이지의 url을 구하기 위한 정규표현식 패턴)
        Pattern urlPattern = Pattern.compile("(<meta property=\"og:url\" content=\"https://(\\S*)\")");
        // 대소문자 구분을 무시하기 위해 소문자로 변환
        word = word.toLowerCase();
        // wordPattern(검색어의 개수를 구하기 위한 정규표현식 패턴)
        Pattern wordPattern = Pattern.compile("(?<=[^a-zA-Z])(" + word + ")[^a-zA-Z]");
        // linkUrlPattern(한 웹페이지의 외부 링크를 구하기 위한 정규표현식 패턴)
        Pattern linkUrlPattern = Pattern.compile("<a href=\"(\\S*)//(\\S*)\"");
        Matcher matcher;
        for (int i = 0; i < pages.length; i++) {
            // 대소문자 구분을 무시하기 위해 소문자로 변환
            pages[i] = pages[i].toLowerCase();
            // url(현재 웹페이지의 url)
            String url = "";
            // basicScore(현재 웹 페이지의 기본점수)
            int basicScore = 0;
            // linkPages(현재 웹 페이지의 외부 링크 리스트)
            List<String> linkPages = new ArrayList<>();
            // 현재 웹 페이지의 url 찾기
            matcher = urlPattern.matcher(pages[i]);
            if (matcher.find()) {
                url = matcher.group(2).trim();
            }
            // 현재 웹 페이지의 검색어 개수 찾기
            matcher = wordPattern.matcher(pages[i]);
            while (matcher.find()) {
                basicScore++;
            }
            // 현재 웹 페이지의 외부 링크 찾기
            matcher = linkUrlPattern.matcher(pages[i]);
            while (matcher.find()) {
                linkPages.add(matcher.group(2).trim());
            }
            // pageMap에 현재 웹 페이지 정보를 담은 page 저장
            Page page = new Page(i, url, linkPages, basicScore);
            pageMap.put(url, page);
        }
        // 링크 점수 계산하기
        for (Page page : pageMap.values()) {
            for (String linkPage : page.linkPages) {
                if (pageMap.containsKey(linkPage)) {
                    // 해당 웹 페이지로 걸린 다른 웹 페이지의 기본 점수 ÷ 외부 링크 수의 총합
                    pageMap.get(linkPage).matchingScore += (double) page.basicScore / page.linkPages.size();
                }
            }
        }
        // answer(매칭점수가 가장 높은 웹페이지의 index)
        int answer = 0;
        // maxScore(최대 매칭점수)
        double maxScore = Double.MIN_VALUE;
        for (Page page : pageMap.values()) {
            // 최대 매칭점수가 같다면
            if (page.matchingScore == maxScore) {
                // index가 작은 것으로 갱신
                if (page.index < answer)
                    answer = page.index;
            }
            // 최대 매칭점수보다 크다면
            else if (page.matchingScore > maxScore) {
                // 최대 매칭점수와 answer 갱신
                answer = page.index;
                maxScore = page.matchingScore;
            }
        }
        // answer 반환
        return answer;
    }

    class Page {
        int index; // index(웹 페이지의 index)
        String url; // url(웹 페이지의 url)
        List<String> linkPages; // linkPages(웹 페이지의 외부 링크 리스트)
        int basicScore; // basicScore(기본점수)
        double matchingScore; // matchingScore(기본점수와 링크점수의 합인 매칭점수)

        public Page(int index, String url, List<String> linkPages, int basicScore) {
            this.index = index;
            this.url = url;
            this.linkPages = linkPages;
            this.basicScore = basicScore;
            this.matchingScore = basicScore;
        }
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L023_42893 solution = new L023_42893();

        String word = "blind";
        String[] pages = {
                "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>",
                "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>",
                "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>" };

        int result = solution.solution(word, pages);

        System.out.println(result);
    }
}
