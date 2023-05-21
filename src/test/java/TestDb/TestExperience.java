/**
 * @author :Jiayi Chen
 * @version :
 * @description :
 * @date : 2023-05-11 16:24
 */
package TestDb;

import com.alibaba.fastjson.JSON;
import entity.TotExperience;
import model.dao.base.DataManager;

import java.io.IOException;
import java.util.ArrayList;

public class TestExperience {
    public static void main(String[] args ) throws IOException {
//        TotExperience totExperience = DataManager.getInstance().getExperience();
//        System.out.println(totExperience);
        ArrayList<TotExperience> totExperiences = DataManager.getInstance().totExperiences;
        for( TotExperience totExperience:totExperiences ) {
            System.out.println(totExperience);
            System.out.println(JSON.toJSONString( totExperience));
        }
    }
}
