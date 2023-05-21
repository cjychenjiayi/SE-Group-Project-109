package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author: Jinyuan Sun
 * @description
 * @Date: 2023/5/2 20:44
 */
@Setter
@Getter
public class Course{
    private String name;
    private double credit;
    private int score;
    private int semester;
    private CourseType type;
    private int cid;

    public Course(int cid, String  name,  double credit, int score, int semester, CourseType type){ //type1:compulsory, 2专业选修, 公选
        this.cid = cid;
        this.name = name;
        this.credit = credit;
        this.score = score;
        this.semester = semester;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Course{" +
                "cid='" + cid + "\'" +
                "name='" + name + '\'' +
                ", credit=" + credit +
                ", score=" + score +
                ", semester=" + semester +
                ", type=" + type +
                '}';
//        return ""
    }

}