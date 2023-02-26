package UI;

import BLL.Student;
import BUS.StudentBUS;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class StudentDetails extends JFrame implements ActionListener {

    DefaultTableModel model = new DefaultTableModel();
    StudentBUS bus = new StudentBUS();
    
    ArrayList<Student> arr = new ArrayList<Student>();
    ArrayList<Student> tempsearch = new ArrayList<Student>();

    JComboBox mssv;
    JTextField txtmssv;
    JTable table;
    JButton search, print, update, add, cancel,xoa;
    
    private void load(){
        StudentBUS bus = new StudentBUS();       
        try{
           bus.docSV();
       }catch(Exception e){
           JOptionPane.showMessageDialog(null, "Lỗi kết nối đến Database.");
           return;
       }
       Vector header = new Vector();
        header.add("PersonID");
        header.add("Lastname");
        header.add("Firstname");
        header.add("HireDate");
        header.add("EnrollmentDate");
            model = new DefaultTableModel(header,0){
            public boolean isCellEditable(int row, int column)
                {
                  return false;
                }
       };	
       showOnTable(bus.list);
    }
    
    private void showOnTable(ArrayList<Student> list){
        model.setRowCount(0);
        for(Student sv:list){
           Vector data = setVector(sv);
           model.addRow(data);
       }
       table.setModel(model);
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
    
    StudentDetails() {
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        mssv = new JComboBox();
        mssv.addItem("MSSV");
        mssv.setBounds(20, 20, 150, 20);
        add(mssv);
        
        txtmssv = new JTextField();
        txtmssv.setBounds(200, 20, 150, 20);
        add(txtmssv);
        
        table = new JTable();
        
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 100, 900, 600);
        add(jsp);
        
        search = new JButton("Search");
        search.setBounds(20, 70, 80, 20);
        search.addActionListener(this);
        add(search);
        
        print = new JButton("Print");
        print.setBounds(120, 70, 80, 20);
        print.addActionListener(this);
        add(print);
        
        add = new JButton("Add");
        add.setBounds(220, 70, 80, 20);
        add.addActionListener(this);
        add(add);
        
        update = new JButton("Update");
        update.setBounds(320, 70, 80, 20);
        update.addActionListener(this);
        add(update);
        
        cancel = new JButton("Cancel");
        cancel.setBounds(420, 70, 80, 20);
        cancel.addActionListener(this);
        add(cancel);
        
        xoa = new JButton("Delete");
        xoa.setBounds(520, 70, 80, 20);
        xoa.addActionListener(this);
        add(xoa);
        
        setSize(900, 700);
        setLocation(300, 100);
        
        load();
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == search) {
                 String[] header = {"MSSV","LastName","FirstName","HireDate","EnrollmentDate"};
                 DefaultTableModel modelsearch = new DefaultTableModel(header, 0);
                 ArrayList<Student> s;
                 s = bus.timkiem(String.valueOf(mssv.getSelectedItem()), txtmssv.getText().toLowerCase().trim());
                 if (s.size() != 0) {
                     for (int i = 0; i < s.size(); i++) {
                         Object[] row = {s.get(i).getMasv(), s.get(i).getLastname(), s.get(i).getFirstname(), s.get(i).getHireDate(), s.get(i).getEnrollmentDate()
                         };
                         modelsearch.addRow(row);
                     }
                     tempsearch.addAll(arr);
                     arr.clear();
                     arr.addAll(s);

                     table.setModel(modelsearch);
                 } else {
                     JOptionPane.showMessageDialog(null, "Không có kết quả phù hợp!");
                 }
            }else if (ae.getSource() == print) {
            try {
                table.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == xoa) {
            int i = table.getSelectedRow();
            String ID = table.getValueAt(i, 0).toString();
            int option = JOptionPane.showConfirmDialog(null, "Xác nhận xóa sinh viên này");
            if(option == JOptionPane.YES_OPTION) {
                        int check = bus.xoasv(ID, i);
                        if(check ==1 ){
                                    JOptionPane.showMessageDialog(null, "Xóa thành công");
                                    setVisible(false);
                        }else   JOptionPane.showMessageDialog(null, "Xóa thất bại");
                        setVisible(false);
            }else setVisible(false);
        }else if (ae.getSource() == add) {
            setVisible(false);
            new AddStudent();
        } else if (ae.getSource() == update) {
            setVisible(false);
            new UpdateStudent();
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new StudentDetails();
    }
}
