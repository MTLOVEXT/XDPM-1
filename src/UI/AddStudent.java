package UI;

import BLL.Student;
import BUS.StudentBUS;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;

public class AddStudent extends JFrame implements ActionListener{
    StudentBUS bus = new StudentBUS();
    
    JTextField fname, lname;
    JTextField txtmmasv;
    JButton submit, cancel;
    JDateChooser hiredate,dcEnrol;
    
    Random ran = new Random();
    long first4 = Math.abs((ran.nextLong() % 9000L) + 1000L);
    public boolean checkSV = false;
    
    private void initData(){
        txtmmasv.setEditable(false);
    }
    
    private Vector setVector(Student sv){
            Vector head = new Vector();
            head.add(sv.getMasv());
            head.add(sv.getLastname());
            head.add(sv.getFirstname());
            head.add(sv.getHireDate());
            head.add(sv.getEnrollmentDate());
            return head;
    }    
    
    AddStudent() {
        
        setSize(900, 450);
        setLocation(350, 50);
        
        setLayout(null);
        
        JLabel heading = new JLabel("New Student Details");
        heading.setBounds(310, 30, 500, 50);
        heading.setFont(new Font("serif", Font.BOLD, 30));
        add(heading);
        
        JLabel lblname = new JLabel("First Name");
        lblname.setBounds(50, 150, 100, 30);
        lblname.setFont(new Font("serif", Font.BOLD, 20));
        add(lblname);
        
        fname = new JTextField();
        fname.setBounds(200, 150, 150, 30);
        add(fname);
        
        JLabel lblfname = new JLabel("Last Name");
        lblfname.setBounds(400, 150, 200, 30);
        lblfname.setFont(new Font("serif", Font.BOLD, 20));
        add(lblfname);
        
        lname = new JTextField();
        lname.setBounds(600, 150, 150, 30);
        add(lname);
        
        JLabel lblrollno = new JLabel("Roll Number");
        lblrollno.setBounds(50, 200, 200, 30);
        lblrollno.setFont(new Font("serif", Font.BOLD, 20));
        add(lblrollno);
        
        txtmmasv = new JTextField("312041"+first4);
        txtmmasv.setBounds(200, 200, 110, 30);
        txtmmasv.setFont(new Font("serif", Font.BOLD, 20));
        add(txtmmasv);
        
        JLabel lbldob = new JLabel("HireDate");
        lbldob.setBounds(400, 200, 200, 30);
        lbldob.setFont(new Font("serif", Font.BOLD, 20));
        add(lbldob);
        
        hiredate = new JDateChooser();
        hiredate.setBounds(600, 200, 150, 30);
        hiredate.setFont(new Font("serif", Font.BOLD, 20));
        add(hiredate);
        
        JLabel lbladdress = new JLabel("EnrollmentDate");
        lbladdress.setBounds(50, 250, 200, 30);
        lbladdress.setFont(new Font("serif", Font.BOLD, 20));
        add(lbladdress);
        
        dcEnrol = new JDateChooser();
        dcEnrol.setBounds(200, 250, 150, 30);
        dcEnrol.setFont(new Font("serif", Font.BOLD, 20));
        add(dcEnrol);
        
        submit = new JButton("Add");
        submit.setBounds(250, 350, 120, 30);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(submit);
        
        cancel = new JButton("Cancel");
        cancel.setBounds(550, 350, 120, 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);
        
        initData();
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            Date selectedDate = hiredate.getDate();
            Date selecteddcEnrol = dcEnrol.getDate();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String dateHire = dateFormat.format(selectedDate);
            String dc = dateFormat.format(selecteddcEnrol);
            Student sv = new Student();
            sv.setMasv(txtmmasv.getText().trim());
            sv.setLastname(lname.getText().trim());
            sv.setFirstname(fname.getText().trim());
            sv.setHireDate(dateHire.trim());
            sv.setEnrollmentDate(dc.trim());
            Vector head = setVector(sv);
            int check = bus.themSV(sv);
            if(check == 1){ 
                JOptionPane.showMessageDialog(null, "Thêm thành công");
                setVisible(false);
                }else{JOptionPane.showMessageDialog(null, "Mã đã tồn tại. Thêm thất bại");
                setVisible(false);
            }}else { 
            setVisible(false);
        }
    }
    
    public static void main(String[] args) {
        new AddStudent();
    }
}
