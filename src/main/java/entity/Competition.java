package entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: Jinyuan Sun
 * @description
 * @Date: 2023/5/11 13:52
 */
@Data
public class Competition {
    private int year;
    private String name;
    private Award award;


    public Competition(int year, String name, Award award){
        this.name = name;
        this.year = year;
        this.award = award;
    }
}
