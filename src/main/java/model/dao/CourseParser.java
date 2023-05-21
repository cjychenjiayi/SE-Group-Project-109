/**
 * @author :Jiayi Chen
 * @version :
 * @description :
 * @date : 2023-05-04 16:00
 */
package model.dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import entity.Course;
import entity.CourseController;

import java.util.ArrayList;

public class CourseParser {
    public static ArrayList<CourseController> CourseControllerParser(String s ) {
        JSONArray courseControllerArray = JSONArray.parseArray(s);
        ArrayList<CourseController>courseControllers = new ArrayList<CourseController>();

        for( Object courseControllerJson: courseControllerArray ){
            int uid = ((JSONObject)courseControllerJson).getInteger("uid");
            JSONArray courses = ((JSONObject)courseControllerJson).getJSONArray("courses");
            ArrayList<Course> courselist = (ArrayList<Course>) JSONArray.parseArray(courses.toJSONString(), Course.class);
            CourseController courseController = new CourseController(uid);
            for( Course course: courselist )
                courseController.addCourseJSON(course);
            courseControllers.add(courseController);
        }
        return courseControllers;
    }
}
