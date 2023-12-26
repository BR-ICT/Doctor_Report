/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DOCTOR_REPORT_R1_V2;

import java.awt.Toolkit;
import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 *
 * @author aticha
 */
public class LoginMain extends javax.swing.JFrame {

    public static String LoginUsername;
    public static String LoginPassword;
    public static String LoginCono;
    public static String LoginDivision;
    public static String LoginUrlConnection;
    public static String LoginCompanyName;
    public static String ProgramName;

    /**
     * Creates new form LoginMain
     */
    public LoginMain() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png"));
        this.setTitle("Login For BR GROUP");
        lblCompanyName.setText("BR GROUP");
        ProgramName = "";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label1 = new java.awt.Label();
        label2 = new java.awt.Label();
        txtUsername = new java.awt.TextField();
        btncancel = new java.awt.Button();
        btnlogin = new java.awt.Button();
        jPasswordField1 = new javax.swing.JPasswordField();
        lblCompanyName = new java.awt.Label();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        CmbCompany = new javax.swing.JComboBox<>();
        cbm_programe = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        label1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        label1.setForeground(new java.awt.Color(51, 51, 51));
        label1.setText("User Name");
        label1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label1MouseClicked(evt);
            }
        });
        getContentPane().add(label1);
        label1.setBounds(50, 60, 80, 21);

        label2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        label2.setForeground(new java.awt.Color(51, 51, 51));
        label2.setText("Password");
        getContentPane().add(label2);
        label2.setBounds(60, 110, 72, 21);

        txtUsername.setText("m3srvadm");
        getContentPane().add(txtUsername);
        txtUsername.setBounds(140, 60, 196, 30);

        btncancel.setLabel("Cancel");
        btncancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelActionPerformed(evt);
            }
        });
        getContentPane().add(btncancel);
        btncancel.setBounds(250, 260, 90, 30);

        btnlogin.setLabel("Login");
        btnlogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnloginActionPerformed(evt);
            }
        });
        getContentPane().add(btnlogin);
        btnlogin.setBounds(140, 260, 90, 30);

        jPasswordField1.setText("m3srvadm");
        getContentPane().add(jPasswordField1);
        jPasswordField1.setBounds(140, 110, 196, 30);

        lblCompanyName.setAlignment(java.awt.Label.CENTER);
        lblCompanyName.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblCompanyName.setText("Company Name");
        getContentPane().add(lblCompanyName);
        lblCompanyName.setBounds(0, 10, 479, 30);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DOCTOR_REPORT_R1_V2/icon.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(360, 50, 100, 110);

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Program");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(70, 210, 60, 30);

        CmbCompany.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BangkokRanch(BR)", "FoodCity(FCT)", "Nissin(NSD)", "ANATIS(TH)", "ANATIS(HK)", "ANATIS(SG)" }));
        CmbCompany.setEnabled(false);
        CmbCompany.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CmbCompanyActionPerformed(evt);
            }
        });
        getContentPane().add(CmbCompany);
        CmbCompany.setBounds(140, 160, 200, 30);

        cbm_programe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Normal", "Local", "THAIMAX", "THAIMAXnew", "Farm", "Doctor Rpt Summary" }));
        getContentPane().add(cbm_programe);
        cbm_programe.setBounds(140, 210, 200, 30);

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Company");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(70, 160, 70, 30);

        setSize(new java.awt.Dimension(504, 372));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btncancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelActionPerformed

        //        Classgetdata cgd =new Classgetdata();
        //       String chhh=  cgd.GetSqlDataReportCashAdvance4("");
        //       System.out.println(chhh);
        //                  ClassgetReport cgr=new ClassgetReport();
        //                cgr.getReportAdvReq1("6000001");
    }//GEN-LAST:event_btncancelActionPerformed

    private void btnloginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnloginActionPerformed
        // TODO add your handling code here:
        Connection conn;
        try {
            LoginUsername = txtUsername.getText().trim();
            LoginPassword = jPasswordField1.getText().trim();
            if (CmbCompany.getSelectedItem() == "BangkokRanch(BR)") {
                this.setTitle("Login For " + lblCompanyName.getText());
                LoginCono = "10";
                LoginDivision = "101";
                LoginCompanyName = "BangkokRanch(BR Company)";
                LoginUrlConnection = "jdbc:jtopenlite://192.200.9.190";
            } else if (CmbCompany.getSelectedItem() == "FoodCity(FCT)") {
                this.setTitle("Login For " + lblCompanyName.getText());
                LoginCono = "400";
                LoginDivision = "400";
                LoginCompanyName = "FoodCity(FCT)";
                LoginUrlConnection = "jdbc:jtopenlite://192.200.9.190";
            } else if (CmbCompany.getSelectedItem() == "Nissin(NSD)") {
                this.setTitle("Login For " + lblCompanyName.getText());
                LoginCono = "500";
                LoginDivision = "500";
                LoginCompanyName = "Nissin(NSD)";
                LoginUrlConnection = "jdbc:jtopenlite://192.200.9.190";
            } else if (CmbCompany.getSelectedItem() == "ANATIS(TH)") {
                this.setTitle("Login For " + lblCompanyName.getText());
                LoginCono = "100";
                LoginDivision = "110";
                LoginCompanyName = "ANATIS(TH)";
                LoginUrlConnection = "jdbc:jtopenlite://192.200.9.190";
            } else if (CmbCompany.getSelectedItem() == "ANATIS(HK)") {
                this.setTitle("Login For " + lblCompanyName.getText());
                LoginCono = "100";
                LoginDivision = "120";
                LoginCompanyName = "ANATIS(HK)";
                LoginUrlConnection = "jdbc:jtopenlite://192.200.9.190";
            } else if (CmbCompany.getSelectedItem() == "ANATIS(SG)") {
                this.setTitle("Login For " + lblCompanyName.getText());
                LoginCono = "100";
                LoginDivision = "130";
                LoginCompanyName = "ANATIS(SG)";
                LoginUrlConnection = "jdbc:jtopenlite://192.200.9.190";
            }

            conn = ConnectDB2.ConnectionDB();
            if (conn.isClosed() != true) {
                this.setVisible(false);
                //  String REPORTS="PO";
//                String ReportN = "Normal";
//                 String ReportN="Summary";
//                String ReportN = "FarmFish";
                String ReportN = cbm_programe.getSelectedItem().toString().trim();

                if (ReportN.equals("Normal")) {
                    ReportDoctor rpo = new ReportDoctor();
                    ProgramName = "Normal";
                    rpo.setVisible(true);
                } else if (ReportN.equalsIgnoreCase("THAIMAX")) {
                    ReportTHAIMAX rps = new ReportTHAIMAX();
                    ProgramName = "THAIMAX";
                    rps.setVisible(true);
                } else if (ReportN.equalsIgnoreCase("THAIMAXnew")) {
                    ReportTHAIMAXnew rpsnew = new ReportTHAIMAXnew();
                    ProgramName = "THAIMAXnew";
                    rpsnew.setVisible(true);
                } else if (ReportN.equalsIgnoreCase("Farm")) {
                    ReportFarmPra rpf = new ReportFarmPra();
                    ProgramName = "Farm";
                    rpf.setVisible(true);
                } else if (ReportN.equalsIgnoreCase("Local")) {
                    ReportDoctorLocal rpf = new ReportDoctorLocal();
                    ProgramName = "Local";
                    rpf.setVisible(true);
                } else {
                    ReportDoctorSummary rps = new ReportDoctorSummary();
                    rps.setVisible(true);
                }

            }

            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.toString());

        }
    }//GEN-LAST:event_btnloginActionPerformed

    private void CmbCompanyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CmbCompanyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CmbCompanyActionPerformed

    private void label1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label1MouseClicked
        txtUsername.setText("JILASA_SAM");
        jPasswordField1.setText("fewdti05");
    }//GEN-LAST:event_label1MouseClicked

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
            java.util.logging.Logger.getLogger(LoginMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CmbCompany;
    private java.awt.Button btncancel;
    private java.awt.Button btnlogin;
    private javax.swing.JComboBox<String> cbm_programe;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField jPasswordField1;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label lblCompanyName;
    private java.awt.TextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
