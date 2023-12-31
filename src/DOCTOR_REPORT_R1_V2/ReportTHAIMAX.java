/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package DOCTOR_REPORT_R1_V2;

import static DOCTOR_REPORT_R1_V2.LoginMain.LoginCono;
import static DOCTOR_REPORT_R1_V2.ReportDoctorLocal.year;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Jilasak
 */
public class ReportTHAIMAX extends javax.swing.JFrame {

    /**
     * Creates new form ReportTHAIMAX
     */
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH); //คศ
    SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH); //คศ

    public ReportTHAIMAX() {
        initComponents();
        this.setTitle("Company  " + LoginMain.LoginCompanyName);
        lblCompanyName.setText(LoginMain.LoginCompanyName);
        jXDate.setFormats(format);
        jXDate.setDate(new Date());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblCompanyName = new java.awt.Label();
        txt_savNo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jXDate = new org.jdesktop.swingx.JXDatePicker();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblCompanyName.setAlignment(java.awt.Label.CENTER);
        lblCompanyName.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblCompanyName.setForeground(new java.awt.Color(204, 0, 0));
        lblCompanyName.setText("Company Name");

        txt_savNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_savNoActionPerformed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(0, 51, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Date:");

        jButton1.setText("Set Series");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Print ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(0, 51, 204));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("เลขที่ใบฝาก :");

        jXDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jXDateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblCompanyName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txt_savNo, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                            .addComponent(jXDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 22, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(302, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCompanyName, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_savNo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jXDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(74, 74, 74)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(141, Short.MAX_VALUE)))
        );

        setSize(new java.awt.Dimension(439, 264));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        SetSeries set = new SetSeries();
        set.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
//        JOptionPane.showMessageDialog(null, "bugged?");
        String sav_no = txt_savNo.getText().trim();
//        JOptionPane.showMessageDialog(null, "bugged2?");
        ClassGetReport cgr = new ClassGetReport();
//        JOptionPane.showMessageDialog(null, sav_no);
        String[] data = getAddressTHMAX(sav_no);
//        JOptionPane.showMessageDialog(null, "bugged4?");
        String year = format.format(jXDate.getDate()).substring(0, 4);
        String month = format.format(jXDate.getDate()).substring(5, 7);
        String day = format.format(jXDate.getDate()).substring(8, 10);
        Integer year2 = Integer.parseInt(year) + 543;
        String year3 = year2.toString();
//        JOptionPane.showMessageDialog(null, "test");
//        JOptionPane.showMessageDialog(null, "test");
//        JOptionPane.showMessageDialog(null, "Test");
        cgr.getReportTHMAX(sav_no, data, day, month, year3);

    }//GEN-LAST:event_jButton2ActionPerformed

    private void txt_savNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_savNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_savNoActionPerformed

    private void jXDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jXDateActionPerformed

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
            java.util.logging.Logger.getLogger(ReportTHAIMAX.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReportTHAIMAX.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReportTHAIMAX.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReportTHAIMAX.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReportTHAIMAX().setVisible(true);
            }
        });
    }

    public String[] getAddressTHMAX(String sav_no) {
        String Address = "";
        try {
//            JOptionPane.showMessageDialog(null, sav_no);
            Connection mysql = ConnectMsSql.ConnectionDB();
            String sql = "SELECT COALESCE(a.Warehouse,'-') as Warehouse\n"
                    + "from BRLAS400.dbo.T_ThaiMaxSave_no a\n"
                    + "WHERE a.Sav_no = '" + sav_no.trim() + "'";
//            JOptionPane.showMessageDialog(null,"connection" + mysql);
            Statement sta = mysql.createStatement();
//            JOptionPane.showMessageDialog(null,sta);
            ResultSet rsl = sta.executeQuery(sql);
            
            while (rsl.next()) {
                Address = rsl.getString("Warehouse").trim();
            }
//            JOptionPane.showMessageDialog(null,"testtesttttt");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex);
            Logger.getLogger(ReportTHAIMAX.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] Address_M3 = getAddress_M3(Address);
        return Address_M3;
    }

    public String[] getAddress_M3(String whs) {
        String[] M3_Address = new String[4];
        try {
            Connection DB2 = ConnectDB2.ConnectionDB();
            String sql = "SELECT OACONO ,OAADK1 ,OACONM ,OAADR1 ,OAADR2 ,OAADR3, TRIM(OAADR1) || ' ' || TRIM(OAADR2) || ' ' || TRIM(OAADR3) AS Address_M3   \n"
                    + "FROM M3FDBPRD.CIADDR \n"
                    + "WHERE OACONO = '" + LoginMain.LoginCono + "' \n"
                    + "AND OAADK1 = '" + whs.trim() + "'";
            Statement sta = DB2.createStatement();
            ResultSet rsl = sta.executeQuery(sql);
            while (rsl.next()) {
                M3_Address[0] = rsl.getString("OACONM").trim();
                M3_Address[1] = rsl.getString("OAADK1").trim();
                M3_Address[2] = rsl.getString("Address_M3").trim();

            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportTHAIMAX.class.getName()).log(Level.SEVERE, null, ex);
        }
        return M3_Address;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private org.jdesktop.swingx.JXDatePicker jXDate;
    private java.awt.Label lblCompanyName;
    private javax.swing.JTextField txt_savNo;
    // End of variables declaration//GEN-END:variables
}
