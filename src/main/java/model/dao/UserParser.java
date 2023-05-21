/**
 * @author :Jiayi Chen
 * @version :
 * @description :
 * @date : 2023-05-09 14:59
 */
package model.dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import entity.User;
import entity.UserController;

import java.util.ArrayList;

public class UserParser {
    public static UserController UserControllerParser(String s ) {
        JSONArray userarray = JSONObject.parseObject(s).getJSONArray("users");
        ArrayList<User> users = (ArrayList<User>) JSONArray.parseArray(userarray.toJSONString(), User.class);

        UserController userController = new UserController();
        for( User user:users ) {
            userController.insert(user);
        }
        return userController;
    }
}
