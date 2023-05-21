/**
 * @author :Jiayi Chen
 * @version :
 * @description :
 * @date : 2023-05-04 16:48
 */
package method;

public class Others {
    public static String backward( String s, int t ) {
        int len = s.length();
        t = Math.max(t, len);
        String ret = s.substring(0, t - 1);
        return ret;
    }
}
