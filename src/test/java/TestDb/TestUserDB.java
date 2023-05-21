/**
 * @author :Jiayi Chen
 * @version :
 * @description :
 * @date : 2023-05-09 14:57
 */
package TestDb;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import entity.*;
import model.dao.base.DataManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static model.dao.HonorParser.HonorControllerParser;

public class TestUserDB {
    public static void main(String[] args) throws IOException {
        UserController userController = DataManager.getInstance().userController;
        System.out.println(userController.toString());
        //userController.insert("abc", "defg", "hij", "klmn@qqq.com", "2178281278", GenderType.MALE, PortraitType.Strawberry);

//        System.out.println(userController.fetchIDbyname());
//        System.out.println(JSON.toJSONString(honorControllers));
//        JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(honorControllers));
//        String str = jsonArray.toJSONString();
//        System.out.println(str);
//
//        ArrayList<HonorController> h1 = HonorControllerParser(str);
//        System.out.println(h1);
//
//        HonorController honorController = honorControllers.get(0);
//        Honor honor = new Honor(7,"shajsja", "ashjasj", "sjaksjk", HonorType.CLASS, new Date());
//        honorController.insertJSON(honor);
        DataManager.getInstance().update();
    }

}
