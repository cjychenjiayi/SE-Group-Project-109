package entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author: Jinyuan Sun
 * @description
 * @Date: 2023/5/11 14:12
 */
public class Role {
    private ArrayList<Experience> classes;
    private ArrayList<Experience> school;
    private ArrayList<Experience> volunteer;

    public Role(){
        this.classes = new ArrayList<>();
        this.school = new ArrayList<>();
        this.volunteer = new ArrayList<>();
    }

    public void AddClasses(Date sDate, Date eDate, String name, String role, String descString){
        this.classes.add(new Experience(sDate, eDate, name, role, descString));
    }

    public void AddSchools(Date sDate, Date eDate, String name, String role, String descString){
        this.school.add(new Experience(sDate, eDate, name, role, descString));
    }

    public void AddVolunteers(Date sDate, Date eDate, String name, String role, String descString){
        this.volunteer.add(new Experience(sDate, eDate, name, role, descString));
    }

    public void AddClassesJson(Experience experience ) {
        this.classes.add(experience);
    }

    public void AddSchoolJson(Experience experience ) {
        this.school.add(experience);
    }

    public void AddVolunteersJson(Experience experience ) {
        this.volunteer.add(experience);
    }

    public ArrayList<Experience> getClasses() {
        return classes;
    }

    public ArrayList<Experience> getSchool() {
        return school;
    }

    public ArrayList<Experience> getVolunteer() {
        return volunteer;
    }

    @Override
    public String toString() {
        return "Role{" +
                "classes=" + classes +
                ", school=" + school +
                ", volunteer=" + volunteer +
                '}';
    }
}
