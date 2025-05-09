/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.wxm.student_data_management_system;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author PC
 */
public class studentData_main extends javax.swing.JFrame {

    private static Connection conn = null;

    private static final String URL = "jdbc:mysql://localhost:3306/student_data";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    DefaultTableModel model;
    
    /**
     * Creates new form studentData_main
     */
    public studentData_main() {
        initComponents();
        connect();
        model = (DefaultTableModel) tblStudents.getModel();
        genderGroup = new ButtonGroup();
        genderGroup.add(rbtnMale);
        genderGroup.add(rbtnFemale);
        loadDB();
        addFont();
    }
    
    private void connect() {
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection established successfully.");
            
            // Add a shutdown hook to close the connection when the program exits
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    if (conn != null && !conn.isClosed()) {
                        conn.close();
                        System.out.println("Connection closed successfully!");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }));
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
            e.printStackTrace();
        } 
    }
    
    public void loadDB() {
        DefaultTableModel model = (DefaultTableModel) tblStudents.getModel();

        model.setRowCount(0); // Clear existing rows

        try {
            String sql = "SELECT * FROM data";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("student_id");
                String name = rs.getString("student_name");
                int age = rs.getInt("student_age");
                String gender = rs.getString("student_gender");
                String address = rs.getString("student_address");
                String contact = rs.getString("student_contact");
                String college = rs.getString("student_college");
                String program = rs.getString("student_program");

                model.addRow(new Object[]{id, name, age, gender, address, contact, college, program});
            }
            
            int[] wrapColumns = {0, 1, 2, 3, 4, 5, 6, 7}; // Name, Address, College, Program

            for (int col : wrapColumns) {
                tblStudents.getColumnModel().getColumn(col).setCellRenderer(new TextAreaRenderer());
            }

            // ✅ Do this after loading data into the table
            adjustRowHeights(tblStudents, wrapColumns);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage());
        }
    }
    
    private void adjustRowHeights(JTable table, int[] wrapCols) {
        for (int row = 0; row < table.getRowCount(); row++) {
            int maxHeight = table.getRowHeight(); // or start from 16

            for (int col : wrapCols) {
                TableCellRenderer renderer = table.getCellRenderer(row, col);
                Component comp = renderer.getTableCellRendererComponent(
                    table, table.getValueAt(row, col), false, false, row, col
                );

                // 🔑 This forces proper line wrapping
                comp.setBounds(0, 0, table.getColumnModel().getColumn(col).getWidth(), Integer.MAX_VALUE);

                Dimension prefSize = comp.getPreferredSize();
                maxHeight = Math.max(maxHeight, prefSize.height);
            }

            if (table.getRowHeight(row) != maxHeight) {
                table.setRowHeight(row, maxHeight);
            }
        }
    }

    
    private void addFont() {
        try {
            // Load font from resources
            InputStream is1 = getClass().getResourceAsStream("/fonts/simsun.ttf");
            Font simsun = Font.createFont(Font.TRUETYPE_FONT, is1).deriveFont(12f); // 12f = font size

            InputStream is2 = getClass().getResourceAsStream("/fonts/rockeb.ttf");
            Font rockeb = Font.createFont(Font.TRUETYPE_FONT, is2).deriveFont(12f); // 12f = font size
            
            InputStream is3 = getClass().getResourceAsStream("/fonts/showg.ttf");
            Font showg = Font.createFont(Font.TRUETYPE_FONT, is3).deriveFont(12f); // 12f = font size
            
            // Register the font
            GraphicsEnvironment ge1 = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge1.registerFont(simsun);
            ge1.registerFont(rockeb);
            ge1.registerFont(showg);

            // Apply the font to a component
            jLabel1.setFont(showg);
            setLabelFontSize(jLabel1, 24f); //override font size
            jLabel9.setFont(simsun);
            jLabel10.setFont(simsun);
            jLabel11.setFont(simsun);
            jLabel12.setFont(simsun);
            jLabel13.setFont(showg);
            setLabelFontSize(jLabel13, 36f); //override font size
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }
    
    public void setLabelFontSize(JLabel label, float size) {
        label.setFont(label.getFont().deriveFont(size));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        genderGroup = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblStudents = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        delete_button = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        rbtnMale = new javax.swing.JRadioButton();
        rbtnFemale = new javax.swing.JRadioButton();
        txtName = new javax.swing.JTextField();
        txtAge = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        txtContact = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cbProgram = new javax.swing.JComboBox<>();
        save_button = new javax.swing.JButton();
        cbCollege = new javax.swing.JComboBox<>();
        update_button = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        clear_button = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1280, 600));
        setResizable(false);
        setSize(new java.awt.Dimension(1280, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        tblStudents.setBackground(new java.awt.Color(0, 0, 0));
        tblStudents.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        tblStudents.setForeground(new java.awt.Color(255, 255, 255));
        tblStudents.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Age", "Gender", "Address", "Contact Number", "College", "Program"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblStudents.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblStudents.setRowHeight(30);
        tblStudents.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblStudents.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblStudents.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblStudentsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblStudents);

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Student Information");
        jLabel13.setToolTipText("");
        jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        delete_button.setBackground(new java.awt.Color(153, 0, 0));
        delete_button.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 12)); // NOI18N
        delete_button.setForeground(new java.awt.Color(255, 255, 255));
        delete_button.setText("DELETE");
        delete_button.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        delete_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 865, Short.MAX_VALUE))
                    .addComponent(delete_button, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(delete_button, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 0, 900, 600));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Student Data");
        jLabel1.setToolTipText("");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Name: ");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Age:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Address:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Contact Number:");

        genderGroup.add(rbtnMale);
        rbtnMale.setText("Male");
        rbtnMale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnMaleActionPerformed(evt);
            }
        });

        genderGroup.add(rbtnFemale);
        rbtnFemale.setText("Female");
        rbtnFemale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnFemaleActionPerformed(evt);
            }
        });

        txtName.setBackground(new java.awt.Color(0, 0, 0));
        txtName.setForeground(new java.awt.Color(255, 255, 255));

        txtAge.setBackground(new java.awt.Color(0, 0, 0));
        txtAge.setForeground(new java.awt.Color(255, 255, 255));
        txtAge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAgeActionPerformed(evt);
            }
        });

        txtAddress.setBackground(new java.awt.Color(0, 0, 0));
        txtAddress.setForeground(new java.awt.Color(255, 255, 255));

        txtContact.setBackground(new java.awt.Color(0, 0, 0));
        txtContact.setForeground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Gender:");

        cbProgram.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbProgramActionPerformed(evt);
            }
        });

        save_button.setBackground(new java.awt.Color(51, 153, 0));
        save_button.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 12)); // NOI18N
        save_button.setForeground(new java.awt.Color(255, 255, 255));
        save_button.setText("SAVE");
        save_button.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        save_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_buttonActionPerformed(evt);
            }
        });

        cbCollege.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CTECH", "CTE", "COM", "COF" }));
        cbCollege.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCollegeActionPerformed(evt);
            }
        });

        update_button.setBackground(new java.awt.Color(0, 51, 153));
        update_button.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 12)); // NOI18N
        update_button.setForeground(new java.awt.Color(255, 255, 255));
        update_button.setText("UPDATE");
        update_button.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        update_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_buttonActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("College:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Program:");

        jLabel9.setText("Activity Group Members:");

        jLabel10.setText("Whelzcy Mark M. Laniba");

        jLabel11.setText("Armando C. Crusat Jr.");

        jLabel12.setText("Iori John M. Bernardez");

        clear_button.setBackground(new java.awt.Color(51, 51, 51));
        clear_button.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 12)); // NOI18N
        clear_button.setForeground(new java.awt.Color(255, 255, 255));
        clear_button.setText("CLEAR");
        clear_button.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        clear_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clear_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12)))
                    .addComponent(jLabel9)
                    .addComponent(jLabel1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtAge, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                            .addComponent(txtAddress)
                            .addComponent(txtContact)
                            .addComponent(txtName)))
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addComponent(save_button, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(update_button, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(112, 112, 112)
                            .addComponent(clear_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addGap(111, 111, 111)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(rbtnMale)
                                    .addGap(18, 18, 18)
                                    .addComponent(rbtnFemale))
                                .addComponent(cbProgram, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbCollege, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(rbtnMale)
                    .addComponent(rbtnFemale))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbCollege, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbProgram, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(save_button, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(update_button, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clear_button, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addContainerGap(81, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 600));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    private void txtAgeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAgeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAgeActionPerformed

    private void rbtnMaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnMaleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbtnMaleActionPerformed

    private void rbtnFemaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnFemaleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbtnFemaleActionPerformed

    private void clear_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clear_buttonActionPerformed
        clearInputs();
    }//GEN-LAST:event_clear_buttonActionPerformed

    private void cbCollegeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCollegeActionPerformed
        String selectedCollege = cbCollege.getSelectedItem().toString();
        cbProgram.removeAllItems();

        switch (selectedCollege) {
            case "CTECH":
                cbProgram.addItem("BSCS");
                cbProgram.addItem("BSIT-ELEC");
                cbProgram.addItem("BSIT-FPST");
                break;
            case "CTE":
                cbProgram.addItem("BSED-MATH");
                cbProgram.addItem("BSED-ENGLISH");
                cbProgram.addItem("BEED");
                break;
            case "COM":
                cbProgram.addItem("BSM");
                break;
            case "COF":
                cbProgram.addItem("BSF");
                break;
        }
    }//GEN-LAST:event_cbCollegeActionPerformed

    private void save_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_buttonActionPerformed
        savetoDB();
    }//GEN-LAST:event_save_buttonActionPerformed

    private void cbProgramActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbProgramActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbProgramActionPerformed

    private void update_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_buttonActionPerformed
        updatetoDB();
    }//GEN-LAST:event_update_buttonActionPerformed

    private void delete_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_buttonActionPerformed
        deletefromDB();
    }//GEN-LAST:event_delete_buttonActionPerformed

    private void tblStudentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblStudentsMouseClicked
        getDBinputs();
    }//GEN-LAST:event_tblStudentsMouseClicked

    private void savetoDB() {
        String name = txtName.getText().trim();
        String ageStr = txtAge.getText().trim();
        String address = txtAddress.getText().trim();
        String contact = txtContact.getText().trim();
        String college = (String) cbCollege.getSelectedItem();
        String program = (String) cbProgram.getSelectedItem();
        String gender = rbtnMale.isSelected() ? "Male" : (rbtnFemale.isSelected() ? "Female" : null);

        // Validation
        if (name.isEmpty() || ageStr.isEmpty() || address.isEmpty() || contact.isEmpty()
                || college == null || program == null || gender == null) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return;
        }

        int age;
        try {
            age = Integer.parseInt(ageStr);
            if (age <= 0 || age > 120) {
                JOptionPane.showMessageDialog(this, "Please enter a valid age.");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Age must be a number.");
            return;
        }

        if (!contact.matches("\\d{7,11}")) {
            JOptionPane.showMessageDialog(this, "Contact must be 7 to 11 digits.");
            return;
        }

        try {
            String sql = "INSERT INTO data (student_name, student_age, student_gender, student_address, student_contact, student_college, student_program) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, gender);
            ps.setString(4, address);
            ps.setString(5, contact);
            ps.setString(6, college);
            ps.setString(7, program);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Student saved to database!");

            loadDB();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error saving to DB: " + e.getMessage());
        }
    }
    
    private void updatetoDB() {
        int row = tblStudents.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student to update.");
            return;
        }

        // Get form inputs
        String name = txtName.getText().trim();
        String ageStr = txtAge.getText().trim();
        String address = txtAddress.getText().trim();
        String contact = txtContact.getText().trim();
        String college = (String) cbCollege.getSelectedItem();
        String program = (String) cbProgram.getSelectedItem();
        String gender = rbtnMale.isSelected() ? "Male" : (rbtnFemale.isSelected() ? "Female" : null);

        // Empty validation
        if (name.isEmpty() || ageStr.isEmpty() || address.isEmpty() || contact.isEmpty()
                || college == null || program == null || gender == null) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return;
        }

        // Validate age and contact
        int age;
        try {
            age = Integer.parseInt(ageStr);
            if (age <= 0 || age > 120) {
                JOptionPane.showMessageDialog(this, "Please enter a valid age.");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Age must be a number.");
            return;
        }

        if (!contact.matches("\\d{7,15}")) {
            JOptionPane.showMessageDialog(this, "Contact must be 7 to 15 digits.");
            return;
        }

        // Compare with original data
        DefaultTableModel model = (DefaultTableModel) tblStudents.getModel();
        String origName = model.getValueAt(row, 1).toString();
        String origAge = model.getValueAt(row, 2).toString();
        String origGender = model.getValueAt(row, 3).toString();
        String origAddress = model.getValueAt(row, 4).toString();
        String origContact = model.getValueAt(row, 5).toString();
        String origCollege = model.getValueAt(row, 6).toString();
        String origProgram = model.getValueAt(row, 7).toString();

        if (name.equals(origName) &&
            ageStr.equals(origAge) &&
            gender.equals(origGender) &&
            address.equals(origAddress) &&
            contact.equals(origContact) &&
            college.equals(origCollege) &&
            program.equals(origProgram)) {

            JOptionPane.showMessageDialog(this, "No changes detected.");
            return;
        }

        // Perform update
        try {
            int id = Integer.parseInt(model.getValueAt(row, 0).toString());
            String sql = "UPDATE data SET student_name=?, student_age=?, student_gender=?, student_address=?, student_contact=?, student_college=?, student_program=? WHERE student_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, gender);
            ps.setString(4, address);
            ps.setString(5, contact);
            ps.setString(6, college);
            ps.setString(7, program);
            ps.setInt(8, id);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Student updated in database!");

            loadDB();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error updating DB: " + e.getMessage());
        }
    }

    
    private void clearInputs() {
        txtName.setText("");
        txtAge.setText("");
        txtAddress.setText("");
        txtContact.setText("");
        genderGroup.clearSelection();
        cbCollege.setSelectedIndex(0);
        cbProgram.removeAllItems();
    }
    
    private void deletefromDB() {
        int row = tblStudents.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student to delete.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete this student?", 
            "Confirm Delete", 
            JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            int id = Integer.parseInt(tblStudents.getValueAt(row, 0).toString());

            String sql = "DELETE FROM data WHERE student_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Student deleted from database!");
            loadDB();
            clearInputs(); // Optional: Clear fields after deletion
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid ID format.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error deleting from DB: " + e.getMessage());
        }
    }

    
    private void getDBinputs() {
        int row = tblStudents.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) tblStudents.getModel();

        // Fill input fields
        txtName.setText(model.getValueAt(row, 1).toString());
        txtAge.setText(model.getValueAt(row, 2).toString());

        String gender = model.getValueAt(row, 3).toString();
        if (gender.equalsIgnoreCase("Male")) {
            rbtnMale.setSelected(true);
        } else if (gender.equalsIgnoreCase("Female")) {
            rbtnFemale.setSelected(true);
        }

        txtAddress.setText(model.getValueAt(row, 4).toString());
        txtContact.setText(model.getValueAt(row, 5).toString());

        cbCollege.setSelectedItem(model.getValueAt(row, 6).toString());

        // Update cbProgram options based on selected college
        cbProgram.removeAllItems();
        String selectedCollege = cbCollege.getSelectedItem().toString();
        switch (selectedCollege) {
            case "CTECH":
                cbProgram.addItem("BSCS");
                cbProgram.addItem("BSIT-ELEC");
                cbProgram.addItem("BSIT-FPST");
                break;
            case "CTE":
                cbProgram.addItem("BSED-MATH");
                cbProgram.addItem("BSED-ENGLISH");
                cbProgram.addItem("BEED");
                break;
            case "COM":
                cbProgram.addItem("BSM");
                break;
            case "COF":
                cbProgram.addItem("BSF");
                break;
        }

        cbProgram.setSelectedItem(model.getValueAt(row, 7).toString());
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(studentData_main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(studentData_main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(studentData_main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(studentData_main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new studentData_main().setVisible(true);
            }
        });
    }
    
    class TextAreaRenderer extends JTextArea implements TableCellRenderer {

        public TextAreaRenderer() {
            setLineWrap(true);
            setWrapStyleWord(true);
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {

            setText(value == null ? "" : value.toString());
            setSize(table.getColumnModel().getColumn(column).getWidth(), Short.MAX_VALUE);

            if (isSelected) {
                setForeground(table.getSelectionForeground());
                setBackground(table.getSelectionBackground());
            } else {
                setForeground(table.getForeground());
                setBackground(table.getBackground());
            }

            setFont(table.getFont());
            return this;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbCollege;
    private javax.swing.JComboBox<String> cbProgram;
    private javax.swing.JButton clear_button;
    private javax.swing.JButton delete_button;
    private javax.swing.ButtonGroup genderGroup;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbtnFemale;
    private javax.swing.JRadioButton rbtnMale;
    private javax.swing.JButton save_button;
    private javax.swing.JTable tblStudents;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtAge;
    private javax.swing.JTextField txtContact;
    private javax.swing.JTextField txtName;
    private javax.swing.JButton update_button;
    // End of variables declaration//GEN-END:variables
}
