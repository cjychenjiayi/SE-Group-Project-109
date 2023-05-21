/**
 * @author :Jiayi Chen
 * @version :
 * @description :
 * @date : 2023-04-30 10:35
 */
package model.dao.base;

import com.alibaba.fastjson.JSON;
import entity.*;
import method.IO;
import model.dao.TotExperienceParser;

import java.io.IOException;
import java.util.ArrayList;

import static model.dao.CourseParser.CourseControllerParser;
import static model.dao.HonorParser.HonorControllerParser;
import static model.dao.UserParser.UserControllerParser;
import static model.dao.TotExperienceParser.TotExperienceControllerParser;

public class DataManager {

    private static DataManager instance = null;
    private static String DatabasePath = "src/main/resources/dataset/";
    private int curr_uid;
    public ArrayList<HonorController> honorControllers;
    public UserController userController;
    public ArrayList<CourseController> courseControllers;
    public ArrayList<TotExperience> totExperiences;
    /**
     * get the unique instance of DataManager
     *
     * @return reference of DataManager object
     * @throws IOException IOException
     */
    public static DataManager getInstance() throws IOException {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    /**
     * DataManager constructor
     *
     * @throws IOException IOException
     */
    private DataManager() throws IOException {
        /**
         * Read information which are stored in JSON files.
         */
        honorControllers = (ArrayList<HonorController>) HonorControllerParser(IO.read(DatabasePath + "Honor.json"));
        if(honorControllers.isEmpty() == true)
            honorControllers = new ArrayList<HonorController>();

        userController = UserControllerParser(IO.read(DatabasePath + "User.json"));

        courseControllers = (ArrayList<CourseController>) CourseControllerParser(IO.read(DatabasePath + "Course.json"));
        if(courseControllers.isEmpty() == true)
            courseControllers = new ArrayList<CourseController>();

        totExperiences = (ArrayList<TotExperience>) TotExperienceControllerParser(IO.read(DatabasePath + "Experience.json"));
        if(totExperiences.isEmpty() == true)
            totExperiences = new ArrayList<TotExperience>();

        curr_uid = 1;
    }
    public void set_uid( int uid ) {
        this.curr_uid = uid;
    }

    /**
     * write all data from memory into disk (files)
     *
     * @throws IOException IOException
     */
    public void update() throws IOException {
        IO.write(DatabasePath + "Honor.json", JSON.toJSONString(honorControllers));
        IO.write(DatabasePath + "User.json", JSON.toJSONString(userController));
        IO.write(DatabasePath + "Course.json", JSON.toJSONString(courseControllers));
        IO.write(DatabasePath + "Experience.json", JSON.toJSONString(totExperiences));
    }

    public CourseController getCourseController() {
        for( CourseController courseController:courseControllers ) {
            if( courseController.getUid() == curr_uid )
                return courseController;
        }
        return new CourseController(-1);
    }

    public HonorController getHonorController() {
        for( HonorController honorController:honorControllers ) {
            if( honorController.getUid() == curr_uid )
                return  honorController;
        }
        return new HonorController(-1);
    }

    public TotExperience getExperience() {
        for( TotExperience totExperience: totExperiences ) {
            if( totExperience.getUid() == curr_uid )
                return  totExperience;
        }
        return  new TotExperience(-1);
    }

    public static User emptyUser = new User(-1,"", "", "" ,"" ,"", GenderType.MALE, PortraitType.Dog);
    public User getUser() {
        ArrayList<User> users = userController.getUsers();
        for( User user:users )
            if( user.getUid() == curr_uid )
                return user;
        return emptyUser;
    }
}
