/**
 * @author :Jiayi Chen
 * @version :
 * @description :
 * @date : 2023-05-11 15:31
 */
package model.dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import entity.*;

import java.util.ArrayList;

public class TotExperienceParser {
    public static ArrayList<TotExperience> TotExperienceControllerParser( String s ) {
        JSONArray totExperienceArray = JSONArray.parseArray(s);
        ArrayList<TotExperience>totExperiences = new ArrayList<TotExperience>();

        for( Object totExperiece: totExperienceArray) {
            int uid = ((JSONObject) totExperiece).getInteger("uid");
            Object skillJson = ((JSONObject) totExperiece ).get("skill");
            Skill skill = SkillParser(skillJson);
            Object roleJson = ((JSONObject) totExperiece ).get("role");
            Role role = RoleParser(roleJson);
            Object extraClassJson = ((JSONObject) totExperiece ).get("extraClass");
            ExtraClass extraClass = ExtraClassParser(extraClassJson);
            TotExperience totExperience = new TotExperience(uid, extraClass, role, skill);
            totExperiences.add(totExperience);
        }

        return totExperiences;
    }

    public static ArrayList<Experience> getExperienceArray( JSONArray experienceJson ) {
        ArrayList<Experience> experiences = new ArrayList<>();
        for( Object exp:experienceJson ) {
            Experience experience = JSONObject.parseObject(exp.toString(), Experience.class);
            experiences.add(experience);
        }
        return experiences;
    }
    public static Skill SkillParser( Object skillJson ) {
        JSONArray experienceArray = ((JSONObject) skillJson).getJSONArray("skills");
        ArrayList<Experience> experiences = getExperienceArray(experienceArray);
        Skill skill = new Skill();
        for( Experience experience: experiences ) {
            skill.AddSkillJson(experience);
        }
        return skill;
    }

    public static Role RoleParser( Object roleJson ) {
        JSONArray classArray = ((JSONObject)roleJson).getJSONArray("classes");
        ArrayList<Experience>classes = getExperienceArray(classArray);
        JSONArray schoolArray = ((JSONObject)roleJson).getJSONArray("school");
        ArrayList<Experience>school = getExperienceArray(schoolArray);
        JSONArray volunteerArray = ((JSONObject)roleJson).getJSONArray("volunteer");
        ArrayList<Experience>volunteer = getExperienceArray(volunteerArray);

        Role role = new Role();
        for( Experience experience: classes ) {
            role.AddClassesJson(experience);
        }
        for( Experience experience: school ) {
            role.AddSchoolJson(experience);
        }
        for( Experience experience: volunteer ) {
            role.AddVolunteersJson(experience);
        }

        return role;
    }

    public static ExtraClass ExtraClassParser( Object extraClassJson ) {
        JSONArray projectsArray = ((JSONObject)extraClassJson).getJSONArray("projects");
        ArrayList<Experience>projects = getExperienceArray(projectsArray);
        JSONArray internsArray = ((JSONObject)extraClassJson).getJSONArray("interns");
        ArrayList<Experience>interns = getExperienceArray(internsArray);
        JSONArray competitionsArray = ((JSONObject)extraClassJson).getJSONArray("competitions");
        ArrayList<Competition>competitions = (ArrayList<Competition>) JSONArray.parseArray(competitionsArray.toJSONString(), Competition.class);

        ExtraClass extraClass = new ExtraClass();
        for( Experience experience: projects ) {
            extraClass.AddProjectJson(experience);
        }
        for( Experience experience: interns ) {
            extraClass.AddInternJson(experience);
        }
        for( Competition competition: competitions ) {
            extraClass.AddCompetitionJson(competition);
        }

        return extraClass;
    }

}
