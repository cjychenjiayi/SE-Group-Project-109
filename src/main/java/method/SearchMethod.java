/**
 * @author :Jiayi Chen
 * @version :
 * @description :
 * @date : 2023-04-17 15:40
 */
package method;


import java.util.ArrayList;

public class SearchMethod {
    public static int[] getNext(String pattern) {
        int i = 0, j = -1, len = pattern.length();
        int[] next = new int[len];
        next[0] = -1;
        while (i < len - 1) {
            if (j == -1 || pattern.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
                next[i] = j;
            } else {
                j = next[j];
            }
        }
        return next;
    }

    public static boolean kmpSearch( String text, String pattern ) {
        pattern = pattern.toLowerCase();
        text = text.toLowerCase();
        int[] next = getNext(pattern);
        int i = 0, j = 0;
        int texting = text.length(), patterning = pattern.length();
        while ( i < texting && j < patterning ) {
            if (j == -1 || text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j == patterning) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean fuzzyMatch(String pattern, String text) {
        pattern = pattern.toLowerCase();
        text = text.toLowerCase();
        int j = 0, len = pattern.length();
        for (int i = 0; i < len; i++) {
            char c = pattern.charAt(i);
            if (c != '*') {
                j = text.indexOf(c, j);
                if (j == -1) {
                    return false;
                }
                j++;
            } else {
                while (i + 1 < len && pattern.charAt(i + 1) == '*') {
                    i++;
                }
                if (i == len - 1) {
                    return true;
                }
                int nextIndex = text.indexOf(pattern.charAt(i + 1), j);
                if (nextIndex == -1) {
                    return false;
                }
                j = nextIndex + 1;
            }
        }
        return j == len + 1;
    }
}

