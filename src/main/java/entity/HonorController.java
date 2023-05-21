/**
 * @author :Jiayi Chen
 * @version :
 * @description :
 * @date : 2023-04-14 10:59
 */
package entity;

import java.util.*;

import static method.Comparators.honorComparator;
import static method.Others.backward;
import static method.SearchMethod.kmpSearch;

public class HonorController {
    private ArrayList<Honor> honors;
    private int uid;
    private int count;
    public HonorController(int uid) {
        this.uid = uid;
        this.count = 0;
        this.honors = new ArrayList<Honor>();
    }

    public void insert(String name, String info, String picpath, HonorType type, Date date ) {
        Honor honor = new Honor(this.count, name, info, picpath, type, date );
        this.honors.add(honor);
        this.count++;
    }

    public void insertJSON( Honor honor){
        this.honors.add(honor);
        this.count++;
    }

    public ArrayList<Honor> getHonorByTypes(ArrayList<HonorType> types) {
        ArrayList<Honor> result = new ArrayList<>();
        for (Honor honor : this.honors) {
            if (types.contains(honor.getType())) {
                result.add(honor);
            }
        }
        Collections.sort(result, honorComparator);
        return result;
    }

    public ArrayList<Honor> searchByText( String pattern ) {
        ArrayList<Honor> result = new ArrayList<>();
        for (Honor honor : this.honors) {
            if (kmpSearch(honor.getInfo(),pattern)) {
                result.add(honor);
            }
        }
        Collections.sort(result, honorComparator);
        return result;
    }

    public ArrayList<Honor> searchByTypesandText(  String pattern, ArrayList<HonorType> types) {
        ArrayList<Honor> result = new ArrayList<>();
        for (Honor honor : this.honors) {
            if (types.contains(honor.getType()) && kmpSearch(honor.getInfo(),pattern)) {
                result.add(honor);
            }
        }
        Collections.sort(result, honorComparator);
        return result;
    }

    public ArrayList<Honor> toDatabase( ) {
        ArrayList<Honor> result = new ArrayList<>();
        for (Honor honor : this.honors) {
            result.add(honor);
        }
        Collections.sort(result, honorComparator);
        return result;
    }

    @Override
    public String toString() {
        String ret = "{\"uid\": " + this.uid + ",\"honors\":[";
        for(Honor honor:this.honors)
            ret += honor.toString() + ",";
        ret = backward(ret, 1);
        ret += "]}";
        return ret;
    }

    public int getUid(){
        return this.uid;
    }
    public ArrayList<Honor> getHonors(){
        return this.honors;
    }
}
