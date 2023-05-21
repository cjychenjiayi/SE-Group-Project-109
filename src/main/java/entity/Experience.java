package entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: Jinyuan Sun
 * @description
 * @Date: 2023/5/11 13:51
 */
@Data
@AllArgsConstructor
public class Experience {

    @JSONField(format="yyyy-MM-dd")
    private Date startDate;
    @JSONField(format="yyyy-MM-dd")
    private Date endDate;


    private String name;
    private String role;
    private String description;

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

//    public String toString() {
//        return
//    }
}
