/**
 * @author :Jiayi Chen
 * @version :
 * @description :
 * @date : 2023-04-30 16:23
 */
package model.dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import entity.Honor;
import entity.HonorController;

import java.util.ArrayList;

public class HonorParser {
    public static ArrayList<HonorController> HonorControllerParser(String s ) {
        JSONArray honorControllerArray = JSONArray.parseArray(s);
        ArrayList<HonorController>honorControllers = new ArrayList<HonorController>();

        for( Object honorControllerJson: honorControllerArray ){
            int uid = ((JSONObject)honorControllerJson).getInteger("uid");
            JSONArray honors = ((JSONObject)honorControllerJson).getJSONArray("honors");
            ArrayList<Honor> honorlist = (ArrayList<Honor>) JSONArray.parseArray(honors.toJSONString(), Honor.class);
            HonorController honorController = new HonorController(uid);
            for( Honor honor: honorlist )
                honorController.insertJSON(honor);
            honorControllers.add(honorController);
        }
        return honorControllers;
    }

}
