package UI;

import BLL.Course;
import BLL.OnsiteCourse;
import BUS.CourseBUS;
import BUS.OnsiteCourseBUS;
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
    
            ArrayList<OnsiteCourse> arr = new ArrayList<OnsiteCourse>();
            ArrayList<OnsiteCourse> tempsearch = new ArrayList<OnsiteCourse>();
            
            JTextField tfname,txtlocaltion,txttimer,txtcourseID,txtsearch; 
            JButton btnaddButton,btncancel,btnsubmit,btnsearch,delete;
            JTable tb_onsite,tb_course;
            JComboBox cbdays, cbsearch,cbgio,cbphut;
            String hour ,minute;
            
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
        lblday.setBounds(50, 150, 100, 45);
        lblday.setFont(new Font("serif", Font.BOLD, 20));
        add(lblday);
        
        String course[] = {"T2,T4,T6", "T3,T5,T7","T6,CN","T5","T6","T7","CN"};
        cbdays = new JComboBox(course);
        cbdays.setBounds(135, 150, 150, 45);
        cbdays.setFont(new Font("serif", Font.BOLD, 20));
        cbdays.setBackground(Color.WHITE);
        add(cbdays);
        
        JLabel lbltime = new JLabel("Time");
        lbltime.setBounds(300, 150, 100, 45);
        lbltime.setFont(new Font("serif", Font.BOLD, 20));
        add(lbltime);
        
        String[] hours = new String[24];
        for (int i = 0; i < 24; i++) {
            hours[i] = String.format("%02d", i);
        }
        cbgio = new JComboBox(hours);
        cbgio.setBounds(400, 150, 60, 35);
        cbgio.setFont(new Font("serif", Font.BOLD, 20));
        cbgio.setBackground(Color.WHITE);
        add(cbgio);
        
        String[] minutes = new String[60];
        for (int i = 0; i < 60; i++) {
            minutes[i] = String.format("%02d", i);
        }
        cbphut = new JComboBox(minutes);
        cbphut.setBounds(470, 150, 60, 35);
        cbphut.setFont(new Font("serif", Font.BOLD, 20));
        cbphut.setBackground(Color.WHITE);
        add(cbphut);
        
        cbsearch = new JComboBox();
        cbsearch.setBounds(100, 200, 150, 50);
        cbsearch.addItem("CourseID");
        cbsearch.addItem("Location");
        cbsearch.addItem("Days");
        cbsearch.addItem("Time");
        cbsearch.setFont(new Font("serif", Font.BOLD, 20));
        add(cbsearch);
        
        txtsearch = new JTextField();
        txtsearch.setBounds(260, 200, 150, 50);
        txtsearch.setFont(new Font("serif", Font.BOLD, 20));
        add(txtsearch);
        
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
        
        delete = new JButton("Delete");
        delete.setBounds(650, 280, 100, 50);
        delete.addActionListener(this);
        delete.setFont(new Font("serif", Font.BOLD, 20));
        add(delete);
        
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
            if (ae.getSource() == btnsearch) {
                        String[] header = {"CourseID", "Location","Days","Time"};
                        DefaultTableModel modelsearch = new DefaultTableModel(header, 0);
                        ArrayList<OnsiteCourse> st;
                        st = busonsite.timkiem(String.valueOf(cbsearch.getSelectedItem()), txtsearch.getText().toLowerCase().trim());
                        if (st.size() != 0) {
                            for (int i = 0; i < st.size(); i++) {
                                Object[] row = {st.get(i).getCourseID(), st.get(i).getLocation(), st.get(i).getDays(), st.get(i).getTime()
                                };
                                modelsearch.addRow(row);
                            }
                            tempsearch.addAll(arr);
                            arr.clear();
                            arr.addAll(st);
                            tb_onsite.setModel(modelsearch);
                        } else {
                                JOptionPane.showMessageDialog(null, "Không có kết quả phù hợp!");
                        }
            }
            else if (ae.getSource() == btnaddButton) {
                            hour = (String) cbgio.getSelectedItem();
                            minute = (String) cbphut.getSelectedItem();
                            String time = (hour + ":" +minute + ":00");
                            OnsiteCourse cs = new OnsiteCourse();
                            cs.setCourseID(txtcourseID.getText());
                            cs.setLocation(txtlocaltion.getText());
                            cs.setDays((String) cbdays.getSelectedItem());
                            cs.setTime(time);
                            int check = busonsite.them(cs);
                            if(check == 1){ 
                                JOptionPane.showMessageDialog(null, "Thêm thành công");
                                setVisible(false);
                                }else{JOptionPane.showMessageDialog(null, "Thêm thất bại");
                                setVisible(false);
                            }
            }
            else if (ae.getSource() == delete) {
                        int i = tb_onsite.getSelectedRow();
                        String ID = tb_onsite.getValueAt(i, 0).toString();
                        int option = JOptionPane.showConfirmDialog(null, "Xác nhận xóa khóa học này");
                        if(option == JOptionPane.YES_OPTION) {
                                    int check = busonsite.xoa(ID, i);
                                    if(check ==1 ){
                                                JOptionPane.showMessageDialog(null, "Xóa thành công");
                                                setVisible(false);
                                    }else   JOptionPane.showMessageDialog(null, "Xóa thất bại");
                                                setVisible(false);
                        }else setVisible(false);
            }
            else if(ae.getSource() == btnsubmit) {
                        hour = (String) cbgio.getSelectedItem();
                        minute = (String) cbphut.getSelectedItem();
                        String time = (hour + ":" +minute + ":00");
                        int i = tb_onsite.getSelectedRow();
                        OnsiteCourse s = new OnsiteCourse();
                        s.setCourseID(txtcourseID.getText());
                        s.setLocation(txtlocaltion.getText());
                        s.setDays((String) cbdays.getSelectedItem());
                        s.setTime(time);
                        int check = busonsite.sua(s, i);
                        if (check == 1) {
                            setModelValue(s, i);
                            JOptionPane.showMessageDialog(null, "Sửa thành công");
                        }
                        else {
                                    JOptionPane.showMessageDialog(null, "Sửa thất bại");
                                    setVisible(false);
                        }
            } else {
                        setVisible(false);
            }
    }
    
            private void tb(java.awt.event.MouseEvent evt) {                                        
            int i = tb_onsite.getSelectedRow();
                        if (i >= 0) {
                                    txtcourseID.setText(tb_onsite.getModel().getValueAt(i, 0).toString());
                                    txtlocaltion.setText(tb_onsite.getModel().getValueAt(i, 1).toString());
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
