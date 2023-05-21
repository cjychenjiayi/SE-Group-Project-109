/**
 * @author :Jiayi Chen
 * @version :
 * @description :
 * @date : 2023-04-30 13:42
 */
package method;

import java.io.*;
import java.io.IOException;

public class IO {
    public static String read(String filepath) throws IOException {
        String ret = "";
        try {
            FileReader fileReader = new FileReader(filepath);
            Reader reader = new InputStreamReader(new FileInputStream(filepath), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            ret = sb.toString();
            return ret;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void write(String filePath, String str) throws IOException {
        File file = new File( filePath);
        PrintStream ps = new PrintStream(new FileOutputStream(file));
        ps.println(str);
        ps.close();
    }
}
