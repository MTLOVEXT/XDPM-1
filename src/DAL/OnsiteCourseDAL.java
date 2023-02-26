
package DAL;

import BLL.OnsiteCourse;
import BLL.StudentGrade;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import untils.MyDataAccess;

public class OnsiteCourseDAL {
    MyDataAccess my = new MyDataAccess("localhost","root","","school");
    public OnsiteCourseDAL(){}
    public ArrayList<OnsiteCourse> doc() throws Exception{
        ArrayList<OnsiteCourse> list = new ArrayList<OnsiteCourse>();
        try{
                String qry = "select * from onsitecourse";
                ResultSet rs = my.executeQuery(qry);
                while(rs.next()){
                    OnsiteCourse OC = new OnsiteCourse();
                    OC.setCourseID(rs.getString(1));
                    OC.setLocation(rs.getString(2));
                    OC.setDays(rs.getString(3));
                    OC.setTime(rs.getString(4));
                    list.add(OC);
                }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi đọc Database");
        }
        finally{
            my.close();
        }
        return list;
    }
    
    public int xoa(String id) {
        int res = 0;
        try {
                        String qry = "Delete from onsitecourse where CourseID = '"+id+" ' ";
                        res = my.executeUpdate(qry);
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null,"Lỗi xóa địa điểm học trong Database");
        }
        return res;
    }
    
    public boolean isValidtoAdd(OnsiteCourse sgrade){
       try{
           ArrayList<OnsiteCourse> arr = doc();
           for (int i = 0; i < arr.size(); i++) {
               if(arr.get(i).getCourseID().equals(sgrade.getCourseID())){
                JOptionPane.showMessageDialog(null,"Mã khóa đã tồn tại");
                return false;}
               else return true;}
        }catch(Exception e){
            return true;
        }
    return true;        
    }
    
    public int them(OnsiteCourse oc){
        if(isValidtoAdd(oc)){
        int res = 0;
        try{
                String qry = "insert into onsitecourse values(";
                qry=qry+"'"+oc.getCourseID()+"'";
                qry=qry+",'"+oc.getLocation()+"'";
                qry=qry+",'"+oc.getDays()+"'";
                qry=qry+",'"+oc.getTime()+"')";
                res = my.executeUpdate(qry);
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"Lỗi thêm khóa học vào Database");
            }
        return res;
        }
        return 0;
    }
    public int sua(OnsiteCourse oc){
        int res = 0;
        try{
            String qry = "update onsitecourse set ";
            qry = qry + "Location='"+oc.getLocation()+"',";
            qry = qry + "Days='"+oc.getDays()+"',";
            qry = qry + "Time='"+oc.getTime()+"'";
            qry = qry +" where CourseID ='"+oc.getCourseID()+"'";
            res = my.executeUpdate(qry);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi sửa khóa học trong Database");
        }
        return res;
    }
}
