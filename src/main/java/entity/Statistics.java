package entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author: Jinyuan Sun
 * @description
 * @Date: 2023/5/2 21:07
 */
@Setter
@Getter
public class Statistics {
    private int count;      // the total course number
    private double gpa;     // gpa of selected courses
    private double avgScore;    // average score of selected courses
    private double totalCredit; // total credit of selected courses

    public Statistics(){
        this.count = 0;
        this.gpa = 0.0;
        this.avgScore = 0.0;
        this.totalCredit = 0.0;
    }


    @Override
    public String toString(){
        return "count: " + getCount() + "\ngpa: " +getGpa() + "\naverage Score: " + getAvgScore() + "\ntotal credit: " + getTotalCredit();
    }
}
