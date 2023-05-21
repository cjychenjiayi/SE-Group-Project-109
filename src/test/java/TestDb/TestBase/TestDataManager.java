/**
 * @author :Jiayi Chen
 * @version :
 * @description :
 * @date : 2023-04-30 13:54
 */
package TestDb.TestBase;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import entity.Honor;
import entity.HonorController;
import entity.HonorType;
import model.dao.base.DataManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static model.dao.HonorParser.HonorControllerParser;

public class TestDataManager {
    public static void main(String[] args) throws IOException {
        ArrayList<HonorController> honorControllers = DataManager.getInstance().honorControllers;
        System.out.println(honorControllers.get(0).toString());
//        System.out.println(JSON.toJSONString(honorControllers));
        JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(honorControllers));
        String str = jsonArray.toJSONString();
//        System.out.println(str);

        ArrayList<HonorController> h1 = HonorControllerParser(str);
//        System.out.println(h1);

//        HonorController honorController = honorControllers.get(0);
//        Honor honor = new Honor(7,"shajsja", "ashjasj", "sjaksjk", HonorType.CLASS, new Date());
//        honorController.insertJSON(honor);
//        DataManager.getInstance().update();
    }

}
