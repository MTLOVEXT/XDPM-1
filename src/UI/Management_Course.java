package UI;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Management_Course extends JFrame implements ActionListener{

            JLabel lblcourseID,lblTitlle,lblCredits,lblDepartment;
            JTextField txt_courseid,txt_Title,txt_credits,txt_departmentid;
            
            Random ran = new Random();
            long first4 = Math.abs((ran.nextLong() % 9000L) + 1000L);
            
            
    
            Management_Course() {
                
                
            }
    
    

            public static void main(String[] args) {
                // TODO code application logic here
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
    
}
