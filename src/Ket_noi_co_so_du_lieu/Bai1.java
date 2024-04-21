package Ket_noi_co_so_du_lieu;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;
public class Bai1 extends JFrame implements ActionListener {
    JLabel lb1, lb2;
    JTextField txt1, txt2;
    JTable table;
    JPanel pn1, pn2, pn3, pn4, pn;
    JButton submit, reset, cancel;
    String tempdata[][] = {{"", "", "", ""}};
    String columns[] = {"ID", "Name", "Address", "Total"};
    Choice ch;
    public void GUI() {
        lb1 = new JLabel("Input Information:");
        lb2 = new JLabel("SQL Query:");

        txt1 = new JTextField();
        txt2 = new JTextField();

        ch = new Choice();
        ch.addItem("SELECT");
        ch.addItem("INSERT");
        ch.addItem("UPDATE");
        ch.addItem("DELETE");

        table = new JTable(tempdata, columns);
        table.setEnabled(false);


        submit = new JButton("Submit");
        reset = new JButton("Reset");
        cancel = new JButton("Cancel");

        submit.addActionListener(this);
        reset.addActionListener(this);
        cancel.addActionListener(this);

        pn1 = new JPanel(new GridLayout(2,2));
        pn1.setBorder(BorderFactory.createEmptyBorder(0,20,0,10));
        pn2 = new JPanel(new GridLayout(1,2));
        pn1.add(lb1);
        pn1.add(txt1);
        pn1.add(lb2);
        pn2.add(txt2);
        pn2.add(ch);
        pn1.add(pn2);

        pn3 = new JPanel(new FlowLayout());
        pn3.add(new JScrollPane(table));

        pn4 = new JPanel(new FlowLayout());
        pn4.add(submit);
        pn4.add(reset);
        pn4.add(cancel);


        pn = new JPanel(new BorderLayout());
        pn.add(pn1, BorderLayout.NORTH);
        pn.add(pn3, BorderLayout.CENTER);
        pn.add(pn4, BorderLayout.SOUTH);

        add(pn);
        setSize(520, 550);
        show();
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==submit) {
            try {
                String url = txt1.getText();
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(url, "root", "");
                Statement stmt = conn.createStatement();
                String sql = txt2.getText();
                if (ch.getSelectedItem().equals("SELECT")) {
                    if (sql.contains("select"))
                    {
                        ResultSet rs = stmt.executeQuery(sql);
                        DefaultTableModel model = new DefaultTableModel();
                        ResultSetMetaData rsmd = rs.getMetaData();
                        int socot = rsmd.getColumnCount();
                        for (int j=1;j<=socot;j++) {
                            model.addColumn(rsmd.getColumnLabel(j));
                        }

                        while (rs.next()) {
                            Object[] row = new Object[socot];
                            for (int i=1;i<=socot;i++) {
                                row[i-1] = rs.getObject(i);
                            }
                            model.addRow(row);
                        }
                        table.setModel(model);
                        rs.close();
                    }
                }
                else if (ch.getSelectedItem().equals("INSERT")) {
                    if (sql.contains("insert"))
                    {
                        stmt.executeUpdate(sql);
                        ResultSet rs = stmt.executeQuery("select * from Table1");
                        DefaultTableModel model = new DefaultTableModel();
                        ResultSetMetaData rsmd = rs.getMetaData();
                        int socot = rsmd.getColumnCount();
                        for (int j=1;j<=socot;j++) {
                            model.addColumn(rsmd.getColumnLabel(j));
                        }

                        while (rs.next()) {
                            Object[] row = new Object[socot];
                            for (int i=1;i<=socot;i++) {
                                row[i-1] = rs.getObject(i);
                            }
                            model.addRow(row);
                        }
                        table.setModel(model);
                        rs.close();
                    }
                }
                else if (ch.getSelectedItem().equals("UPDATE")) {
                    if (sql.contains("update"))
                    {
                        stmt.executeUpdate(sql);
                        ResultSet rs = stmt.executeQuery("select * from Table1");
                        DefaultTableModel model = new DefaultTableModel();
                        ResultSetMetaData rsmd = rs.getMetaData();
                        int socot = rsmd.getColumnCount();
                        for (int j=1;j<=socot;j++) {
                            model.addColumn(rsmd.getColumnLabel(j));
                        }

                        while (rs.next()) {
                            Object[] row = new Object[socot];
                            for (int i=1;i<=socot;i++) {
                                row[i-1] = rs.getObject(i);
                            }
                            model.addRow(row);
                        }
                        table.setModel(model);
                        rs.close();
                    }
                }
                else if (ch.getSelectedItem().equals("DELETE")) {
                    if (sql.contains("delete"))
                    {
                        stmt.executeUpdate(sql);
                        ResultSet rs = stmt.executeQuery("select * from Table1");
                        DefaultTableModel model = new DefaultTableModel();
                        ResultSetMetaData rsmd = rs.getMetaData();
                        int socot = rsmd.getColumnCount();
                        for (int j=1;j<=socot;j++) {
                            model.addColumn(rsmd.getColumnLabel(j));
                        }

                        while (rs.next()) {
                            Object[] row = new Object[socot];
                            for (int i=1;i<=socot;i++) {
                                row[i-1] = rs.getObject(i);
                            }
                            model.addRow(row);
                        }
                        table.setModel(model);
                        rs.close();
                    }
                }
                stmt.close();
                conn.close();

            }
            catch (Exception a) {
                a.printStackTrace();
            }
        }
        if (e.getSource() == reset) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
            model.setColumnIdentifiers(new Object[] {"ID", "Name", "Address","Total"});
            model.addRow(new Object[] {"", "", "", ""});
        }
        if (e.getSource() == cancel) {
            System.exit(0);
        }

    }
    public Bai1(String st) {
        super(st);
        GUI();
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
    public static void main(String[] args) {
        new Bai1("CSDL");
    }

}


