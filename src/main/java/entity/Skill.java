package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/**
 * @author: Jinyuan Sun
 * @description
 * @Date: 2023/5/11 14:18
 */
public class Skill {
    private ArrayList<Experience> skills;

    public Skill(){
        this.skills = new ArrayList<>();
    }

    public void AddSkill(Date sDate, Date eDate, String name, String role, String descString){
        skills.add(new Experience(sDate, eDate, name, role, descString));
    }

    @Override
    public String toString() {
        return "Skill{" +
                "skills=" + skills +
                '}';
    }

    public void AddSkillJson(Experience experience){
        skills.add(experience);
    }

    public ArrayList<Experience> getSkills() {
        return skills;
    }
}
