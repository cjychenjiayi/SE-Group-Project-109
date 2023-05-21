/**
 * @author :Jiayi Chen
 * @version :
 * @description :
 * @date : 2023-04-30 19:17
 */
package method;
import freemarker.core.XMLOutputFormat;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class GenerateCV {
    public static void main(String[] args) throws FileNotFoundException {
        String title = "使用Freemaker导出文档";
//        List<String> lists = new ArrayList<String>();
//        lists.add("firstName");
//        lists.add("lastName");
//        lists.add("message");
        Map<String,Object> dataMap = new HashMap<String, Object>();
        // 变量名必须与模板里设置的名字一致
        dataMap.put("firstName", "shashjah");
//        dataMap.put("lists", lists);

        getTempFile(dataMap, "template.ftl");
    }

    // 配置信息可以自行查阅
    public static Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        configuration.setDefaultEncoding("UTF-8");
        // 对象属性为空时，避免抛出异常
        configuration.setClassicCompatible(true);
        configuration.setOutputFormat(XMLOutputFormat.INSTANCE);
        configuration.setClassForTemplateLoading(GenerateCV.class, "/CV");
        return configuration;
    }

    /**
     * @param dataMap 参数
     * @param templateName 模板名称
     */
    public static File getTempFile(Map dataMap, String templateName) throws FileNotFoundException {
        Template template = null;
        Writer out = null;
        // 临时文件
        File outFile = null;
        try {
            template = getConfiguration().getTemplate(templateName);
            String name = "导出" + (int) (Math.random() * 100000) + ".docx";
            outFile = new File(name);
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"utf-8"));
            // 模板整合内容
            template.process(dataMap, out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{ //finally下面的代码很重要 否则的话你导不出word 必须flush close 一下才行
            if(out!=null){
                try {
                    out.flush();
                    out.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        return outFile;
    }
}
