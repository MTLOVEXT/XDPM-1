package UI;

import BLL.Student;
import BUS.StudentBUS;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class UpdateStudent extends JFrame implements ActionListener{
    
    StudentBUS bus = new StudentBUS();
    DefaultTableModel model = new DefaultTableModel();
    
    JTextField txtname,txtfname,crollno;
    JLabel labelrollno;
    JButton submit, cancel;
    JDateChooser Hire, Enroll;
    JTable table;
    
            public void loadstudent() {
                StudentBUS bus = new StudentBUS();
                try {
                    bus.docSV();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Lỗi kết nối đến Database.");
                    return;
                }
                Vector header = new Vector();
                header.add("Student ID");
                header.add("Last Name");
                header.add("First Name");
                header.add("Hire Date");
                header.add("Enrollment Date");
                model = new DefaultTableModel(header, 0) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                showOnTablestudent(bus.list);
            }
            
            public void showOnTablestudent(ArrayList<Student> list) {
                model.setRowCount(0);
                for (Student st : list) {
                    Vector data = setVectorstudent(st);
                    model.addRow(data);
                }
                table.setModel(model);
            }
            
            public Vector setVectorstudent(Student s) {
                Vector head = new Vector();
                head.add(s.getMasv());
                head.add(s.getLastname());
                head.add(s.getFirstname());
                head.add(s.getHireDate());
                head.add(s.getEnrollmentDate());
                return head;
            }
    
            public void setModelValue(Student student, int i) {
                    model.setValueAt(student.getMasv(), i, 0);
                    model.setValueAt(student.getLastname(), i, 1);
                    model.setValueAt(student.getFirstname(), i, 2);
                    table.setModel(model);
            }
    
    UpdateStudent() {        
        setSize(850, 750);
        setLocation(150, 50);
        
        setLayout(null);
        
        JLabel heading = new JLabel("Update Student Details");
        heading.setBounds(50, 10, 500, 50);
        heading.setFont(new Font("Tahoma", Font.ITALIC, 35));
        add(heading);
        
        JLabel lblrollnumber = new JLabel("Select Roll Number");
        lblrollnumber.setBounds(50, 100, 200, 30);
        lblrollnumber.setFont(new Font("serif", Font.PLAIN, 20));
        add(lblrollnumber);
        
        crollno = new JTextField();
        crollno.setBounds(250, 100, 150, 30);
        add(crollno);
        
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(50, 150, 100, 30);
        lblname.setFont(new Font("serif", Font.BOLD, 20));
        add(lblname);
        
        txtname = new JTextField();
        txtname.setBounds(200, 150, 150, 30);
        txtname.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(txtname);
        
        JLabel lblfname = new JLabel("Last Name");
        lblfname.setBounds(400, 150, 200, 30);
        lblfname.setFont(new Font("serif", Font.BOLD, 20));
        add(lblfname);
        
        txtfname = new JTextField();
        txtfname.setBounds(600, 150, 150, 30);
        txtfname.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(txtfname);
        
        JLabel lblrollno = new JLabel("Studentcode");
        lblrollno.setBounds(50, 200, 200, 30);
        lblrollno.setFont(new Font("serif", Font.BOLD, 20));
        add(lblrollno);
        
        labelrollno = new JLabel();
        labelrollno.setBounds(200, 200, 200, 30);
        labelrollno.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(labelrollno);
        
        JLabel lblcourse = new JLabel("HireDate");
        lblcourse.setBounds(50, 250, 200, 30);
        lblcourse.setFont(new Font("serif", Font.BOLD, 20));
        add(lblcourse);
        
        Hire = new JDateChooser();
        Hire.setBounds(200, 250, 150, 30);
        add(Hire);
        
        JLabel lblbranch = new JLabel("EnrollmentDate");
        lblbranch.setBounds(400, 250, 200, 30);
        lblbranch.setFont(new Font("serif", Font.BOLD, 20));
        add(lblbranch);
        
        Enroll = new JDateChooser();
        Enroll.setBounds(600, 250, 150, 30);
        add(Enroll);
        
        submit = new JButton("Update");
        submit.setBounds(250, 300, 120, 30);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(submit);
        
        cancel = new JButton("Cancel");
        cancel.setBounds(450, 300, 120, 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);
        
        table = new JTable();
        loadstudent();
        
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(50, 350, 730, 350);
        add(jsp);
                
                        table.addMouseListener(new java.awt.event.MouseAdapter() {
                                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                tb_st(evt);
                                    }
                        });
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
            if(ae.getSource() == submit) {
                        int i = table.getSelectedRow();
                        java.util.Date selectedDate = Hire.getDate();
                        java.util.Date selecteddcEnrol = Enroll.getDate();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                        String dateHire = dateFormat.format(selectedDate);
                        String dc = dateFormat.format(selecteddcEnrol);
                        Student s = new Student();
                        s.setMasv(crollno.getText());
                        s.setFirstname(txtfname.getText());
                        s.setLastname(txtname.getText());
                        s.setHireDate(dateHire.trim());
                        s.setEnrollmentDate(dc.trim());
                        int check = bus.suaSV(s, i);
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
    
            private void tb_st(java.awt.event.MouseEvent evt) {                                        
                        int i = table.getSelectedRow();
                        
                                    if (i >= 0) {
                                                crollno.setText(table.getModel().getValueAt(i, 0).toString());
                                                txtname.setText(table.getModel().getValueAt(i, 1).toString());
                                                txtfname.setText(table.getModel().getValueAt(i, 2).toString());
                                    }
            }
    
    public static void main(String[] args) {
        new UpdateStudent();
    }
}
