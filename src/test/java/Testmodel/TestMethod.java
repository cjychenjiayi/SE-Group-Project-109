/**
 * @author :Jiayi Chen
 * @version :
 * @description :
 * @date : 2023-04-14 12:03
 */
package Testmodel;

import static method.SearchMethod.*;

public class TestMethod {
    public static void main(String[] args ) {
        String a = "ABBCCA", b = "CA";
        System.out.println(kmpSearch(a,b));
    }

}
