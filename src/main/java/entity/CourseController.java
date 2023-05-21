package entity;

//import model.exception.CourseException;

import java.util.ArrayList;
import java.util.Collections;

import static method.Comparators.courseComparator;


/**
 * @author: Jinyuan Sun
 * @description
 * @Date: 2023/5/2 20:56
 */

public class CourseController{
    private ArrayList<Course> courses;
    private Statistics statistics;
    private int uid;
    private int count;

    public CourseController( int uid ){
        this.courses = new ArrayList<Course>();
        this.statistics = new Statistics();
        this.uid = uid;
        this.count = 0;
    }

    public void addCourse(String name,  double credit, int score, int semester, CourseType type){
        this.count++;
        Course course = new Course(count,name, credit, score, semester, type);
        courses.add(course);
        statistics = this.calculate(0);
    }
    public void addCourseJSON(Course course){
        courses.add(course);
        this.count = Math.max(this.count, course.getCid());
        statistics =  this.calculate(0);
    }

    /**
     * return the courses of selected types.
     * @param types
     * @return ArrayList of Courses
     */
    public ArrayList<Course> getCourseByTypes(ArrayList<CourseType> types){
        ArrayList<Course> result = new ArrayList<>();
        for (Course course : this.courses) {
            if (types.contains(course.getType())) {
                result.add(course);
            }
        }
        Collections.sort(result, courseComparator);
        return result;
    }

    /**
     * return the courses of selected semesters.
     * @param semesters
     * @return ArrayList of Courses
     */
    public ArrayList<Course> getCourseBySemesters(ArrayList<Integer> semesters){
        ArrayList<Course> result = new ArrayList<>();
        for (Course course : this.courses) {
            if (semesters.contains(course.getSemester())) {
                result.add(course);
            }
        }
        Collections.sort(result, courseComparator);
        return result;
    }

    public ArrayList<Course> toDatabase() {
        ArrayList<Course> result = new ArrayList<>();
        for (Course course : this.courses) {
            result.add(course);
        }
        Collections.sort(result, courseComparator);
        return result;
    }

    /**
     * calculate the GPA etc. related to the course
     * @param semester
     * @return the calculated result <class>Statistics</class>
     */
    public Statistics calculate(int semester){
        Statistics ret = new Statistics();
        int tmpCount = 0;
        double tmpCredit = 0.0;
        double weightedScoreSum = 0.0;
        double gradePointSum = 0.0;
        if (semester == 0){     // 全部学期
            for (Course i : this.courses){
                tmpCount += 1;
                tmpCredit += i.getCredit();
                weightedScoreSum += i.getScore() * i.getCredit();
                if (i.getScore() >= 60)
                    gradePointSum += ( 4 - 3 * Math.pow(100 - i.getScore(), 2)/1600 ) * i.getCredit();
            }

        }else{
            for (Course i : this.courses){
                if (i.getSemester() != semester)
                    continue;
                else{
                    tmpCount += 1;
                    tmpCredit += i.getCredit();
                    weightedScoreSum += i.getScore() * i.getCredit();
                    if (i.getScore() >= 60)
                        gradePointSum += ( 4 - 3 * Math.pow(100 - i.getScore(), 2)/1600 ) * i.getCredit();
                }
            }
        }
        ret.setTotalCredit(tmpCredit);
        ret.setAvgScore(weightedScoreSum / tmpCredit);
        ret.setCount(tmpCount);
        ret.setGpa(gradePointSum / tmpCredit);
        return ret;
    }

    public int getUid() {
        return this.uid;
    }

    public ArrayList<Course> getCourses() {
        return this.courses;
    }

    @Override
    public String toString() {
        String ret = "uid: " + this.uid + "\n";
        for(Course course:this.courses) {
            ret = ret + course.toString() + "\n";
        }
        return ret;
    }

    public Statistics getStatistics() {
        statistics = calculate(0);
        return this.statistics;
    }
}