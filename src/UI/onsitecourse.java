package UI;

import BLL.Course;
import BLL.OnsiteCourse;
import BLL.StudentGrade;
import BUS.CourseBUS;
import BUS.OnsiteCourseBUS;
import BUS.StudentGradeBUS;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

public class onsitecourse extends JFrame implements ActionListener{
    
            JTextField tfname,txtlocaltion,txttimer,txtcourseID; 
            JButton btnaddButton,btncancel,btnsubmit,btnsearch;
            JTable tb_onsite,tb_course;
            JComboBox cbdays, cbbranch, cbsemester;
            
            OnsiteCourseBUS busonsite = new OnsiteCourseBUS();
            DefaultTableModel modelonsite = new DefaultTableModel();
            
            CourseBUS buscourse = new CourseBUS();
            DefaultTableModel modelcourse = new DefaultTableModel();
    
            Random ran = new Random();
            long first4 = Math.abs((ran.nextLong() % 9000L) + 1000L);
            
            public void loadonsite() {
                OnsiteCourseBUS bus = new OnsiteCourseBUS();
                try {
                    bus.doc();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Lỗi kết nối đến Database.");
                    return;
                }
                Vector header = new Vector();
                header.add("Course ID");
                header.add("Location");
                header.add("Days");
                header.add("Time");
                modelonsite = new DefaultTableModel(header, 0) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                showOnTable(bus.list);
            }
            
            public void showOnTable(ArrayList<OnsiteCourse> list) {
                modelonsite.setRowCount(0);
                for (OnsiteCourse s : list) {
                    Vector data = setVector(s);
                    modelonsite.addRow(data);
                }
                tb_onsite.setModel(modelonsite);
            }
            
            public Vector setVector(OnsiteCourse s) {
                Vector head = new Vector();
                head.add(s.getCourseID());
                head.add(s.getLocation());
                head.add(s.getDays());
                head.add(s.getTime());
                return head;
            }
            
            public void loadcourse() {
                CourseBUS bus = new CourseBUS();
                try {
                    bus.docCourse();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Lỗi kết nối đến Database.");
                    return;
                }
                Vector header = new Vector();
                header.add("Course ID");
                header.add("Title");
                modelcourse = new DefaultTableModel(header, 0) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                showOnTablecourse(bus.list);
            }
            
            public void showOnTablecourse(ArrayList<Course> list) {
                modelcourse.setRowCount(0);
                for (Course s : list) {
                    Vector data = setVectorcourse(s);
                    modelcourse.addRow(data);
                }
                tb_course.setModel(modelcourse);
            }
            
            public Vector setVectorcourse(Course s) {
                Vector head = new Vector();
                head.add(s.getCourseID());
                head.add(s.getTitle());
                return head;
            }
            
            public void setModelValuecourse(Course on, int i) {
                modelcourse.setValueAt(on.getCourseID(), i, 0);
                tb_course.setModel(modelcourse);
            }
            
            public void setModelValue(OnsiteCourse on, int i) {
                modelonsite.setValueAt(on.getCourseID(), i, 0);
                modelonsite.setValueAt(on.getLocation(), i, 1);
                modelonsite.setValueAt(on.getDays(), i, 2);
                modelonsite.setValueAt(on.getTime(), i, 3);
                tb_onsite.setModel(modelonsite);
            }
    
    onsitecourse() {
        setSize(800, 750);
        setLocation(250, 50);
        setLayout(null);
        
        getContentPane().setBackground(Color.WHITE);
        
        JLabel heading = new JLabel("Onsite Course");
        heading.setBounds(50, 10, 400, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 30));
        add(heading);
        
        JLabel lblname = new JLabel("Course ID");
        lblname.setBounds(50, 80, 100, 50);
        lblname.setFont(new Font("serif", Font.BOLD, 20));
        add(lblname);
        
        txtcourseID = new JTextField();
        txtcourseID.setBounds(150, 90, 100, 30);
        txtcourseID.setFont(new Font("serif", Font.BOLD, 20));
        add(txtcourseID);
        
        JLabel lblurl = new JLabel("Localtion");
        lblurl.setBounds(300, 80, 100, 45);
        lblurl.setFont(new Font("serif", Font.BOLD, 20));
        add(lblurl);
        
        txtlocaltion = new JTextField();
        txtlocaltion.setBounds(400, 80, 300, 45);
        txtlocaltion.setFont(new Font("serif", Font.BOLD, 20));
        add(txtlocaltion);
        
        JLabel lblday = new JLabel("Days");
        lblday.setBounds(80, 150, 100, 45);
        lblday.setFont(new Font("serif", Font.BOLD, 20));
        add(lblday);
        
        String course[] = {"T2,T4,T6", "T3,T5,T7","T6,CN","T5","T6","T7","CN"};
        cbdays = new JComboBox(course);
        cbdays.setBounds(135, 150, 200, 45);
        cbdays.setFont(new Font("serif", Font.BOLD, 20));
        cbdays.setBackground(Color.WHITE);
        add(cbdays);
        
        JLabel lbltime = new JLabel("Time");
        lbltime.setBounds(350, 150, 100, 45);
        lbltime.setFont(new Font("serif", Font.BOLD, 20));
        add(lbltime);
        
        txttimer = new JTextField();
        txttimer.setBounds(400, 150, 200, 45);
        txttimer.setFont(new Font("serif", Font.BOLD, 20));
        add(txttimer);
        
        btnaddButton = new JButton("Add");
        btnaddButton.setBounds(50, 280, 100, 50);
        btnaddButton.addActionListener(this);
        btnaddButton.setFont(new Font("serif", Font.BOLD, 20));
        add(btnaddButton);
        
        btnsubmit = new JButton("Edit");
        btnsubmit.setBounds(200, 280, 100, 50);
        btnsubmit.addActionListener(this);
        btnsubmit.setFont(new Font("serif", Font.BOLD, 20));
        add(btnsubmit);
        
        btnsearch = new JButton("Search");
        btnsearch.setBounds(350, 280, 100, 50);
        btnsearch.addActionListener(this);
        btnsearch.setFont(new Font("serif", Font.BOLD, 20));
        add(btnsearch);
        
        btncancel = new JButton("Cancel");
        btncancel.setBounds(500, 280, 100, 50);
        btncancel.addActionListener(this);
        btncancel.setFont(new Font("serif", Font.BOLD, 20));
        add(btncancel);
        
        tb_onsite = new JTable();
        loadonsite();
        
        JScrollPane jsp = new JScrollPane(tb_onsite);
        jsp.setBounds(400, 350, 400, 360);
        add(jsp);
        
        tb_course = new JTable();
        loadcourse();
        
        JScrollPane jsp1 = new JScrollPane(tb_course);
        jsp1.setBounds(0, 350, 400, 360);
        add(jsp1);
        
                        tb_onsite.addMouseListener(new java.awt.event.MouseAdapter() {
                                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                tb(evt);
                                    }
                        });
                        
                        tb_course.addMouseListener(new java.awt.event.MouseAdapter() {
                                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                tb_course(evt);
                                    }
                        });
        
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent ae) {
            
    }
    
            private void tb(java.awt.event.MouseEvent evt) {                                        
            int i = tb_onsite.getSelectedRow();
                        if (i >= 0) {
                                    txtcourseID.setText(tb_onsite.getModel().getValueAt(i, 0).toString());
                                    txtlocaltion.setText(tb_onsite.getModel().getValueAt(i, 1).toString());
                                    txttimer.setText(tb_onsite.getModel().getValueAt(i, 3).toString());
                        }
            }
            
            private void tb_course(java.awt.event.MouseEvent evt) {                                        
            int i = tb_course.getSelectedRow();
                        if (i >= 0) {
                                    txtcourseID.setText(tb_course.getModel().getValueAt(i, 0).toString());
                        }
            }

    public static void main(String[] args) {
        new onsitecourse();
    }
    
}
