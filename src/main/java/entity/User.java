/**
 * @author :Jiayi Chen
 * @version :
 * @description :
 * @date : 2023-05-04 18:44
 */
package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class User {
    private int uid;
    private String userName;
    private String password;
    private String name;
    private String email;
    private String phone;
    private GenderType gender;
    private PortraitType portrait;

}
