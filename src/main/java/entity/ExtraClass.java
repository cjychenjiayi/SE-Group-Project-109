package entity;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author: Jinyuan Sun
 * @description
 * @Date: 2023/5/2 20:57
 */
public class ExtraClass {
    private ArrayList<Competition> competitions;
    private ArrayList<Experience> projects;
    private ArrayList<Experience> interns;

    public ExtraClass(){
        this.competitions = new ArrayList<Competition>();
        this.projects = new ArrayList<Experience>();
        this.interns = new ArrayList<Experience>();
    }

    public void AddCompetition(int year, String name, Award awards){
        this.competitions.add(new Competition(year, name, awards));
    }

    public void AddProjects(Date sDate, Date eDate, String name, String role, String descString){
        this.projects.add(new Experience(sDate, eDate, name, role, descString));
    }

    public void AddInterns(Date sDate, Date eDate, String name, String role, String descString){
        this.interns.add(new Experience(sDate, eDate, name, role, descString));
    }

    public void AddCompetitionJson(Competition competition ) {
        this.competitions.add(competition);
    }

    public void AddProjectJson(Experience experience ) {
        this.projects.add(experience);
    }

    public void AddInternJson(Experience experience ) {
        this.interns.add(experience);
    }

    public ArrayList<Competition> getCompetitions() {
        return competitions;
    }

    public ArrayList<Experience> getProjects() {
        return projects;
    }

    public ArrayList<Experience> getInterns() {
        return interns;
    }

    @Override
    public String toString() {
        return "ExtraClass{" +
                "competitions=" + competitions +
                ", projects=" + projects +
                ", interns=" + interns +
                '}';
    }
}