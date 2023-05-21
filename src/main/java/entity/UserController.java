/**
 * @author :Jiayi Chen
 * @version :
 * @description :
 * @date : 2023-05-04 18:23
 */
package entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.*;

@Setter
public class UserController {
    private HashMap<String, Integer> nameToid;
    private HashMap<String, String> nameTopassword;
    private int count = 0;
    private ArrayList<User> users;

    public UserController() {
        this.count = 0;
        this.users = new ArrayList<User>();
        this.nameToid = new HashMap<String, Integer>();
        this.nameTopassword = new HashMap<String, String>();
    }

    public void insert( User user ){
        nameToid.put(user.getUserName(), user.getUid());
        nameTopassword.put(user.getUserName(), user.getPassword());
        count = Math.max(count, user.getUid());
        users.add(user);
    }

    public void insert(String userName,  String password, String name, String email,
                       String phone, GenderType gender, PortraitType portrait ) {
        if( nameToid.containsKey(userName)) {
            return;
        }
        count++;
        User user = new User(count, userName, password,  name, email, phone, gender, portrait );
        nameToid.put(user.getUserName(), user.getUid());
        nameTopassword.put(user.getUserName(), user.getPassword());
        users.add(user);
    }
    public int fetchIDbyname( String userName ) {
        if(nameToid.containsKey(userName) == false) {
            return -1;
        }
        return nameToid.get(userName);
    }

    public String fetchPWbyname( String userName ) {
        if(nameTopassword.containsKey(userName) == false) {
            return "";
        }
        return nameTopassword.get(userName);
    }

    public ArrayList<User> getUsers() {
        return this.users;
    }

    public int getUid( String userName, String password ) {
        String pwd = this.fetchPWbyname(userName);
        if(pwd == "") {
            return -2; // No this  userName
        }
        if(!pwd.matches( password)) {
            return -1; // Wrong password
        }
        return this.fetchIDbyname(userName);
    }
    @Override
    public String toString() {
        String ret = "";
        for( User user:this.users) {
            ret += user.toString() + "\n";
        }
        return ret;
    }

}
