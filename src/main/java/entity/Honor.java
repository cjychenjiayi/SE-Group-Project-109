/**
 * @author :Jiayi Chen
 * @version :
 * @description :
 * @date : 2023-04-14 10:44
 */
package entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class Honor {
    private String name;
    private String info;
    private String picpath;
    private HonorType type;
    private int hid;


    @JSONField(format="yyyy-MM-dd")
    private Date date;


    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public Honor( int hid, String name, String info, String picpath, HonorType type, Date date ) {
        this.hid = hid;
        this.name = name;
        this.info = info;
        this.picpath = picpath;
        this.type = type;
        this.date = date;
    }


    @Override
    public String toString() {
        return "{\n\"hid\": " + this.hid + ",\n\"name\": \"" + this.name + "\",\n\"Info\": \"" +
                this.info + "\",\n\"picpath\": \"" + this.picpath + "\",\n\"type\": \"" + this.type
                + "\",\n\"date\": \"" + format.format(this.date) + "\"\n}";
    }
}
