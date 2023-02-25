
package BUS;

import BLL.OnsiteCourse;
import DAL.OnsiteCourseDAL;
import java.util.ArrayList;

public class OnsiteCourseBUS {
    public static ArrayList<OnsiteCourse> list = new ArrayList<>();    
    public OnsiteCourseBUS(){}
    public ArrayList<OnsiteCourse> doc(){
        OnsiteCourseDAL data = new OnsiteCourseDAL();
        try{
            list = new ArrayList<OnsiteCourse>();
            list = data.doc();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public int them(OnsiteCourse sv){
        OnsiteCourseDAL data = new OnsiteCourseDAL();
        int check = data.them(sv);
        if(check==1)
            list.add(sv);
        return check;
    }
    public int sua(OnsiteCourse sv,int i){
        OnsiteCourseDAL data = new OnsiteCourseDAL();
        int check = data.sua(sv);
        if(check ==1)
            list.set(i,sv);
        return check;
    }
            public static ArrayList<OnsiteCourse> timkiem(String key, String query) {
                ArrayList<OnsiteCourse> temp = new ArrayList<>();
                OnsiteCourseDAL data = new OnsiteCourseDAL();
                try{
                ArrayList<OnsiteCourse> s = data.doc();
                if (key.equals("Location")) {
                    for (int i = 0; i < s.size(); i++) {
                        if (s.get(i).getLocation().toLowerCase().equals(query)) {
                            temp.add(s.get(i));
                        }

                    }
                    return temp;
                }
                if (key.equals("CourseID")) {
                    for (int i = 0; i < s.size(); i++) {
                        if (s.get(i).getCourseID().toLowerCase().equals(query)) {
                            temp.add(s.get(i));
                        }

                    }
                    return temp;
                }
                if (key.equals("Days")) {
                    for (int i = 0; i < s.size(); i++) {
                        if (s.get(i).getDays().toLowerCase().equals(query)) {
                            temp.add(s.get(i));
                        }

                    }
                    return temp;
                }
                if (key.equals("Time")) {
                    for (int i = 0; i < s.size(); i++) {
                        if (s.get(i).getTime().toLowerCase().equals(query)) {
                            temp.add(s.get(i));
                        }

                    }
                    return temp;
                }
                }catch(Exception e){
                    e.printStackTrace();
                }
                return null;
            }    
}
