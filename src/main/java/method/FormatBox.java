/**
 * @author :Jiayi Chen
 * @version :
 * @description :
 * @date : 2023-05-09 14:53
 */
package method;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatBox {
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
    public static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
    public static final String dateForm1Format = "yyyy-mm-dd";

    public static boolean isChar( char c ) {
        if( ( c >= 'a' && c <= 'z') || ( c >= 'A' && c <= 'Z') )
            return true;
        return false;
    }
    public static boolean isDigit( char c ) {
        return (c >= '0' && c <= '9') ? true : false;
    }

    public static boolean isString(String str){
        int len = str.length();
        for( int i = 0; i < len; i++ ) {
            char c = str.charAt(i);
            if( !isChar(c) && !isDigit(c) )
                return false;
        }
        return true;
    }

    public static boolean isPassword(String str){
       int len = str.length();
       return ( len == 6 && isString(str) );
    }

    public static boolean isPhone(String str) {
        int len = str.length();
        for( int i = 0; i < len; i++ )
            if( !isDigit(str.charAt(i)))
                return false;
        return (len==11);
    }

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    public static boolean isEmail(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidURL(String url) {
        try {
            new URL(url).toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isImage(String url) {
        String[] formats = new String[] {"jpg", "jpeg", "png"};
        for (String format : formats) {
            if (url.endsWith("." + format)) {
                return true;
            }
        }
        return false;
    }
}