/**
 * @author :Jiayi Chen
 * @version :
 * @description :
 * @date : 2023-05-04 17:52
 */
package method;

import entity.Course;
import entity.Honor;

import java.util.Comparator;

public class Comparators {
    public static Comparator<Honor> honorComparator = new Comparator<Honor>() {
        @Override
        public int compare(Honor h1, Honor h2) {
            return h1.getDate().compareTo(h2.getDate());
        }
    };

    public static Comparator<Course> courseComparator = new Comparator<Course>() {
        @Override
        public int compare(Course c1, Course c2) {
            return c1.getSemester() - c2.getSemester();
        }
    };
}
