/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DOCTOR_REPORT_R1_V2;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Aticha
 */
public class ReportDoctorSummary extends javax.swing.JFrame {

    /**
     * Creates new form MainJFrame
     */
    public ReportDoctorSummary() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png"));
        this.setTitle("Company  " + LoginMain.LoginCompanyName);
        lblCompanyName.setText(LoginMain.LoginCompanyName);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCancel = new java.awt.Button();
        jLabel1 = new javax.swing.JLabel();
        btnPrint = new java.awt.Button();
        lblCompanyName = new java.awt.Label();
        jLabel2 = new javax.swing.JLabel();
        cmbMonth = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cmbYear = new javax.swing.JComboBox<>();
        Excel = new java.awt.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Anatis_Invioce");
        getContentPane().setLayout(null);

        btnCancel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnCancel.setLabel("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancel);
        btnCancel.setBounds(350, 260, 70, 30);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Month");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(90, 200, 50, 22);

        btnPrint.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnPrint.setLabel("Print");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });
        getContentPane().add(btnPrint);
        btnPrint.setBounds(160, 260, 80, 30);

        lblCompanyName.setAlignment(java.awt.Label.CENTER);
        lblCompanyName.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblCompanyName.setForeground(new java.awt.Color(204, 0, 0));
        lblCompanyName.setText("Company Name");
        getContentPane().add(lblCompanyName);
        lblCompanyName.setBounds(0, 20, 530, 30);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DOCTOR_REPORT_R1_V2/icon.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(380, 130, 100, 110);

        cmbMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        getContentPane().add(cmbMonth);
        cmbMonth.setBounds(150, 200, 200, 30);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setText("Report Month Summary ร.น ๑");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(50, 60, 500, 29);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Year");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(90, 140, 50, 22);

        cmbYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2018", "2019", "2020", "2021", "2022", "2023", "2024" }));
        getContentPane().add(cmbYear);
        cmbYear.setBounds(150, 140, 200, 30);

        Excel.setBackground(new java.awt.Color(0, 153, 0));
        Excel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        Excel.setForeground(new java.awt.Color(255, 255, 255));
        Excel.setLabel("Excel");
        Excel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExcelActionPerformed(evt);
            }
        });
        getContentPane().add(Excel);
        Excel.setBounds(70, 260, 70, 30);

        setSize(new java.awt.Dimension(565, 381));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnCancelActionPerformed


    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        // TODO add your handling code here:
        btnPrint.enable(false);
        ClassGetReport cgr = new ClassGetReport();
        String Years = cmbYear.getSelectedItem().toString();
        String Months = cmbMonth.getSelectedItem().toString();
        cgr.GetReportDoctorSummary(Years, Months);
        btnPrint.enable(true);
    }//GEN-LAST:event_btnPrintActionPerformed

    private void ExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExcelActionPerformed
        Excel.enable(false);
        ClassGetReport cgr = new ClassGetReport();
        String Years = cmbYear.getSelectedItem().toString();
        String Months = cmbMonth.getSelectedItem().toString();
        cgr.GetReportDoctorSummaryExcel(Years, Months);
        Excel.enable(true);
    }//GEN-LAST:event_ExcelActionPerformed

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
            java.util.logging.Logger.getLogger(ReportDoctorSummary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReportDoctorSummary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReportDoctorSummary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReportDoctorSummary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReportDoctorSummary().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button Excel;
    private java.awt.Button btnCancel;
    private java.awt.Button btnPrint;
    private javax.swing.JComboBox<String> cmbMonth;
    private javax.swing.JComboBox<String> cmbYear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private java.awt.Label lblCompanyName;
    // End of variables declaration//GEN-END:variables
}
