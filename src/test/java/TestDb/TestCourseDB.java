/**
 * @author :Jiayi Chen
 * @version :
 * @description :
 * @date : 2023-05-11 10:50
 */
package TestDb;

import entity.CourseController;
import entity.CourseType;
import model.dao.base.DataManager;

import java.io.IOException;
import java.util.ArrayList;

public class TestCourseDB {
    public static void main(String[] args) throws IOException {

        ArrayList <CourseController>courseControllers = DataManager.getInstance().courseControllers;
        for( CourseController courseController: courseControllers) {
            System.out.println(courseController.toString());
        }

//        CourseController courseController1 = courseControllers.get(0);
//        courseController1.addCourse("PE","sajskakj", 3.7, 90, 3, CourseType.PUBLIC_ELECTIVES);
//        for( CourseController courseController: courseControllers) {
//            System.out.println(courseController.toString());
//        }
        DataManager.getInstance().update();
    }
}
