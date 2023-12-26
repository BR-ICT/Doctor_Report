/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DOCTOR_REPORT_R1_V2;

import static DOCTOR_REPORT_R1_V2.LoginMain.LoginCono;
import static DOCTOR_REPORT_R1_V2.LoginMain.LoginDivision;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;

import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ThaiBuddhistDate;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Aticha
 */
public class ReportDoctorLocal extends javax.swing.JFrame {

    public static int year = 0;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH); //คศ
    SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH); //คศ
    DefaultTableModel model1;
    String selectedyear = new String();
    DecimalFormat df = new DecimalFormat("#.00");
    public static Date chosendate;

    /**
     * Creates new form MainJFrame
     */
    public ReportDoctorLocal() {

        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png"));
        this.setTitle("Company  " + LoginMain.LoginCompanyName);
        lblCompanyName.setText(LoginMain.LoginCompanyName);
        model1 = (DefaultTableModel) jTable1.getModel();
        jXStartDate.setFormats(format);
        jXStartDate.setDate(new Date());

        Locale.setDefault(Locale.US);
        Date d = new Date();

        SimpleDateFormat C = new SimpleDateFormat("yyyy");

        String YYYY = C.format(d);
        year = Integer.parseInt(YYYY) + 543;

        btnPrint.setEnabled(false);
        btnPrint.setEnabled(false);

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
        documentfoundnum = new java.awt.Label();
        jLabel1 = new javax.swing.JLabel();
        lblCompanyName = new java.awt.Label();
        btnPrint = new java.awt.Button();
        jXStartDate = new org.jdesktop.swingx.JXDatePicker();
        jbSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        btnView = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jCheckBoxSelectAll = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Anatis_Invioce");
        getContentPane().setLayout(null);

        btnCancel.setBackground(new java.awt.Color(240, 240, 240));
        btnCancel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnCancel.setForeground(new java.awt.Color(0, 0, 0));
        btnCancel.setLabel("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancel);
        btnCancel.setBounds(710, 480, 90, 30);

        documentfoundnum.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        documentfoundnum.setForeground(new java.awt.Color(0, 0, 0));
        documentfoundnum.setText("0");
        getContentPane().add(documentfoundnum);
        documentfoundnum.setBounds(90, 490, 190, 30);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Customer Order (Local)");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(280, 50, 300, 29);

        lblCompanyName.setAlignment(java.awt.Label.CENTER);
        lblCompanyName.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblCompanyName.setForeground(new java.awt.Color(204, 0, 0));
        lblCompanyName.setText("Company Name");
        getContentPane().add(lblCompanyName);
        lblCompanyName.setBounds(290, 10, 270, 30);

        btnPrint.setActionCommand("Print V.2");
        btnPrint.setBackground(new java.awt.Color(204, 255, 255));
        btnPrint.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnPrint.setLabel("Print");
        btnPrint.setName(""); // NOI18N
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });
        getContentPane().add(btnPrint);
        btnPrint.setBounds(600, 480, 100, 30);

        jXStartDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jXStartDateActionPerformed(evt);
            }
        });
        getContentPane().add(jXStartDate);
        jXStartDate.setBounds(90, 100, 180, 29);

        jbSearch.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jbSearch.setForeground(new java.awt.Color(102, 0, 204));
        jbSearch.setText("Search");
        jbSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSearchActionPerformed(evt);
            }
        });
        getContentPane().add(jbSearch);
        jbSearch.setBounds(280, 100, 90, 30);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Deliverer Detail", "CO No.", "Inv No.", "Customer Code", "Customer Name", "Date", "Print"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setColumnSelectionAllowed(true);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(6).setMinWidth(50);
            jTable1.getColumnModel().getColumn(6).setMaxWidth(50);
        }

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 140, 780, 330);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("DATE :");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(20, 100, 60, 22);

        btnView.setBackground(new java.awt.Color(153, 255, 255));
        btnView.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnView.setForeground(new java.awt.Color(0, 0, 0));
        btnView.setText("View");
        btnView.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewActionPerformed(evt);
            }
        });
        getContentPane().add(btnView);
        btnView.setBounds(700, 100, 94, 23);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Found:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(20, 490, 60, 25);

        jCheckBoxSelectAll.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jCheckBoxSelectAll.setText("Select All");
        jCheckBoxSelectAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxSelectAllActionPerformed(evt);
            }
        });
        getContentPane().add(jCheckBoxSelectAll);
        jCheckBoxSelectAll.setBounds(670, 60, 130, 27);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setText("Customer Order (Local)");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(280, 50, 300, 29);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Printed Document ");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(530, 100, 180, 25);

        setSize(new java.awt.Dimension(840, 575));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    Integer amounttick = 0;

    private void getData() {

//        Runnable runnable = new Runnable() {
//
//            public void run() {
//                jButton1.setEnabled(false);
        jbSearch.setEnabled(false);
        try {

            while (model1.getRowCount() > 0) {
                for (int i = 0; i < model1.getRowCount(); ++i) {
                    model1.removeRow(i);
                }
            }

            Connection conn = ConnectDB2.ConnectionDB();
            Statement sta = conn.createStatement();

            String Sql = "SELECT IFNULL(b.DCTX40,' ') AS DCTX40,a.*\n"
                    + "FROM \n"
                    + "(SELECT DISTINCT OAORNO, OACUNO, OKCUNM, UAIVNO, UAIVDT, UACONO\n"
                    + "FROM m3fdbprd.OOHEAD a, m3fdbprd.OCUSMA b, BRLDTA0100.VIIVRN1 c, M3FDBPRD.ODLINE d,\n"
                    + "BRLDTA0100.ITEMNRN1 AS ITEMS\n"
                    + "WHERE b.OKCONO = a.OACONO\n"
                    + "AND b.OKCUNO = a.OACUNO\n"
                    + "AND c.UBORNO = a.OAORNO\n"
                    + "AND ITEMS.RNCONO = D.UBCONO\n"
                    + "AND ITEMS.RNITNO = D.UBITNO\n"
                    + "AND d.UBORNO = c.UBORNO\n"
                    + "AND d.UBITNO NOT IN ('WA004000500', 'PA033001000')\n"
                    + "AND UACONO = '" + LoginCono + "' \n"
                    + "AND UAIVDT = '" + format2.format(jXStartDate.getDate()) + "'\n"
                    + ") AS a\n"
                    + "LEFT JOIN \n"
                    + "(SELECT A.*,C.DCTX40\n"
                    + "FROM(\n"
                    + "select card_upc ,card_carno,card_meth,card_date,a.caro_orno,card_cord,card_cspe,OACONO,OADIVI,OAORNO\n"
                    + "FROM brldta0100.sal_cardel,brldta0100.sal_carord a,M3FDBPRD.OOHEAD\n"
                    + "Where card_date = a.CARO_DATE\n"
                    + "--where card_date IN  (20210704,20210711,20210718,20210725,20210801,20210808,20210815,20210822,20210829,20210905,20210912\n"
                    + "--,20210919,20210926,20211003,20211010)\n"
                    + "--and card_cord <> '0'\n"
                    + "--and card_carno not in (--'TK000030','TK000031','TK000032','TK000033')\n"
                    + "--and card_tearm NOT IN  ('N','C')\n"
                    + "--and card_date between 20210416 AND 20230616\n"
                    + "AND card_meth = a.CARO_METH\n"
                    + "AND card_carno = a.caro_carno\n"
                    + "AND a.caro_orno = oaorno\n"
                    + "--AND oacuno ='TH01010088'\n"
                    + "--AND card_meth <='102'\n"
                    + "AND oacono =10\n"
                    + "--AND oacuno IN ('TH75050004')\n"
                    + "--group by card_upc,card_carno,card_meth, DCTX40\n"
                    + ") A\n"
                    + "LEFT  JOIN M3FDBPRD.dcarri AS C ON C.DCTRCA = A.CARD_CARNO AND C.dccono =10\n"
                    + "ORDER BY CARD_DATE) AS b\n"
                    + "ON b.OAORNO = a.OAORNO AND b.OACONO = a.UACONO";

//            String Sql = "SELECT OAORNO, OACUNO, OKCUNM, UAIVNO, UAIVDT\n"
//                    + "FROM m3fdbprd.OOHEAD a, m3fdbprd.OCUSMA b, BRLDTA0100.VIIVRN1 c\n"
//                    + "WHERE b.OKCONO = a.OACONO \n"
//                    + "AND b.OKCUNO = a.OACUNO\n"
//                    + "AND c.UBORNO = a.OAORNO \n"
//                 + "AND UACONO = '" + LoginCono + "' \n"
//                    + "AND UAIVDT = '" + format2.format(jXStartDate.getDate()) + "'";
//            String Sql = "SELECT UACONO, UADIVI, UBORNO, UAIVNO, UAIVDT  \n"
//                    + "FROM BRLDTA0100.VIIVRN1 \n"
//                    + "WHERE UACONO = '" + LoginCono + "' \n"
//                    + "AND UAIVDT = '" + format2.format(jXStartDate.getDate()) + "' \n"
//                    + "ORDER BY UBORNO";
            ResultSet rs = sta.executeQuery(Sql);

            while (rs.next()) {
                String getDate = rs.getString("UAIVDT").trim();
                Date cvDate = new SimpleDateFormat("yyyyMMdd").parse(getDate);

                model1.insertRow(model1.getRowCount(), new Object[]{
                    rs.getString("DCTX40").trim(),
                    rs.getString("OAORNO").trim(),
                    rs.getString("UAIVNO").trim(),
                    rs.getString("OACUNO").trim(),
                    rs.getString("OKCUNM").trim(),
                    format.format(cvDate),});

            }
            documentfoundnum.setText(Integer.toString(model1.getRowCount()) + " Document(s)");
            rs.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
//                jButton1.setEnabled(true);
        jbSearch.setEnabled(true);
//            }

//        };
//        new Thread(runnable)
//                .start();
    }

    private String getMaxRNNumber() {

        try {

            Connection conn = ConnectDB2.ConnectionDB();
            Statement sta = conn.createStatement();

            String Sql = "SELECT UACONO, UADIVI, UBORNO, UAIVNO, UAIVDT  \n"
                    + "FROM BRLDTA0100.VIIVRN1 \n"
                    + "WHERE UACONO = '" + LoginCono + "' \n"
                    + "AND UAIVDT = '" + format2.format(jXStartDate.getDate()) + "' \n"
                    + "ORDER BY UBORNO";

            ResultSet rs = sta.executeQuery(Sql);

            while (rs.next()) {
                String getDate = rs.getString("UAIVDT").trim();
                Date cvDate = new SimpleDateFormat("yyyyMMdd").parse(getDate);

                model1.insertRow(model1.getRowCount(), new Object[]{
                    rs.getString("UBORNO").trim(),
                    rs.getString("UAIVNO").trim(),
                    format.format(cvDate),});

            }
            rs.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return null;

    }

    private Boolean getDuplicateCO(String conumber) {

        try {

            Connection conn = ConnectDB2.ConnectionDB();
            Statement sta = conn.createStatement();

            String Sql = "SELECT COUNT(*) AS COUNT  \n"
                    + "FROM BRLDTA0100.INVCNRN1 \n"
                    + "WHERE INCONO = '" + LoginCono + "' \n"
                    + "AND INORNO = '" + conumber + "'";

            ResultSet rs = sta.executeQuery(Sql);

            while (rs.next()) {
                int count = rs.getInt("COUNT");
                if (count > 0) {
                    return true;
                }

            }
            rs.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return false;

    }

    private String insertRNNumber(String cono, String divi, String conumber, String invnumber, String year, String date, String type) {

        try {

            Connection conn = ConnectDB2.ConnectionDB();
            Statement sta = conn.createStatement();

            String Sql = "INSERT INTO BRLDTA0100.INVCNRN1 \n"
                    + "(INCONO, INDIVI, INORNO, INIVNO, INDATE, INRNNO,INTYPE) \n"
                    + "VALUES('" + cono + "' \n"
                    + ", '" + divi + "' \n"
                    + ", '" + conumber + "' \n"
                    + ", '" + invnumber + "' \n"
                    + ", '" + date + "' \n"
                    + ", (SELECT CASE WHEN CAST(MAX(INRNNO) AS DECIMAL(10,0)) > 0 THEN CAST(MAX(INRNNO) AS DECIMAL(10,0)) + 1 ELSE CAST(('" + year + "' || '000001') AS DECIMAL(10,0)) END AS INRNNO \n"
                    + "FROM BRLDTA0100.INVCNRN1 \n"
                    + "WHERE INCONO = '" + cono + "'\n"
                    + "AND SUBSTRING(INRNNO,0,3) = '" + year + "'),'" + type + "' )";
            System.out.println("insertRNNumber\n" + Sql);
            System.out.println(date);
            sta.execute(Sql);

            Connection conn2 = ConnectMsSql.ConnectionDB();
            Statement sta2 = conn2.createStatement();
            String sqlserver = "INSERT INTO BRLAS400.dbo.INVCNRN1 \n"
                    + "(INCONO, INDIVI, INORNO, INIVNO, INDATE, INRNNO,INTYPE) \n"
                    + "SELECT \n"
                    + "'" + cono + "' \n"
                    + ", '" + divi + "' \n"
                    + ", '" + conumber + "' \n"
                    + ", '" + invnumber + "' \n"
                    + ", '" + date + "' \n"
                    + ", CASE WHEN CAST(MAX(INRNNO) AS DECIMAL(10,0)) > 0 THEN CAST(MAX(INRNNO) AS DECIMAL(10,0)) + 1 ELSE CAST(('" + year + "' + '000001') AS DECIMAL(10,0)) END AS INRNNO \n"
                    + ",'" + type + "'\n"
                    + "FROM BRLAS400.dbo.INVCNRN1 \n"
                    + "WHERE INCONO = '" + cono + "'\n"
                    + "AND SUBSTRING(INRNNO,0,3) = '" + year + "'";
            sta2.execute(sqlserver);

            System.out.println("insertRNNumberTHMAX\n" + Sql);
            System.out.println(date);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return null;

    }

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        btnPrint.enable(false);
        Integer printtimes = 0;
        boolean lastrow = false;
        ClassGetReport cgr = new ClassGetReport();
        Amounttick();
//        String Customer = cgr.getcustomer(txtInvoice.getText().trim());
//        if (Customer.equals("TH08130002")) {
//            String ss = cgr.GetDoctorRunningNo(txtInvoice.getText().trim());
//            cgr.GetReportDoctorBigc(txtInvoice.getText().trim());
//        } else {
//            String ss = cgr.GetDoctorRunningNo(txtInvoice.getText().trim());
//            cgr.GetReportDoctorV2(txtInvoice.getText().trim());
//        }
        try {

//            // Creating and initializing TemporalAccessor object
////            LocalDateTime localdate = LocalDateTime.parse("2018-12-30");
//            String getDate = format.format(jXStartDate.getDate());
//            LocalDate localdate = LocalDate.parse(getDate);
//
//            // Creating and initializing ThaiBuddhistDate Object
//            ThaiBuddhistDate thDate = ThaiBuddhistDate.from(localdate);
//            // Display the result
//            System.out.println("ThaiBuddhistDate: " + thDate);
//            use to check which row is checked
            for (int i = 0; i <= model1.getRowCount() - 1; i++) {
                Boolean print = (Boolean) model1.getValueAt(i, 6);
                if (print == null) {
                    print = false;
                }

                if (print) {
                    printtimes++;
                    String conumber = (String) model1.getValueAt(i, 1).toString().trim();
                    String invnumber = (String) model1.getValueAt(i, 2).toString().trim();
                    String date = (String) model1.getValueAt(i, 5).toString().trim();

                    LocalDate localdate = LocalDate.parse(date);
                    ThaiBuddhistDate thDate = ThaiBuddhistDate.from(localdate);

                    String year = thDate.toString().substring(16, 20);
                    String subYear = thDate.toString().substring(18, 20);

                    System.out.println("conumber: " + conumber + " invnumber: " + invnumber + " date: " + date + " year: " + subYear);

                    insertRNNumber(LoginCono, LoginDivision, conumber, invnumber, subYear, format2.format(jXStartDate.getDate()), "Local");
                    if (printtimes == amounttick) {
                        lastrow = true;
                        System.out.println("statement true");
                    }

                    cgr.GetReportDoctorLocal(conumber, year, lastrow);
                    //selectedyear คือปีที่ จำ กับ selectednum คือเลข invoice ที่เลือก
                    //System.out.print("allyear is " + selectedyear);
                    //System.out.print("allnum is " + selectednum);

                }
            }
            getData();

        } catch (DateTimeException e) {
            System.out.println("Passed parameter can" + " not form a date");
            System.out.println("Exception thrown: " + e);
        }

        btnPrint.enable(true);
    }//GEN-LAST:event_btnPrintActionPerformed

    private void MouseListener() {

        jTable1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table = (JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    System.out.println(".mousePressed()");
//                    if ("No".equals(DoubleClick)) {
//                        String[] getHouse = String.valueOf(mTransection.getValueAt(jTableTransection.getSelectedRow(), 0)).split(" : ");
//                        vTRAN_HOUSE = getHouse[0];
//                        vTRAN_DATE = String.valueOf(mTransection.getValueAt(jTableTransection.getSelectedRow(), 1));
//                        String[] getItem = String.valueOf(mTransection.getValueAt(jTableTransection.getSelectedRow(), 2)).split(" : ");
//                        vTRAN_ITEM = getItem[0];
//                        vTRAN_QTY = String.valueOf(mTransection.getValueAt(jTableTransection.getSelectedRow(), 3));
//                        String[] getSource = String.valueOf(mTransection.getValueAt(jTableTransection.getSelectedRow(), 5)).split(" : ");
//                        vTRAN_PENE = getSource[0];
//                        String[] getType = String.valueOf(mTransection.getValueAt(jTableTransection.getSelectedRow(), 6)).split(" : ");
//                        vTRAN_TYPE = getType[0];
//                        vRow = jTableTransection.getSelectedRow();
//                        new BRIssueStock02().setVisible(true);
//                        DoubleClick = "Yes";
//                    }
                }
            }
        });

    }

    private void CheckRemark() {

        btnPrint.setEnabled(false);
        for (int i = 0; i <= model1.getRowCount() - 1; i++) {

            Boolean print = (Boolean) model1.getValueAt(i, 6);

            if (print == null) {
                print = false;
            }

            if (print) {
                btnPrint.setEnabled(true);
                break;
            } else {
                btnPrint.setEnabled(false);
            }
        }

    }

    private void Amounttick() {
        amounttick = 0;

        btnPrint.setEnabled(false);

        for (int o = 0; o <= model1.getRowCount() - 1; o++) {

            Boolean print = (Boolean) model1.getValueAt(o, 6);

            if (print == null) {
                print = false;
            }

            if (print) {
                btnPrint.setEnabled(true);
                amounttick++;
            } else {
                btnPrint.setEnabled(false);
            }
            System.out.println("amount tick" + amounttick);
        }

    }

    private void jXStartDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXStartDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jXStartDateActionPerformed

    private void jbSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSearchActionPerformed
        // TODO add your handling code here:
        getData();
    }//GEN-LAST:event_jbSearchActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
//        MouseListener();
        CheckRemark();
    }//GEN-LAST:event_jTable1MouseClicked

    private void btnViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewActionPerformed
        // TODO add your handling code here:
        //เปิดดูตารางเอกสารที่ปริ้นไปแล้ว
        chosendate = jXStartDate.getDate();
        new ReportPrinted().show(true);
    }//GEN-LAST:event_btnViewActionPerformed

    private void jCheckBoxSelectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxSelectAllActionPerformed
        // TODO add your handling code here:

        if (jCheckBoxSelectAll.isSelected()) {
            for (int i = 0; i <= model1.getRowCount() - 1; i++) {
                model1.setValueAt(true, i, 5);
            }
        } else {
            for (int i = 0; i <= model1.getRowCount() - 1; i++) {
                model1.setValueAt(false, i, 5);
            }
        }
        CheckRemark();
    }//GEN-LAST:event_jCheckBoxSelectAllActionPerformed

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
            java.util.logging.Logger.getLogger(ReportDoctorLocal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReportDoctorLocal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReportDoctorLocal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReportDoctorLocal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReportDoctorLocal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button btnCancel;
    private java.awt.Button btnPrint;
    private javax.swing.JButton btnView;
    private java.awt.Label documentfoundnum;
    private javax.swing.JCheckBox jCheckBoxSelectAll;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private org.jdesktop.swingx.JXDatePicker jXStartDate;
    private javax.swing.JButton jbSearch;
    private java.awt.Label lblCompanyName;
    // End of variables declaration//GEN-END:variables
}