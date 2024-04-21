package Ket_noi_co_so_du_lieu;
import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

public class Bai2 extends JFrame implements ActionListener
{
    private static final long serialVersionUID = 1L;
    JFrame mainFrame;
    JLabel label1, label2;
    JButton btnSearch, btnReset, btnExit;
    JTextField txtSearch;
    JRadioButton[] radioBtn = new JRadioButton[5];
    JTable myTb;
    JScrollPane scroll;
    JPanel pn1, pn2, pn3, pn4;
    public void GUI()
    {
        mainFrame = new JFrame("SQL Searching");
        mainFrame.setLayout(new FlowLayout());
        mainFrame.setSize(500,600);

        label1 = new JLabel("Nhap noi dung");
        label2 = new JLabel("Tim kiem theo");

        btnSearch = new JButton("Search");
        btnReset = new JButton("Reset");
        btnExit = new JButton("Exit");

        txtSearch = new JTextField(10);
        btnSearch.addActionListener(this);
        btnReset.addActionListener(this);
        btnExit.addActionListener(this);

        ButtonGroup bg = new ButtonGroup();
        String[] radioName = {"ID", "Name","Birthday","Address","Gender"};
        for (int i=0;i<5;i++)
        {
            radioBtn[i] = new JRadioButton();
            radioBtn[i].setText(radioName[i]);
            bg.add(radioBtn[i]);
        }

        String[] header = {"ID", "Name","Birthday","Address","Gender"};
        String[][] data = {{"","","","", ""}};
        myTb = new JTable(data,header);
        scroll = new JScrollPane(myTb);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        pn1 = new JPanel(new FlowLayout());
        pn2 = new JPanel(new FlowLayout());
        pn3 = new JPanel(new FlowLayout());
        pn4 = new JPanel(new FlowLayout());

        pn2.add(label1);
        pn2.add(txtSearch);
        pn2.add(btnSearch);
        pn2.add(btnReset);
        pn2.add(btnExit);

        pn3.add(label2);
        for (int i=0;i<5;i++) pn3.add(radioBtn[i]);

        pn4.add(scroll);
        mainFrame.add(pn1);
        mainFrame.add(pn2);
        mainFrame.add(pn3);
        mainFrame.add(scroll);
        mainFrame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });
        mainFrame.setVisible(true);
    }
    public void createTable()
    {
        Vector header = new Vector();
        Vector data = new Vector();

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con  = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Java1", "root", "");
            Statement stmt = con.createStatement();
            String sql = "select * from Table2";
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columns = rsmd.getColumnCount();
            for (int i=1;i<=columns;i++)
            {
                header.add(rsmd.getColumnLabel(i));
            }
            int index = 0;
            for (int i=0;i<5;i++)
            {
                if (radioBtn[i].isSelected()) index = i+1;
            }
            String text = txtSearch.getText();
            while (rs.next())
            {
                Vector rows = new Vector<>();

                String t = rs.getObject(index).toString();
                if (t.contains(text))
                {
                    for (int i=1;i<=columns;i++)
                    {
                        rows.add(rs.getObject(i));
                    }
                    data.add(rows);
                }
            }
            rs.close();
            stmt.close();
        }
        catch(Exception e) {
            System.out.println("Error " + e);
        }
        myTb.setModel(new DefaultTableModel(data,header));
    }
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == btnSearch)
        {
            createTable();
        }
        if (e.getSource() == btnExit)
        {
            System.exit(0);
        }
        if (e.getSource() == btnReset)
        {
            for (int i=0;i<4;i++)
            {
                radioBtn[i].setSelected(false);
            }
            txtSearch.setText("");
            DefaultTableModel model = (DefaultTableModel)myTb.getModel();
            model.setRowCount(0);

        }
    }
    public  Bai2()
    {
        GUI();
    }
    public static void main(String[] args)
    {
        new Bai2();
    }
}
