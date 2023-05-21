/**
 * @author :Jiayi Chen
 * @version :
 * @description :
 * @date : 2023-05-09 15:26
 */
package TestEntity;

import entity.GenderType;
import entity.PortraitType;
import entity.User;
import entity.UserController;
import static org.junit.jupiter.api.Assertions.*;

public class TestUserController {
    public static void main(String[] args) {
        User user = new User(1,"cjy", "12345", "Jiayi Chen", "123@cjy.com", "12345678910", GenderType.MALE, PortraitType.Dog);
        User user2 = new User(2,"syt", "12718", "Yuntian Shi", "12345@syt.com", "3293792", GenderType.FEMALE, PortraitType.Rabbit);
        UserController userController = new UserController();
        userController.insert(user);
        userController.insert(user2);
        userController.insert("sjy",  "hhhhhh", "Jinyuan Sun", "123@sjy.com", "121827817", GenderType.MALE, PortraitType.Dog);

        assertEquals(userController.fetchIDbyname("cjy"), 1);
        assertEquals(userController.fetchPWbyname("sjy"), "hhhhhh");

    }
}
