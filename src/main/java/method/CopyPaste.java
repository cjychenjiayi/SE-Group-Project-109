/**
 * @author :Jiayi Chen
 * @version :
 * @description :
 * @date : 2023-04-30 18:51
 */
package method;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static method.Others.backward;


public class CopyPaste {
    private static Path resource = Paths.get("src/main/resources/");
    public static String copyFile(String strSource) throws IOException {
        String strDest = currPath();
        Path sourcePath = Paths.get(strSource);
        String tot = strDest + File.separator + sourcePath.getFileName();
        String sorcePathFile = sourcePath.getFileName().toString();
        Path destinationPath = Paths.get(tot);
        int cnt = 0;
        do {
            cnt++;
            tot = strDest + File.separator + sorcePathFile;
            destinationPath = Paths.get(tot);
            File destinationFile = new File(destinationPath.toString());
            if (!destinationFile.exists())
                break;
            else
                sorcePathFile = Integer.toString(cnt) + "_" + sourcePath.getFileName().toString();

        }while (true);
        try {
            Files.copy(sourcePath, destinationPath);
        } catch (IOException e) {

            return "Error";
        }
        return "dataset/pictures/" + sorcePathFile;
    }

    private static String currPath() {
        File file = new File("src\\main\\resources\\dataset\\pictures");
        String absolutePath = file.getAbsolutePath();

        return absolutePath;
    }

}
