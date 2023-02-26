package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Project extends JFrame implements ActionListener {

        Project() {
        setSize(1500, 850);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/bg.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1500, 750, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image);
        
        JMenuBar mb = new JMenuBar();
        
        // New Information
        JMenu newInformation = new JMenu("New Information");
        newInformation.setForeground(Color.BLUE);
        mb.add(newInformation);
        
        JMenuItem studentInfo = new JMenuItem("New Student Information");
        studentInfo.setBackground(Color.WHITE);
        studentInfo.addActionListener(this);
        newInformation.add(studentInfo);
        
        // Details
        JMenu details = new JMenu("View Details");
        details.setForeground(Color.RED);
        mb.add(details);
        
        JMenuItem studentdetails = new JMenuItem("View Student Details");
        studentdetails.setBackground(Color.WHITE);
        studentdetails.addActionListener(this);
        details.add(studentdetails);
        
        JMenuItem Onlinecoursedetails = new JMenuItem("View Online Course Details");
        Onlinecoursedetails.setBackground(Color.WHITE);
        Onlinecoursedetails.addActionListener(this);
        details.add(Onlinecoursedetails);
        
        JMenuItem Courseinstructordetails = new JMenuItem("View Course Instructor Details");
        Courseinstructordetails.setBackground(Color.WHITE);
        Courseinstructordetails.addActionListener(this);
        details.add(Courseinstructordetails);
        
        JMenuItem onsitecourse = new JMenuItem("View Onsite Course Details");
        onsitecourse.setBackground(Color.WHITE);
        onsitecourse.addActionListener(this);
        details.add(onsitecourse);
        
        JMenuItem managementCourse = new JMenuItem("View Management Course Details");
        managementCourse.setBackground(Color.WHITE);
        managementCourse.addActionListener(this);
        details.add(managementCourse);
        
        // Exams
        JMenu exam = new JMenu("Examination");
        exam.setForeground(Color.BLUE);
        mb.add(exam);
        
        JMenuItem examinationdetails = new JMenuItem("Examination Results");
        examinationdetails.setBackground(Color.WHITE);
        examinationdetails.addActionListener(this);
        exam.add(examinationdetails);
        
        JMenuItem entermarks = new JMenuItem("Enter Marks");
        entermarks.setBackground(Color.WHITE);
        entermarks.addActionListener(this);
        exam.add(entermarks);
        
        // UpdateInfo
        JMenu updateInfo = new JMenu("Update Details");
        updateInfo.setForeground(Color.RED);
        mb.add(updateInfo);
        
        JMenuItem updatestudentinfo = new JMenuItem("Update Student Details");
        updatestudentinfo.setBackground(Color.WHITE);
        updatestudentinfo.addActionListener(this);
        updateInfo.add(updatestudentinfo);
       
        // about
        JMenu about = new JMenu("About");
        about.setForeground(Color.BLUE);
        mb.add(about);
        
        JMenuItem ab = new JMenuItem("About");
        ab.setBackground(Color.WHITE);
        ab.addActionListener(this);
        about.add(ab);
        
        // exit
        JMenu exit = new JMenu("Exit");
        exit.setForeground(Color.RED);
        mb.add(exit);
        
        JMenuItem ex = new JMenuItem("Exit");
        ex.setBackground(Color.WHITE);
        ex.addActionListener(this);
        exit.add(ex);
        
        setJMenuBar(mb);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        String msg = ae.getActionCommand();
        
        if (msg.equals("Exit")) {
            setVisible(false);
        } else if (msg.equals("Calculator")) {
            try {
                Runtime.getRuntime().exec("calc.exe");
            } catch (Exception e) {
                
            }
        } else if (msg.equals("Notepad")) {
            try {
                Runtime.getRuntime().exec("notepad.exe");
            } catch (Exception e) {
                
            }
        }  else if (msg.equals("New Student Information")) {
            new AddStudent();
        }else if (msg.equals("View Student Details")) {
            new StudentDetails();
        }else if (msg.equals("View Online Course Details")) {
            new Onlinecourse();
        }else if (msg.equals("View Course Instructor Details")) {
            new Courseinstructor();
        }else if (msg.equals("View Onsite Course Details")) {
            new onsitecourse();
        }else if (msg.equals("View Management Course Details")) {
            new Management_Course();
        }else if (msg.equals("Update Teacher Details")) {
            new UpdateTeacher();
        } else if (msg.equals("Update Student Details")) {
            new UpdateStudent();
        } else if (msg.equals("Enter Marks")) {
            new EnterMarks();
        } else if (msg.equals("Examination Results")) {
            new ExaminationDetails();
        } else if (msg.equals("About")) {
            new About();
        } 
    }
    
    public static void main(String[] args) {
        new Project();
    }

}
