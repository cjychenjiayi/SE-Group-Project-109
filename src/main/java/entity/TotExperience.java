/**
 * @author :Jiayi Chen
 * @version :
 * @description :
 * @date : 2023-05-11 15:23
 */
package entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class TotExperience {
    private int uid;
    private ExtraClass extraClass;
    private Role role;
    private Skill skill;

    public TotExperience( int uid, ExtraClass extraClass, Role role, Skill skill ) {
        this.uid = uid;
        this.extraClass = extraClass;
        this.role = role;
        this.skill = skill;
    }

    public TotExperience( int uid ) {
        this.uid = uid;
        this.extraClass = new ExtraClass();
        this.role = new Role();
        this.skill = new Skill();
    }
}
